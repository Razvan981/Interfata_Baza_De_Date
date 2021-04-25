package controller;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import model.Produs;
import model.InfoDatabase;

public class ProdusTableImpl extends AbstractTableModel {

    private List<Produs> lista_produse;

    public ProdusTableImpl(List<Produs> lista_produse) {
        this.lista_produse = lista_produse;
    }

    private String[] nume_coloane = {InfoDatabase.COD_PRODUS, InfoDatabase.NUME_PRODUS, InfoDatabase.NR_BUC_STOC, InfoDatabase.PRET };

    @Override
    public String getColumnName(int column) {
        return nume_coloane[column];
    }

    @Override
    public int getColumnCount() {
        return nume_coloane.length;
    }

    @Override
    public int getRowCount() {
        return lista_produse.size();
    }

    @Override
    public Object getValueAt(int row, int column) {
        if(row < 0 || row >= lista_produse.size()) return null;
        Produs obj = lista_produse.get(row);
        switch(column)
        {
            case 0: return obj.getCodProdus();
            case 1: return obj.getNumeProdus();
            case 2: return obj.getNrBucStoc();
            case 3: return obj.getPret();
            default: return null;
        }
    }
}