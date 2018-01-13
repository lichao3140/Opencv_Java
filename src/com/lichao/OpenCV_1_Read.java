package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * 
 * <p>Title: OpenCV_1_Read </p>
 * <p>Description: 图像读取与保存</p>
 * <p>Company: </p>
 * @author LICHAO
 * @date 2018-1-10 下午7:10:14
 */
public class OpenCV_1_Read {

	public static void main(String[] args) {
		//加载jar包
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		//CV_LOAD_IMAGE_COLOR:彩色图像,三通道  CV_LOAD_IMAGE_GRAYSCALE:灰色图像,单通道
		Mat src = Imgcodecs.imread(".\\img\\lena.png", Imgcodecs.CV_LOAD_IMAGE_COLOR);
		//矩形定义
		Rect rect = new Rect(10, 10, 200, 200);
		//tl:左上角的点 br:右下角的点 scarlar:表示颜色
		Imgproc.rectangle(src, rect.tl(), rect.br(), new Scalar(0, 0, 255), 2, Imgproc.LINE_8, 0);
		//保存图像
		Imgcodecs.imwrite(".\\screenshot\\lena_resut.png", src);
	}
}
