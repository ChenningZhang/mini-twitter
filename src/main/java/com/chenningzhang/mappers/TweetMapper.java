package com.chenningzhang.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chenningzhang.model.Tweet;

public class TweetMapper implements RowMapper<Tweet> {

	@Override
	public Tweet mapRow(ResultSet arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Tweet tweet = new Tweet();
		tweet.setId(arg0.getInt("id"));
		tweet.setUserId(arg0.getInt("userId"));
		tweet.setContent(arg0.getString("content"));
		tweet.setDate(arg0.getDate("date"));
		return tweet;
	}

}
