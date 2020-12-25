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
import com.batcha.keepData.model.keepDataDAO;
import com.batcha.keepData.model.keepDataService;
import com.batcha.keepData.model.keepDataVO;
import com.batcha.memInfo.model.MemInfoService;
import com.batcha.mvInfo.model.MvInfoService;
import com.batcha.mvInfo.model.MvInfoVO;
import com.batcha.starsData.model.starsDataService;
import com.batcha.starsData.model.starsDataVO;
import com.controller.Controller;

public class MovieDetailController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//파라미터 임시로 mvNo 1번 넣어둠, 목록화면에서 mvNo 넘겨줘야 함
	//	String mvNo=request.getParameter("mvNo");
	//	int no=Integer.parseInt(mvNo);
	//	int iMvNo=Integer.parseInt(mvNo);
		
		int iMvNo=1;

		HttpSession session = request.getSession();
		int memNo=0;
		if(session.getAttribute("memno")!=null) {
			String t_memno=String.valueOf(session.getAttribute("memno"));
			memNo=Integer.parseInt(t_memno);
		}
		
		MvInfoService mvService=new MvInfoService();
		starsDataService starsService=new starsDataService();
		keepDataService keepService=new keepDataService();
		keepDataVO keepVo=new keepDataVO();
		
		//===================여기부터는 공통된 사항
		//영화정보조회
		MvInfoVO mvVo=new MvInfoVO();
		try {
			mvVo=mvService.selectMv(iMvNo);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		//===================영화평점조회
		//평점조회
		float avgStars=0.0f;
		int memCntOfMv=0;
		List<starsDataVO> starsList=null;
		try {
			avgStars=starsService.getAvgStars(iMvNo);
			//list=starsService.selectAllStarsByNo(iMvNo, false);
			if(starsService.selectAllStarsByNo(iMvNo, false)!=null 
					|| !starsService.selectAllStarsByNo(iMvNo, false).isEmpty()) {
				starsList=starsService.selectAllStarsByNo(iMvNo, false);
				memCntOfMv=starsList.size();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		//평점분포조회 todo.. 그래프 뿌릴 수 있으면
		int[] graphData = {0,0,0,0,0,0,0,0,0,0};
		for(int i=0; i<starsList.size(); i++) { //평점리스트의 n번째가 평점 몇점인지 가져와서 데이터배열에 입력
			int star=starsList.get(i).getStars();
			System.out.println("star="+star);
			if(star==1) {
				graphData[0]++;
				System.out.println("graphData[0]"+graphData[0]);
			}else if(star==2) {
				graphData[1]++;
				System.out.println("graphData[1]"+graphData[1]);
			}else if(star==3) {
				graphData[2]++;
				System.out.println("graphData[2]"+graphData[2]);
			}else if(star==4) {
				graphData[3]++;
				System.out.println("graphData[3]"+graphData[3]);
			}else if(star==5) {
				graphData[4]++;
				System.out.println("graphData[4]"+graphData[4]);
			}else if(star==6) {
				graphData[5]++;
				System.out.println("graphData[5]"+graphData[5]);
			}else if(star==7) {
				graphData[6]++;
				System.out.println("graphData[6]"+graphData[6]);
			}else if(star==8) {
				graphData[7]++;
				System.out.println("graphData[7]"+graphData[7]);
			}else if(star==9) {
				graphData[8]++;
				System.out.println("graphData[8]"+graphData[8]);
			}else if(star==10) {
				graphData[9]++;
				System.out.println("graphData[9]"+graphData[9]);
			}
		}
		
		
		
		//===================keep관련 
		boolean keptCheck=false; //초기화, 논유저면 그대로 false
		if(memNo>0) { //유저인 경우는 isKept 확인
			int cnt=0;
			try {
				cnt=keepService.isKept(memNo, iMvNo);
			}catch(SQLException e) {
				e.printStackTrace();
			}
			if(cnt==keepService.IS_KEPT) {
				keptCheck=true;
			}else if(cnt==keepService.IS_NOT_KEPT){
				keptCheck=false;
			}
		}		
		
		String keepBtn="";
		if(request.getParameter("keepBtn")!=null) {
			System.out.println("keepBtn의 값="+request.getParameter("keepBtn"));
			keepBtn=request.getParameter("keepBtn");
		}
		//결과 저장
		
		//===================개인정보 관련 
		//keptCheck 여기서도
		int didStars=0;
		int memStars=0;
		
		if(memNo>0) { //로그인 되어 있으면
			try {
				//평점여부조회
				didStars=starsService.didStars(memNo, iMvNo);
				if(didStars==starsDataService.YES_YOU_DID) { //영화평점 가져오기
					memStars=starsService.getStarsByMemNo(memNo, iMvNo);
				}else if(didStars==starsDataService.NO_YOU_DIDNT) {
					memStars=0;
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		//코멘트 리스트 받아오기
		CmtDataService cmtService=new CmtDataService();
		List<CmtDataVO> cmtList=new ArrayList<CmtDataVO>();
		try {
			cmtList=cmtService.selectCmtByNo(1, false);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		//셋팅 		
		request.setAttribute("cmtList", cmtList);
		request.setAttribute("mvVo", mvVo);
		request.setAttribute("memStars", memStars);
		request.setAttribute("didStars", didStars);
		request.setAttribute("keptCheck", keptCheck);
		request.setAttribute("avgStars", avgStars);
		request.setAttribute("memCntOfMv", memCntOfMv);
		request.setAttribute("graphData", graphData);
		
		
		return "/movie/movieDetail.jsp?mvNo="+iMvNo;
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
