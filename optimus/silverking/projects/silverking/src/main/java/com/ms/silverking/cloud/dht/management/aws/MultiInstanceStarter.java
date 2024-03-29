/*
 * Morgan Stanley makes this available to you under the Apache License, Version 2.0 (the "License").
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0.
 * See the NOTICE file distributed with this work for additional information regarding copyright ownership.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ms.silverking.cloud.dht.management.aws;

import static com.ms.silverking.cloud.dht.management.aws.Util.findStoppedInstancesWithKeyPair;
import static com.ms.silverking.cloud.dht.management.aws.Util.getIds;
import static com.ms.silverking.cloud.dht.management.aws.Util.getInstanceIds;
import static com.ms.silverking.cloud.dht.management.aws.Util.getIps;
import static com.ms.silverking.cloud.dht.management.aws.Util.getMyIp;
import static com.ms.silverking.cloud.dht.management.aws.Util.getUniqueKeyPairName;
import static com.ms.silverking.cloud.dht.management.aws.Util.print;
import static com.ms.silverking.cloud.dht.management.aws.Util.printDone;
import static com.ms.silverking.cloud.dht.management.aws.Util.printNoDot;
import static com.ms.silverking.cloud.dht.management.aws.Util.waitForInstancesToBeReachable;
import static com.ms.silverking.cloud.dht.management.aws.Util.waitForInstancesToBeRunning;

import java.util.List;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceStateChange;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StartInstancesResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultiInstanceStarter {

  private static Logger log = LoggerFactory.getLogger(MultiInstanceStarter.class);

  private final AmazonEC2 ec2;
  private final String keyPairName;
  private List<Instance> instances;

  public MultiInstanceStarter(AmazonEC2 ec2, String launchHostIp) {
    this.ec2 = ec2;
    this.keyPairName = getUniqueKeyPairName(launchHostIp);

    instances = null;
  }

  public void run() {
    instances = findStoppedInstancesWithKeyPair(ec2, keyPairName);
    startInstances();
    waitForInstancesToBeRunning(ec2, instances);
    waitForInstancesToBeReachable(ec2, instances);
  }

  private void startInstances() {
    printNoDot("Starting Instances");

    List<String> ips = getIps(instances);
    for (String ip : ips) log.info("{}", ip);
    StartInstancesRequest startInstancesRequest = new StartInstancesRequest();
    startInstancesRequest.withInstanceIds(getInstanceIds(instances));

    StartInstancesResult result = ec2.startInstances(startInstancesRequest);
    List<InstanceStateChange> startingInstances = result.getStartingInstances();

    print("");
    printDone(getIds(startingInstances));
  }

  public static void main(String[] args) {
    String launchHostIp = getMyIp();
    log.info(
        "Attempting to start all instances with keypair: {}", getUniqueKeyPairName(launchHostIp));
    MultiInstanceStarter starter =
        new MultiInstanceStarter(AmazonEC2ClientBuilder.defaultClient(), launchHostIp);
    starter.run();
  }
}
