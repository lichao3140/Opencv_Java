package com.lichao.bean;

import java.util.List;

/**
 * <p>Title: Result</p>
 * <p>Description: BAIDU平台返回人脸信息结果</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018年3月11日 下午9:37:46
 */
public class Result {

    private Location location;  //人脸在图片中的位置
    private int face_probability;  //人脸置信度，范围0-1
    private int rotation_angle;  //人脸框相对于竖直方向的顺时针旋转角，[-180,180] 
    private double yaw;  //三维旋转之左右旋转角[-90(左), 90(右)] 
    private double pitch;  //三维旋转之俯仰角度[-90(上), 90(下)] 
    private double roll;  //平面内旋转角[-180(逆时针), 180(顺时针)] 
    private List<Landmark> landmark;  //4个关键点位置，左眼中心、右眼中心、鼻尖、嘴中心。face_fields包含landmark时返回
    private List<Landmark72> landmark72;  // 72个特征点位置，face_fields包含landmark时返回 
    private double age;  //年龄。face_fields包含age时返回 
    private double beauty;  //美丑打分，范围0-100，越大表示越美。face_fields包含beauty时返回
    private int expression;  //表情，0，不笑；1，微笑；2，大笑。face_fields包含expression时返回 
    private double expression_probablity;  //表情置信度，范围0~1。face_fields包含expression时返回 
    private List<Faceshape> faceshape;  //脸型置信度。face_fields包含faceshape时返回 
    private String gender;  //male、female。face_fields包含gender时返回 
    private double gender_probability;  //性别置信度，范围[0~1]，face_fields包含gender时返回 
    private int glasses;  //是否带眼镜，0-无眼镜，1-普通眼镜，2-墨镜。face_fields包含glasses时返回 
    private double glasses_probability;  //眼镜置信度，范围[0~1]，face_fields包含glasses时返回 
    private String race;  //yellow、white、black、arabs。face_fields包含race时返回 
    private double race_probability;  //人种置信度，范围[0~1]，face_fields包含race时返回 
    private Qualities qualities;  //face_fields包含qualities时返回 
    public void setLocation(Location location) {
         this.location = location;
     }
     public Location getLocation() {
         return location;
     }

    public void setFace_probability(int face_probability) {
         this.face_probability = face_probability;
     }
     public int getFace_probability() {
         return face_probability;
     }

    public void setRotation_angle(int rotation_angle) {
         this.rotation_angle = rotation_angle;
     }
     public int getRotation_angle() {
         return rotation_angle;
     }

    public void setYaw(double yaw) {
         this.yaw = yaw;
     }
     public double getYaw() {
         return yaw;
     }

    public void setPitch(double pitch) {
         this.pitch = pitch;
     }
     public double getPitch() {
         return pitch;
     }

    public void setRoll(double roll) {
         this.roll = roll;
     }
     public double getRoll() {
         return roll;
     }

    public void setLandmark(List<Landmark> landmark) {
         this.landmark = landmark;
     }
     public List<Landmark> getLandmark() {
         return landmark;
     }

    public void setLandmark72(List<Landmark72> landmark72) {
         this.landmark72 = landmark72;
     }
     public List<Landmark72> getLandmark72() {
         return landmark72;
     }

    public void setAge(double age) {
         this.age = age;
     }
     public double getAge() {
         return age;
     }

    public void setBeauty(double beauty) {
         this.beauty = beauty;
     }
     public double getBeauty() {
         return beauty;
     }

    public void setExpression(int expression) {
         this.expression = expression;
     }
     public int getExpression() {
         return expression;
     }

    public void setExpression_probablity(double expression_probablity) {
         this.expression_probablity = expression_probablity;
     }
     public double getExpression_probablity() {
         return expression_probablity;
     }

    public void setFaceshape(List<Faceshape> faceshape) {
         this.faceshape = faceshape;
     }
     public List<Faceshape> getFaceshape() {
         return faceshape;
     }

    public void setGender(String gender) {
         this.gender = gender;
     }
     public String getGender() {
         return gender;
     }

    public void setGender_probability(double gender_probability) {
         this.gender_probability = gender_probability;
     }
     public double getGender_probability() {
         return gender_probability;
     }

    public void setGlasses(int glasses) {
         this.glasses = glasses;
     }
     public int getGlasses() {
         return glasses;
     }

    public void setGlasses_probability(double glasses_probability) {
         this.glasses_probability = glasses_probability;
     }
     public double getGlasses_probability() {
         return glasses_probability;
     }

    public void setRace(String race) {
         this.race = race;
     }
     public String getRace() {
         return race;
     }

    public void setRace_probability(double race_probability) {
         this.race_probability = race_probability;
     }
     public double getRace_probability() {
         return race_probability;
     }

    public void setQualities(Qualities qualities) {
         this.qualities = qualities;
     }
     public Qualities getQualities() {
         return qualities;
     }
}