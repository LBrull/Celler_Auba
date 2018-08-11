package com.presentacio;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

class ContactsTableModel extends DefaultTableModel {

    ContactsTableModel(Object rowData[][], Object columnNames[]) {
        super(rowData, columnNames);
    }
    ContactsTableModel() {}

    @Override
    public void addRow(Vector rowData) {
        super.addRow(rowData);
    }

    @Override
    public Class getColumnClass(int col) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Object object = super.getValueAt(row, column);
        if (object == null) return "";
        else return object;
    }

    public int getRow(String name, String surname) {
        boolean found = false;
        int i=0;
        int res = 0;
        while (!found && i<getRowCount()) {
            if (getValueAt(i, 1).equals(name) && getValueAt(i, 2).equals(surname)) {
                res = i;
                found = true;
            }
            ++i;
        }
        return res;
    }
}
