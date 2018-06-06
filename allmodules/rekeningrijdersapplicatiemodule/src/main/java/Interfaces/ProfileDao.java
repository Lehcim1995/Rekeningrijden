package Interfaces;

import domain.Profile;
import exception.CouldNotCreateProfileException;

public interface ProfileDao {

    void authenticate(String username, String password) throws SecurityException;

    String issueToken(String login);

    Profile addProfile(String login, String password, int bsn) throws CouldNotCreateProfileException;

    Profile findByBSN(int bsn);
}
