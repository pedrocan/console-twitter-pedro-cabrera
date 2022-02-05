package infrastructure.ui;

import domain.Tweet;

import java.text.SimpleDateFormat;
import java.util.List;

public class ConsolePrinterTweets {

    public static void printPrettier(List<Tweet> tweets){

        String pattern = "dd-MM-yyyy HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        for(Tweet tweet : tweets){
            System.out.println(tweet.getUser() + " ->" + tweet.getText() + "(" + simpleDateFormat.format( tweet.getCreatedAt() ) +")");
        }

    }
}
