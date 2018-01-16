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
 * <p>Title: OpenCV_28_DilateErode</p>
 * <p>Description:图象形态学---腐蚀与膨胀</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018年1月16日 下午9:43:35
 */
public class OpenCV_28_DilateErode {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\morph.png");//输入图像，膨胀和腐蚀也适用于彩色图像
		if (src.empty()) {
			return;
		}
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_28_Input", src);
		
		Mat dst = new Mat();
		//获取结构元素,膨胀矩形Imgproc.MORPH_RECT  
		Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
		//膨胀
		//Imgproc.dilate(src, dst, kernel);
		//膨胀多次
		//Imgproc.dilate(src, dst, kernel, new Point(0, 0), 3);
		
		//腐蚀
		//Imgproc.erode(src, dst, kernel);
		//腐蚀多次
		Imgproc.erode(src, dst, kernel, new Point(0, 0), 3);
		
		ImageUI ui_dilate = new ImageUI();
		ui_dilate.imshow("OpenCV_Dilate_Output", dst);
		
	}
}
