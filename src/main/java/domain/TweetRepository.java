package domain;

import java.util.List;

public interface TweetRepository {

    public List<Tweet> fetchTweets(String user);

    public void post(Tweet tweet);

}
