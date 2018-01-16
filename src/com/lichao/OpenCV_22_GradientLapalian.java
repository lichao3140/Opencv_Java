package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_22_GradientLapalian</p>
 * <p>Description: 图像梯度二阶倒数(拉普拉斯算子)</p>
 * <p>Company:</p>
 * @author Administrator
 * @date 2018年1月16日 下午3:00:31
 */
public class OpenCV_22_GradientLapalian {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\test.png");//输入图像
		
		if (src.empty()) {
			return;
		}
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_22_Input", src);
		
		//定义拉普拉斯算子---两条不同的算子，有负数选着CV_32F类型
		Mat kernel = new Mat(3, 3, CvType.CV_32F);
		float[] data = new float[]{0, 1, 0, 1, -4, 1, 0, 1, 0};
		//float[] data = new float[]{1, 1, 1, 1, -8, 1, 1, 1, 1};
		kernel.put(0, 0, data);
		
		Mat dst = new Mat();
//		Imgproc.filter2D(src, dst, CvType.CV_32F, kernel);
//		Core.convertScaleAbs(dst, dst);
		//OpenCV API的拉普拉斯方式
		Imgproc.Laplacian(src, dst, CvType.CV_32F, 3, 1.0, 0);
		
		Mat image = new Mat();
		Core.normalize(dst, dst, 0, 255, Core.NORM_MINMAX);
		dst.convertTo(image, CvType.CV_8UC3);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_Lapalian_Output", image);
		
		image.release();
	}
}
