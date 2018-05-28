package service;

import Interfaces.ProfileDao;
import domain.Invoice;
import domain.Profile;
import exception.CouldNotCreateProfileException;
import rest.CollectInvoicesREst;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class RekeningAdministratieService {

    @Inject
    CollectInvoicesREst rest;
  
    @Inject
    ProfileDao profileDao;

    public List<Invoice> GetInvoicesByVehicleAndOwner(int trackerId, int ownerId) {
        return rest.getVehicleOwnerInvoices(trackerId + "", ownerId);
    }

    public void authenticate(String login, String password) throws SecurityException {
        try{
            profileDao.authenticate(login, password);
        }
        catch(Exception e){
            throw new SecurityException(e.getMessage());
        }
    }

    public String issueToken(String login) {
        return profileDao.issueToken(login);
    }

    public Profile addProfile(String login, String password) throws CouldNotCreateProfileException {
        return profileDao.addProfile(login, password);
    }
}