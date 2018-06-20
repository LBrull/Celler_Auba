package com.presentacio;

public class PresentationController {

    private static PresentationController instance = null;
    private ManagePeople managePeople;

    private PresentationController() {
    }

    public static PresentationController getInstance() {
        if (instance == null) {
            instance = new PresentationController();
            System.out.println("returned presentationController instance");
        }
        return instance;
    }

    public void run(){
        managePeople = new ManagePeople();
    }

}
