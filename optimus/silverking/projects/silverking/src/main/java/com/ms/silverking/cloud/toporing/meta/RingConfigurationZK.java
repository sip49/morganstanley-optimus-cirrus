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
package com.ms.silverking.cloud.toporing.meta;

import java.io.File;
import java.io.IOException;

import com.ms.silverking.cloud.management.MetaToolModuleBase;
import com.ms.silverking.cloud.management.MetaToolOptions;
import com.ms.silverking.cloud.zookeeper.SilverKingZooKeeperClient.KeeperException;
import com.ms.silverking.io.StreamParser;
import org.apache.zookeeper.CreateMode;

public class RingConfigurationZK extends MetaToolModuleBase<RingConfiguration, MetaPaths> {
  public RingConfigurationZK(MetaClient mc) throws KeeperException {
    super(mc, mc.getMetaPaths().getConfigPath());
  }

  @Override
  public RingConfiguration readFromFile(File file, long version) throws IOException {
    return RingConfiguration.parse(StreamParser.parseLine(file), version);
  }

  @Override
  public RingConfiguration readFromZK(long version, MetaToolOptions options) throws KeeperException {
    return RingConfiguration.parse(zk.getString(getVBase(version)), version);
  }

  @Override
  public void writeToFile(File file, RingConfiguration instance) throws IOException {
    throw new RuntimeException("writeToFile not implemented for WeightSpecifications");
  }

  @Override
  public String writeToZK(RingConfiguration ringConfig, MetaToolOptions options) throws IOException, KeeperException {
    String path;

    path = zk.createString(base + "/", ringConfig.toString(), CreateMode.PERSISTENT_SEQUENTIAL);
    return path;
  }
}
