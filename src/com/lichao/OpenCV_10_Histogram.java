package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import com.util.HistogramUtil;

/**
 * 
 * <p>Title: OpenCV_10_Histogram</p>
 * <p>Description: 多种类型直方图绘制</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018年1月15日 上午11:56:04
 */
public class OpenCV_10_Histogram {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\lena.png");
		if (src.empty()) {
			return;
		}
		//灰度直方图---矩形块
		//HistogramUtil.showGrayHistogramRectangle(src);
		//灰度直方图---描点
		//HistogramUtil.showGrayHistogramLine(src);
		//3个彩色直方图---矩形块
		HistogramUtil.showColorHistogram(src);
		//1个彩色直方图---描点
		HistogramUtil.showColorHistogramInOne(src);
	}
}
