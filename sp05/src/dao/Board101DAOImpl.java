package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.Board101Bean;

public class Board101DAOImpl implements Board101DAO {

	Connection con = null;// 디비 연동 레퍼런스
	PreparedStatement pstmt = null;// 쿼리문 실행 레퍼런스
	ResultSet rs = null;// 쿼리결과 레코드를 저장할 레퍼런스
	DataSource ds = null;// 커넥션 풀, JNDI 레퍼런스
	String sql = null;

	public Board101DAOImpl() throws Exception {
		Context ctx = new InitialContext();
		ds = (DataSource) ctx.lookup("java:comp/env/jdbc/xe");
	}

	//거주 지역명 가져오기
	public List<String> getCity() {
		List<String> cityNames = new ArrayList<String>();
		try {
			sql = "select * from city order by no";
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cityNames.add(rs.getString("cityName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityNames;
	}

	public int insert(Board101Bean b) {
		int cnt=0;
		try {
			con=ds.getConnection();
			sql="insert into board101(no,name,addr,pass,gender,city,cont,regdate)"
					+ " values(board101_no_seq.nextval,?,?,?,?,?,?,sysdate)";
			
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, b.getName());
			pstmt.setString(2, b.getAddr());
			pstmt.setString(3, b.getPass());
			pstmt.setString(4, b.getGender());
			pstmt.setString(5, b.getCity());
			pstmt.setString(6, b.getCont());
			
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

}
