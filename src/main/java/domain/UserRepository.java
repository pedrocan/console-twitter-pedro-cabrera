package domain;

import java.util.List;

public interface UserRepository {

    public void follow(String userFrom, String userTo);

    public List<String> getFollowers(String user);

    public List<String> getUsersFollow(String user);

}
