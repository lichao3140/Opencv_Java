package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_24_HoughLine</p>
 * <p>Description:直线检测---霍夫直线变换</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018年1月16日 下午4:34:27
 */
public class OpenCV_24_HoughLine {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\lines.png");//输入图像
		if (src.empty()) {
			return;
		}
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_24_Input", src);
		
		//1.前提条件---边缘检测已完成
		Mat edges = new Mat();
		Imgproc.Canny(src, edges, 50, 150, 3, true);
		
		//2.平面空间到极坐标空间转换
		Mat lines = new Mat();
		Imgproc.HoughLinesP(edges, lines, 1, Math.PI/180.0, 50, 10, 10);
		
		Mat result = Mat.zeros(src.size(), src.type());
		for (int i = 0; i < lines.rows(); i++) {
			int[] oneline = new int[4];
			lines.get(i, 0, oneline);
			Imgproc.line(result, new Point(oneline[0], oneline[1]), new Point(oneline[2], oneline[3]), 
					new Scalar(0, 0, 255), 2, 8, 0);
		}
		
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_HoughLine_Output", result);
	}
}
