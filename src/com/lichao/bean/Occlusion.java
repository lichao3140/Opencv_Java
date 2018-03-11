package com.lichao.bean;

/**
 * ÈËÁ³¸÷²¿·ÖÕÚµ²µÄ¸ÅÂÊ
 */
public class Occlusion {

    private int left_eye;  //×óÑÛÕÚµ²±ÈÀı 
    private int right_eye;  //ÓÒÑÛÕÚµ²±ÈÀı 
    private int nose;  //±Ç×ÓÕÚµ²±ÈÀı
    private int mouth;  //×ì°ÍÕÚµ²±ÈÀı
    private int left_cheek;  //×óÁ³¼ÕÕÚµ²±ÈÀı 
    private int right_cheek;  //ÓÒÁ³¼ÕÕÚµ²±ÈÀı
    private int chin;  //ÏÂ°ÍÕÚµ²±ÈÀı
    public void setLeft_eye(int left_eye) {
         this.left_eye = left_eye;
     }
     public int getLeft_eye() {
         return left_eye;
     }

    public void setRight_eye(int right_eye) {
         this.right_eye = right_eye;
     }
     public int getRight_eye() {
         return right_eye;
     }

    public void setNose(int nose) {
         this.nose = nose;
     }
     public int getNose() {
         return nose;
     }

    public void setMouth(int mouth) {
         this.mouth = mouth;
     }
     public int getMouth() {
         return mouth;
     }

    public void setLeft_cheek(int left_cheek) {
         this.left_cheek = left_cheek;
     }
     public int getLeft_cheek() {
         return left_cheek;
     }

    public void setRight_cheek(int right_cheek) {
         this.right_cheek = right_cheek;
     }
     public int getRight_cheek() {
         return right_cheek;
     }

    public void setChin(int chin) {
         this.chin = chin;
     }
     public int getChin() {
         return chin;
     }

}