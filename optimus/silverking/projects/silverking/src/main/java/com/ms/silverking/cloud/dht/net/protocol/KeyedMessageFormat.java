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

import com.ms.silverking.cloud.dht.common.DHTKey;
import com.ms.silverking.numeric.NumConversion;

public class KeyedMessageFormat extends MessageFormat {
  public static final int baseBytesPerKeyEntry = DHTKey.BYTES_PER_KEY;
  public static final int size = baseBytesPerKeyEntry;

  public static final int keyMslOffset = 0;
  public static final int keyLslOffset = NumConversion.BYTES_PER_LONG;
}
