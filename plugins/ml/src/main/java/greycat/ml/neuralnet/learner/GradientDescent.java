/**
 * Copyright 2017 The GreyCat Authors.  All rights reserved.
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
package greycat.ml.neuralnet.learner;


import greycat.ml.common.matrix.MatrixOps;
import greycat.ml.neuralnet.layer.Layer;
import greycat.ml.neuralnet.process.ExMatrix;
import greycat.struct.DMatrix;
import greycat.struct.ENode;

public class GradientDescent extends AbstractLearner {

    public static String LEARNING_RATE = "learningrate";
    public static double LEARNING_RATE_DEF = 0.001;

    public static String REGULARIZATION_RATE = "regularizationrate";
    public static double REGULARIZATION_RATE_DEF = 0.000001;

    private double learningRate;
    private double regularization;


    //param[0] => learning rate
    //param[1] => regularization rate
    public GradientDescent(ENode backend) {
        super(backend);
        learningRate = backend.getWithDefault(LEARNING_RATE, LEARNING_RATE_DEF);
        regularization = backend.getWithDefault(REGULARIZATION_RATE, REGULARIZATION_RATE_DEF);
    }

    @Override
    protected void update(Layer[] layers) {
        DMatrix w;
        DMatrix dw;

        double alpha = 1 - learningRate * regularization / steps;
        double beta = -learningRate / steps;

        for (int i = 0; i < layers.length; i++) {
            ExMatrix[] weights = layers[i].getModelParameters();
            for (int j = 0; j < weights.length; j++) {
                w = weights[j].getW();
                dw = weights[j].getDw();

                //w= (1- learningRate * regularization / samples ) * w - learningRate * dw / samples ;
                //Ref: https://www.coursera.org/learn/machine-learning/lecture/QrMXd/regularized-linear-regression
                MatrixOps.addInPlace(w, alpha, dw, beta);
                dw.fill(0);
            }
        }
    }
}