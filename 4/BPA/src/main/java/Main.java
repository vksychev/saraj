
public class Main {

    static final int N = 8;

    static double[][] matrix1 = {{2, 4, 3, 0, 0, 0, 0, 4},
            {2, 3, 3, 4, 3, 2, 4, 3},
            {0, 0, 5, 6, 0, 1, 0, 5},
            {0, 5, 3, 3, 3, 3, 4, 0},
            {0, 4, 2, 4, 5, 4, 6, 0},
            {0, 3, 4, 0, 0, 5, 6, 0},
            {3, 0, 4, 0, 5, 0, 6, 0},
            {4, 0, 3, 0, 6, 0, 7, 0}};

    static double[][] matrix2 = {{2, 4, 3, 0, 0, 0, 0, 4},
            {2, 3, 3, 4, 3, 2, 4, 3},
            {0, 0, 5, 6, 0, 1, 0, 5},
            {0, 5, 3, 3, 3, 3, 4, 0},
            {0, 4, 2, 4, 5, 4, 6, 0},
            {0, 3, 4, 0, 11, 5, 6, 0},
            {3, 0, 4, 0, 5, 0, 6, 0},
            {4, 0, 3, 0, 6, 0, 7, 0}};

    static double[][] matrix3 = {{2, 4, 3, 0, 0, 0, 0, 4},
            {2, 3, 3, 4, 3, 2, 4, 3},
            {0, 11, 5, 6, 0, 1, 0, 5},
            {0, 5, 3, 3, 3, 3, 4, 0},
            {0, 4, 2, 4, 5, 4, 6, 0},
            {0, 3, 4, 0, 0, 5, 6, 0},
            {3, 0, 4, 0, 5, 0, 6, 0},
            {4, 0, 3, 0, 6, 0, 7, 0}};

    static double[][] filter = {
            {0.6080939315687557, 0.3919060684312457, 0.3919060684312452, -0.39190606843124665, -0.39190606843124604, 0.3919060684312454, 0.39190606843124576, -0.39190606843124665},
            {-0.06524370561652254, 1.0652437056165256, 0.0652437056165246, -0.06524370561652837, -0.06524370561652654, 0.0652437056165272, 0.06524370561652526, -0.0652437056165297},
            {0.7487491930277574, -0.748749193027759, 0.2512508069722422, 0.7487491930277608, 0.7487491930277603, -0.74874919302776, -0.7487491930277592, 0.748749193027761},
            {-1.0958279535183926, 1.095827953518398, 1.0958279535183948, -0.09582795351840057, -1.0958279535183992, 1.0958279535183995, 1.0958279535183957, -1.0958279535184023},
            {0.030624596513883856, -0.030624596513879193, -0.030624596513883662, 0.03062459651387689, 1.0306245965138783, -0.030624596513878416, -0.03062459651388133, 0.030624596513873115},
            {-0.5925193673337619, 0.5925193673337623, 0.5925193673337622, -0.592519367333764, -0.5925193673337614, 1.5925193673337625, 0.5925193673337635, -0.5925193673337622},
            {-1.2556084570690746, 1.255608457069075, 1.2556084570690755, -1.2556084570690775, -1.255608457069076, 1.2556084570690753, 2.2556084570690778, -1.255608457069074},
            {-0.019972562943834767, 0.019972562943834656, 0.019972562943834746, -0.019972562943834746, -0.019972562943834656, 0.019972562943834628, 0.019972562943834912, 0.9800274370561651}
    };

    static double[] adamarFour(double[] line) {
        double[] newline = {line[0], line[4], line[2], line[6], line[1], line[5], line[3], line[7]};
        double[] result = new double[N];
        int delta = 4;
        for (int i = 0; i < 4; i++) {
            result[i] = newline[i] + newline[i + delta];
        }
        for (int i = 4; i < 8; i++) {
            result[i] = -newline[i] + newline[i - delta];
        }


        newline = result.clone();
        delta = 2;
        for (int i = 0; i < 2; i++) {
            result[i] = newline[i] + newline[i + delta];
        }
        for (int i = 2; i < 4; i++) {
            result[i] = -newline[i] + newline[i - delta];
        }

        for (int i = 4; i < 6; i++) {
            result[i] = newline[i] - newline[i + delta];
        }
        for (int i = 6; i < 8; i++) {
            result[i] = newline[i] + newline[i - delta];
        }


        newline = result.clone();

        result[0] = newline[0] + newline[1];
        result[1] = newline[0] - newline[1];

        result[2] = newline[2] - newline[3];
        result[3] = newline[2] + newline[3];

        result[4] = newline[4] + newline[5];
        result[5] = newline[4] - newline[5];

        result[6] = newline[6] - newline[7];
        result[7] = newline[6] + newline[7];


        return result;


    }

//    for(double d: result){
//        System.out.print(d);
//        System.out.print(" ");
//    }
//        System.out.println();

    static double[] adamarFourRev(double[] line) {
        double[] newline = line.clone();
        double[] result = new double[N];


        int delta = 4;
        for (int i = 0; i < 4; i++) {
            result[i] = newline[i] + newline[i + delta];
        }
        for (int i = 4; i < 8; i++) {
            result[i] = -newline[i] + newline[i - delta];
        }


        newline = result.clone();
        delta = 2;
        for (int i = 0; i < 2; i++) {
            result[i] = newline[i] + newline[i + delta];
        }
        for (int i = 2; i < 4; i++) {
            result[i] = -newline[i] + newline[i - delta];
        }

        for (int i = 4; i < 6; i++) {
            result[i] = newline[i] - newline[i + delta];
        }
        for (int i = 6; i < 8; i++) {
            result[i] = newline[i] + newline[i - delta];
        }


        newline = result.clone();

        result[0] = newline[0] + newline[1];
        result[1] = newline[0] - newline[1];

        result[2] = newline[2] - newline[3];
        result[3] = newline[2] + newline[3];

        result[4] = newline[4] + newline[5];
        result[5] = newline[4] - newline[5];

        result[6] = newline[6] - newline[7];
        result[7] = newline[6] + newline[7];


        return new double[]{result[0], result[7], result[4], result[3], result[2], result[5], result[6], result[1]};
    }

    static String printArray(double[][] matrix) {
        StringBuilder s = new StringBuilder();
        s.append("[").append("\n");
        for (int i = 0; i < N; i++) {
            s.append("[");
            for (int j = 0; j < N; j++) {
                s.append(matrix[i][j]).append(", ");
            }
            s.deleteCharAt(s.length() - 1);
            s.deleteCharAt(s.length() - 1);
            s.append("],").append("\n");
        }
        s.deleteCharAt(s.length() - 1);
        s.deleteCharAt(s.length() - 1);
        s.append("\n").append("]");
        return s.toString();
    }

    static double[][] adamarMatrix(double[][] m) {
        double[][] matrix = m.clone();

        for (int i = 0; i < N; i++) {
            matrix[i] = adamarFour(matrix[i]);
        }


        for (int i = 0; i < N; i++) {
            double[] result = adamarFour(new double[]{matrix[0][i], matrix[1][i], matrix[2][i], matrix[3][i],
                    matrix[4][i], matrix[5][i], matrix[6][i], matrix[7][i]});

            for (int j = 0; j < N; j++) {
                matrix[j][i] = result[j];
            }
        }

        return matrix;
    }

    static double[][] reverseAdamarMatrix(double[][] m) {
        double[][] matrix = m.clone();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = matrix[i][j] / N;
                matrix[i][j] = matrix[i][j] / N;
                matrix[i][j] = matrix[i][j] / N;
            }
        }

        for (int i = 0; i < N; i++) {
            matrix[i] = adamarFourRev(matrix[i]);
        }


        for (int i = 0; i < N; i++) {
            double[] result = adamarFourRev(new double[]{matrix[0][i], matrix[1][i], matrix[2][i], matrix[3][i],
                    matrix[4][i], matrix[5][i], matrix[6][i], matrix[7][i]});

            for (int j = 0; j < N; j++) {
                matrix[j][i] = result[j];
            }
        }


        return matrix;
    }

    static public double[][] sss(double[][] m) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                m[i][j] *= N;
                m[i][j] *= N;
            }
        }
        return m;
    }
    static public double[][] ss(double[][] m) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                m[i][j] /= N;
                m[i][j] /= N;
            }
        }
        return m;
    }

    static double[][] mult(double[][] a, double[][] b){
        int m = a.length;
        int n = b[0].length;
        int o = b.length;
        double[][] res = new double[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < o; k++) {
                    res[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        double[][] adamar1 = adamarMatrix(matrix1);
        double[][] adamar2 = adamarMatrix(matrix2);
        double[][] adamar3 = adamarMatrix(matrix3);



        double[][] adamar21 = mult(adamar2,filter);

        double[][] adamar31 = mult(adamar3,filter);


        adamar31 = reverseAdamarMatrix(adamar31);
        adamar21 = reverseAdamarMatrix(adamar21);


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int temp =(int) Math.round(adamar31[i][j]*10000);
                adamar31[i][j] = (double) temp/ 10000;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                adamar31[i][j] = adamar31[i][j]*N;
            }
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int temp =(int) Math.round(adamar21[i][j]*10000);
                adamar21[i][j] = (double) temp/ 10000;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                adamar21[i][j] = adamar21[i][j]*N;
            }
        }

        System.out.println(printArray(adamar21));
        System.out.println(printArray(adamar31));
//        System.out.println(printArray(filter));
//


//
//        System.out.println(printArray(matrix2));
//
//        System.out.println(printArray(reverseAdamarMatrix(adamar31)));



//
//        double[] a = {1,2,3,4,5,6,7,8};
//
//        a= adamarFour(a);
//
//        for (double d : a) {
//            System.out.print(d);
//            System.out.print(" ");
//        }
//        System.out.println();
//
//        for (int i = 0; i < N; i++) {
//
//                a[i] /= N;
//        }
//
//        a= adamarFourRev(a);
//        for (double d : a) {
//            System.out.print(d);
//            System.out.print(" ");
//        }
//        System.out.println();
    }
}
