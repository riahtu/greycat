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
package greycat.ml.neuralnet.functions;

import greycat.ml.neuralnet.process.ActivationUnit;

public class TanhUnit implements ActivationUnit {


    private static TanhUnit static_unit = null;

    public static TanhUnit instance() {
        if (static_unit == null) {
            static_unit = new TanhUnit();
        }
        return static_unit;
    }

    /**
     * {@native ts
     * if (x === Infinity) {return 1;}
     * if (x === -Infinity) {return -1;}
     * let y = Math.exp(x * 2);
     * return (y - 1) / (y + 1);
     * }
     */
    @Override
    public double forward(double x) {
        return Math.tanh(x);
    }

    @Override
    public double backward(double x, double fct) {
        return 1- fct*fct;
    }

    /**
     * {@native ts
     * return (Math.exp(x) + Math.exp(-x)) / 2;
     * }
     */
    private static double cosh(double x) {
        return Math.cosh(x);
    }


//    public static void main(String[] arg){
//        Random rand = new Random();
//
//        for(int i=0;i<10;i++) {
//            double x = rand.nextDouble();
//
//            TanhUnit th = new TanhUnit();
//            double y = th.forward(x);
//            double yp = th.backward(x, y);
//
//            double temp = (1 - y * y);
//            System.out.println("yp: " + yp);
//            System.out.println("temp: " + temp);
//            System.out.println();
//        }
//    }

}