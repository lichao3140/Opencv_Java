package com.lichao.bean;

/**
 * 人脸在图片中的位置
 */
public class Location {

    private int left;  //人脸区域离左边界的距离 
    private int top;  //人脸区域离上边界的距离
    private int width;  //人脸区域的宽度 
    private int height;  //人脸区域的高度 
    public void setLeft(int left) {
         this.left = left;
     }
     public int getLeft() {
         return left;
     }

    public void setTop(int top) {
         this.top = top;
     }
     public int getTop() {
         return top;
     }

    public void setWidth(int width) {
         this.width = width;
     }
     public int getWidth() {
         return width;
     }

    public void setHeight(int height) {
         this.height = height;
     }
     public int getHeight() {
         return height;
     }

}