package com.lichao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_31_WaterShed</p>
 * <p>Description:分水岭算法</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018年1月17日 上午8:29:55
 */
public class OpenCV_31_WaterShed {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		//1、输入图像
		Mat src = Imgcodecs.imread(".\\img\\cards.png");
		if (src.empty()) {
			return;
		}
		ImageUI ui = new ImageUI();
		ui.imshow("原始图像", src);
		
		//去背景颜色---黑色背景颜色就不用去背景
		int width = src.cols();
		int heght = src.rows();
		int dims = src.channels();
		byte[] data = new byte[width * heght * dims];
		src.get(0, 0, data);
		int index = 0;
		int r = 0, g = 0, b = 0;
		for (int row = 0; row < heght; row++) {
			for (int col = 0; col < width; col++) {
				index = row * width * dims + col * dims;
				b = data[index]&0xff;
				g = data[index + 1]&0xff;
				r = data[index + 2]&0xff;
				//把白色背景变为黑色
				if (b == 255 && g ==255 && r == 255) {
					data[index] = (byte)0;
					data[index + 1] = (byte)0;
					data[index + 2] = (byte)0;
				}
			}
		}
		src.put(0, 0, data);
		ImageUI background = new ImageUI();
		background.imshow("去背景", src);
		
		//2、灰度
		Mat gray = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
		ImageUI ui_gray = new ImageUI();
		ui_gray.imshow("灰度图像", gray);
		
		//3、二值图像
		Mat binary = new Mat();
		//自动阈值二值化
		Imgproc.threshold(gray, binary, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
		ImageUI ui_binary = new ImageUI();
		ui_binary.imshow("二值化图像", binary);
		
		//4、距离变换
		Mat dist = new Mat();
		Imgproc.distanceTransform(binary, dist, Imgproc.DIST_L2, 3);
		Core.normalize(dist, dist, 0, 255, Core.NORM_MINMAX);
		Mat dist_8u = new Mat();
		//转换为彩色图像
		dist.convertTo(dist_8u, CvType.CV_8U);
		ImageUI ui_dist = new ImageUI();
		ui_dist.imshow("距离变换后的图像", dist_8u);
		
		//5、寻找种子
		//二值化,去除尖锐边角和噪声(0.4~1.0)*255范围进行二值化
		Imgproc.threshold(dist, dist, 102, 255, Imgproc.THRESH_BINARY);
		//形态学操作
		Mat erode = new Mat();
		//获得腐蚀算子，不同的输入图像，要调整Size()函数的值
		Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(13, 13));
		//腐蚀
		Imgproc.erode(dist, erode, kernel);
		Mat erode_8u = new Mat();
        erode.convertTo(erode_8u, CvType.CV_8U);
        ImageUI ui_erode = new ImageUI();
        ui_erode.imshow("腐蚀后的图像", erode_8u);
		
		//6、生成Marker
        //轮廓查找
  		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
  		Mat hierarchy = new Mat();
  		Imgproc.findContours(erode_8u, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE, new Point(0, 0));
  		Mat markers = Mat.zeros(dist.size(), CvType.CV_32SC1);
  		//随机颜色数组
  		Scalar[] colors = new Scalar[contours.size()];
  		Random random = new Random();
  		for (int i = 0; i < contours.size(); i++) {
  			//把Marker绘制上
  			Imgproc.drawContours(markers, contours, i, new Scalar(i+1), -1);
  			colors[i] = new Scalar(random.nextInt(255), random.nextInt(255), random.nextInt(255));
  		}
  		Imgproc.circle(markers, new Point(5, 5), 3, new Scalar(255), -1);
        
		//7、分水岭变换
		Imgproc.watershed(src, markers);
		//显示结果
		Mat mark = Mat.zeros(markers.size(), CvType.CV_8UC1);
		markers.convertTo(mark, CvType.CV_8U);
		Core.bitwise_not(mark, mark);
		ImageUI out = new ImageUI();
		out.imshow("分水岭变换", mark);
		
		//填色
		Mat dst = new Mat(src.size(), CvType.CV_8UC3);
		int[] idxv = new int[1];
		for (int row = 0; row < markers.rows(); row++) {
			for (int col = 0; col < markers.cols(); col++) {
				markers.get(row, col, idxv);
				if (idxv[0] > 0 && idxv[0] <= contours.size()) {
					double[] rgb = colors[idxv[0] - 1].val;
					dst.put(row, col, new byte[]{(byte)rgb[0], (byte)rgb[1], (byte)rgb[2]});
				} else {
					dst.put(row, col, new byte[]{(byte)0, (byte)0, (byte)0});
				}
			}
		}
		ImageUI fout = new ImageUI();
		fout.imshow("分割边缘的图像", dst);
	}
}
