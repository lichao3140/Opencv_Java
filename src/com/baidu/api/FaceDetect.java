package com.baidu.api;

import java.net.URLEncoder;
import org.opencv.core.Point;
import com.baidu.aip.util.Base64Util;
import com.google.gson.Gson;
import com.lichao.bean.JsonRootBean;
import com.lichao.bean.Result;
import com.util.FileUtil;
import com.util.HttpUtil;

/**
 * <p>Title: FaceDetect</p>
 * <p>Description:人脸检测</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018年3月11日 下午8:50:42
 */
public class FaceDetect {
	
    public static Point[] detect(String filePath) {
    	Point[] points;
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v1/detect";
        try {
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "max_face_num=" + 5 + "&face_fields=" + "age,beauty,expression,faceshape,gender,glasses,landmark,race,qualities" + "&image=" + imgParam;

            String accessToken = AuthService.getAuth();
            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            
            //GSON解析JSON
            Gson gson = new Gson();
            JsonRootBean jsonRootBean = gson.fromJson(result, JsonRootBean.class);
            int k72 = jsonRootBean.getResult().get(0).getLandmark72().size();
            points = new Point[k72];
            for(int i = 0; i < k72; i ++) {
//            	System.out.print("X" + i + ":" + jsonRootBean.getResult().get(0).getLandmark72().get(i).getX() + "\t");
//            	System.out.println("Y" + i + ":" + jsonRootBean.getResult().get(0).getLandmark72().get(i).getY());
            	double x = (double) jsonRootBean.getResult().get(0).getLandmark72().get(i).getX();
            	double y = (double) jsonRootBean.getResult().get(0).getLandmark72().get(i).getY();
            	points[i] = new Point(x, y);
            }
            return points;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
//    public static void main(String[] args) {
//    	FaceDetect.detect(".\\img\\tangwei.png");
//	}
}