import application.AllTweetsByUserSearcher;
import application.FollowUser;
import application.PostTweet;
import application.WallTweets;
import domain.Tweet;
import infrastructure.InMemoryTweetRepository;
import infrastructure.InMemoryUserRepository;
import infrastructure.ui.ConsolePrinterTweets;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleTwitter {

    private static final String POST_COMMAND = "->";
    private static final String FOLLOWS_COMMAND = "follows";
    private static final String WALL_COMMAND       = "wall";

    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        InMemoryTweetRepository inMemoryTweetRepository =new InMemoryTweetRepository();
        InMemoryUserRepository inMemoryUserRepository = new InMemoryUserRepository();

        //Añadir instrucciones de uso
        System.out.println("Probando.....");

        String input = scan.nextLine();

        //Command detect regexp
        //^([a-zA-Z]+)\s(\bwall\b|\bfollows\b|->)(\s.*)

        Pattern regex = Pattern.compile("^([a-zA-Z]+)\\s(\\bwall\\b|\\bfollows\\b|->)(\\s.*)",
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE );

        Matcher regexMatcher = regex.matcher(input);

        String user = "", command = "", text = "" ;

        if(regexMatcher.find()){

            user = regexMatcher.group(1);
            command = regexMatcher.group(2);
            text = regexMatcher.group(3);

        }else{
            user = input;
            //LAUNCH READ OWN TWEETS
            AllTweetsByUserSearcher allTweetsByUserSearcher = new AllTweetsByUserSearcher(inMemoryTweetRepository);
            List<Tweet>               tweetList             = allTweetsByUserSearcher.searchAll(user);
            ConsolePrinterTweets.printPrettier(tweetList);

        }

        if(POST_COMMAND.equals(command)){
            PostTweet postTweet = new PostTweet(inMemoryTweetRepository);
            postTweet.post(user, text);
        }
        if(FOLLOWS_COMMAND.equals(command)){
            FollowUser followUser = new FollowUser(inMemoryUserRepository);
            followUser.follow(user, text);
        }
        if(WALL_COMMAND.equals(command)){
            WallTweets wallTweets = new WallTweets(inMemoryTweetRepository,inMemoryUserRepository);
            List<Tweet> tweetList = wallTweets.wall(user);
            ConsolePrinterTweets.printPrettier(tweetList);

        }

        //Extraer procesador de comandos
        while(!"exit".equals(input)){

            System.out.println("Has introducido : " + input);
            input = scan.nextLine();

        }


    }
}
