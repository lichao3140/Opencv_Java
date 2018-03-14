package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_9_Skeleton</p>
 * <p>Description:骨架提取</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018年3月14日 下午4:17:31
 */
public class OpenCV_34_Skeleton {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\house.png");
		if (src.empty()) {
			return;
		}
		
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_34_Skeleton", src);
		
		Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2GRAY);
		Imgproc.threshold(src, src, 1, 255,Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
		
		Mat dst = src.clone();
		
		int k = 0;//腐蚀至消失的次数
		Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_CROSS, new Size(3, 3));
		Mat res = null;//骨架操作的结果
		do {
			Mat dst2 = new Mat();
			Imgproc.morphologyEx(dst, dst2, Imgproc.MORPH_OPEN, element);
			Mat tmp = new Mat();
			Core.subtract(dst, dst2, tmp);
			if (res == null) {
				res = tmp;
			} else {
				Core.add(tmp, res, res);
			}
			k++;
			Imgproc.erode(src, dst, element, new Point(-1, -1), k);
		} while (Core.countNonZero(dst) > 0);
		
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_34_Skeleton", dst);
		src.release();
		dst.release();
	}
}
