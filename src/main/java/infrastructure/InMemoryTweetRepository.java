package infrastructure;

import domain.Tweet;
import domain.TweetRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTweetRepository implements TweetRepository {

    private final List<Tweet>                  allTweetsApp = new ArrayList<>();
    private final HashMap<String, List<Tweet>> tweetsByUser = new HashMap<>();

    @Override
    public List<Tweet> fetchTweets(String user) {
       return tweetsByUser.get(user);
    }

    @Override
    public void post(Tweet tweet) {

        allTweetsApp.add(tweet);

        if(tweetsByUser.get(tweet.getUser())== null ){
            List<Tweet> tweetsUser = new ArrayList<>();
            tweetsUser.add(tweet);
            tweetsByUser.put(tweet.getUser(), tweetsUser);
        }else {
            tweetsByUser.get(tweet.getUser()).add(tweet);
        }
    }

}
