package application;

import domain.Tweet;
import domain.TweetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PostTweetShould {

    @Mock
    TweetRepository tweetRepository;

    private PostTweet postUnderTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        postUnderTest = new PostTweet(tweetRepository);
    }

    @Test
    void expected_exception_invalid_user_param(){

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> postUnderTest.post("","text post"),
                "Invalid user param"
        );

        assertTrue(thrown.getMessage().contains("Invalid user param"));

    }

    @Test
    void expected_exception_invalid_text_param(){

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> postUnderTest.post("pedro@gmail.com",""),
                "Invalid text param"
        );

        assertTrue(thrown.getMessage().contains("Invalid text param"));

    }

    @Test
    void create_new_post_is_ok(){

        postUnderTest.post("pedro@gmail.com","post test");

        verify(tweetRepository,times(1)).post(Mockito.any(Tweet.class));
    }

}
