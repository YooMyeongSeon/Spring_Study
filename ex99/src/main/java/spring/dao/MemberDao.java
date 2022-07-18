package spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import spring.vo.ListCommand;
import spring.vo.Member;

public class MemberDao {
	
//	private JdbcTemplate jdbcTemplate;
//
//	public MemberDao(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}
//	
//	public Member selectByEmail(String email) { //이메일을 통한 멤버 조회
//		String sql = "select * from members where email=?";
//		
//		//각 레코드의 데이터를 읽어오는데 도움을 주는 클래스 : RowMapper(인터페이스)
//		
//		//1. RowMapper를 구현한 클래스 활용 방법
//		//List<Member> list = jdbcTemplate.query(sql, new MapperSqlToMember(), email);
//		
//		//2. 익명구현객체 활용 방법
//		List<Member> list = jdbcTemplate.query(sql, new RowMapper<Member>() {
//			@Override
//			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Member m = new Member(
//						rs.getString("email"),
//						rs.getString("password"),
//						rs.getString("name"),
//						rs.getTimestamp("regDate")
//						);
//				
//				m.setId(rs.getLong("id"));
//				
//				return m;
//			}}, email);
//		
//		//3. 람다 활용 방법
////		List<Member> list = jdbcTemplate.query(sql, (rs.rowNum)->{
////			Member m = new Member(
////					rs.getString("email"),
////					rs.getString("password"),
////					rs.getString("name"),
////					rs.getTimestamp("regDate")
////					);
////			
////			m.setId(rs.getLong("id"));
////			
////			return m;
////		}, email);
//		
//		return list.isEmpty()?null:list.get(0);
//	}
//		
//	public List<Member> selectAll() { //모든 멤버 조회
//		List<Member> list = jdbcTemplate.query("select * from members", new RowMapper<Member>() {
//			@Override
//			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Member m = new Member(
//						rs.getString("email"),
//						rs.getString("password"),
//						rs.getString("name"),
//						rs.getTimestamp("regDate")
//						);
//				
//				m.setId(rs.getLong("id"));
//				
//				return m;
//			}
//		});
//		return list;
//	}
//
//	public int count() {
//		String sql = "select count(*) from members";
//		
//		Integer cnt = jdbcTemplate.queryForObject(sql, Integer.class);
//
//		return cnt;
//	}
//	
//	public void insertMember(Member member) {
//		String sql = "insert into members values(members_seq.nextval, ?, ?, ?, ?)";
//		
////		jdbcTemplate.update(sql, member.getEmail(), member.getPassword(), member.getName(), new Timestamp(member.getRegisterDate().getTime())); //반영된 데이터의 갯수를 반환
//		
//		//직접 제어하는 경우 : Key 값 가져오기
//		KeyHolder keyholder = new GeneratedKeyHolder();
//
//		jdbcTemplate.update(new PreparedStatementCreator() {
//			@Override
//			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//				PreparedStatement psmt = con.prepareStatement(sql, new String[] {"id"});
//				
//				psmt.setString(1, member.getEmail());
//				psmt.setString(2, member.getPassword());
//				psmt.setString(3, member.getName());
//				psmt.setTimestamp(4, new Timestamp(member.getRegisterDate().getTime()));
//				
//				return psmt;
//			}
//		}, keyholder);
//		
//		Number keyValue = keyholder.getKey();
//		member.setId(keyValue.longValue()); //시퀀스로 자동 생성된 id값 읽기
//	}
//	
//	public void updateMember(Member member) {
//		String sql = "update members set name=?, password=? where email=?";
//		
//		jdbcTemplate.update(sql, member.getName(), member.getPassword(), member.getEmail());
//	}
//	
//	public void deleteMember(String email) {
//		String sql = "delete from members where email=?";
//		
//		int num = jdbcTemplate.update(sql, email);
//		
//		if (num != 0) {
//			System.out.println(num + "명 삭제 완료");
//		} else {
//			System.out.println("삭제 실패");
//		}
//	}
//	
//	//날짜를 이용한 데이터 조회
//	public List<Member> selectByRegDate(Date from, Date To) {
//		String sql = "";
//		
//		List<Member> list = jdbcTemplate.query(sql, new RowMapper<Member>() {
//			@Override
//			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Member m = new Member(rs.getString("email"), rs.getString("password"), rs.getString("name"), rs.getTimestamp("regdate"));
//				m.setId(rs.getLong("id"));
//				return m;
//			}
//		}, from, To);
//		return list;
//	}
//	
//	//아이디 번호를 통한 데이터 조회
//	public Member selectById(Long id) {
//		String sql = "";
//		
//		List<Member> result = jdbcTemplate.query(sql, new RowMapper<Member>() {
//			@Override
//			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Member m = new Member(rs.getString("email"), rs.getString("password"), rs.getString("name"), rs.getTimestamp("regdate"));
//				m.setId(rs.getLong("id"));
//				return m;
//			}
//		},id);
//		
//		return result.isEmpty()?null:result.get(0);
//	}
	
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public Member selectByEmail(String email) {
		return sqlSession.selectOne("spring.member.selectByEmail", email);
	}
	
	public List<Member> selectAll() {
		return sqlSession.selectList("spring.member.selectAll");
	}
	
	public int count() {
		return sqlSession.selectOne("spring.member.selectCount");
	}
	
	public List<Member> selectByRegDate(ListCommand searchDate) {
		return sqlSession.selectList("spring.member.selectByRegDate", searchDate);
	}
	
	public Member selectById(Long id) {
		return sqlSession.selectOne("spring.member.selectById", id);
	}
	
	public void insertMember(Member member) {
		sqlSession.insert("spring.member.insertMember", member);
		sqlSession.commit();
	}
	
	public void updateMember(Member member) {
		sqlSession.update("spring.member.updateMember", member);
		sqlSession.commit();
	}
	
	public void deleteMember(String email) {
		sqlSession.delete("spring.member.deleteMember", email);
		sqlSession.commit();
	}
}