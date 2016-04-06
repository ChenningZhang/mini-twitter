package com.chenningzhang.objectDAO;

import java.util.List;

import com.chenningzhang.model.User;

public interface UserDAO {
	public List<String> getFollowings(int userId) throws Exception;
	public List<String> getFollowers(int userId) throws Exception;
	public int followUser(int currUserId, int followUserId) throws Exception;
	public int unfollowUser(int currUserId, int unfollowUserId) throws Exception;
	/*
	 * Check if user with id=currUserId is followed by user with id=followerId.
	 * return 1 if true, 0 otherwise.
	 */
	public int checkFollowed(int currUserId, int followerId) throws Exception;
	
	/*
	 * Returns the logged in user's id.
	 */
	public User userAuthentication() throws Exception;
	
	public void userLogin(int userId) throws Exception;
	
	public Boolean checkUserExist(int userId) throws Exception;
}
