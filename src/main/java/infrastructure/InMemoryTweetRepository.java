package infrastructure;

import domain.Tweet;
import domain.TweetRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTweetRepository implements TweetRepository {

    private final List<Tweet> tweets = new ArrayList<>();
    private final HashMap<String, List<Tweet>> tweetsByUser = new HashMap<>();

    @Override
    public List<Tweet> fetchTweets(String user) {
        tweetsByUser.get(user);
    }

    @Override
    public void post(Tweet tweet) {

        tweets.add(tweet);

        if(tweetsByUser.get(tweet.getUser()).isEmpty()){
            tweetsByUser.put(tweet.getUser(), tweets);
        }else {
            tweetsByUser.get(tweet.getUser()).add(tweet);
        }
    }

}
