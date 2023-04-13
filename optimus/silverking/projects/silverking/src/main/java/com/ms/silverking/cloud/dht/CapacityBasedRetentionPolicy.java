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
package com.ms.silverking.cloud.dht;

import com.ms.silverking.text.FieldsRequirement;
import com.ms.silverking.text.ObjectDefParser2;

abstract class CapacityBasedRetentionPolicy implements ValueRetentionPolicy {
  static {
    ObjectDefParser2.addParserWithExclusions(CapacityBasedRetentionPolicy.class, null,
        FieldsRequirement.ALLOW_INCOMPLETE, null);
  }

  protected final long capacityBytes;

  public CapacityBasedRetentionPolicy(long capacityBytes) {
    this.capacityBytes = capacityBytes;
  }

  public long getCapacityBytes() {
    return capacityBytes;
  }

  @Override
  public int hashCode() {
    return Long.hashCode(capacityBytes);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (this.getClass() != o.getClass()) {
      return false;
    }

    CapacityBasedRetentionPolicy other;

    other = (CapacityBasedRetentionPolicy) o;
    return capacityBytes == other.capacityBytes;
  }
}
