package com.presentacio;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

class ContactsTableModel extends DefaultTableModel{
    ContactsTableModel(Object rowData[][], Object columnNames[]) {
        super(rowData, columnNames);
    }

    public Class getColumnClass(int col) {
        Vector v = (Vector) dataVector.elementAt(0);
        return v.elementAt(col).getClass();
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
