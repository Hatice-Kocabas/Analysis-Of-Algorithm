

public class Matrix {
    
    
    // Applied on any matrix ~ O(n^3)
    public static double[][] naiveMultiplication(double[][] A, double[][] B) {
        if(A[0].length != B.length) return null;  // invalid multiplication
        
        double [][] C = new double[A.length][B[0].length];
        for(int i=0; i<C.length; i++) {
            for(int j=0; j<C[0].length; j++) {
                C[i][j] = 0;
                
                for(int k=0; k<A[i].length; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }       
            }
        }
        return C;
    }
    
    // applied for square matrices where n is a power of two (if matrix is square, we can make it power of two easily)
    public static double[][] StrassenMatrixMultiplication(double[][] A, double[][] B, int n) {
        double log = Math.log(n) / Math.log(2);
        if(log != (int)Math.ceil(log)) {  // not power of 2
            int closestPower = (int)Math.pow(2, Math.ceil(log));
            
            double[][] modifiedA = new double[closestPower][closestPower];
            double[][] modifiedB = new double[closestPower][closestPower];
            
            for(int i=0; i<modifiedA.length; i++) {
                for(int j=0; j<modifiedB.length; j++) {
                    if(i < n && j < n) {
                        modifiedA[i][j] = A[i][j];
                        modifiedB[i][j] = B[i][j];
                    } else {
                        modifiedA[i][j] = 0;
                        modifiedB[i][j] = 0;
                    }
                } 
            }
            A = modifiedA;
            B = modifiedB;
            n = closestPower;
        }

        if (n==1) {
            return new double[][] {{A[0][0] * B[0][0]}};
        } else {
            double[][] A11 = partition(A, 0, n/2, 0, n/2, n/2);
            double[][] A12 = partition(A, 0, n/2, n/2, n, n/2);
            double[][] A21 = partition(A, n/2, n, 0, n/2, n/2);
            double[][] A22 = partition(A, n/2, n, n/2, n, n/2);
            double[][] B11 = partition(B, 0, n/2, 0, n/2, n/2);
            double[][] B12 = partition(B, 0, n/2, n/2, n, n/2);
            double[][] B21 = partition(B, n/2, n, 0, n/2, n/2);
            double[][] B22 = partition(B, n/2, n, n/2, n, n/2);

            double[][] I = StrassenMatrixMultiplication(matrixOpt(A12, A22, '-'), matrixOpt(B21, B22, '+'), n/2);    // I = (A12 - A22)(B21 + B22)
            double[][] II =  StrassenMatrixMultiplication(matrixOpt(A11, A22, '+'), matrixOpt(B11, B22, '+'), n/2);  // II = (A11 + A22)(B11 + B22)
            double[][] III = StrassenMatrixMultiplication(matrixOpt(A11, A21, '-'), matrixOpt(B11, B12, '+'), n/2);  // III = (A11 - A21)(B11 + B12)
            double[][] IV = StrassenMatrixMultiplication(matrixOpt(A11, A12, '+'), B22, n/2);                        // IV = (A11 + A12)B22
            double[][] V =  StrassenMatrixMultiplication(A11, matrixOpt(B12, B22, '-'), n/2);                        // V = A11(B12 - B22)
            double[][] VI = StrassenMatrixMultiplication(A22, matrixOpt(B21, B11, '-'), n/2);                        // VI = A22(B21 - B11)
            double[][] VII = StrassenMatrixMultiplication(matrixOpt(A21, A22, '+'), B11, n/2);                        // VII = (A21 + A22)B11
            
            double[][] C11 = matrixOpt(matrixOpt(I, II, '+'), matrixOpt(VI, IV, '-'), '+');         // C11 = I + II - IV + VI
            double[][] C12 = matrixOpt(IV, V, '+');                                                 // C12 = IV + V
            double[][] C21 = matrixOpt(VI, VII, '+');                                               // C21 = VI + VII
            double[][] C22 = matrixOpt(matrixOpt(II, V, '+'), matrixOpt(III, VII, '+'), '-');       // C22 = II - III + V - VII
            
            double[][] C = collapse(C11, C12, C21, C22, n);
            return C;
        }
        
        

    }
    
    public static double[][] collapse(double[][] C11, double[][] C12, double[][] C21, double[][] C22, int n) {
        double[][] C = new double[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(i < n/2 && j < n/2) C[i][j] = C11[i][j];
                if(i < n/2 && j >= n/2) C[i][j] = C12[i][j - n/2];
                if(i >= n/2 && j < n/2) C[i][j] = C21[i - n/2][j];
                if(i >= n/2 && j >= n/2) C[i][j] = C22[i - n/2][j - n/2];
            }
        }
        return C;
    }
    public static double[][] partition(double[][] src, int row1, int row2, int col1, int col2, int n) {
        double[][] part = new double[n][n];
        for(int i=row1; i<row2; i++) {
            for(int j=col1; j<col2; j++) {
                part[i - row1][j - col1] = src[i][j];
            }
        }
        return part;
    }
    public static double[][] matrixOpt(double[][] A, double[][] B, char opr) {
        double [][] C = new double[A.length][A.length];
        for(int i=0; i<A.length; i++) {
            for(int j=0; j<A.length; j++) {
                C[i][j] = opr == '+' ?  A[i][j] + B[i][j] : A[i][j] - B[i][j];
            }
        }
        return C;
    }
    
    
    public static double[][] StrassenMatrixMultiplicationModified(double[][] A, double[][] B, int n) {
        double log = Math.log(n) / Math.log(2);
        if(log != (int)Math.ceil(log)) {  // not power of 2
            int closestPower = (int)Math.pow(2, Math.ceil(log));
            
            double[][] modifiedA = new double[closestPower][closestPower];
            double[][] modifiedB = new double[closestPower][closestPower];
            
            for(int i=0; i<modifiedA.length; i++) {
                for(int j=0; j<modifiedB.length; j++) {
                    if(i < n && j < n) {
                        modifiedA[i][j] = A[i][j];
                        modifiedB[i][j] = B[i][j];
                    } else {
                        modifiedA[i][j] = 0;
                        modifiedB[i][j] = 0;
                    }
                } 
            }
            A = modifiedA;
            B = modifiedB;
            n = closestPower;
        }

        if (n==1) {
            return new double[][] {{A[0][0] * B[0][0]}};
        } else {
            double[][] A11 = partition(A, 0, n/2, 0, n/2, n/2);
            double[][] A12 = partition(A, 0, n/2, n/2, n, n/2);
            double[][] A21 = partition(A, n/2, n, 0, n/2, n/2);
            double[][] A22 = partition(A, n/2, n, n/2, n, n/2);
            double[][] B11 = partition(B, 0, n/2, 0, n/2, n/2);
            double[][] B12 = partition(B, 0, n/2, n/2, n, n/2);
            double[][] B21 = partition(B, n/2, n, 0, n/2, n/2);
            double[][] B22 = partition(B, n/2, n, n/2, n, n/2);

            double[][] I = StrassenMatrixMultiplication(matrixOpt(A12, A22, '-'), matrixOpt(B21, B22, '+'), n/2);    // I = (A12 - A22)(B21 + B22)
            double[][] II =  StrassenMatrixMultiplication(matrixOpt(A11, A22, '+'), matrixOpt(B11, B22, '+'), n/2);  // II = (A11 + A22)(B11 + B22)
            double[][] III = StrassenMatrixMultiplication(matrixOpt(A11, A21, '-'), matrixOpt(B11, B12, '+'), n/2);  // III = (A11 - A21)(B11 + B12)
            double[][] IV = StrassenMatrixMultiplication(matrixOpt(A11, A12, '+'), B22, n/2);                        // IV = (A11 + A12)B22
            double[][] V =  StrassenMatrixMultiplication(A11, matrixOpt(B12, B22, '-'), n/2);                        // V = A11(B12 - B22)
            double[][] VI = StrassenMatrixMultiplication(A22, matrixOpt(B21, B11, '-'), n/2);                        // VI = A22(B21 - B11)
            double[][] VII = StrassenMatrixMultiplication(matrixOpt(A21, A22, '+'), B11, n/2);                        // VII = (A21 + A22)B11
            
            double[][] C11 = matrixOpt(matrixOpt(I, II, '+'), matrixOpt(VI, IV, '-'), '+');         // C11 = I + II - IV + VI
            double[][] C12 = matrixOpt(IV, V, '+');                                                 // C12 = IV + V
            double[][] C21 = matrixOpt(VI, VII, '+');                                               // C21 = VI + VII
            double[][] C22 = matrixOpt(matrixOpt(II, V, '+'), matrixOpt(III, VII, '+'), '-');       // C22 = II - III + V - VII
            
            double[][] C = collapse(C11, C12, C21, C22, n);
            return C;}
        }
        
        
    
    public static void main(String[] args) {
        double[][] A = {{1, 1, 1},
                 {2, 2, 2},
                 {3, 3, 3}};
        double [][] B = {{1, 1, 1},
                {2, 2, 2},
                {3, 3, 3}};

        
        double[][] res = StrassenMatrixMultiplication(A, B, A.length);
      
        for(int i=0; i<res.length; i++) {
            for(int j=0; j<res.length; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
        
        
    }

}