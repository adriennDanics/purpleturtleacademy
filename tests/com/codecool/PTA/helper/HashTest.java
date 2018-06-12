package com.codecool.PTA.helper;

import org.junit.jupiter.api.*;

import static junit.framework.Assert.*;

public class HashTest {

    private Hash hash;

    @BeforeEach
    private void init() {
        this.hash = new Hash();

    }

    @Test
    public void checkCorrectPassword() {
        String hashStr = hash.hashPassword("testpassword");
        assertTrue(hash.isPasswordCorrect("testpassword", hashStr));
    }

    @Test
    public void checkIncorrectPassword() {
        String hashStr = hash.hashPassword("testpassword");
        assertFalse(hash.isPasswordCorrect("something", hashStr));
    }

}
