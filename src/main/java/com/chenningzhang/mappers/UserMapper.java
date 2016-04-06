package com.chenningzhang.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chenningzhang.model.User;

public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUserId(arg0.getInt("userId"));
		user.setUserName(arg0.getString("userName"));
		return user;
	}
	
}
