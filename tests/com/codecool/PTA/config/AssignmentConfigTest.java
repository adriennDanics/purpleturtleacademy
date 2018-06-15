package com.codecool.PTA.config;

import com.codecool.PTA.persistence.PersistenceImplementation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AssignmentConfigTest {

    @Test
    void testFillData() {
        assertTrue(new AssignmentConfig(mock(PersistenceImplementation.class)).fillData());
    }

}