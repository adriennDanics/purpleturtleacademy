package com.codecool.PTA.helper;

import org.junit.jupiter.api.*;

import static junit.framework.Assert.*;

public class PasswordHashingTest {

    private PasswordHashing passwordHashing;

    @BeforeEach
    private void init() {
        this.passwordHashing = new PasswordHashing();

    }

    @Test
    public void checkCorrectPassword() {
        String hashStr = passwordHashing.hashPassword("testpassword");
        assertTrue(passwordHashing.isPasswordCorrect("testpassword", hashStr));
    }

    @Test
    public void checkIncorrectPassword() {
        String hashStr = passwordHashing.hashPassword("testpassword");
        assertFalse(passwordHashing.isPasswordCorrect("something", hashStr));
    }

}
