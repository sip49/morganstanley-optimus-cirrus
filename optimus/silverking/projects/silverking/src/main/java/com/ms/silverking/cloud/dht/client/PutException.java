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
package com.ms.silverking.cloud.dht.client;

import java.util.Map;

import com.ms.silverking.cloud.dht.client.gen.NonVirtual;

/**
 * Thrown when a put fails. See KeyedOperationException for details on partial results.
 *
 * @see KeyedOperationException
 */
@NonVirtual
public abstract class PutException extends KeyedOperationException {
  protected PutException(
      Map<Object, OperationState> operationState, Map<Object, FailureCause> failureCause) {
    super(operationState, failureCause);
  }

  protected PutException(
      String message,
      Map<Object, OperationState> operationState,
      Map<Object, FailureCause> failureCause) {
    super(message, operationState, failureCause);
  }

  protected PutException(
      String message,
      Throwable cause,
      Map<Object, OperationState> operationState,
      Map<Object, FailureCause> failureCause) {
    super(message, cause, operationState, failureCause);
  }

  protected PutException(
      Throwable cause,
      Map<Object, OperationState> operationState,
      Map<Object, FailureCause> failureCause) {
    super(cause, operationState, failureCause);
  }
}
