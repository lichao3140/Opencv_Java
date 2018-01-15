package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_15_BinaryImage</p>
 * <p>Description: 图像二值化</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018年1月15日 下午8:00:31
 */
public class OpenCV_15_BinaryImage {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\test1.png");//输入图像
		
		if (src.empty()) {
			return;
		}
		
		Mat gray = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_15_Input", gray);
		
		//常用方法一手动阈值:使用中值127，但这方法不够科学
		//自动：使用全局阈值方法Imgproc.THRESH_OTSU时候127这个值无效，是由全局阈值方法去决定
		//自动：三角阈值法Imgproc.THRESH_TRIANGLE多数用于细胞检测等
		Mat dst = new Mat();
		Imgproc.threshold(gray, dst, 127, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_Binary_Output", dst);
	}
}
