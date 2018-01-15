package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * 
 * <p>Title: OpenCV_12_HistogramCompare</p>
 * <p>Description: 直方图的比较</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018年1月15日 下午4:38:54
 */
public class OpenCV_12_HistogramCompare {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src1 = Imgcodecs.imread(".\\img\\lena.png");
		Mat src2 = Imgcodecs.imread(".\\img\\tinygreen.png");
		
		if (src1.empty() || src2.empty()) {
			return;
		}
		
		Mat h1 = calculateHist(src1);
		Mat h2 = calculateHist(src2);
		double match1 = Imgproc.compareHist(h1, h2, Imgproc.CV_COMP_CORREL);//(0-1)值越接近1匹配度越高
		double match2 = Imgproc.compareHist(h1, h2, Imgproc.CV_COMP_CHISQR);//值越小匹配度越高
		double match3 = Imgproc.compareHist(h1, h2, Imgproc.CV_COMP_BHATTACHARYYA);//(0-1)值越接近0匹配度越高
		
		System.out.println("match1:" + match1);
		System.out.println("match2:" + match2);
		System.out.println("match3:" + match3);
	}
	
	/**
	 * 降维
	 * @param src
	 * @return
	 */
	private static Mat calculateHist(Mat src) {
		int width = src.cols();
		int height = src.rows();
		int dims = src.channels();
		byte[] data = new byte[width * height * dims];
		src.get(0, 0, data);
		
		Mat h = Mat.zeros(16 * 16 * 16, 1, CvType.CV_32FC1);
		int bsize = 256 / 16;
		
		int r = 0, g = 0, b = 0;
		int index = 0;
		float[] count = new float[1];
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				b = data[row * dims * width + col * dims]&0xff;
				g = data[row * dims * width + col * dims + 1]&0xff;
				r = data[row * dims * width + col * dims + 2]&0xff;
				
				index = (b / bsize) * 16 * 16 + (g / bsize) * 16 + (r / bsize);
				h.get(index, 0, count);
				count[0]++;
				h.put(index, 0, count);
			} 
		}
		return h;
	}
}
