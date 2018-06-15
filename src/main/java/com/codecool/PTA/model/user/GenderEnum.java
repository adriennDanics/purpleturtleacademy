package com.codecool.PTA.model.user;

public enum GenderEnum {
    MALE("male", "static/boyTurtle.jpeg"),
    FEMALE("female", "static/girlTurtle.jpg"),
    OTHER("other", "static/otherTurtle.jpg");

    private String stringified;
    private String image;

    GenderEnum(String choices, String imagePath){
        this.stringified = choices;
        this.image = imagePath;
    }

    public String getStringified() {
        return stringified;
    }

    public String getImage() {
        return image;
    }
}
