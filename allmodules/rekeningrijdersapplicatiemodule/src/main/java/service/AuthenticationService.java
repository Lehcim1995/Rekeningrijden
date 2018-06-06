package service;

import Interfaces.ProfileDao;
import domain.Profile;
import exception.CouldNotCreateProfileException;
import model.RegisteredModel;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AuthenticationService {

    @Inject
    private ProfileDao profileDAO;

    public RegisteredModel RegisterProfile(RegisteredModel profile) {

        //TODO request owner
        try {
            profileDAO.addProfile(profile.getEmail(), profile.getPassword(), profile.getBsn());
        } catch (CouldNotCreateProfileException e) {
            e.printStackTrace();
        }

        return profile;
    }

    public Profile findByBSN(int bsn) {
        return profileDAO.findByBSN(bsn);
    }
}
