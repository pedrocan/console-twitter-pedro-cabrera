package application;

import domain.UserRepository;

public class FollowUser {

    private final UserRepository userRepository;

    public FollowUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void follow(String userFrom, String userTo){

        System.out.println("Command Executed:: follow ");

        userRepository.follow(userFrom, userTo);
    }
}
