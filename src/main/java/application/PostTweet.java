package application;


import domain.Tweet;
import domain.TweetRepository;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Random;

public final class PostTweet {

    private final TweetRepository repository;

    public PostTweet(TweetRepository repository) {
        this.repository = repository;
    }

    //TODO change -> TweetDTO user + text
    public void post(String user, String text ) throws IllegalArgumentException {

        if(StringUtils.isBlank(user) ||StringUtils.isEmpty(user)) {
            throw new IllegalArgumentException("Invalid user param");
        }

        if(StringUtils.isBlank(text) ||StringUtils.isEmpty(text)){
            throw new IllegalArgumentException("Invalid text param");
        }

        Tweet tweet = new Tweet();
        tweet.setUser(user);
        tweet.setText(text);
        tweet.setCreatedAt(new Date());
        //UUID client param
        tweet.setId(new Random().nextLong());

        repository.post(tweet);

        //"Possible event triggered"
        System.out.println("Command Executed:: post");
    }
}
