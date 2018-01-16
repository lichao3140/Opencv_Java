package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

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
		
		//算子，有负数选着CV_32SC1类型
		Mat kernel = new Mat(3, 3, CvType.CV_32SC1);
		int[] data = new int[]{0, 1, 0, 1, -4, 1, 0, 1, 0};
		
		
	}
}
