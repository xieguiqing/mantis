/*
 * Copyright 2019 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.mantisrx.runtime.parameter.type;

import io.mantisrx.runtime.parameter.ParameterDecoder;
import io.mantisrx.runtime.parameter.ParameterDefinition;


public class StringParameter extends ParameterDefinition.Builder<String> {

    @Override
    public ParameterDecoder<String> decoder() {
        return new ParameterDecoder<String>() {
            @Override
            public String decode(String value) {
                return value;
            }
        };
    }

    @Override
    public String getTypeDescription() {
        return String.class.getSimpleName();
    }

    @Override
    public Class<String> classType() {
        return String.class;
    }

}
