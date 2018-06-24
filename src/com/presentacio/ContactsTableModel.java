package com.presentacio;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

class ContactsTableModel extends DefaultTableModel{

    ContactsTableModel(Object rowData[][], Object columnNames[]) {
        super(rowData, columnNames);
    }

    @Override
    public void addRow(Vector rowData) {
        super.addRow(rowData);
    }

    @Override
    public Class getColumnClass(int col) {
        Vector v = (Vector) dataVector.elementAt(0);
        return v.elementAt(col).getClass();
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
