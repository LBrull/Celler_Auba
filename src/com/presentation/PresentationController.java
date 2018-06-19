package com.presentation;

public class PresentationController {

    private static PresentationController instance = null;

    private PresentationController() {
    }

    public static PresentationController getInstance() {
        if (instance == null) {
            instance = new PresentationController();
        }
        return instance;
    }
}
