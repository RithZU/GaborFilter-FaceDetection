import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import javax.imageio.ImageIO;

public class main {
	
	public static void main(String args[]) {
		int GABORWIDTH = 90;
		int GABORHEIGHT = 90;
		
		BufferedImage subLena;
		float[][] subLenaPixels;
		float[][] gaboredPixel = new float[140][140];
		BufferedImage lena = null;
		BufferedImage gabor0;
		double[][] lenaPixels;
		float[][] filter1Pixels = new float[140][140];
		float[][] gaboredLenaPixels ;
		double[][] gabor0Pixels;
		int subi=0,subj=0;
		
//		float[][] arr1 = {
//				{1,1,1},
//				{1,1,1},
//				{1,1,1}
//		};
//		float[][] arr2 = new float[3][3];
//		
//		Matrix.copyArray(arr1, arr2);
//		for(int i=0;i<arr2.length;i++) {
//			for(int j=0;j<arr2[0].length;j++) {
//				System.out.print(arr2[i][j]+"\t");
//			}
//			System.out.println();
//		}
		
		
		ArrayList<BufferedImage> gabors = new ArrayList<BufferedImage>();
		//add gabor filters to ArrayList
		for(int i=0;i<40;i++) {
			BufferedImage perGabor = null;
			try {
				perGabor = ImageIO.read(new File("res/gabor"+i+".jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gabors.add(perGabor);
		}
		lena = Utils.readImage("res/lena.jpg");
		
		
		gabor0 = gabors.get(0);
		gabor0Pixels = Utils.imageToPixels(gabor0);
		int gaborWidth = gabor0.getWidth();
		int gaborHeight = gabor0.getHeight();
		
		lena = Utils.convertToGrayScaled(lena);
		Utils.writeImage(lena, "grayLena");
		lenaPixels = Utils.imageToPixels(lena);
		
		
//		for(int i=0;i<lenaPixels.length;i++) {
//			for(int j=0;j<lenaPixels[0].length;j++) {
//				System.out.print(lenaPixels[i][j]+"\t");
//			}
//			System.out.println();
//		}
		//Utils.writeImage(Utils.pixelsToImage(lenaPixels), "grayScaled");
		
		
//		int k=0,l=0;
//		for(int i=0;i<=lena.getHeight()-90;i+=gaborWidth,k++) {
//			for(int j=0;j<lena.getWidth()-90;j+=gaborHeight,l++) {
//				
//				BufferedImage subLena = lena.getSubimage(i, j, gaborWidth, gaborHeight);
//				float[][] subLenaPixels = Utils.imageToPixels(subLena);
//				subLenaPixels = Matrix.multiplicar(subLenaPixels, gabor0Pixels);
//				subLenaPixels = Utils.normalizePixels(subLenaPixels, gaborWidth);
//				lenaPixels[k][l]
//			}
//		}
		
		
		
		gaboredLenaPixels = new float[lena.getWidth()][lena.getHeight()];
		int factor = 9;
		int stepSize= 90/factor;
		int n=0;
		double[][] result = new double[lena.getWidth()/stepSize][lena.getHeight()/stepSize];
		for(int g=0;g<gabors.size();g++)
		{
		for(int i=0;i<=result.length-stepSize+1;i++) {
			
			for(int j=0;j<=result[0].length-stepSize+1;j++) {
				//System.out.println(i+"\t"+j);
				
				
				double tempResult = 0;
				
				subLena = lena.getSubimage(stepSize*i, j*stepSize, gaborWidth, gaborHeight);
				
				tempResult = Matrix.dotProduct(Utils.imageToPixels(subLena), Utils.imageToPixels(gabors.get(g)), 90);
				System.out.print(tempResult+"\t");
				result[i][j] = tempResult;
				//Utils.writeImage(subLena, "subLena"+(n++));
				
				
				
//				subLenaPixels = Utils.imageToPixels(subLena);
//				
//				subLenaPixels = Utils.normalizePixels(subLenaPixels, gaborWidth);
				//Utils.writeImage(Utils.pixelsToImage(subLenaPixels), "subGabored"+(n++));
//				
//				subi = 0;
//				subj=0;
//				
//				for(int k=i;k<=90+i;k++) {
//					
//					//System.out.println(subi+"\t"+subj);
//					++subi;
//					++subj;
//					//System.out.println();
//					for(int l=j;l<=90+j;l++) {
//						//System.out.println(subi+"\t"+subj);
//						if(subi<90 && subj<90) {
//						gaboredLenaPixels[k][l] = subLenaPixels[subi][subj];
//						}
//						System.out.println(k+"\t"+l);
//						
//						
//						
//					}
//					
					
					
//					
//				}
//				subi = 0;
//				subj=0;
				
			}
			System.out.println();
			
			
			
		}
		Utils.writeImage(Utils.pixelsToImage(result), "gaboredImage"+g);
		System.out.println("Done");
		}
		
	System.out.println("Done");
		
		
//		for(int i=0;i<gaboredLenaPixels.length;i++) {
//			for(int j=0;j<gaboredLenaPixels[0].length;j++) {
//				System.out.print(gaboredLenaPixels[i][j]+"\t");
//			}
//			System.out.println();
//		}
		
		//gaboredLenaPixels = Matrix.multiplicar(lenaPixels, gabor0Pixels);
		//gaboredLenaPixels = Utils.normalizePixels(gaboredLenaPixels, 90);
//		BufferedImage mouth = null;
//		try {
//			mouth = ImageIO.read(new File("res/mouth.png"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		mouth = mouth.getSubimage(10, 10, 90, 90);
//		Utils.writeImage(mouth, "mouthSub");
//		float[][] mouthPixel = Utils.imageToPixels(mouth);
//		
//		mouthPixel = Matrix.multiplicar(gabor0Pixels, mouthPixel);
//		mouthPixel = Utils.normalizePixels(mouthPixel, 90);
//		Utils.writeImage(Utils.pixelsToImage(mouthPixel), "MouthTest");
		
		
		
		
		//Utils.writeImage(Utils.pixelsToImage(gaboredLenaPixels), "TestFilter1");

		
		
		
		
			 
			 
			 
			 
			
			 
		
	}

}
