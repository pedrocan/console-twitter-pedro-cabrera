package infrastructure.ui;

import application.*;
import domain.Tweet;
import infrastructure.InMemoryTweetRepository;
import infrastructure.InMemoryUserRepository;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ProcessorCommandInput {

    private static final String POST_COMMAND    = "->";
    private static final String FOLLOWS_COMMAND = "follows";
    private static final String WALL_COMMAND    = "wall";

    //For demo injection
    static final InMemoryTweetRepository inMemoryTweetRepository = new InMemoryTweetRepository();
    static final InMemoryUserRepository  inMemoryUserRepository  = new InMemoryUserRepository();

    public static void process(String input){

        Matcher regexMatcher = prepareCommandRegExp(input);

        executeCommandReadTweets(input, regexMatcher);

        executeCommandWithParams(regexMatcher);
    }

    private static Matcher prepareCommandRegExp(String input) {

        //Command detect regexp
        //^([a-zA-Z]+)\s(\bwall\b|\bfollows\b|->)(\s.*)

        Pattern regex = Pattern.compile("^([a-zA-Z]+)\\s(\\bwall\\b|\\bfollows\\b|->)(.*)",
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE );

        return regex.matcher(input);

    }

    private static void executeCommandReadTweets(String input, Matcher regexMatcher) {

        if(!regexMatcher.find()) {

            //LAUNCH READ OWN TWEETS
            AllTweetsByUserSearcher allTweetsByUserSearcher = new AllTweetsByUserSearcher(inMemoryTweetRepository);
            List<Tweet>             tweetList               = allTweetsByUserSearcher.searchAll(input);
            ConsolePrinterTweets.printPrettier(tweetList);

        }
    }

    private static void executeCommandWithParams(Matcher regexMatcher) {

        String user;
        String command;
        String text;

        if (regexMatcher.groupCount() == 3) {

            user = regexMatcher.group(1);
            command = regexMatcher.group(2);
            text = regexMatcher.group(3).replaceFirst(" ", "");

            //Extract pattern
            if (POST_COMMAND.equals(command)) {

                try {

                    PostTweet postTweet = new PostTweet(inMemoryTweetRepository);
                    postTweet.post(user, text);

                } catch (IllegalArgumentException exception) {
                    System.err.println(exception.getMessage());
                }

            }
            if (FOLLOWS_COMMAND.equals(command)) {
                FollowUser followUser = new FollowUser(inMemoryUserRepository);
                followUser.follow(user, text);
            }
            if (WALL_COMMAND.equals(command)) {
                WallTweets  wallTweets = new WallTweets(inMemoryTweetRepository, inMemoryUserRepository);
                List<Tweet> tweetList  = wallTweets.wall(user);
                ConsolePrinterTweets.printPrettier(tweetList);

            }

        }
    }
}
