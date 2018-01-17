package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_32_FaceDetect</p>
 * <p>Description: 人脸检测</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018年1月17日 上午11:51:06
 */
public class OpenCV_32_FaceDetect {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\test.png");
		if (src.empty()) {
			return;
		}
		ImageUI ui = new ImageUI();
		ui.imshow("原始图像", src);
		
		CascadeClassifier detector = new CascadeClassifier();
		//特征数据路径
		String filePath = "F:\\opencv\\OpenCV3.2\\opencv\\build\\etc\\haarcascades\\haarcascade_frontalface_alt_tree.xml";
		//加载特征数据
		if (detector.load(filePath)) {
			MatOfRect faces = new MatOfRect();
			detector.detectMultiScale(src, faces, 1.1, 3, 0, new Size(30, 30), new Size(200, 200));
			Rect[] rects = faces.toArray();
			Mat resutl = src.clone();
			for (int i = 0; i < rects.length; i++) {
				Imgproc.rectangle(resutl, rects[i].tl(), rects[i].br(), new Scalar(0, 0, 255), 2);
			}
			ImageUI out = new ImageUI();
			out.imshow("人脸检测", resutl);
		}
	}
}
