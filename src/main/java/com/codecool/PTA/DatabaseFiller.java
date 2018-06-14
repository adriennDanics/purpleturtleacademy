package com.codecool.PTA;

import com.codecool.PTA.config.AssignmentConfig;
import com.codecool.PTA.persistence.PersistenceImplementation;

public class DatabaseFiller {
    public static void main(String[] args) {
        PersistenceImplementation pim = new PersistenceImplementation("ptaPU");
        AssignmentConfig assignmentConfig = new AssignmentConfig(pim);
        assignmentConfig.fillDB();
    }
}
