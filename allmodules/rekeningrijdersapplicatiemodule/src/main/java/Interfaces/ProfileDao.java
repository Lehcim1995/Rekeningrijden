package Interfaces;

public interface ProfileDao {

    void authenticate(String username, String password) throws SecurityException;

    String issueToken(String login);
}
