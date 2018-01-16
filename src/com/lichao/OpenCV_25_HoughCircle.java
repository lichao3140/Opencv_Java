package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_25_HoughCircle</p>
 * <p>Description: 圆检测</p>
 * <p>Company:</p>
 * @author Administrator
 * @date 2018年1月16日 下午5:12:03
 */
public class OpenCV_25_HoughCircle {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\coins.jpg");//输入图像
		if (src.empty()) {
			return;
		}
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_25_Input", src);
		
		Mat gray = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
		
		Imgproc.GaussianBlur(gray, gray, new Size(3, 3), 0);
		
		//霍夫
		Mat circles = new Mat();
		Imgproc.HoughCircles(gray, circles, Imgproc.HOUGH_GRADIENT, 1, 10, 150, 50, 10, 50);
		
		Mat result = src.clone();
		for (int i = 0; i < circles.cols(); i++) {
			float[] info = new float[3];
			circles.get(0, i, info);
			Imgproc.circle(result, new Point((int)info[0], (int)info[1]), (int)info[2], new Scalar(0, 0, 255), 2, 8, 0);
		}
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_HoughCircle_Output", result);
	}
}
