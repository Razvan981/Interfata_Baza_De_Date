package controller;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import model.DetaliiComanda;
import model.InfoDatabase;

public class DetaliiComandaTableImpl extends AbstractTableModel {

    private List<DetaliiComanda> lista_detalii_comenzi;

    public DetaliiComandaTableImpl(List<DetaliiComanda> lista_detalii_comenzi) {
        this.lista_detalii_comenzi = lista_detalii_comenzi;
    }

    private String[] nume_coloane = {InfoDatabase.ID_DETALII_COMANDA, InfoDatabase.ID_COMANDA_DETALII_COMANDA, 
    		InfoDatabase.COD_PRODUS_DETALII_COMANDA, InfoDatabase.NR_BUC_COMANDATE_DETALII_COMANDA };

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
        return lista_detalii_comenzi.size();
    }

    @Override
    public Object getValueAt(int row, int column) {
        if(row < 0 || row >= lista_detalii_comenzi.size()) return null;
        DetaliiComanda obj = lista_detalii_comenzi.get(row);
        switch(column)
        {
            case 0: return obj.getIdDetaliiComanda();
            case 1: return obj.getIdComanda();
            case 2: return obj.getCodProdus();
            case 3: return obj.getNrBucComandate();
            default: return null;
        }
    }
}