package com.lichao.bean;

/**
 * 真实人脸/卡通人脸置信度
 */
public class Type {

    private double human;  //真实人脸置信度，[0~1]，大于0.5可以判断为人脸
    private double cartoon;  //卡通人脸置信度，[0~1]
    public void setHuman(double human) {
         this.human = human;
     }
     public double getHuman() {
         return human;
     }

    public void setCartoon(double cartoon) {
         this.cartoon = cartoon;
     }
     public double getCartoon() {
         return cartoon;
     }

}