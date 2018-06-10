import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Utils {

	public static double[][] imageToPixels(BufferedImage img){
		double[][] pixels = new double[img.getWidth()][img.getHeight()];
		
		for(int i=0;i<img.getWidth();i++) {
			for(int j=0;j<img.getHeight();j++) {
				int p = img.getRGB(i, j);
				
		        int r = (p>>16)&0xff;
		        int g = (p>>8)&0xff;
		        int b = p&0xff;
				int avg = (r+g+b)/3;
				
				pixels[i][j] = avg;
				
			}
		}
		return pixels;
	}
	public static BufferedImage pixelsToImage(double[][] pixelsImage) {
		BufferedImage newImg = new BufferedImage(pixelsImage.length,pixelsImage[0].length,BufferedImage.TYPE_INT_RGB);
		for(int i=0;i<newImg.getWidth();i++) {
			for(int j=0;j<newImg.getHeight();j++) {
				int x = (int) pixelsImage[i][j];
				int p = ((x&0x0ff)<<16)|((x&0x0ff)<<8)|(x&0x0ff);
				newImg.setRGB(i, j, p);
				
			}
		}
		return newImg;
	}
	public static BufferedImage convertToGrayScaled(BufferedImage img) {
		//get image width and height
	    int width = img.getWidth();
	    int height = img.getHeight();

	    //convert to grayscale
	    for(int y = 0; y < height; y++){
	      for(int x = 0; x < width; x++){
	        int p = img.getRGB(x,y);

	        int a = (p>>24)&0xff;
	        int r = (p>>16)&0xff;
	        int g = (p>>8)&0xff;
	        int b = p&0xff;

	        //calculate average
	        int avg = (r+g+b)/3;

	        //replace RGB value with avg
	        p = (a<<24) | (avg<<16) | (avg<<8) | avg;

	        img.setRGB(x, y, p);
	      }
	    }
	    return img;
		
	}
	public static BufferedImage readImage(String path) {
		BufferedImage img = null;
		 try {
				img = ImageIO.read(new File(path));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 return img;
		
	}
	public static void writeImage(BufferedImage img,String fileName) {
		try {
			ImageIO.write(img,"jpg", new File("res/"+fileName+".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static float[][] normalizePixels(double[][] matrix, int norm) {
		float[][] newMatrix = new float[matrix.length][matrix[0].length];
		for(int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix[0].length;j++) {
				newMatrix[i][j] = (float) (matrix[i][j]/Math.pow(norm,2));
			}
		}
		
		return newMatrix;
	}
	
}
