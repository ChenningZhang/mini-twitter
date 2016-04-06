package com.chenningzhang.objectDAO.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.chenningzhang.mappers.TweetMapper;
import com.chenningzhang.model.Tweet;
import com.chenningzhang.objectDAO.TweetDAO;

@Repository
public class TweetJDBCTemplate implements TweetDAO {
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
		
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public List<Tweet> getTweets(int userId, String searchKeyword) throws Exception {
		// TODO Auto-generated method stub
		String sql = "";
		if (searchKeyword == null) {
			sql = "SELECT * FROM tweets WHERE userId = :userId" +
					" UNION " +
					"SELECT tweets.id, followers.userId, tweets.content, tweets.date FROM tweets, followers WHERE followers.userId = tweets.userId AND followers.followedById = :userId";
		} else {
			sql = "SELECT * FROM tweets WHERE userId = :userId AND tweets.content like :pattern" +
					" UNION " +
					"SELECT tweets.id, followers.userId, tweets.content, tweets.date FROM tweets, followers WHERE followers.userId = tweets.userId AND followers.followedById = :userId AND tweets.content like :pattern";
		}
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource("userId", userId).addValue("pattern", "%"+searchKeyword+"%");
		return this.namedParameterJdbcTemplate.query(sql, sqlParameterSource, new TweetMapper());
	}

}
