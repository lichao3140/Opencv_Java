package com.lichao;

import java.util.Arrays;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_17_BinaryBigImage</p>
 * <p>Description: 超大图像二值化</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018年1月16日 上午9:20:03
 */
public class OpenCV_17_BinaryBigImage {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\case9.jpg");//输入图像
		
		if (src.empty()) {
			return;
		}
		//图像变小
		Mat minSrc = new Mat();
		Imgproc.resize(src, minSrc, new Size(src.cols()/4, src.rows()/4));
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_17_Input", minSrc);
		
		Mat gray = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
		
		Mat dst = new Mat();
		//高斯自适应图像二值化
		Imgproc.adaptiveThreshold(gray, dst, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 55, 10);
		
		//运用分块处理
		int width = gray.cols();
		int height = gray.rows();
		int block = 256;
		Rect rect = new Rect();
		for (int y = 0; y < height; y+=block) {
			for (int x = 0; x < width; x+=block) {
				rect.x = x;
				rect.y = y;
				rect.width = block;
				rect.height = block;
				if ((rect.x + block) >= width) {
					rect.width = width - rect.x;
				}
				if ((rect.y + block) >= height) {
					rect.height = height - rect.y;
				}
				Mat roi = gray.submat(rect);
				//计算roi的均值
				MatOfDouble means = new MatOfDouble();
				MatOfDouble dev = new MatOfDouble();
				//标准方差
				Core.meanStdDev(roi, means, dev);
				double[] means1 = new double[1];
				means.get(0, 0, means1);
				double[] dev1 = new double[1];
				dev.get(0, 0, dev1);
				//输出均值
//				System.out.println("means:" + means1[0]);
//				System.out.println("dev:" + dev1[0]);
				//空白图像过滤，在二值化处理时候可以直接忽略
				if (dev1[0] < 10) {
					byte[] data = new byte[roi.rows() * roi.cols()];
					roi.get(0, 0, data);
					Arrays.fill(data, (byte)255);
					roi.put(0, 0, data);
					continue;
				}
				Imgproc.threshold(roi, dst, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
				dst.copyTo(roi);
			}
		}
		Imgcodecs.imwrite(".\\screenshot\\BinaryBigImage1_resut.png", gray);
	}
}
