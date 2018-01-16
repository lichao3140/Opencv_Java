package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_23_Canny</p>
 * <p>Description:Canny边缘提取</p>
 * <p>Company:</p>
 * @author Administrator
 * @date 2018年1月16日 下午3:58:14
 */
public class OpenCV_23_Canny {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\lena.png");//输入图像
		
		if (src.empty()) {
			return;
		}
		
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_23_Input", src);
		
		//使用OpenCV Canny API用法一
		/*//1.高斯模糊
		Mat dst = new Mat();
		Imgproc.GaussianBlur(src, dst, new Size(3, 3), 0);
		//2.灰度处理
		Mat gray = new Mat();
		Imgproc.cvtColor(dst, gray, Imgproc.COLOR_BGR2GRAY);
		//3.边缘提取
		Mat output = new Mat();
		Imgproc.Canny(gray, output, 50, 150, 3, true);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_Canny_Output", output);*/
		
		//使用OpenCV Canny API用法二
		Mat dst = new Mat();
		Imgproc.GaussianBlur(src, dst, new Size(3, 3), 0);
		
		Mat gray = new Mat();
		Imgproc.cvtColor(dst, gray, Imgproc.COLOR_BGR2GRAY);
		
		Mat xgrad = new Mat();
		Imgproc.Sobel(gray, xgrad, CvType.CV_16S, 1, 0);
		
		Mat ygrad = new Mat();
		Imgproc.Sobel(gray, ygrad, CvType.CV_16S, 0, 1);
		//边缘提取
		Mat output = new Mat();
		Imgproc.Canny(xgrad, ygrad, output, 50, 150);
		
		//让提取边缘结果变彩色
		Mat edges = new Mat();
		src.copyTo(edges, output);
		
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_Canny_Output", edges);
		
		//使用OpenCV Canny API用法三
		/*Mat dst = new Mat();
		Imgproc.GaussianBlur(src, dst, new Size(3, 3), 0);
		
		Mat output = new Mat();
		Imgproc.Canny(dst, output, 50, 150);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_Canny_Output", output);*/
	}
}
