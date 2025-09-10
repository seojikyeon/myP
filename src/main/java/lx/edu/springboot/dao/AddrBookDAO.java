package lx.edu.springboot.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lx.edu.springboot.vo.AddrBookVO;

@Component
public class AddrBookDAO {
	
	@Autowired
	SqlSession session;

	public int insertDB(AddrBookVO ab) throws Exception {			
		return session.insert("insertDB", ab);
	}

	public List<AddrBookVO> getDBList() throws Exception {
		
		List<AddrBookVO> result = new ArrayList<AddrBookVO>();				
		return session.selectList("getDBList");
	}
	
	public AddrBookVO getDB(int abId) throws Exception {
		
		return session.selectOne("getDB", abId);
	}
	
	public int updateDB(AddrBookVO ab) throws Exception {
		return session.update("updateDB", ab);
	}
	
	
	
	/*
	 * public boolean deleteDB(int abId) throws Exception {
	 * 
	 * boolean result = false; SqlSession session = SessionUtil.getSession(); int
	 * deletedAmout = session.delete("deleteDB", abId); session.commit(); if
	 * (deletedAmout > 0) result = true;
	 * 
	 * return result; }
	 */
		/*
		 * System.out.println("deleteDB 함수 호출" + abId); boolean message = false;
		 * Connection con = getConnection(); String sql =
		 * "delete from addrbook where ab_id =?"; PreparedStatement psmt =
		 * con.prepareStatement(sql); psmt.setInt(1, abId); psmt.execute(); con.close();
		 * 
		 * message = true;
		 */


	/*
	 * connection = getConnection(); PreparedStatement pstmt =
	 * connection.prepareStatement(sqlInsertDB); boolean result= false; try {
	 * pstmt.setString(1, ab.getAbName());
	 * //AB_ID,AB_NAME,ab_email,ab_comdept,ab_birth,ab_tel,ab_memo) "
	 * pstmt.setString(2, ab.getAbEmail()); pstmt.setString(3, ab.getAbTel());
	 * pstmt.setString(4, ab.getAbBirth()); pstmt.setString(5, ab.getAbComdept());
	 * pstmt.setString(6, ab.getAbMemo()); result= pstmt.execute(); } catch
	 * (Exception e) { e.printStackTrace(); }
	 */

	
	/*
	 * public AddrBookVO getDB(int abId) throws Exception{
	 *  	return session.selectOne("getDB", abID);
	 *  	} 
	 */

	

	

	public static Connection getConnection() throws Exception {
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:comp/env");
		DataSource dataSource = (DataSource) envContext.lookup("jdbc/mydb");
		Connection connection = dataSource.getConnection();
		return connection;
	}
}
