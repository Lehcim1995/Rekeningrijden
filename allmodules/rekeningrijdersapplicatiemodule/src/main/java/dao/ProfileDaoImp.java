package dao;

import Interfaces.ProfileDao;
import com.google.common.hash.Hashing;
import domain.Profile;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import util.DateUtil;
import util.KeyGenerator;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;

public class ProfileDaoImp implements ProfileDao, Serializable {

    @PersistenceContext(unitName = "rekeningrijdedrappPU")
    private EntityManager em;

    @Inject
    private KeyGenerator keyGenerator;

    @Context
    private UriInfo uriInfo;

    @Inject
    private DateUtil dateutil;

    @Override
    public void authenticate(String username, String password) throws SecurityException {
        try {
            Profile profile = em.createQuery("SELECT p FROM Profile p WHERE p.username = :username AND p.password = :password", Profile.class)
                    .setParameter("username", username).setParameter("password", Hashing.sha256()
                            .hashString(password, StandardCharsets.UTF_8)
                            .toString())
                    .getSingleResult();
        } catch (Exception e) {
            throw new SecurityException("Invalid user/password");
        }
    }

    @Override
    public String issueToken(String login) {
        Key key = keyGenerator.generateKey();
        String jwtToken = Jwts.builder()
                .setSubject(login)
//                .setIssuer(uriInfo.getAbsolutePath().toString() )
                //TODO: Set the correct value on the issuer (what value should be assigned to the issuer?)
                .setIssuer(login)
                .setIssuedAt(new Date())
                .setExpiration(dateutil.toDate(LocalDateTime.now().plusMinutes(15L)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        System.out.println("#### generating token for a key : " + jwtToken + " - " + key);
        return jwtToken;
    }
}
