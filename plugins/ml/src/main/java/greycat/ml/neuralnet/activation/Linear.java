/**
 * Copyright 2017-2019 The GreyCat Authors.  All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package greycat.ml.neuralnet.activation;

class Linear implements Activation {

    private static Linear static_unit = null;

    public static Linear instance() {
        if (static_unit == null) {
            static_unit = new Linear();
        }
        return static_unit;
    }

    @Override
    public double forward(double x) {
        return x;
    }

    @Override
    public double backward(double x, double fct) {
        return 1;
    }

    @Override
    public int getId() {
        return Activations.LINEAR;
    }

    @Override
    public double getParam() {
        return 0;
    }
}
