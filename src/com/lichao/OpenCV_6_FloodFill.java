package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;

import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_6_FloodFill</p>
 * <p>Description: ROI区域与泛洪填充</p>
 * <p>Company:</p>
 * @author Administrator
 * @date 2018年1月14日 下午9:19:03
 */
public class OpenCV_6_FloodFill {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\test.png");
		if (src.empty()) {
			return;
		}
		
		/*Rect rect = new Rect(120, 40, 200, 200);
		Mat roi = src.submat(rect);
		Mat gray = Mat.zeros(roi.size(), roi.type());
		gray.setTo(new Scalar(50, 50, 50));
		Core.add(roi, gray, roi);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_roi_Output", roi);*/
		
		Rect rect = new Rect(100, 300, 200, 200);
		Mat roi = Imgcodecs.imread(".\\img\\roi.png");
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_roi_Output", roi);
		
		Mat roi1 = src.submat(rect);
		Core.addWeighted(roi, 0.3, roi1, 0.7, 0, roi);
		
		roi.copyTo(src.submat(rect));
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_6_Input", src);
	}
}
