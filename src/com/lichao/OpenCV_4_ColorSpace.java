package com.lichao;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_4_ColorSpace</p>
 * <p>Description: RGB色彩空间</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018年1月13日 下午10:14:57
 */
public class OpenCV_4_ColorSpace {
	
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\lena.png");//BGR
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_4_Input", src);
		/*Mat dst = new Mat();
		//Imgproc.cvtColor(src, dst, Imgproc.COLOR_BGR2GRAY);//GRAY
		//Imgproc.cvtColor(src, dst, Imgproc.COLOR_BGR2HLS);//HLS
		//Imgproc.cvtColor(src, dst, Imgproc.COLOR_BGR2YUV);//YUV
		Imgproc.cvtColor(src, dst, Imgproc.COLOR_BGR2HSV);//HSV
		Mat binary = new Mat();
		//颜色值过滤inRange,过滤不同的颜色取不同值
		Core.inRange(src, new Scalar(30, 45, 45), new Scalar(180, 255, 255), binary);
		
		ImageUI output = new ImageUI();
		output.imshow("OpenCV_4_Output", binary);*/
		
		//通道分离  split
		List<Mat> mv = new ArrayList<Mat>();
		Core.split(src, mv);
		int index = 1;
		for (Mat m : mv) {
			if (index == 1) {
				m.setTo(new Scalar(0, 0, 0));//消除1通道
			}
			ImageUI output = new ImageUI();
			output.imshow("channel-" + index, m);
			index++;
		}
		
		//通道合并  merge
		/*Mat dst = new Mat(src.size(), src.type());
		Core.merge(mv, dst);
		ImageUI output_merge = new ImageUI();
		output_merge.imshow("merge_result", dst);*/
		
		//通道混淆mixChannels
		List<Mat> channels = new ArrayList<Mat>();
		channels.add(new Mat(src.size(), CvType.CV_8UC1));
		Core.mixChannels(mv, channels, new MatOfInt(2, 0));
		ImageUI output_mix = new ImageUI();
		output_mix.imshow("mix_result", channels.get(0));
	}
}
