package controller;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import model.Comanda;
import model.InfoDatabase;

public class ComandaTableImpl extends AbstractTableModel {

    private List<Comanda> lista_comenzi;

    public ComandaTableImpl(List<Comanda> lista_comenzi) {
        this.lista_comenzi = lista_comenzi;
    }

    private String[] nume_coloane = {InfoDatabase.ID_COMANDA, InfoDatabase.NR_COMANDA, InfoDatabase.CNP_CLIENT_COMANDA, InfoDatabase.DATA_COMANDA };

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
        return lista_comenzi.size();
    }

    @Override
    public Object getValueAt(int row, int column) {
        if(row < 0 || row >= lista_comenzi.size()) return null;
        Comanda obj = lista_comenzi.get(row);
        switch(column)
        {
            case 0: return obj.getIdComanda();
            case 1: return obj.getNrComanda();
            case 2: return obj.getCnpClient();
            case 3: return obj.getDataComandaComanda();
            default: return null;
        }
    }
}