package com.lichao;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_26_FindContours</p>
 * <p>Description: 轮廓发现</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018年1月16日 下午7:29:30
 */
public class OpenCV_26_FindContours {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\test.png");//输入图像
		if (src.empty()) {
			return;
		}
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_26_Input", src);
		
		demoOne(src);
		
		demoTwo(src);
	}
	
	/**
	 * 有阈值，复杂图像，比demoOne更精确
	 * @param src
	 */
	public static void demoTwo(Mat src) {
		Mat gray = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
		
		//双边模糊
		Mat dst = new Mat();
		Imgproc.bilateralFilter(gray, dst, 0, 50, 5);
		//边缘提取
		Mat edges = new Mat();
		Imgproc.Canny(dst, edges, 30, 200);
		
		ImageUI ui_binary = new ImageUI();
		ui_binary.imshow("OpenCV_Binary_Two", edges);
		
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Mat hierarchy = new Mat();
		Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE, new Point(0, 0));
		
		Mat result = src.clone();
		for (int i = 0; i < contours.size(); i++) {
			Imgproc.drawContours(result, contours, i, new Scalar(0, 0, 255), 2);
		}
		
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_Result_demoTwo", result);
	}
	
	/**
	 * 无阈值，使用于简单图像
	 * @param src
	 */
	public static void demoOne(Mat src) {
		Mat gray = new Mat();
		Mat binary = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
		//二值化
		Imgproc.threshold(gray, binary, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
		
		ImageUI ui_binary = new ImageUI();
		ui_binary.imshow("OpenCV_Binary_One", binary);
		
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Mat hierarchy = new Mat();
		Imgproc.findContours(binary, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE, new Point(0, 0));
		
		Mat result = src.clone();
		for (int i = 0; i < contours.size(); i++) {
			Imgproc.drawContours(result, contours, i, new Scalar(0, 255, 0), 2);
		}
		
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_Result_demoOne", result);
	}
}
