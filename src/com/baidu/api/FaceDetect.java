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
	
    public static String detect(String filePath) {
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
            
           
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
    	FaceDetect.detect(".\\img\\tangwei.png");
	}
}