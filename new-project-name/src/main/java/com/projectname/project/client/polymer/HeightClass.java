package com.projectname.project.client.polymer;

public enum HeightClass {
    NORMAL(""),
    MEDIUM_TALL("medium-tall"),
    TALL("tall");

    private final String value;

    HeightClass(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
