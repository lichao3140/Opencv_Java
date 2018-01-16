package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_30_MorePH</p>
 * <p>Description:其他形态学操作：顶帽、黑帽、基本梯度、内部梯度、外部梯度</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018年1月16日 下午11:13:47
 */
public class OpenCV_30_MorePH {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\test.png");//输入图像，形态学的梯度也适用于彩色图像
		if (src.empty()) {
			return;
		}
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_30_Input", src);
		
		Mat gray = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
		
		Mat binary = new Mat();
		//自动阈值二值化
		Imgproc.threshold(gray, binary, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
		
		//顶帽操作：开操作与原图像的差值
//		Mat dst = new Mat();
//		Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(15, 15));
//		Imgproc.morphologyEx(binary, dst, Imgproc.MORPH_TOPHAT, kernel);
		
		//黑帽操作：闭操作与原图像的差值
//		Mat dst = new Mat();
//		Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(15, 15));
//		Imgproc.morphologyEx(binary, dst, Imgproc.MORPH_BLACKHAT, kernel);
		
//		ImageUI out = new ImageUI();
//		out.imshow("OpenCV_Out_Output", dst);
		
		//基本梯度：膨胀后的图像减去腐蚀后的图像得到差值图像(可以用彩色图像)
//		Mat dst = new Mat();
//		Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
//		Imgproc.morphologyEx(src, dst, Imgproc.MORPH_GRADIENT, kernel);
		
		//内部梯度：原图像减去腐蚀之后的图像得到差值图像
//		Mat inter = new Mat();
//		Mat dst = new Mat();
//		Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
//		Imgproc.erode(src, dst, kernel);
//		Core.subtract(src, dst, inter);
		
		//外部梯度：图像膨胀之后再减去原来的图像得到的差值图像
		Mat exter = new Mat();
		Mat dst = new Mat();
		Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
		Imgproc.dilate(src, dst, kernel);
		Core.subtract(dst, src, exter);
		
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_Out_Output", exter);
	}
}
