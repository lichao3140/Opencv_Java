package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;

import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_5_MatMath</p>
 * <p>Description: MAT像素算术运算和逻辑</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018年1月14日 下午8:21:57
 */
public class OpenCV_5_MatMath {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\test.png");
		if (src.empty()) {
			return;
		}
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_5_Input", src);
		
		//创建一张图像
		Mat blackImg = Mat.zeros(src.size(), src.type());
		
		//亮度调节：相加--add  相减--subtract
		/*Mat dst = new Mat();
		blackImg.setTo(new Scalar(30, 30, 30));
		//Core.add(src, blackImg, dst);
		Core.subtract(src, blackImg, dst);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_Add_Output", dst);*/
		
		//对比度：相乘--multiply  相除--divide
		/*Mat dst = new Mat();
		blackImg.setTo(new Scalar(2, 2, 2));
		//Core.multiply(src, blackImg, dst);
		Core.divide(src, blackImg, dst);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_multiply_Output", dst);*/
		
		//权重加减：参数调节实现不同效果
		/*Mat dst = new Mat();
		blackImg.setTo(new Scalar(2, 2, 2));
		Core.addWeighted(src, 0.5, blackImg, 0.5, 0.0, dst, src.type());
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_Weight_Output", dst);*/
		
		/*Mat mask = Mat.zeros(src.size(), src.type());
		Rect rect = new Rect(120, 80, 200, 200);
		mask.submat(rect).setTo(new Scalar(255, 255, 255));*/
		
		//与操作：bitwise_and
		/*Mat dst = new Mat();
		Core.bitwise_and(src, mask, dst);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_bitwise_Output", dst);*/
		
		//或操作：bitwise_or
		/*Mat dst = new Mat();
		Core.bitwise_or(src, mask, dst);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_bitwise_Output", dst);
		
		//或操作：bitwise_or
		Mat dst = new Mat();
		Core.bitwise_not(src, dst);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_bitwise_Output", dst);*/
		
		Mat mask = Mat.zeros(src.size(), CvType.CV_8UC1);
		Rect rect = new Rect(120, 80, 200, 200);
		mask.submat(rect).setTo(new Scalar(255, 255, 255));
		
		/*Mat dst = new Mat();
		Core.bitwise_not(src, dst, mask);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_bitwise_Output", dst);*/
		
		Mat red = new Mat(src.size(), src.type());
		red.setTo(new Scalar(0, 0, 255));
		Mat dst = new Mat();
		Core.bitwise_and(src, red, dst, mask);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_bitwise_Output", dst);
	}
}
