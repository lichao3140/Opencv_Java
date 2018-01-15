package com.util;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 * 
 * <p>Title: HistogramUtil</p>
 * <p>Description: 绘制直方图工具类</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018年1月15日 下午1:50:22
 */
public class HistogramUtil {

	/**
	 * 绘制彩色图像灰度直方图---矩形
	 * @param src  原始图片
	 */
	public static void showGrayHistogramRectangle(Mat src) {
		Mat gray = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_GrayHistogram_Input", gray);
		
		List<Mat> images = new ArrayList<>();
		images.add(gray);
		Mat mask = Mat.ones(src.size(), CvType.CV_8UC1);
		Mat hist = new Mat();
		//统计直方图
		Imgproc.calcHist(images, new MatOfInt(0), mask, hist, new MatOfInt(256), new MatOfFloat(0, 255));
		//归一化
		Core.normalize(hist, hist, 0, 255, Core.NORM_MINMAX);
		//显示直方图
		int height = hist.rows();
		Mat plot = Mat.zeros(400, 600, src.type());
		float[] hisdata = new float[256];
		hist.get(0, 0, hisdata);
		int offsetX = 50;
		int offsetY = 350;
		Imgproc.line(plot, new Point(offsetX, 0), new Point(offsetX, offsetY), new Scalar(255, 255, 255));//Y
		Imgproc.line(plot, new Point(offsetX, offsetY), new Point(600, offsetY), new Scalar(255, 255, 255));//X
		for (int i = 0; i < height - 1; i++) {
			int y1 = (int)hisdata[i];
			//绘制矩形
			Rect rect = new Rect();
			rect.x = offsetX + i;
			rect.y = offsetY - y1;
			rect.width = 1;
			rect.height = y1;
			Imgproc.rectangle(plot, rect.tl(), rect.br(), new Scalar(0, 0, 255));
		}
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_GrayHistogramRectangle_OutPut", plot);
	} 
	
	/**
	 * 绘制彩色图像灰度直方图---描点
	 * @param src  原始图片
	 */
	public static void showGrayHistogramLine(Mat src) {
		Mat gray = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_GrayHistogram_Input", gray);
		
		List<Mat> images = new ArrayList<>();
		images.add(gray);
		Mat mask = Mat.ones(src.size(), CvType.CV_8UC1);
		Mat hist = new Mat();
		//统计直方图
		Imgproc.calcHist(images, new MatOfInt(0), mask, hist, new MatOfInt(256), new MatOfFloat(0, 255));
		//归一化
		Core.normalize(hist, hist, 0, 255, Core.NORM_MINMAX);
		//显示直方图
		int height = hist.rows();
		Mat plot = Mat.zeros(400, 600, src.type());
		float[] hisdata = new float[256];
		hist.get(0, 0, hisdata);
		int offsetX = 50;
		int offsetY = 350;
		Imgproc.line(plot, new Point(offsetX, 0), new Point(offsetX, offsetY), new Scalar(255, 255, 255));//Y
		Imgproc.line(plot, new Point(offsetX, offsetY), new Point(600, offsetY), new Scalar(255, 255, 255));//X
		for (int i = 0; i < height - 1; i++) {
			int y1 = (int)hisdata[i];
			int y2 = (int)hisdata[i + 1];
			//绘制描点
			Imgproc.line(plot, new Point(offsetX+i, offsetY-y1), new Point(offsetX+i+1, offsetY-y2), new Scalar(0, 0, 255));
		}
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_GrayHistogramLine_OutPut", plot);
	} 
	
	/**
	 * 绘制彩色图像彩色直方图---RGB三个分别画出来
	 * @param src
	 */
	public static void showColorHistogram(Mat src) {
		int offsetX = 50;
		int offsetY = 350;
		Scalar[] colorTab = new Scalar[]{new Scalar(255, 0, 0), new Scalar(0, 255, 0), new Scalar(0, 0, 255)};//三种颜色
		String[] colorArr = new String[]{"Blue", "Green", "Red"};
		int index = 0;
		List<Mat> mv = new ArrayList<>();
		//把三个通道的数据分别放到各自的Mat中
		Core.split(src, mv);
		for (Mat color : mv) {
			List<Mat> images = new ArrayList<>();
			images.add(color);
			Mat mask = Mat.ones(src.size(), CvType.CV_8UC1);
			Mat hist = new Mat();
			//统计直方图
			Imgproc.calcHist(images, new MatOfInt(0), mask, hist, new MatOfInt(256), new MatOfFloat(0, 255));
			//归一化
			Core.normalize(hist, hist, 0, 255, Core.NORM_MINMAX);
			//显示直方图
			Mat plot = Mat.zeros(400, 600, src.type());
			int height = hist.rows();
			float[] hisdata = new float[256];
			hist.get(0, 0, hisdata);
			Imgproc.line(plot, new Point(offsetX, 0), new Point(offsetX, offsetY), new Scalar(255, 255, 255));//Y
			Imgproc.line(plot, new Point(offsetX, offsetY), new Point(600, offsetY), new Scalar(255, 255, 255));//X
			for (int i = 0; i < height - 1; i++) {
				int y1 = (int)hisdata[i];
				//绘制矩形
				Rect rect = new Rect();
				//拉长步长 *2
				rect.x = offsetX + i * 2;
				rect.y = offsetY - y1;
				rect.width = 1;
				rect.height = y1;
				Imgproc.rectangle(plot, rect.tl(), rect.br(), colorTab[index]);
			}
			ImageUI out = new ImageUI();
			out.imshow("OpenCV_" + colorArr[index] + "_OutPut", plot);
			index++;
		}
	}
	
	/**
	 * 绘制彩色图像彩色直方图---RGB画在一个里面
	 * @param src
	 */
	public static void showColorHistogramInOne(Mat src) {
		Mat plot = Mat.zeros(400, 600, src.type());
		int offsetX = 50;
		int offsetY = 350;
		Imgproc.line(plot, new Point(offsetX, 0), new Point(offsetX, offsetY), new Scalar(255, 255, 255));//Y
		Imgproc.line(plot, new Point(offsetX, offsetY), new Point(600, offsetY), new Scalar(255, 255, 255));//X
		Scalar[] colorTab = new Scalar[]{new Scalar(255, 0, 0), new Scalar(0, 255, 0), new Scalar(0, 0, 255)};//三种颜色
		int index = 0;
		List<Mat> mv = new ArrayList<>();
		//把三个通道的数据分别放到各自的Mat中
		Core.split(src, mv);
		for (Mat color : mv) {
			List<Mat> images = new ArrayList<>();
			images.add(color);
			Mat mask = Mat.ones(src.size(), CvType.CV_8UC1);
			Mat hist = new Mat();
			//统计直方图
			Imgproc.calcHist(images, new MatOfInt(0), mask, hist, new MatOfInt(256), new MatOfFloat(0, 255));
			//归一化
			Core.normalize(hist, hist, 0, 255, Core.NORM_MINMAX);
			//显示直方图
			int height = hist.rows();
			float[] hisdata = new float[256];
			hist.get(0, 0, hisdata);
			for (int i = 0; i < height - 1; i++) {
				int y1 = (int)hisdata[i];
				int y2 = (int)hisdata[i + 1];
				//拉长步长*2
				int x1 = i * 2;
				int x2 = (i + 1) * 2;
				//绘制描点
				Imgproc.line(plot, new Point(offsetX+x1, offsetY-y1), new Point(offsetX+x2, offsetY-y2), colorTab[index]);
			}
			index++;
		}
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_GrayHistogramLine_OutPut", plot);
	}
	
	
}
