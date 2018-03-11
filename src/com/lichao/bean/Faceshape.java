package com.lichao.bean;

/**
 * 脸型
 */
public class Faceshape {

    private String type;  //脸型：square/triangle/oval/heart/round 
    private double probability;  //置信度：0~1
    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

    public void setProbability(double probability) {
         this.probability = probability;
     }
     public double getProbability() {
         return probability;
     }

}