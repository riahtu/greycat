package org.mwdb.math.matrix;

import org.mwdb.math.matrix.blassolver.SVD;

/**
 * Created by assaad on 25/03/16.
 */
public interface KSVDDecompose {
    KSVDDecompose factor(KMatrix A, boolean workInPlace);

    KMatrix getU();

    KMatrix getVt();

    double[] getS();

    KMatrix getSMatrix();
}
