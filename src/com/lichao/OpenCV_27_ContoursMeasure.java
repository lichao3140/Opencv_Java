package com.lichao;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_27_ContoursMeasure</p>
 * <p>Description:对象测量</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018年1月16日 下午8:07:40
 */
public class OpenCV_27_ContoursMeasure {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\contours.png");//输入图像
		if (src.empty()) {
			return;
		}
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_27_Input", src);
		
		MeasureObject(src);
	}
	
	/**
	 * 对象测量
	 * @param src
	 */
	public static void MeasureObject(Mat src) {
		Mat gray = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
		
		//双边模糊
		Mat dst = new Mat();
		Imgproc.bilateralFilter(gray, dst, 0, 50, 5);
		//边缘提取
		Mat edges = new Mat();
		Imgproc.Canny(dst, edges, 30, 200);
		
		ImageUI ui_binary = new ImageUI();
		ui_binary.imshow("OpenCV_Binary", edges);
		
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Mat hierarchy = new Mat();
		Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE, new Point(0, 0));
		
		//设置数字为十进制显示格式，保留后面小数点两位
		NumberFormat nf = new DecimalFormat("###.##");
		Mat result = src.clone();
		for (int i = 0; i < contours.size(); i++) {
			//测量周长
			double arclength = Imgproc.arcLength(new MatOfPoint2f(contours.get(i).toArray()), true);
			//测量面积
			double area = Imgproc.contourArea(contours.get(i));
			//计算几何距
			Moments mm = Imgproc.moments(contours.get(i));
			int cx = (int)(mm.m10 / mm.m00);
			int cy = (int)(mm.m01 / mm.m00);
			//画出中心点的位置（垂心）, -1为实心
			Imgproc.circle(result, new Point(cx, cy), 2, new Scalar(0, 255, 255), -1);
			
			//中文会乱码
			String text = "Arc:" + nf.format(arclength) + ",Area:" + nf.format(area);
			//描绘轮廓
			Imgproc.drawContours(result, contours, i, new Scalar(0, 0, 255), 2);
			//描绘文字到图像上
			int[] pt = new int[2];
			contours.get(i).get(0, 0, pt);
			Imgproc.putText(result, text, new Point(pt[0], pt[1]), Core.FONT_HERSHEY_PLAIN, 1, new Scalar(255, 0, 0));
			
			//多边形拟合---根据边寻找
			MatOfPoint2f approxCurve = new MatOfPoint2f();
			//得到闭合多边形
			Imgproc.approxPolyDP(new MatOfPoint2f(contours.get(i).toArray()), approxCurve, 4, true);
			System.out.println(approxCurve.rows());
			if (approxCurve.rows() == 6) {//得到四边形
				Imgproc.drawContours(result, contours, i, new Scalar(0, 255, 0), 4);
			}
			
		}
		
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_MeasureObject", result);
	}
}
