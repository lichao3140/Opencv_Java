package com.lichao;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_18_Pyramid</p>
 * <p>Description: 图像金字塔</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018年1月16日 上午10:20:56
 */
public class OpenCV_18_Pyramid {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\test.png");//输入图像
		
		if (src.empty()) {
			return;
		}
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_18_Input", src);
		
		//金字塔设为3层
		int level = 3;
		Mat temp = src.clone();
		List<Mat> result = new ArrayList<Mat>();
		for (int i = 0; i < level; i++) {//大图变小
			Mat dst = new Mat();
			Imgproc.pyrDown(temp, dst);
			ImageUI out = new ImageUI();
			out.imshow("高斯金字塔" + i, dst);
			dst.copyTo(temp);
			result.add(dst);
		}
		
		for (int i = result.size() - 1; i >= 0; i--) {//小图变大
			Mat expand = new Mat();
			Mat lapalian = new Mat();
			if (i-1 < 0) {
				Imgproc.pyrUp(result.get(i), expand, src.size());
				Core.subtract(src, expand, lapalian);
				
				//为了观看结果而添加，实际操作不用添加
				Mat constant = new Mat(lapalian.size(), lapalian.type());
				constant.setTo(new Scalar(127, 127, 127));
				Core.add(constant, lapalian, lapalian);
				
				ImageUI out = new ImageUI();
				out.imshow("拉普拉斯金字塔" + i, lapalian);
			} else {
				Imgproc.pyrUp(result.get(i), expand, result.get(i-1).size());
				Core.subtract(result.get(i-1), expand, lapalian);
				
				//为了观看结果而添加，实际操作不用添加
				Mat constant = new Mat(lapalian.size(), lapalian.type());
				constant.setTo(new Scalar(127, 127, 127));
				Core.add(constant, lapalian, lapalian);
				
				ImageUI out = new ImageUI();
				out.imshow("拉普拉斯金字塔" + i, lapalian);
			}
			
		}
	}
}
