package com.chenningzhang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chenningzhang.exception.ResourceNotFoundException;
import com.chenningzhang.exception.UnauthorizedException;
import com.chenningzhang.model.Tweet;
import com.chenningzhang.objectDAO.TweetDAO;
import com.chenningzhang.objectDAO.UserDAO;

@Controller
public class TweetController {
	private TweetDAO tweetDAO;
	private UserDAO userDAO;
	
	@Autowired
	public TweetController(TweetDAO tweetDAO, UserDAO userDAO) {
		this.tweetDAO = tweetDAO;
		this.userDAO = userDAO;
	}
	
	@ResponseBody
	@RequestMapping(value="/tweets", method=RequestMethod.GET)
	public List<Tweet> getTweets(@RequestParam(required=true) int userId, @RequestParam(required=false) String search) throws Exception {
		if (!userDAO.checkUserExist(userId)) {
			throw new ResourceNotFoundException("The user does not exist!");
		}
		try {
			if(userDAO.userAuthentication().getUserId() != userId) {
				throw new UnauthorizedException("The user is not authorized!");
			} else {
				return tweetDAO.getTweets(userId, search);
			}
		} catch (EmptyResultDataAccessException e) {
			throw new UnauthorizedException("The user is not authorized!");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/follow", method=RequestMethod.GET)
	public Map<String, String> follow(@RequestParam(required=true) int currUserId, @RequestParam(required=true) int followUserId) throws Exception {
		if (!userDAO.checkUserExist(currUserId)) {
			throw new ResourceNotFoundException("The user does not exist!");
		}
		try {
			if(userDAO.userAuthentication().getUserId() != currUserId) {
				throw new UnauthorizedException("The user is not authorized!");
			}
			Map<String, String> result = new HashMap<String, String>();
			if (currUserId == followUserId) {
				result.put("Follow", "ERROR: Cannot follow self!");
			}
			else if(userDAO.checkFollowed(followUserId, currUserId) == 1) {
				result.put("Follow", "ERROR: Already followed!");
			}
			else if (userDAO.followUser(currUserId, followUserId) == 1) {
				result.put("Follow", "Successfully followed!");
			}
			return result;
		} catch(EmptyResultDataAccessException e) {
			throw new UnauthorizedException("The user is not authorized!");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/unfollow", method=RequestMethod.GET)
	public Map<String, String> unfollow(@RequestParam(required=true) int currUserId, @RequestParam(required=true) int unfollowUserId) throws Exception {
		if (!userDAO.checkUserExist(currUserId)) {
			throw new ResourceNotFoundException("The user does not exist!");
		}
		try {
			if(userDAO.userAuthentication().getUserId() != currUserId) {
				throw new UnauthorizedException("The user is not authorized!");
			}
			Map<String, String> result = new HashMap<String, String>();
			if(currUserId == unfollowUserId) {
				result.put("Unfollow", "ERROR: Cannot unfollow self!");
			}
			else if (userDAO.checkFollowed(unfollowUserId, currUserId) == 0) {
				result.put("Unfollow", "ERROR: Not yet followed!");
			}
			else if(userDAO.unfollowUser(currUserId, unfollowUserId) == 1) {
				result.put("Unfollow", "Successfully unfollowed!");
			}
			return result;
		} catch(EmptyResultDataAccessException e) {
			throw new UnauthorizedException("The user is not authorized!");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/followers-and-following-users", method=RequestMethod.GET)
	public Map<String, List<String>> getFollowersAndFollowingUsers(@RequestParam(required=true) int userId) throws Exception {
		if (!userDAO.checkUserExist(userId)) {
			throw new ResourceNotFoundException("The user does not exist!");
		}
		try {
			if(userDAO.userAuthentication().getUserId() != userId) {
				throw new UnauthorizedException("The user is not authorized!");
			}
			Map<String, List<String>> result = new HashMap<String, List<String>>();
			result.put("People I am following", userDAO.getFollowings(userId));
			result.put("People following me", userDAO.getFollowers(userId));
			return result;
		} catch(EmptyResultDataAccessException e) {
			throw new UnauthorizedException("The user is not authorized!");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/login")
	public Map<String, String> userLogin(@RequestParam(required=true) int userId) throws Exception {
		if(!userDAO.checkUserExist(userId)) {
			throw new ResourceNotFoundException("The user does not exist!");
		} else {
			Map<String, String> result = new HashMap<String, String>();
			userDAO.userLogin(userId);
			result.put("Login", "Successfully logged in!");
			return result;
		}
	}
}
