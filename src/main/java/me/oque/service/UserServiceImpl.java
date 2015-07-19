package me.oque.service;

import me.oque.entity.UserInfo;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * User service with OWASP recommended implementation for save and auth
 *
 * Created by Dmitry Smorzhok on 19.07.15.
 */
@Service("userService")
public class UserServiceImpl extends SelectionServiceImpl implements UserService {

    private static final int ITERATION_NUMBER = 1000;

    @Override
    public void saveUser(UserInfo user) {
        if (user != null && StringUtils.hasText(user.getPasswordHash())) {
            // Uses a secure Random not a simple Random
            SecureRandom random;
            try {
                random = SecureRandom.getInstance("SHA1PRNG");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            // Salt generation 64 bits long
            byte[] bSalt = new byte[8];
            random.nextBytes(bSalt);
            // Digest computation
            byte[] bDigest;
            try {
                bDigest = getHash(ITERATION_NUMBER, user.getPasswordHash(), bSalt);
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            BASE64Encoder endecoder = new BASE64Encoder();
            String sDigest = endecoder.encode(bDigest);
            String sSalt = endecoder.encode(bSalt);
            user.setPasswordHash(sDigest);
            user.setSalt(sSalt);
            selectionDao.save(user);
        }
    }

    @Override
    public boolean authenticateUser(String username, String password) {
        boolean userExist = true;
        if (username == null || password == null) {
            // Time resistant attack
            // Computation time is equal to the time needed by a legitimate user
            userExist = false;
            username = "";
            password = "";
        }
        DetachedCriteria query = DetachedCriteria.forClass(UserInfo.class)
                .add(Restrictions.eq("nickname", username));
        UserInfo user = selectionDao.getObjectByQuery(query);
        String digest, salt;
        if (user != null) {
            digest = user.getPasswordHash();
            salt = user.getSalt();
            if (digest == null || salt == null) {
                throw new RuntimeException("Database inconsistant Salt or Digested Password altered");
            }
        } else {
            // Time resistant attack (Even if the user does not exist the
            // Computation time is equal to the time needed for a legitimate user
            digest = "000000000000000000000000000=";
            salt = "00000000000=";
            userExist = false;
        }

        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bDigest;
        byte[] bSalt;
        byte[] proposedDigest;
        try {
            bDigest = decoder.decodeBuffer(digest);
            bSalt = decoder.decodeBuffer(salt);
            proposedDigest = getHash(ITERATION_NUMBER, password, bSalt);
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return Arrays.equals(proposedDigest, bDigest) && userExist;
    }

    /**
     * From a password, a number of iterations and a salt,
     * returns the corresponding digest
     * @param iterationNb int The number of iterations of the algorithm
     * @param password String The password to encrypt
     * @param salt byte[] The salt
     * @return byte[] The digested password
     */
    private byte[] getHash(int iterationNb, String password, byte[] salt)
            throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        digest.reset();
        digest.update(salt);
        byte[] input = digest.digest(password.getBytes("UTF-8"));
        for (int i = 0; i < iterationNb; i++) {
            digest.reset();
            input = digest.digest(input);
        }
        return input;
    }

}
