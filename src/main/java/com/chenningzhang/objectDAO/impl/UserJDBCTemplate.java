package com.chenningzhang.objectDAO.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.chenningzhang.mappers.UserMapper;
import com.chenningzhang.model.User;
import com.chenningzhang.objectDAO.UserDAO;

@Repository
public class UserJDBCTemplate implements UserDAO {
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<String> getFollowings(int userId) throws Exception {
		String sql = "SELECT users.userName, users.userId FROM followers, users WHERE followers.followedById = :userId AND users.userId = followers.userId";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource("userId", userId);
		List<User> followingUsers = this.namedParameterJdbcTemplate.query(sql, sqlParameterSource, new UserMapper());
		List<String> result = new ArrayList<String>();
		for(int i=0; i<followingUsers.size(); i++) {
			String name = followingUsers.get(i).getUserName();
			result.add(name);
		}
		return result;
	}

	@Override
	public List<String> getFollowers(int userId) throws Exception {
		String sql = "SELECT users.userName, users.userId FROM followers, users WHERE followers.userId = :userId AND users.userId = followers.followedById";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource("userId", userId);
		List<User> followers = this.namedParameterJdbcTemplate.query(sql, sqlParameterSource, new UserMapper());
		List<String> result = new ArrayList<String>();
		for(int i=0; i<followers.size(); i++) {
			String name = followers.get(i).getUserName();
			result.add(name);
		}
		return result;
	}

	@Override
	public int followUser(int currUserId, int followUserId) throws Exception {
		String sql = "INSERT INTO followers (userId, followedById) VALUES (:userId, :followedById)";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource("followedById", currUserId).addValue("userId", followUserId);
		return this.namedParameterJdbcTemplate.update(sql, sqlParameterSource);
	}

	@Override
	public int unfollowUser(int currUserId, int unfollowUserId) throws Exception {
		String sql = "DELETE FROM followers WHERE userId = :userId AND followedById = :followedById";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource("followedById", currUserId).addValue("userId", unfollowUserId);
		return this.namedParameterJdbcTemplate.update(sql, sqlParameterSource);
	}
	
	@Override
	public int checkFollowed(int currUserId, int followerId) throws Exception {
		String sql = "SELECT users.userName, users.userId FROM followers, users WHERE followers.userId= :currUserId AND followers.followedById= :followerId AND users.userId=followers.followedById";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource("currUserId", currUserId).addValue("followerId", followerId);
		List<User> followers = this.namedParameterJdbcTemplate.query(sql, sqlParameterSource, new UserMapper());
		if (followers.size() == 0) {
			return 0;
		} else {
			return 1;
		}
	}

	@Override
	public User userAuthentication() throws Exception {
		String sql = "SELECT users.userId, users.userName FROM users WHERE users.loggedin=1";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource();
		User loggedinUser = this.namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, new UserMapper());
		return loggedinUser;
	}

	@Override
	public void userLogin(int userId) throws Exception {
		String sqlLogoutUser = "UPDATE users SET loggedin=0 WHERE loggedin=1";
		this.namedParameterJdbcTemplate.update(sqlLogoutUser, new MapSqlParameterSource());
		String sqlLoginUser = "UPDATE users SET loggedin=1 WHERE userId= :userId";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource("userId", userId);
		this.namedParameterJdbcTemplate.update(sqlLoginUser, sqlParameterSource);
	}

	@Override
	public Boolean checkUserExist(int userId) throws Exception {
		try {
			String sql = "SELECT users.userId, users.userName FROM users WHERE users.userId = :userId";
			SqlParameterSource sqlParameterSource = new MapSqlParameterSource("userId", userId);
			this.namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, new UserMapper());
			return true;
		} catch(EmptyResultDataAccessException e) {
			return false;
		}
		
	}

}
