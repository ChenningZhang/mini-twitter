package com.chenningzhang.objectDAO;

import java.util.List;

import com.chenningzhang.model.Tweet;

public interface TweetDAO {
	public List<Tweet> getTweets(int userId, String searchKeyword) throws Exception;
}
