package controller;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import model.Client;
import model.InfoDatabase;

public class ClientTableImpl extends AbstractTableModel {

    private List<Client> lista_clienti;

    public ClientTableImpl(List<Client> lista_clienti) {
        this.lista_clienti = lista_clienti;
    }

    private String[] nume_coloane = {InfoDatabase.CNP_CLIENT, InfoDatabase.NUME_CLIENT, InfoDatabase.PRENUME_CLIENT,
    		InfoDatabase.ADRESA_CLIENT , InfoDatabase.NR_TELEFON_CLIENT };

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
        return lista_clienti.size();
    }

    @Override
    public Object getValueAt(int row, int column) {
        if(row < 0 || row >= lista_clienti.size()) return null;
        Client obj = lista_clienti.get(row);
        switch(column)
        {
            case 0: return obj.getCNP();
            case 1: return obj.getNume();
            case 2: return obj.getPrenume();
            case 3: return obj.getAdresa();
            case 4: return obj.getNrTelefon();
            default: return null;
        }
    }
}