package application;


import domain.Tweet;
import domain.TweetRepository;

import java.util.Date;
import java.util.Random;

public final class TweetCreator {

    private final TweetRepository repository;

    public TweetCreator(TweetRepository repository) {
        this.repository = repository;
    }

    public void create(String user, String text ) {

        Tweet tweet = new Tweet();
        tweet.setUser(user);
        tweet.setText(text);
        tweet.setCreatedAt(new Date());
        //UUID client param
        tweet.setId(new Random().nextLong());

        repository.post(tweet);
    }
}
