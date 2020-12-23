package com.batcha.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.common.PagingVO;
import com.batcha.qna.model.QnaDAO;
import com.batcha.qna.model.QnaService;
import com.batcha.qna.model.QnaVO;
import com.controller.Controller;

public class QnaListController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//1) write.jsp에서 [글목록]클릭하면 get방식으로 이동
		//또는 write_ok.jsp에서 글쓰기 성공하면 get방식으로 이동
		//2) list.jsp에서 검색 버튼 클릭하면 post방식으로 submit
		//3) list.jsp에서 페이지번호를 클릭하면 get방식으로 이동

		//1	
		String condition=request.getParameter("searchCondition");
		String keyword=request.getParameter("searchKeyword");
		
		//2
		QnaService service=new QnaService();
		List<QnaVO> list=null;
		
		try{
			list=service.selectAll(condition, keyword);	
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		//3
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		//페이징 처리
		int currentPage=1;  //현재 페이지
		
		//페이지번호를 클릭한 경우 처리
		if(request.getParameter("currentPage")!=null){
		  currentPage=Integer.parseInt(request.getParameter("currentPage"));		
		}
		
		//[1] 현재 페이지와 무관한 변수
		int totalRecord=0;  //총 레코드 수
		if(list!=null && !list.isEmpty()){
			totalRecord = list.size();  //예) 17
		}
		
		int pageSize=10;  //한 페이지에 보여줄 레코드(행) 수
		int blockSize=10; //한 블럭에 보여줄 페이지 수
			
		PagingVO pageVo = new PagingVO(currentPage, totalRecord, pageSize,
				blockSize);
		
		/*
		 * if(keyword !=null && !keyword.isEmpty()){ <p>검색어 : keyword, list.size()건
		 * 검색되었습니다.</p> }else{ keyword=""; }
		 */
		//3
		request.setAttribute("list", list);
		request.setAttribute("pageVo", pageVo);
		
		return "/qna/list.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
