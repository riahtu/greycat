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
package greycat.ml.neuralnet.loss;


import greycat.ml.neuralnet.process.LossUnit;

/**
 * Created by assaad on 13/02/2017.
 */
public class LossUnits {
    public static final int SUM_OF_SQUARES = 0;
    public static final int SOFTMAX = 1;
    public static final int DEFAULT = SUM_OF_SQUARES;

    public static LossUnit getUnit(int lossUnit, double[] unitArgs) {
        switch (lossUnit) {
            case SUM_OF_SQUARES:
                return LossSumOfSquares.instance();
            case SOFTMAX:
                return LossSoftmax.instance();
        }
        return getUnit(DEFAULT, null);
    }
}