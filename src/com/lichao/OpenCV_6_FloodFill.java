package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_6_FloodFill</p>
 * <p>Description: ROI区域与泛洪填充</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018年1月14日 下午9:19:03
 */
public class OpenCV_6_FloodFill {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\test.png");
		if (src.empty()) {
			return;
		}
		
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_6_Input", src);
		
		/*Rect rect = new Rect(120, 40, 200, 200);
		Mat roi = src.submat(rect);
		Mat gray = Mat.zeros(roi.size(), roi.type());
		gray.setTo(new Scalar(50, 50, 50));
		Core.add(roi, gray, roi);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_roi_Output", roi);*/
		
		/*Rect rect = new Rect(100, 300, 200, 200);
		Mat roi = Imgcodecs.imread(".\\img\\roi.png");
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_roi_Output", roi);
		
		Mat roi1 = src.submat(rect);
		Core.addWeighted(roi, 0.3, roi1, 0.7, 0, roi);
		
		roi.copyTo(src.submat(rect));
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_6_Input", src);*/
		
		//泛洪填充
		//floodFill(Mat image, Mat mask, Point seedPoint, Scalar newVal)
		//不能对彩色图像进行填充，只能填充二值图像，先转换成灰度图像,再转成二值图像
		/*Mat gray = new Mat();
		Mat binary = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
		Imgproc.threshold(gray, binary, 127, 255, Imgproc.THRESH_BINARY);
		ImageUI binaryOut = new ImageUI();
		binaryOut.imshow("OpenCV_binary_Output", binary);
		
		Mat mask = Mat.zeros(src.rows() + 2, src.cols() + 2, CvType.CV_8UC1);
		Imgproc.cvtColor(binary, binary, Imgproc.COLOR_GRAY2BGR);
		Imgproc.floodFill(binary, mask, new Point(src.cols()/2, src.rows()/2), new Scalar(255, 0, 255));
		Imgproc.floodFill(binary, mask, new Point(2, 2), new Scalar(0, 255, 255));
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_floodFill_Output", binary);*/

		//可以对彩色图像进行填充
		//floodFill(Mat image, Mat mask, Point seedPoint, Scalar newVal, Rect rect, Scalar loDiff, Scalar upDiff, int flags)
		Rect rect = new Rect(1, 1, src.cols(), src.rows());
		Mat mask = Mat.zeros(src.rows() + 2, src.cols() + 2, CvType.CV_8UC1);
		Imgproc.floodFill(src, mask, new Point(src.cols()/2, src.rows()/2), new Scalar(255, 0, 255),
				rect, new Scalar(100, 100, 100), new Scalar(100, 100, 100), Imgproc.FLOODFILL_FIXED_RANGE);
		
		Imgproc.floodFill(src, mask, new Point(2, 2), new Scalar(255, 255, 0),
				rect, new Scalar(100, 100, 100), new Scalar(100, 100, 100), Imgproc.FLOODFILL_FIXED_RANGE);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_floodFill_Output", src);
	}
}
