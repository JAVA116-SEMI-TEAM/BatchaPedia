package com.batcha.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;

import com.batcha.cmtData.model.CmtDataService;
import com.batcha.cmtData.model.CmtDataVO;
import com.batcha.common.PagingVO;
import com.batcha.keepData.model.keepDataDAO;
import com.batcha.keepData.model.keepDataService;
import com.batcha.keepData.model.keepDataVO;
import com.batcha.memInfo.model.MemInfoService;
import com.batcha.memInfo.model.MemInfoVO;
import com.batcha.mvInfo.model.MvInfoService;
import com.batcha.mvInfo.model.MvInfoVO;
import com.batcha.starsData.model.starsDataService;
import com.batcha.starsData.model.starsDataVO;
import com.controller.Controller;

public class MovieDetailController implements Controller{
	private StarGraphController starGraphCtrl;
	
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		// ============== Parameter, service Setting
		MvInfoService mvService=new MvInfoService();
		starsDataService starsService=new starsDataService();
		keepDataService keepService=new keepDataService();
		CmtDataService cmtService=new CmtDataService();
		MemInfoService memService=new MemInfoService();
		
		HttpSession session = request.getSession();
		String userid="";
		if(session.getAttribute("userid")!=null) {
			userid=String.valueOf(session.getAttribute("userid"));
		}
		
		MemInfoVO memVo=null;
		try {
			memVo=memService.selectMember(userid);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		int memNo=memVo.getMemNo();
		String strMvNo=request.getParameter("mvNo");
		System.out.println("strMvNo="+strMvNo);
		int mvNo=Integer.parseInt(strMvNo);
		
		request.setAttribute("memNo", memNo);
		request.setAttribute("mvNo", mvNo);
		request.setAttribute("userid", userid);
		
		// ================ info - 영화정보 ===================
		//영화정보조회
		MvInfoVO mvVo=new MvInfoVO();
		try {
			mvVo=mvService.selectMv(mvNo);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("mvVo", mvVo);

		// ================ info - 평점정보 ===================
		int didStars=0;
		int memStars=0;
		
		if(memNo>0) { //로그인 되어 있으면
			try {
				//평점여부조회
				didStars=starsService.didStars(memNo, mvNo);
				if(didStars==starsDataService.YES_YOU_DID) { //영화평점 가져오기
					memStars=starsService.getStarsByMemNo(memNo, mvNo);
				}else if(didStars==starsDataService.NO_YOU_DIDNT) {
					memStars=0;
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("didStars", didStars);
		request.setAttribute("memStars", memStars);

		// ================ info - 킵정보 ===================
		keepDataVO keepVo=new keepDataVO();

		boolean keptCheck=false; //초기화, 논유저면 그대로 false
		if(memNo>0) { //유저인 경우는 isKept 확인
			int cnt=0;
			try {
				cnt=keepService.isKept(memNo, mvNo);
			}catch(SQLException e) {
				e.printStackTrace();
			}
			if(cnt==keepService.IS_KEPT) {
				keptCheck=true;
			}
		}		
		
		String keepBtn="";
		if(request.getParameter("keepBtn")!=null) {
			System.out.println("keepBtn의 값="+request.getParameter("keepBtn"));
			keepBtn=request.getParameter("keepBtn");
		}

		request.setAttribute("keptCheck", keptCheck);

		// ================ cmtWrite ===================
		//영화에 해당 회원의 코멘트가 있으면 본인 코멘트 노출, 작성영역 숨김처리
		int cmtWroteOrNot=0;
		CmtDataVO cmtVo=null;
		if(memNo>0) { //유저가 로그인 한 상태인 경우
			try {
				cmtWroteOrNot=cmtService.alreadyWroteOrNot(memNo, mvNo); //썼는지 안 썼는지 판별하는 함수
				if(cmtWroteOrNot==CmtDataService.ALREADY_WROTE) { //이미 썼다면
					//코멘트 vo 를 돌려줘서 출력해줘야겠지?
					cmtVo=cmtService.selectOneCmt(memNo, mvNo);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		request.setAttribute("cmtVo", cmtVo); //뷰에서는 이게 null이면 입력화면, 아니면 코멘트 내용 넣어주면 됨
		
		// ================ starsGraph ===================
		float avgStars=0.0f;
		int memCntOfMv=0;
		List<starsDataVO> starsList=null;
		try {
			avgStars=starsService.getAvgStars(mvNo);
			if(starsService.selectAllStarsByNo(mvNo, false)!=null 
					|| !starsService.selectAllStarsByNo(mvNo, false).isEmpty()) {
				starsList=starsService.selectAllStarsByNo(mvNo, false);
				memCntOfMv=starsList.size();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		//평점분포데이터
		int[] graphData=new int[10]; //이렇게 해도 되나..
		graphData=starsService.makeStarGraph(starsList);
		
		request.setAttribute("memCntOfMv", memCntOfMv);
		request.setAttribute("avgStars", avgStars);
		request.setAttribute("graphData", graphData);
		
		// ================ cmtList ===================
		//코멘트 리스트 받아오기
		List<CmtDataVO> cmtList=new ArrayList<CmtDataVO>();
		try {
			cmtList=cmtService.selectCmtByNo(mvNo, false);
			System.out.println(mvNo+"번 영화 코멘트 리스트 조회결과 "+cmtList.size()+"건");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		//페이징 처리
		int currentPage=1;
		if(request.getParameter("currentPage")!=null 
				&& !request.getParameter("currentPage").isEmpty()) {
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		}
		
		int totalCmt=0;
		if(cmtList!=null) totalCmt=cmtList.size();
		int pageSize=5;
		int blockSize=5;
		
		PagingVO pageVo=new PagingVO(currentPage, totalCmt, pageSize, blockSize);
		
		//3
		request.setAttribute("pageVo", pageVo);
		request.setAttribute("cmtList", cmtList);
		request.setAttribute("cmtListSize", cmtList.size());
		request.setAttribute("mvNo", mvNo);
		
		
		//======================================= 화면으로 이동
		return "/movie/movieDetail.jsp?mvNo="+mvNo;
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
