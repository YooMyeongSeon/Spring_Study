package spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import spring.vo.Member;

public class MapperSqlToMember implements RowMapper<Member>{

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
}