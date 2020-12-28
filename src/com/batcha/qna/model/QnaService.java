package com.batcha.qna.model;

import java.sql.SQLException;
import java.util.List;


public class QnaService {
	
private QnaDAO qnaDao;
	
	public QnaService() {
		qnaDao=new QnaDAO();
	}
	
	/*
	 * public int selectById(String id) throws SQLException{ return
	 * qnaDao.selectById(id); }
	 */
	
	public int insertQna(QnaVO vo) throws SQLException{
		return qnaDao.insertQna(vo);
	}
	
	public List<QnaVO> selectAll(String condition, String keyword)
			throws SQLException{
		return qnaDao.selectAll(condition, keyword);
	}
	
	public int updateReadCount(int qnano) throws SQLException{
		return qnaDao.updateReadCount(qnano);
	}
	
	public QnaVO selectByNo(int qnano) throws SQLException{
		return qnaDao.selectByNo(qnano);
	}
	
	public int deleteQna(int qnano) throws SQLException{
		return qnaDao.deleteQna(qnano);
	}
	
	public int updateQna(QnaVO vo) throws SQLException{
		return qnaDao.updateQna(vo);
	}
	
	public int reply(QnaVO vo) throws SQLException{
		return qnaDao.reply(vo);
	}

	public int deleteQnaAdmin(int qnano) throws SQLException{
		return qnaDao.deleteQnaAdmin(qnano);
	}
}
