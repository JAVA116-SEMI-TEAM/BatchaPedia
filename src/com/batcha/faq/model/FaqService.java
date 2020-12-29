package com.batcha.faq.model;

import java.sql.SQLException;
import java.util.List;

public class FaqService {
	private FaqDAO faqDao;
	
	public FaqService() {
		faqDao = new FaqDAO();
	}
	
	//등록 - insert
	public int insertFaq(FaqVO faqVo) throws SQLException {
		return faqDao.insertFaq(faqVo);
	}
	
	//전체 조회 - select
	public List<FaqVO> selectAllFaq() throws SQLException{
		return faqDao.selectAllFaq();
	}
	
	//번호로 조회 - select(no)
	public FaqVO selectFaq(int faqNo) throws SQLException {
		return faqDao.selectFaq(faqNo);
	}
	
	//수정 - udpate
	public int updateFaq(FaqVO faqVo) throws SQLException {
		return faqDao.updateFaq(faqVo);
	}
	
	//삭제 - delete
	public int deleteFaq(int faqNo) throws SQLException {
		return faqDao.deleteFaq(faqNo);
	}
}
