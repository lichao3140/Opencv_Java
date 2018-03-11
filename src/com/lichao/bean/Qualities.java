package com.lichao.bean;

/**
 * 人脸质量信息
 */
public class Qualities {

    private Occlusion occlusion;  //人脸各部分遮挡的概率，范围[0~1]，0表示完整，1表示不完整 
    private int blur;  //人脸模糊程度，范围[0~1]，0表示清晰，1表示模糊 
    private int illumination;  //取值范围在[0~255],表示脸部区域的光照程度 
    private int completeness;  //人脸完整度，0或1, 0为人脸溢出图像边界，1为人脸都在图像边界内 
    private Type type;  //真实人脸/卡通人脸置信度
    public void setOcclusion(Occlusion occlusion) {
         this.occlusion = occlusion;
     }
     public Occlusion getOcclusion() {
         return occlusion;
     }

    public void setBlur(int blur) {
         this.blur = blur;
     }
     public int getBlur() {
         return blur;
     }

    public void setIllumination(int illumination) {
         this.illumination = illumination;
     }
     public int getIllumination() {
         return illumination;
     }

    public void setCompleteness(int completeness) {
         this.completeness = completeness;
     }
     public int getCompleteness() {
         return completeness;
     }

    public void setType(Type type) {
         this.type = type;
     }
     public Type getType() {
         return type;
     }

}