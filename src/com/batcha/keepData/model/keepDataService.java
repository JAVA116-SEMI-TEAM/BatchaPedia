package com.batcha.keepData.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class keepDataService {
   keepDataDAO keepDao;
   
   //ŵ �ƴ��� �ȵƴ��� �Ǵ��Ҷ� �� ��?
   public final static int IS_NOT_KEPT=0;
   public final static int IS_KEPT=1;
   
   
   public keepDataService() {
      keepDao=new keepDataDAO();
   }
   
   public int isKept(int memNo, int mvNo) throws SQLException {
      return keepDao.isKept(memNo, mvNo);
   }
   
   //ŵ��Ͽ� �߰�
   public int insertKeep(int memNo, int mvNo) throws SQLException {
      return keepDao.insertKeep(memNo, mvNo);
   }
   
   //ŵ��Ͽ��� ����
   public int deleteKeep(int memNo, int mvNo) throws SQLException {
      return keepDao.deleteKeep(memNo, mvNo);
   }   
   
   //ȸ����, ��ȭ�� ��� ŵ����Ʈ ������. �� ������ �ʿ��ϸ� list.size�� Ȱ�� ����
   public List<keepDataVO> selectAllKeepByMemNo(int no, boolean isMemNo) throws SQLException{
      return keepDao.selectAllKeepByMemNo(no, isMemNo);
   }
   
}