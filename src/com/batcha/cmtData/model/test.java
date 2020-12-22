package com.batcha.cmtData.model;

public class test {
	public static void main(String[] args) {
		
		String sql="select";
		boolean wannaCnt=false;
		boolean isMemNo=false;
		
		if(wannaCnt) {
			sql+=" count(*) as count";
		}else {
			sql+=" *";
		}
		
		sql+=" from cmtData where";		
		
		if(isMemNo) {
			sql+=" memNo=?";
		}else {
			sql+=" mvNo=?";
		}
		System.out.println("sql="+sql);
	}
}
