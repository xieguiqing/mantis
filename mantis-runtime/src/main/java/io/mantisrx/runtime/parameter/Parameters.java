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

package io.mantisrx.runtime.parameter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Parameters {

    private Set<String> requiredParameters = new HashSet<>();
    private Set<String> parameterDefinitions = new HashSet<>();
    private Map<String, Object> state = new HashMap<>();

    public Parameters() {}

    public Parameters(Map<String, Object> state,
                      Set<String> requiredParameters,
                      Set<String> parameterDefinitions) {
        this.state = state;
        this.requiredParameters = requiredParameters;
        this.parameterDefinitions = parameterDefinitions;
    }

    public Object get(String key) {
        // check if required parameter, make sure
        // has a value
        if (requiredParameters.contains(key) &&
                !state.containsKey(key)) {
            throw new ParameterException("Attempting to reference a required parameter witn no value: " + key + ", check parameter definitions for job.");
        }
        // check if parameter definition exists
        if (!parameterDefinitions.contains(key)) {
            throw new ParameterException("Attempting to reference parameter: " + key + ", with no definition, check parameter definitions in job.");
        }
        return state.get(key);
    }

    public Object get(String key, Object defaultValue) {
        Object value = defaultValue;
        try {
            value = get(key);
            if (value == null) {
                value = defaultValue;
            }
        } catch (ParameterException ex) {
            value = defaultValue;
        }
        return value;
    }
}
