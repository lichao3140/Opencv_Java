package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_21_GradientSobel</p>
 * <p>Description:图像梯度一阶倒数(索贝尔算子)</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018年1月16日 下午2:26:59
 */
public class OpenCV_21_GradientSobel {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\test.png");//输入图像
		
		if (src.empty()) {
			return;
		}
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_21_Input", src);
		
		//使用Soble()和Scharr()函数都可以实现图像的一阶倒数，而且Scharr更为明显
		//求Soble X方向
		Mat xgrad = new Mat();
		Imgproc.Sobel(src, xgrad, CvType.CV_32F, 1, 0);
		Core.convertScaleAbs(xgrad, xgrad);
		Mat ximage = new Mat();
		//归一化
		Core.normalize(xgrad, xgrad, 0, 255, Core.NORM_MINMAX);
		xgrad.convertTo(ximage, CvType.CV_8UC3);
		ImageUI ui_xgrad = new ImageUI();
		ui_xgrad.imshow("OpenCV_xgrad_Output", ximage);
		
		//求Soble Y方向
		Mat ygrad = new Mat();
		Imgproc.Sobel(src, ygrad, CvType.CV_32F, 0, 1);
		Core.convertScaleAbs(ygrad, ygrad);
		
		Mat yimage = new Mat();
		Core.normalize(ygrad, ygrad, 0, 255, Core.NORM_MINMAX);
		ygrad.convertTo(yimage, CvType.CV_8UC3);
		ImageUI ui_ygrad = new ImageUI();
		ui_ygrad.imshow("OpenCV_ygrad_Output", yimage);
		
		//结果梯度图像
		Mat grad = new Mat();
		Core.add(xgrad, ygrad, grad);
		Core.normalize(grad, grad, 0, 255, Core.NORM_MINMAX);
		Mat xyImage = new Mat();
		grad.convertTo(xyImage, CvType.CV_8UC3);
		ImageUI ui_grad = new ImageUI();
		ui_grad.imshow("OpenCV_Gradient_Output", xyImage);
	}
}
