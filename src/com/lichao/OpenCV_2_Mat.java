package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class OpenCV_2_Mat {
	
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		//第一种创建方法  create
		/*Mat src = new Mat();
		src.create(300, 300, CvType.CV_8UC3);
		src.setTo(new Scalar(0, 0, 255));
		Imgcodecs.imwrite(".\\screenshot\\mat.jpg", src);*/
		
		//第二种创建方法 直接初始化
		//zeros:0黑色   ones:1黑色    CV_8UC1:单通道     CV_8UC3:三通道
		/*Mat src = Mat.zeros(300, 300, CvType.CV_8UC1);
		Imgcodecs.imwrite(".\\screenshot\\mat.jpg", src);*/
		
		//类型与大小读取
		Mat src = Imgcodecs.imread(".\\img\\lena.png", Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
		int type = src.type();
		int width = src.cols();
		int heigth = src.rows();
		int channels = src.channels();
		int depth = src.depth();
		System.out.println("type:" + type);
		System.out.println("width:" + width);
		System.out.println("heigth:" + heigth);
		System.out.println("channels:" + channels);
		System.out.println("depth:" + depth);
		if (CvType.CV_8UC3 == type) {
			System.out.println("type is CV_8UC3");
		} else if (CvType.CV_8UC1 == type) {
			System.out.println("type is CV_8UC1");
		} else {
			System.out.println("UnKnow...");
		}
		
		//Mat类型转换  变换成浮点数图像
		Mat dst = new Mat(src.size(), CvType.CV_32FC1);
		src.convertTo(dst, CvType.CV_32F);
	}
}
