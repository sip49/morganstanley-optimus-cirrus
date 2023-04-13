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
package com.ms.silverking.cloud.dht.net.protocol;

import java.nio.ByteBuffer;

import com.ms.silverking.numeric.NumConversion;

public class PutUpdateMessageFormat extends KeyedMessageFormat {
  public static final int versionSize = NumConversion.BYTES_PER_LONG;
  public static final int storageStateSize = 1;

  public static final int versionOffset = 0;
  public static final int storageStateOffset = versionSize;

  public static final int optionBytesSize = versionSize + storageStateSize;

  public static byte getStorageState(ByteBuffer buf) {
    return buf.get(storageStateOffset);
  }
}
