package spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import spring.vo.Member;

public class MemberDao {
	
	private JdbcTemplate jdbcTemplate;

	public MemberDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Member selectByEmail(String email) { //이메일을 통한 멤버 조회
		String sql = "select * from members where email=?";
		
		//각 레코드의 데이터를 읽어오는데 도움을 주는 클래스 : RowMapper(인터페이스)
		
		//1. RowMapper를 구현한 클래스 활용 방법
		//List<Member> list = jdbcTemplate.query(sql, new MapperSqlToMember(), email);
		
		//2. 익명구현객체 활용 방법
		List<Member> list = jdbcTemplate.query(sql, new RowMapper<Member>() {
			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member m = new Member(
						rs.getString("email"),
						rs.getString("password"),
						rs.getString("name"),
						rs.getTimestamp("regDate")
						);
				
				m.setId(rs.getLong("id"));
				
				return m;
			}}, email);
		
		//3. 람다 활용 방법
//		List<Member> list = jdbcTemplate.query(sql, (rs.rowNum)->{
//			Member m = new Member(
//					rs.getString("email"),
//					rs.getString("password"),
//					rs.getString("name"),
//					rs.getTimestamp("regDate")
//					);
//			
//			m.setId(rs.getLong("id"));
//			
//			return m;
//		}, email);
		
		return list.isEmpty()?null:list.get(0);
	}
		
	public List<Member> selectAll() { //모든 멤버 조회
		List<Member> list = jdbcTemplate.query("select * from members", new RowMapper<Member>() {
			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member m = new Member(
						rs.getString("email"),
						rs.getString("password"),
						rs.getString("name"),
						rs.getTimestamp("regDate")
						);
				
				m.setId(rs.getLong("id"));
				
				return m;
			}
		});
		return list;
	}

	public int count() {
		String sql = "select count(*) from members";
		
		Integer cnt = jdbcTemplate.queryForObject(sql, Integer.class);

		return cnt;
	}
	
	public void insertMember(Member member) {
		String sql = "insert into members values(members_seq.nextval, ?, ?, ?, ?)";
		
//		jdbcTemplate.update(sql, member.getEmail(), member.getPassword(), member.getName(), new Timestamp(member.getRegisterDate().getTime())); //반영된 데이터의 갯수를 반환
		
		//직접 제어하는 경우 : Key 값 가져오기
		KeyHolder keyholder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement psmt = con.prepareStatement(sql, new String[] {"id"});
				
				psmt.setString(1, member.getEmail());
				psmt.setString(2, member.getPassword());
				psmt.setString(3, member.getName());
				psmt.setTimestamp(4, new Timestamp(member.getRegisterDate().getTime()));
				
				return psmt;
			}
		}, keyholder);
		
		Number keyValue = keyholder.getKey();
		member.setId(keyValue.longValue()); //시퀀스로 자동 생성된 id값 읽기
	}
	
	public void updateMember(Member member) {
		String sql = "update members set name=?, password=? where email=?";
		
		jdbcTemplate.update(sql, member.getName(), member.getPassword(), member.getEmail());
	}
	
	public void deleteMember(String email) {
		String sql = "delete from members where email=?";
		
		int num = jdbcTemplate.update(sql, email);
		
		if (num != 0) {
			System.out.println(num + "명 삭제 완료");
		} else {
			System.out.println("삭제 실패");
		}
	}
}