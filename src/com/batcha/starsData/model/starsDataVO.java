package com.batcha.starsData.model;

public class starsDataVO {
	private int starsNo;
	private int memNo;
	private int mvNo;
	private int stars;
	
	public starsDataVO() {
		super();
	}
	
	public starsDataVO(int starsNo, int memNo, int mvNo, int stars) {
		super();
		this.starsNo = starsNo;
		this.memNo = memNo;
		this.mvNo = mvNo;
		this.stars = stars;
	}
	
	public int getStarsNo() {
		return starsNo;
	}
	public void setStarsNo(int starsNo) {
		this.starsNo = starsNo;
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
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	
	@Override
	public String toString() {
		return "starsDataVO [starsNo=" + starsNo + ", memNo=" + memNo + ", mvNo=" + mvNo + ", stars=" + stars + "]";
	}
	
}
