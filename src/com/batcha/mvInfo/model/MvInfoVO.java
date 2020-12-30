package com.batcha.mvInfo.model;

import java.sql.Timestamp;
import java.util.Date;

public class MvInfoVO {
	private int mvNo;
	private String mvTitle;
	private String genre;
	private String director;
	private String actors;
	private String story;
	private String thumbnail;
	private String nation;
	private String makeYear; //제작년도
	private int boxOffice; //박스오피스 순위
	private Timestamp regdate;
	private String mvCode;
	private String mvTitleEn;
	
	public MvInfoVO() {
		super();
	}

	public MvInfoVO(int mvNo, String mvTitle, String genre, String director, String actors, String story,
			String thumbnail, String nation, String makeYear, int boxOffice,
			Timestamp regdate, String mvCode, String mvTitleEn) {
		super();
		this.mvNo = mvNo;
		this.mvTitle = mvTitle;
		this.genre = genre;
		this.director = director;
		this.actors = actors;
		this.story = story;
		this.thumbnail = thumbnail;
		this.nation = nation;
		this.makeYear = makeYear;
		this.boxOffice = boxOffice;
		this.regdate = regdate;
		this.mvCode = mvCode;
		this.mvTitleEn = mvTitleEn;
	}

	public int getMvNo() {
		return mvNo;
	}

	public void setMvNo(int mvNo) {
		this.mvNo = mvNo;
	}

	public String getMvTitle() {
		return mvTitle;
	}

	public void setMvTitle(String mvTitle) {
		this.mvTitle = mvTitle;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getMakeYear() {
		return makeYear;
	}

	public void setMakeYear(String makeYear) {
		this.makeYear = makeYear;
	}

	public int getBoxOffice() {
		return boxOffice;
	}

	public void setBoxOffice(int boxOffice) {
		this.boxOffice = boxOffice;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	public String getMvCode() {
		return mvCode;
	}

	public void setMvCode(String mvCode) {
		this.mvCode = mvCode;
	}

	public String getMvTitleEn() {
		return mvTitleEn;
	}

	public void setMvTitleEn(String mvTitleEn) {
		this.mvTitleEn = mvTitleEn;
	}

	@Override
	public String toString() {
		return "MvInfoVO [mvNo=" + mvNo + ", mvTitle=" + mvTitle + ", genre=" + genre + ", director=" + director
				+ ", actors=" + actors + ", story=" + story + ", thumbnail=" + thumbnail + ", nation=" + nation
				+ ", makeYear=" + makeYear + ", boxOffice=" + boxOffice + ", regdate=" + regdate + ", mvCode=" + mvCode + ", mvTitleEn=" + mvTitleEn + "]";
	}

}
