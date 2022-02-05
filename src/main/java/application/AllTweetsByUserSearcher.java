package application;

import domain.Tweet;
import domain.TweetRepository;

import java.util.List;

public class AllTweetsByUserSearcher {

    private final TweetRepository repository;

    public AllTweetsByUserSearcher(TweetRepository repository) {
        this.repository = repository;
    }

    public List<Tweet> searchAll(String user){

        System.out.println("Command Executed:: tweets ");

        return repository.fetchTweets(user);
    }
}
