package com.batcha.mynotice.model;

import java.sql.SQLException;
import java.util.List;

public class NoticeService {
	public static final int AUTHOR=1; //작성자가 관리자
	
	private NoticeDAO noticeDao;
	
	public NoticeService() {
		noticeDao = new NoticeDAO();
	}
	
	public int insertNotice(NoticeVO vo) throws SQLException {
		return noticeDao.insertNotice(vo);
	}
	
	public List<NoticeVO> selectAllNotice() throws SQLException {
		return noticeDao.selectAllNotice();
	}
	
	public NoticeVO selectNotice(int noticeNo) throws SQLException {
		return noticeDao.selectNotice(noticeNo);
	}
	
	public int updateNotice(NoticeVO vo) throws SQLException {
		return noticeDao.updateNotice(vo);
	}
	
	public int deleteNotice(int noticeNo) throws SQLException {
		return noticeDao.deleteNotice(noticeNo);
	}
	
	public List<NoticeVO> selectAllNotice(String condition,String keyword) throws SQLException {
		return noticeDao.selectAllNotice(condition, keyword);
	}
	
	public int updateReader(int noticeNo) throws SQLException {
		return noticeDao.updateReader(noticeNo);
	}
}
