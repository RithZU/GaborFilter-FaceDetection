import java.awt.image.BufferedImage;

public class Matrix {
	
	public static double dotProduct(double[][] A, double[][] B,int gaborSize) {

        int aRows = A.length;
        int aColumns = A[0].length;
        int bRows = B.length;
        int bColumns = B[0].length;

        double result=0;

        for (int i = 0; i < 90; i++) { // aRow
            for (int j = 0; j < 90; j++) { // bColumn
                result += ((A[i][j]/255) * (B[i][j])/255);
            }
        }
        result =   (result/ Math.pow(gaborSize, 2));
        result = (result * 255);

        return result;
    }
	public static void copyArray(float[][] src, float[][] dest) {
		for(int i=0;i<dest.length;i++) {
			for(int j=0;j<dest[0].length;j++) {
				dest[i][j] = src[i][j];
			}
		}
	}
	

}
