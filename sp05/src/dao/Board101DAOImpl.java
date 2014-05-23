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

import org.apache.ibatis.session.SqlSession;

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

	private SqlSession sqlSession;//mybatis쿼리문 실행 변수
	
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
		//this.sqlSession이 mybatis쿼리문 실행 객체가 된다.
	}//setter DI 의존관계를 만듬.

	/* 저장 */
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

	/* ORM mybatis 디비 연동 프레임웍 쿼리문 실행 메서드(월말평가)
	 * 1.insert(); 레코드 저장. ibatis에서 사용되는 insert()메서드는
	 *   반환값이 Object()형이다. 하지만 mybatis에서 사용되는 insert()
	 *   메서드는 반환값이 저장 성공된 레코드 행의 개수가 반환된다.
	 *   자바 jdbc에서 insert,update,delete쿼리문을 실행하는
	 *   executeUpdate()메서드가 반환값이 쿼리문 성공시 실행된 레코드 행의
	 *   갯수를 반환한다. ibatis는 단종됨. 새롭게 나온것이 mybatis이다.
	 * 
	 * 2.udpate():레코드 수정. 반환값은 수정 성공시 실행된 레코드행(row)의 갯수를 반환
	 * 
	 * 3.delete():레코드 삭제. 반환값은 삭제 성공시 실행된 레코드행(row)의 갯수를 반환
	 * 
	 * 4.단 하나의 레코드를 반환: ibatis에서는 queryForObject()메서드를 사용한다.
	 *   하지만 mybatis에서는 selectOne()메서드를 사용한다.
	 *   
	 * 5.하나 이상 레코드를 반환해서 컬렉션 List로 반환:
	 *   ibatis에서는 queryForList(),
	 *   mybatis에서는 selectList()메서드를 사용한다.
	 */
	/* 목록 */
	@Override
	public List<Board101Bean> getList() {
		return this.sqlSession.selectList("Board.bList");
		//Board는 네임스페이스 이름, bList는 select 아이디 이름
	}

	/*내용보기+수정폼+삭제폼*/
	@Override
	public Board101Bean getCont(int no) {
	   return this.sqlSession.selectOne("Board.b_cont",no);
	}

	//수정하기
	public void updateBoard(Board101Bean b) {
	  try {
		  con=ds.getConnection();
		sql="update board101 set name=?,addr=?,gender=?,city=?,cont=? where no=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, b.getName());
		pstmt.setString(2, b.getAddr());
		pstmt.setString(3, b.getGender());
		pstmt.setString(4, b.getCity());
		pstmt.setString(5, b.getCont());
		pstmt.setInt(6, b.getNo());
		
		pstmt.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	}

}
