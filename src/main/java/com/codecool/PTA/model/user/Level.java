package com.codecool.PTA.model.user;

public enum Level {
    BEGINNER(0),
    INTERMEDIATE(200),
    ADVANCED(500),
    ALUMNI(1000);

    private int entryRequirement;

    Level(int entryRequirement){
        this.entryRequirement = entryRequirement;
    }

    public int getEntryRequirement() {
        return entryRequirement;
    }

    public Level getNextLevel(){
        int next = this.ordinal() + 1;
        if(next > Level.values().length - 1){
            return ALUMNI;
        } else {
            return Level.values()[next];
        }
    }
}
