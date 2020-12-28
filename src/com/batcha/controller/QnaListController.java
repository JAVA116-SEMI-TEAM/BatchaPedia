package com.batcha.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.batcha.common.PagingVO;
import com.batcha.qna.model.QnaDAO;
import com.batcha.qna.model.QnaService;
import com.batcha.qna.model.QnaVO;
import com.controller.Controller;

public class QnaListController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		HttpSession session = request.getSession();
		String dbUserid=(String) session.getAttribute("userid");
		
		if(dbUserid==null || dbUserid.isEmpty()){
			request.setAttribute("msg", "로그인 후, 접근가능합니다!");
			request.setAttribute("url", "/main.do");
			
			return "/common/message.jsp";
		}
		
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
		int blockSize=5; //한 블럭에 보여줄 페이지 수
			
		PagingVO pageVo = new PagingVO(currentPage, totalRecord, pageSize,
				blockSize);
		
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
