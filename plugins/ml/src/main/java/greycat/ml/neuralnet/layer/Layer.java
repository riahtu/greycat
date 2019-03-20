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
package greycat.ml.neuralnet.layer;

import greycat.ml.neuralnet.activation.Activation;
import greycat.ml.neuralnet.process.ExMatrix;
import greycat.ml.neuralnet.process.ProcessGraph;
import greycat.struct.matrix.RandomInterface;

public interface Layer {

    ExMatrix forward(ExMatrix input, ProcessGraph g);

    ExMatrix[] getLayerParameters();

    Layer create(int inputs, int outputs, int activationUnit, double[] activationParams);

    Layer init(int weightInitType, RandomInterface random, double std);

    void resetState();

    int inputDimensions();

    int outputDimensions();

    Activation getActivation();

    void print(boolean details);
}
