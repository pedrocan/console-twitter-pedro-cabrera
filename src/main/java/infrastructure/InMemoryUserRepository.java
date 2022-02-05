package infrastructure;

import domain.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryUserRepository implements UserRepository {

    private final HashMap<String, List<String>> followingByUser = new HashMap<>();

    @Override
    public void follow(String userFrom, String userTo) {

        if(followingByUser.get(userFrom) == null ){

            List<String> following = new ArrayList<>();

            following.add(userTo);

            followingByUser.put(userFrom, following);

        }else{

            followingByUser.get(userFrom).add(userTo);
        }
    }

    @Override
    public List<String> getFollowers(String user) {
        return null;
    }

    @Override
    public List<String> getUsersFollow(String user) {
        return followingByUser.get(user);
    }
}
