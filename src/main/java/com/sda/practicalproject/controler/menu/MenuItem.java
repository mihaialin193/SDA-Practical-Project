package com.sda.practicalproject.controler.menu;

public enum MenuItem {
    ADD_VET(1, "Add a Vet"),

    UPDATE_VET(2, "Update a Vet"),

    DELETE_VET(3, "Delete a Vet"),

    VIEW_VET_LIST(4, "View Vet list"),

    VIEW_VET_BY_ID(5,"View Vet by ID"),

    ADD_PET(6, "Add a Pet"),

    VIEW_PET_LIST(7,"View Pet list"),

    VIEW_PET_BY_ID(8,"View vet by id"),

    EXIT(100,"Quit app"),

    UNKNOWN(9999, "Unknown option");

    private final int option;

    private final String displayName;



    MenuItem(int option, String displayName) {
        this.option = option;
        this.displayName = displayName;
    }

    public int getOption() {
        return option;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static void printMenuItems() {
        for (MenuItem value : values()) {
            if (value == UNKNOWN) {
                break;
            }
            System.out.println(value.option + " -> " + value.displayName);
        }
    }

    public static MenuItem searchByOption(int inputOption){
        for (MenuItem value : values()) {
            if(value.option== inputOption){
                return value;
            }
        }
        return UNKNOWN;
    }
}
