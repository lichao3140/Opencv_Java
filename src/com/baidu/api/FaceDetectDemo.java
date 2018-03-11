package com.baidu.api;

import java.net.URLEncoder;
import com.baidu.aip.util.Base64Util;
import com.util.FileUtil;
import com.util.HttpUtil;

/**
* 人脸探测
*/
public class FaceDetectDemo {

    /**
    * 重要提示代码中所需工具类
    * FileUtil,Base64Util,HttpUtil,GsonUtils请从
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * 下载
    */
    public static String detect() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v1/detect";
        try {
            // 本地文件路径
            String filePath = ".\\img\\tangwei.png";
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "max_face_num=" + 5 + "&face_fields=" + "age,beauty,expression,faceshape,gender,glasses,landmark,race,qualities" + "&image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            //String accessToken = "24.d182a312a98ae340a60587ff6c51c6f5.2592000.1523363473.282335-10910534";
            String accessToken = AuthService.getAuth();
            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

 //返回参数api
//    log_id	uint64	是	日志id
//    result_num	uint32	是	人脸数目
//    result	object[]	是	人脸属性对象的集合
//    +age	double	否	年龄。face_fields包含age时返回
//    +beauty	double	否	美丑打分，范围0-100，越大表示越美。face_fields包含beauty时返回
//    +location	object	是	人脸在图片中的位置
//    ++left	uint32	是	人脸区域离左边界的距离
//    ++top	uint32	是	人脸区域离上边界的距离
//    ++width	uint32	是	人脸区域的宽度
//    ++height	uint32	是	人脸区域的高度
//    +face_probability	double	是	人脸置信度，范围0-1
//    +rotation_angle	int32	是	人脸框相对于竖直方向的顺时针旋转角，[-180,180]
//    +yaw	double	是	三维旋转之左右旋转角[-90(左), 90(右)]
//    +pitch	double	是	三维旋转之俯仰角度[-90(上), 90(下)]
//    +roll	double	是	平面内旋转角[-180(逆时针), 180(顺时针)]
//    +expression	uint32	否	表情，0，不笑；1，微笑；2，大笑。face_fields包含expression时返回
//    +expression_probability	double	否	表情置信度，范围0~1。face_fields包含expression时返回
//    +faceshape	object[]	否	脸型置信度。face_fields包含faceshape时返回
//    ++type	string	是	脸型：square/triangle/oval/heart/round
//    ++probability	double	是	置信度：0~1
//    +gender	string	否	male、female。face_fields包含gender时返回
//    +gender_probability	double	否	性别置信度，范围[0~1]，face_fields包含gender时返回
//    +glasses	uint32	否	是否带眼镜，0-无眼镜，1-普通眼镜，2-墨镜。face_fields包含glasses时返回
//    +glasses_probability	double	否	眼镜置信度，范围[0~1]，face_fields包含glasses时返回
//    +landmark	object[]	否	4个关键点位置，左眼中心、右眼中心、鼻尖、嘴中心。face_fields包含landmark时返回
//    ++x	uint32	否	x坐标
//    ++y	uint32	否	y坐标
//    +landmark72	object[]	否	72个特征点位置，face_fields包含landmark时返回
//    ++x	uint32	否	x坐标
//    ++y	uint32	否	y坐标
//    +race	string	否	yellow、white、black、arabs。face_fields包含race时返回
//    +race_probability	double	否	人种置信度，范围[0~1]，face_fields包含race时返回
//    +qualities	object	否	人脸质量信息。face_fields包含qualities时返回
//    ++occlusion	object	是	人脸各部分遮挡的概率，范围[0~1]，0表示完整，1表示不完整
//    +++left_eye	double	是	左眼遮挡比例
//    +++right_eye	double	是	右眼遮挡比例
//    +++nose	double	是	鼻子遮挡比例
//    +++mouth	double	是	嘴巴遮挡比例
//    +++left_cheek	double	是	左脸颊遮挡比例
//    +++right_cheek	double	是	右脸颊遮挡比例
//    +++chin	double	是	下巴遮挡比例
//    ++blur	double	是	人脸模糊程度，范围[0~1]，0表示清晰，1表示模糊
//    ++illumination	-	是	取值范围在[0~255],表示脸部区域的光照程度
//    ++completeness	-	是	人脸完整度，0或1, 0为人脸溢出图像边界，1为人脸都在图像边界内
//    ++type	object	是	真实人脸/卡通人脸置信度
//    +++human	-	是	真实人脸置信度，[0~1]，大于0.5可以判断为人脸
//    +++cartoon	-	是	卡通人脸置信度，[0~1]

 
    public static void main(String[] args) {
//        FaceDetect.detect();
//   {"result_num":1,"result":[{"location":{"left":77,"top":144,"width":149,"height":154},"face_probability":1,"rotation_angle":1,"yaw":-0.45118400454521,"pitch":5.3421592712402,"roll":1.474733710289,"landmark":[{"x":115,"y":169},{"x":185,"y":171},{"x":150,"y":212},{"x":149,"y":249}],"landmark72":[{"x":76,"y":170},{"x":78,"y":194},{"x":81,"y":219},{"x":87,"y":244},{"x":100,"y":270},{"x":123,"y":292},{"x":148,"y":300},{"x":173,"y":294},{"x":197,"y":273},{"x":212,"y":247},{"x":218,"y":222},{"x":223,"y":198},{"x":225,"y":173},{"x":99,"y":171},{"x":106,"y":166},{"x":114,"y":165},{"x":123,"y":167},{"x":130,"y":174},{"x":122,"y":175},{"x":114,"y":176},{"x":105,"y":174},{"x":115,"y":169},{"x":88,"y":155},{"x":98,"y":145},{"x":111,"y":144},{"x":123,"y":147},{"x":134,"y":155},{"x":122,"y":155},{"x":111,"y":153},{"x":99,"y":153},{"x":170,"y":175},{"x":178,"y":169},{"x":186,"y":167},{"x":193,"y":168},{"x":200,"y":174},{"x":194,"y":176},{"x":186,"y":178},{"x":178,"y":177},{"x":185,"y":171},{"x":166,"y":157},{"x":177,"y":149},{"x":188,"y":147},{"x":200,"y":148},{"x":210,"y":158},{"x":200,"y":157},{"x":189,"y":156},{"x":178,"y":157},{"x":140,"y":175},{"x":137,"y":190},{"x":134,"y":205},{"x":129,"y":220},{"x":139,"y":221},{"x":160,"y":221},{"x":170,"y":221},{"x":166,"y":205},{"x":163,"y":191},{"x":160,"y":176},{"x":150,"y":212},{"x":122,"y":248},{"x":135,"y":241},{"x":149,"y":241},{"x":163,"y":242},{"x":174,"y":249},{"x":163,"y":257},{"x":148,"y":261},{"x":134,"y":257},{"x":135,"y":247},{"x":149,"y":248},{"x":162,"y":248},{"x":162,"y":250},{"x":149,"y":250},{"x":136,"y":249}],"age":31.849361419678,"beauty":58.011905670166,"expression":0,"expression_probablity":0.99998700618744,"faceshape":[{"type":"square","probability":0.10893177986145},{"type":"triangle","probability":0.0039200717583299},{"type":"oval","probability":0.73189455270767},{"type":"heart","probability":0.052113145589828},{"type":"round","probability":0.10314042121172}],"gender":"male","gender_probability":0.99999928474426,"glasses":0,"glasses_probability":0.99999630451202,"race":"yellow","race_probability":0.99998033046722,"qualities":{"occlusion":{"left_eye":0,"right_eye":0,"nose":0,"mouth":0,"left_cheek":0,"right_cheek":0,"chin":0},"blur":0,"illumination":0,"completeness":0,"type":{"human":0.99938035011292,"cartoon":0.00061963585903868}}}],"log_id":4130331474} 	
    	System.out.println(FaceDetectDemo.detect());
    }
}