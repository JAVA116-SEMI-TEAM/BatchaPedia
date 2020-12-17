package com.batcha.mvInfo.model;

import java.sql.Timestamp;

public class MvInfoVO {
	private int mvNo;
	private String mvTitle;
	private String genre;
	private String director;
	private String actors;
	private String story;
	private String thumbnail;
	private String nation;
	private String showYear; //개봉년도
	private String makeYear; //제작년도
	private int boxOffice; //박스오피스 순위
	private Timestamp startdate;
	private Timestamp enddate;
	private Timestamp regdate;
	
	public MvInfoVO() {
		super();
	}
	
	public MvInfoVO(int mvNo, String mvTitle, String genre, String director, String actors, String story,
			String thumbnail, String nation, String showYear, String makeYear, int boxOffice, Timestamp startdate,
			Timestamp enddate, Timestamp regdate) {
		super();
		this.mvNo = mvNo;
		this.mvTitle = mvTitle;
		this.genre = genre;
		this.director = director;
		this.actors = actors;
		this.story = story;
		this.thumbnail = thumbnail;
		this.nation = nation;
		this.showYear = showYear;
		this.makeYear = makeYear;
		this.boxOffice = boxOffice;
		this.startdate = startdate;
		this.enddate = enddate;
		this.regdate = regdate;
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
	public String getShowYear() {
		return showYear;
	}
	public void setShowYear(String showYear) {
		this.showYear = showYear;
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
	public Timestamp getStartdate() {
		return startdate;
	}
	public void setStartdate(Timestamp startdate) {
		this.startdate = startdate;
	}
	public Timestamp getEnddate() {
		return enddate;
	}
	public void setEnddate(Timestamp enddate) {
		this.enddate = enddate;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "MvInfoVO [mvNo=" + mvNo + ", mvTitle=" + mvTitle + ", genre=" + genre + ", director=" + director
				+ ", actors=" + actors + ", story=" + story + ", thumbnail=" + thumbnail + ", nation=" + nation
				+ ", showYear=" + showYear + ", makeYear=" + makeYear + ", boxOffice=" + boxOffice + ", startdate="
				+ startdate + ", enddate=" + enddate + ", regdate=" + regdate + "]";
	}
	
}
