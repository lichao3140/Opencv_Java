package com.lichao;

import java.util.Random;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_8_Gaussian</p>
 * <p>Description: 高斯模糊</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018年1月15日 上午10:32:32
 */
public class OpenCV_8_Gaussian {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\lena.png");
		if (src.empty()) {
			return;
		}
		
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_8_Input", src);
		
		int width = src.cols();
		int height = src.rows();
		int dims = src.channels();
		byte[] data = new byte[width * height * dims];
		src.get(0, 0, data);
		int r = 0, g = 0, b = 0;
		Random random = new Random();
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				//高斯模糊加的值
				double rf = random.nextGaussian() * 30;
				double gf = random.nextGaussian() * 30;
				double bf = random.nextGaussian() * 30;
				
				b = data[row * width * dims + col * dims] & 0xff;
				g = data[row * width * dims + col * dims + 1] & 0xff;
				r = data[row * width * dims + col * dims + 2] & 0xff;
				b = clamp(b + bf);
				r = clamp(r + rf);
				g = clamp(g + gf);
				
				//加上高斯噪声
				data[row * width * dims + col * dims] = (byte)b;
				data[row * width * dims + col * dims + 1] = (byte)g;
				data[row * width * dims + col * dims + 2] =(byte)r;
			}
		}
		
		src.put(0, 0, data);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_Gaussian_Noise", src);
		
		//高斯去噪
		Mat dst = new Mat();
		//高斯滤波，调整Size(5, 5)值
		Imgproc.GaussianBlur(src, dst, new Size(5, 5), 15);
		ImageUI outBlur = new ImageUI();
		outBlur.imshow("OpenCV_Gaussian_Blur", dst);
	}
	
	/**
	 * 把值控制在0~255之间
	 * @param d
	 * @return
	 */
	public static int clamp(double d) {
		if (d > 255) {
			return 255;
		} else if (d < 0) {
			return 0;
		} else {
			return (int)d;
		}
	}
}
