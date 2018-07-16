package com.presentacio;

import javax.swing.event.ListDataListener;
import java.util.List;

public class AlbaraTypeComboBoxModel implements javax.swing.ComboBoxModel {

    private List<String> array;

    public AlbaraTypeComboBoxModel (List<String> array) {
        this.array = array;
    }

    @Override
    public void setSelectedItem(Object anItem) {

    }

    @Override
    public Object getSelectedItem() {
        return null;
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public Object getElementAt(int index) {
        return null;
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}
