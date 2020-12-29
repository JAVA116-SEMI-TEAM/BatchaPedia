package com.batcha.keepData.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class keepDataService {
   keepDataDAO keepDao;
   
   //킵 됐는지 안됐는지 판단할때 쓸 것?
   public final static int IS_NOT_KEPT=0;
   public final static int IS_KEPT=1;
   
   
   public keepDataService() {
      keepDao=new keepDataDAO();
   }
   
   public int isKept(int memNo, int mvNo) throws SQLException {
      return keepDao.isKept(memNo, mvNo);
   }
   
   //킵목록에 추가
   public int insertKeep(int memNo, int mvNo) throws SQLException {
      return keepDao.insertKeep(memNo, mvNo);
   }
   
   //킵목록에서 삭제
   public int deleteKeep(int memNo, int mvNo) throws SQLException {
      return keepDao.deleteKeep(memNo, mvNo);
   }   
   
   //회원별, 영화별 모든 킵리스트 꺼내기. 총 개수가 필요하면 list.size로 활용 가능
   public List<keepDataVO> selectAllKeepByMemNo(int no, boolean isMemNo) throws SQLException{
      return keepDao.selectAllKeepByMemNo(no, isMemNo);
   }
   
}