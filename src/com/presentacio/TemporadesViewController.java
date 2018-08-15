package com.presentacio;

public class TemporadesViewController {
    private static TemporadesViewController instance = null;
    //private static DBTemporadesController dbTemporadesController = DBTemporadesController.getInstance();

    // TODO: vistes associades al controlador
    //private TemporadesView temporadesView;

    private TemporadesViewController() {
    }

    public static TemporadesViewController getInstance() {
        if (instance == null) {
            instance = new TemporadesViewController();
        }
        return instance;
    }

    public void showTemporadesView() {
        //new TemporadesView();
    }
}
