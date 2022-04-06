package com.ho.lec24.member.dao;

/*
 * update 3가지
 * select 쿼리만 날림: 이것도 4가지
 * 
 * 
 */

import java.beans.PropertyVetoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.ho.lec24.member.Member;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class MemberDao implements IMemberDao {

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String userid = "scott";
	private String userpw = "tiger";
	

//	private DriverManagerDataSource dataSource;
//	private DriverManagerDataSource dataSource;
	private ComboPooledDataSource dataSource;
	
	private JdbcTemplate template;
	
	
	public MemberDao() {
		
		// 초기 설정
		// 맨날 class.name -> driver에서 커넥션 객체 -> 쿼리 -> 메모리 해제...
		// 한 번만 하면 됨 
		// spring
		// datasource = new org.springframework.jdbc.datasource.DriverManagerDataSource()
//		dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(driver); // 이게 좀 다름
//		dataSource.setUrl(url);
//		dataSource.setUsername(userid);
//		dataSource.setPassword(userpw);
		

//		dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClass(driver); // 이런 메서드에서 차이가 있음
//		dataSource.setJdbcUrl(url);
//		dataSource.setUser(userid);
//		dataSource.setPassword(userpw);
	
		// c3p0
		// combopool 쓰려면 예외 처리 반드시 해야 함 
		// servlet-context.xml (스프링 설정 파일)에 쓰게 되면
		// autowired annotation 달고 이건 필요없음
		dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass(driver);
			dataSource.setJdbcUrl(url);
			dataSource.setUser(userid);
			dataSource.setPassword(userpw);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		
		template = new JdbcTemplate();
		template.setDataSource(dataSource);
	}
	
	/*
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	*/
	
	@Override
	public int memberInsert(final Member member) {
		
		int result = 0;
		
		final String sql = "INSERT INTO member (memId, memPw, memMail) values (?,?,?)";
		
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(driver);
//		dataSource.setUrl(url);
//		dataSource.setUsername(userid);
//		dataSource.setPassword(userpw);
//		
		// JDBC template
		// datasource를 넣어주고 템플릿만 이용하면 됨 
//		JdbcTemplate template = new JdbcTemplate();
//		template.setDataSource(dataSource);
		
//		1st
//		result = template.update(sql, member.getMemId(), member.getMemPw(), member.getMemMail());
		
//		2nd
//		result = template.update(new PreparedStatementCreator() {
//			
//			@Override
//			public PreparedStatement createPreparedStatement(Connection conn)
//					throws SQLException {
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, member.getMemId());
//				pstmt.setString(2, member.getMemPw());
//				pstmt.setString(3, member.getMemMail());
//				
//				return pstmt;
//			}
//		});
		
//      3rd
		result = template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, member.getMemId());
				pstmt.setString(2, member.getMemPw());
				pstmt.setString(3, member.getMemMail());
				
			}
		});
		
		return result;
		
		// 이거 없애도 됨
		// jdbc template 있어서 
		
		/*
		int result = 0;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, userpw);
			String sql = "INSERT INTO member (memId, memPw, memMail) values (?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemId());
			pstmt.setString(2, member.getMemPw());
			pstmt.setString(3, member.getMemMail());
			result = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
		*/
	}

	@Override
	public Member memberSelect(final Member member) {
		
		List<Member> members = null;
		
		// 다른 곳에서 값이 변동될 수 있으므로 final로 선언하겠음
		final String sql = "SELECT * FROM member WHERE memId = ? AND memPw = ?";
		
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(driver);
//		dataSource.setUrl(url);
//		dataSource.setUsername(userid);
//		dataSource.setPassword(userpw);
//		
//		JdbcTemplate template = new JdbcTemplate();
//		template.setDataSource(dataSource);
		
//		1st
		// 처음에는 sql문, new preparedStatement
//		members = template.query(sql, new PreparedStatementSetter() {
//			
//			@Override
//			public void setValues(PreparedStatement pstmt) throws SQLException {
//				pstmt.setString(1, member.getMemId());
//				pstmt.setString(2, member.getMemPw());
//			}
		
		// 가져온 정보 매핑 
//		}, new RowMapper<Member>() {
//
//			@Override
//			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Member mem = new Member();
//				mem.setMemId(rs.getString("memId"));
//				mem.setMemPw(rs.getString("memPw"));
//				mem.setMemMail(rs.getString("memMail"));
//				mem.setMemPurcNum(rs.getInt("memPurcNum"));
//				return mem;
//			}
//		});
		
//		2nd
//		members = template.query(new PreparedStatementCreator() {
//			
//			@Override
//			public PreparedStatement createPreparedStatement(Connection conn)
//					throws SQLException {
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, member.getMemId());
//				pstmt.setString(2, member.getMemPw());
//				return pstmt;
//			}
//		}, new RowMapper<Member>() {
//
//			@Override
//			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Member mem = new Member();
//				mem.setMemId(rs.getString("memId"));
//				mem.setMemPw(rs.getString("memPw"));
//				mem.setMemMail(rs.getString("memMail"));
//				mem.setMemPurcNum(rs.getInt("memPurcNum"));
//				return mem;
//			}
//		});
		
//		3rd
//		members = template.query(sql, new RowMapper<Member>() {
//
//			@Override
//			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Member mem = new Member();
//				mem.setMemId(rs.getString("memId"));
//				mem.setMemPw(rs.getString("memPw"));
//				mem.setMemMail(rs.getString("memMail"));
//				mem.setMemPurcNum(rs.getInt("memPurcNum"));
//				return mem;
//			}
//			
//		}, member.getMemId(), member.getMemPw());
		
//		4th
		// 배열로 값 넣어줄 수도 있음 {}
		members = template.query(sql, new Object[]{member.getMemId(), member.getMemPw()}, new RowMapper<Member>() {

			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member mem = new Member();
				mem.setMemId(rs.getString("memId"));
				mem.setMemPw(rs.getString("memPw"));
				mem.setMemMail(rs.getString("memMail"));
				mem.setMemPurcNum(rs.getInt("memPurcNum"));
				return mem;
			}
			
		});
		
		if(members.isEmpty()) 
			return null;
		
		return members.get(0);
		
		/*
		Member mem = null;
		
		try {
			
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, userpw);
			String sql = "SELECT * FROM member WHERE memId = ? AND memPw = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemId());
			pstmt.setString(2, member.getMemPw());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String memId = rs.getString("memid");
				String memPw = rs.getString("mempw");
				String memMail = rs.getString("memMail");
				int memPurcNum = rs.getInt("memPurcNum");
				
				mem = new Member();
				mem.setMemId(memId);
				mem.setMemPw(memPw);
				mem.setMemMail(memMail);
				mem.setMemPurcNum(memPurcNum);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return mem;
		*/
		
	}

	@Override
	public int memberUpdate(final Member member) {
		
		int result = 0;
		
		final String sql = "UPDATE member SET memPw = ?, memMail = ? WHERE memId = ?";
		
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(driver);
//		dataSource.setUrl(url);
//		dataSource.setUsername(userid);
//		dataSource.setPassword(userpw);
//		
//		JdbcTemplate template = new JdbcTemplate();
//		template.setDataSource(dataSource);
		
//		1st
//		result = template.update(sql, member.getMemPw(), member.getMemMail(),  member.getMemId());

//		2nd
		// 익명 클래스 넣어서 할 수도 있음 
//		result = template.update(new PreparedStatementCreator() {
//			
//			@Override
//			public PreparedStatement createPreparedStatement(Connection conn)
//					throws SQLException {
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, member.getMemPw());
//				pstmt.setString(2, member.getMemMail());
//				pstmt.setString(3, member.getMemId());
//				
//				return pstmt;
//			}
//		});
		
//		3rd
		// setter
		// 1st -> 2nd -> 3rd 해 보기 
		// 2번째 3번쨰 사용할 때는 sql문이 외부에서 사용될 수 있으므로 final로 
		result = template.update(sql, new PreparedStatementSetter() {
					
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, member.getMemPw());
				pstmt.setString(2, member.getMemMail());
				pstmt.setString(3, member.getMemId());
			}
		});
		return result;
		
		/*
		int result = 0;
		
		try {
			
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, userpw);
			String sql = "UPDATE member SET memPw = ?, memMail = ? WHERE memId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemPw());
			pstmt.setString(2, member.getMemMail());
			pstmt.setString(3, member.getMemId());
			result = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
		*/
		
	}

	@Override
	public int memberDelete(final Member member) {
		
		int result = 0;
		
		final String sql = "DELETE member WHERE memId = ? AND memPw = ?";
		
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(driver);
//		dataSource.setUrl(url);
//		dataSource.setUsername(userid);
//		dataSource.setPassword(userpw);
//		
//		JdbcTemplate template = new JdbcTemplate();
//		template.setDataSource(dataSource);
		
//		1st
//		result = template.update(sql, member.getMemId(), member.getMemPw());
		
//		2nd
//		result = template.update(new PreparedStatementCreator() {
//			
//			@Override
//			public PreparedStatement createPreparedStatement(Connection conn)
//					throws SQLException {
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, member.getMemId());
//				pstmt.setString(2, member.getMemPw());
//				
//				return pstmt;
//			}
//		});
		
//		3rd
		result = template.update(sql, new PreparedStatementSetter() {
					
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, member.getMemId());
				pstmt.setString(2, member.getMemPw());
			}
		});
		
		return result;
		
		/*
		int result = 0;
		
		try {
			
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, userpw);
			String sql = "DELETE member WHERE memId = ? AND memPw = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemId());
			pstmt.setString(2, member.getMemPw());
			result = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
		*/
	}

}