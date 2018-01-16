package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_29_OpenClose</p>
 * <p>Description: 图象形态学---开闭操作</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018年1月16日 下午10:32:10
 */
public class OpenCV_29_OpenClose {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\morph02.png");//输入图像
		if (src.empty()) {
			return;
		}
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_29_Input", src);
		
		//demoOne(src);
		//detectLine(src);
		removeLines(src);
	}
	
	/**
	 * 去干扰
	 * @param src
	 */
	public static void removeLines(Mat src) {
		Mat gray = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
		
		Mat binary = new Mat();
		//自动阈值二值化
		Imgproc.threshold(gray, binary, 0, 255, Imgproc.THRESH_BINARY_INV | Imgproc.THRESH_OTSU);
		
		Mat dst = new Mat();
		//结构元素，设置Size()函数
		Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
		//开操作:先腐蚀再膨胀
		Imgproc.morphologyEx(binary, dst, Imgproc.MORPH_OPEN, kernel);
		
		ImageUI ui_dilate = new ImageUI();
		ui_dilate.imshow("OpenCV_Open_DetectLine", dst);
	}
	
	/**
	 * 线段提取
	 * @param src
	 */
	public static void detectLine(Mat src) {
		Mat gray = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
		
		Mat binary = new Mat();
		//自动阈值二值化
		Imgproc.threshold(gray, binary, 0, 255, Imgproc.THRESH_BINARY_INV | Imgproc.THRESH_OTSU);
		
		Mat dst = new Mat();
		//保留X方向线，设置Size()函数
		//Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(40, 1));
		//保留Y方向线，设置Size()函数
		Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(1, 30));
		//开操作:先腐蚀再膨胀
		Imgproc.morphologyEx(binary, dst, Imgproc.MORPH_OPEN, kernel);
		
		ImageUI ui_dilate = new ImageUI();
		ui_dilate.imshow("OpenCV_Open_DetectLine", dst);
	}
	
	/**
	 * 去多余图像
	 * @param src
	 */
	public static void demoOne(Mat src) {
		Mat gray = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
		
		Mat binary = new Mat();
		//自动阈值二值化
		Imgproc.threshold(gray, binary, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
		
		Mat dst = new Mat();
		//结构元素
		Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5));
		//开操作:先腐蚀再膨胀
		//Imgproc.morphologyEx(binary, dst, Imgproc.MORPH_OPEN, kernel);
		//多次开操作
		Imgproc.morphologyEx(binary, dst, Imgproc.MORPH_OPEN, kernel, new Point(0, 0), 5);
		
		//闭操作:先膨胀再腐蚀
		Mat close = new Mat();
		Imgproc.morphologyEx(binary, close, Imgproc.MORPH_CLOSE, kernel, new Point(0, 0), 2);
		
		ImageUI ui_dilate = new ImageUI();
		ui_dilate.imshow("OpenCV_Open_Close", close);
	}
}
