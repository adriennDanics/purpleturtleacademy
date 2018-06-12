package com.codecool.PTA.helper;

import org.mindrot.jbcrypt.BCrypt;

public class Hash {

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean isPasswordCorrect(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

}
