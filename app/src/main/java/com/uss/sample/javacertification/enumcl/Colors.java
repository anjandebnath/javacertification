package com.uss.sample.javacertification.enumcl;

public enum Colors {

    RED("#ff0000"),
    BLUE("#3366cc"),
    BLACK("#000000"); // terminated the enum by a semicolon because it contains the attributes and methods

    private String hexValue;

    private Colors(String hexValue) {  //
        this.hexValue = hexValue;
    }

    public String getHexValue() {
        return hexValue;
    }


}
