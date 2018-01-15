package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_16_BinaryAdapt</p>
 * <p>Description: 自适应阈值二值化</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018年1月15日 下午11:35:00
 */
public class OpenCV_16_BinaryAdapt {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\text1.png");//输入图像
		
		if (src.empty()) {
			return;
		}
		
		Mat gray = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_16_Input", gray);
		
		//图像光照均匀时候使用---全局阈值Imgproc.THRESH_BINARY
		/*Mat dst = new Mat();
		//自适应均值阈值---Imgproc.ADAPTIVE_THRESH_MEAN_C
		//Imgproc.adaptiveThreshold(gray, dst, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 5, 10);
		//自适应高斯阈值---Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C  （注意blockSize一定是要奇数）
		Imgproc.adaptiveThreshold(gray, dst, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 15, 10);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_BinaryAdapt_Output", dst);*/
		
		//图像光照不均匀时候使用---局部阈值Imgproc.THRESH_BINARY_INV
		Mat dst = new Mat();
		Imgproc.adaptiveThreshold(gray, dst, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY_INV, 55, 10);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_BinaryAdapt_Output", dst);
	}
}
