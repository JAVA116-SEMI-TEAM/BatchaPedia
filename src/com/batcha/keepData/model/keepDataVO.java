package com.batcha.keepData.model;

public class keepDataVO {
   private int keepNo;
   private int memNo;
   private int mvNo;
   
   public keepDataVO() {
      super();
   }
   
   public keepDataVO(int keepNo, int memNo, int mvNo) {
      super();
      this.keepNo = keepNo;
      this.memNo = memNo;
      this.mvNo = mvNo;
   }
   
   public int getKeepNo() {
      return keepNo;
   }
   public void setKeepNo(int keepNo) {
      this.keepNo = keepNo;
   }
   public int getMemNo() {
      return memNo;
   }
   public void setMemNo(int memNo) {
      this.memNo = memNo;
   }
   public int getMvNo() {
      return mvNo;
   }
   public void setMvNo(int mvNo) {
      this.mvNo = mvNo;
   }
   
   @Override
   public String toString() {
      return "keepDataVO [keepNo=" + keepNo + ", memNo=" + memNo + ", mvNo=" + mvNo + "]";
   }
   
}