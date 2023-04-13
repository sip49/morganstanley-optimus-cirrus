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
package com.ms.silverking.net.security;

import java.util.Optional;

public interface Authenticable {
  /**
   * Add authentication result into current Authenticable object
   *
   * @param result authentication result generated by Authenticator
   */
  public void setAuthenticationResult(AuthenticationResult result);

  /**
   * Get authentication result for current Authenticable object
   *
   * @return a <b>present authentication result</b> if current object has been authenticated; <b>Optional.empty</b>
   * if haven't been authenticated
   */
  Optional<AuthenticationResult> getAuthenticationResult();

  public default boolean hasBeenAuthenticated() {
    return getAuthenticationResult().isPresent();
  }
}
