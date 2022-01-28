package application;

import domain.Tweet;
import domain.TweetRepository;
import domain.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class WallTweets {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    public WallTweets(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository  = userRepository;
    }

    public List<Tweet> wall(String user){

        System.out.println("Command Executed:: wall ");

        List<String> followingUsers = userRepository.getUsersFollow(user);
        List<Tweet> wallTweets = new ArrayList<>();

        for(String userFollow: followingUsers){
            wallTweets.addAll(tweetRepository.fetchTweets(userFollow));
        }

        return wallTweets;

    }
}
