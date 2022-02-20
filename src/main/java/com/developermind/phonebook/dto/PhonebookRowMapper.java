package com.developermind.phonebook.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PhonebookRowMapper implements RowMapper<PhonebookDto> {
	
	@Override
	public PhonebookDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new PhonebookDto(rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"),
				rs.getString("mobile"), rs.getString("address"), rs.getString("nickName"), rs.getString("relation"),
				rs.getString("photo"), rs.getLong("id"));
	}
}
