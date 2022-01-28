package application;


import domain.Tweet;
import domain.TweetRepository;

import java.util.Date;
import java.util.Random;

public final class PostTweet {

    private final TweetRepository repository;

    public PostTweet(TweetRepository repository) {
        this.repository = repository;
    }

    public void post(String user, String text ) {

        System.out.println("Command Executed:: post ");

        Tweet tweet = new Tweet();
        tweet.setUser(user);
        tweet.setText(text);
        tweet.setCreatedAt(new Date());
        //UUID client param
        tweet.setId(new Random().nextLong());

        repository.post(tweet);
    }
}
