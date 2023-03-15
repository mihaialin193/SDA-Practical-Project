package com.sda.practicalproject;

import com.sda.practicalproject.utils.SessionManager;

public class Main {
    public static void main(String[] args) {
        SessionManager.getSessionFactory().openSession();


        SessionManager.shutdown();
    }
}