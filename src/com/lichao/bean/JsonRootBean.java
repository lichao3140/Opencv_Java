package com.lichao.bean;

import java.util.List;

/**
 * <p>Title: Result</p>
 * <p>Description: BAIDU平台返回结果</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018年3月11日 下午9:37:46
 */
public class JsonRootBean {

    private int result_num; //人脸数目
    private List<Result> result;
    private long log_id;
    public void setResult_num(int result_num) {
         this.result_num = result_num;
     }
     public int getResult_num() {
         return result_num;
     }

    public void setResult(List<Result> result) {
         this.result = result;
     }
     public List<Result> getResult() {
         return result;
     }

    public void setLog_id(long log_id) {
         this.log_id = log_id;
     }
     public long getLog_id() {
         return log_id;
     }

}