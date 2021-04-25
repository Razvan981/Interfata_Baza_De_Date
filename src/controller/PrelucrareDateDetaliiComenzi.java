package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import model.*;


public class PrelucrareDateDetaliiComenzi {

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private List<DetaliiComanda> lista_detalii_comenzi;

    public PrelucrareDateDetaliiComenzi (List<DetaliiComanda> lista_detalii_comenzi) {   	
        this.lista_detalii_comenzi = lista_detalii_comenzi;
    }
    

    private void readDetaliiComandaTable(String SQL) throws SQLException {
        try {
          connect();
          statement = connection.createStatement();
          resultSet = statement.executeQuery(SQL);

          lista_detalii_comenzi.clear();

          while (resultSet.next())
          {
        	  lista_detalii_comenzi.add(new DetaliiComanda(resultSet.getInt(1), resultSet.getInt(2), 
        			  resultSet.getString(3), resultSet.getInt(4)));
          } 

        } catch (Exception e) {
            System.out.println(e);
            System.err.println("SQL: " + SQL);
        } finally {
          connection.close();
        }

      }

    public void readAllDetaliiComandaTable() {
        try {
        	readDetaliiComandaTable("SELECT * FROM " + InfoDatabase.TABLE_DETALII_COMANDA);
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void searchInDetaliiComandaTable(int id_detalii_comanda, int id_comanda, String cod_produs, int nr_buc_comandate, boolean partialMatch, boolean valueLargerThan) {
       
    	if (id_detalii_comanda == 0 && id_comanda == 0 && cod_produs.length() == 0 && nr_buc_comandate == 0 ) {
    		readAllDetaliiComandaTable();
            return;
            
        }

        StringBuilder sbSql = new StringBuilder();

        sbSql.append("SELECT * FROM ");
        sbSql.append(InfoDatabase.TABLE_DETALII_COMANDA);
        sbSql.append(" WHERE ");

        List<String> whereStatments = new ArrayList<String>();

        if (id_detalii_comanda != 0 && valueLargerThan && partialMatch) {
            whereStatments.add(InfoDatabase.ID_DETALII_COMANDA + " > " + id_detalii_comanda);
        } else if (id_detalii_comanda != 0 && partialMatch == false) {
            whereStatments.add(InfoDatabase.ID_DETALII_COMANDA + " = " + id_detalii_comanda);
        }else if (id_detalii_comanda != 0) {
            whereStatments.add(InfoDatabase.ID_DETALII_COMANDA + " < " + id_detalii_comanda);
        }

        if (id_comanda != 0 && valueLargerThan && partialMatch) {
            whereStatments.add(InfoDatabase.ID_COMANDA_DETALII_COMANDA + " > " + id_comanda);
        } else if (id_comanda != 0 && partialMatch == false) {
            whereStatments.add(InfoDatabase.ID_COMANDA_DETALII_COMANDA + " = " + id_comanda);
        } else if (id_comanda != 0) {
            whereStatments.add(InfoDatabase.ID_COMANDA_DETALII_COMANDA + " < " + id_comanda);
        }
        
        if (cod_produs.length() > 0 && partialMatch) {
            whereStatments.add(InfoDatabase.COD_PRODUS_DETALII_COMANDA + " LIKE '%" + cod_produs + "%'");
        } else if (cod_produs.length() > 0) {
            whereStatments.add(InfoDatabase.COD_PRODUS_DETALII_COMANDA + " = '" + cod_produs + "'");
        }
        
        if (nr_buc_comandate != 0 && valueLargerThan && partialMatch) {
            whereStatments.add(InfoDatabase.NR_BUC_COMANDATE_DETALII_COMANDA + " > " + nr_buc_comandate);
        } else if (nr_buc_comandate != 0 && partialMatch == false) {
            whereStatments.add(InfoDatabase.NR_BUC_COMANDATE_DETALII_COMANDA + " = " + nr_buc_comandate);
        } else if (nr_buc_comandate != 0) {
            whereStatments.add(InfoDatabase.NR_BUC_COMANDATE_DETALII_COMANDA + " < " + nr_buc_comandate);
        }
        

        Iterator<String> iter = whereStatments.iterator();
        boolean first = true;
        while (iter.hasNext()) {
            if (!first) {
                sbSql.append(" AND ");
            } else {
                first = false;
            }
            sbSql.append(iter.next());          
        }


        try {
        	readDetaliiComandaTable(sbSql.toString());
        } catch (SQLException e) {
            System.out.println("SQL: " + sbSql.toString());
            e.printStackTrace();
        }
    }

    public void addDetaliiComanda(DetaliiComanda detalii_comanda) throws SQLException {
        String sql = "INSERT INTO " + InfoDatabase.TABLE_DETALII_COMANDA + " (" + InfoDatabase.ID_DETALII_COMANDA +" , " + 
        		InfoDatabase.ID_COMANDA_DETALII_COMANDA + ", " + InfoDatabase.COD_PRODUS_DETALII_COMANDA  + ", " + 
        		InfoDatabase.NR_BUC_COMANDATE_DETALII_COMANDA  + ") " + "VALUES (?, ? , ?, ?)";

        if (detalii_comanda == null) {
        	JOptionPane.showMessageDialog(null, "  Au fost gasite campuri necompletate. \nVa rugam sa completati toate campurile !!");
            return;
        }

        try {
            connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, detalii_comanda.getIdDetaliiComanda());
            preparedStatement.setInt(2, detalii_comanda.getIdComanda());
            preparedStatement.setString(3, detalii_comanda.getCodProdus());
            preparedStatement.setInt(4, detalii_comanda.getNrBucComandate());
            preparedStatement.executeUpdate();
            lista_detalii_comenzi.add(detalii_comanda);
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, "Cererea de adaugare nu a fost aprobata !! :( ");
            System.out.println(e);
            e.printStackTrace();
        } finally {
            connection.close();
            preparedStatement.close();
        }

    }
    
    public void deleteFromDetaliiComandaTable(int id_detalii_comanda, int id_comanda, String cod_produs, int nr_buc_comandate,
    		boolean partialMatch, boolean valueLargerThan) throws Exception {
        
    	if (id_detalii_comanda == 0 && id_comanda == 0 && cod_produs.length() == 0 && nr_buc_comandate == 0 ) {
    		JOptionPane.showMessageDialog(null, "Precizati conditiile de stergere !!");
            return;
            
        }

        StringBuilder sbSql = new StringBuilder();

        sbSql.append("DELETE FROM ");
        sbSql.append(InfoDatabase.TABLE_DETALII_COMANDA);
        sbSql.append(" WHERE ");

        List<String> whereStatments = new ArrayList<String>();

        if (id_detalii_comanda != 0 && valueLargerThan && partialMatch) {
            whereStatments.add(InfoDatabase.ID_DETALII_COMANDA + " > " + id_detalii_comanda);
        } else if (id_detalii_comanda != 0 && partialMatch == false) {
            whereStatments.add(InfoDatabase.ID_DETALII_COMANDA + " = " + id_detalii_comanda);
        }else if (id_detalii_comanda != 0) {
            whereStatments.add(InfoDatabase.ID_DETALII_COMANDA + " < " + id_detalii_comanda);
        }

        if (id_comanda != 0 && valueLargerThan && partialMatch) {
            whereStatments.add(InfoDatabase.ID_COMANDA_DETALII_COMANDA + " > " + id_comanda);
        } else if (id_comanda != 0 && partialMatch == false) {
            whereStatments.add(InfoDatabase.ID_COMANDA_DETALII_COMANDA + " = " + id_comanda);
        } else if (id_comanda != 0) {
            whereStatments.add(InfoDatabase.ID_COMANDA_DETALII_COMANDA + " < " + id_comanda);
        }
        
        if (cod_produs.length() > 0 && partialMatch) {
            whereStatments.add(InfoDatabase.COD_PRODUS_DETALII_COMANDA + " LIKE '%" + cod_produs + "%'");
        } else if (cod_produs.length() > 0) {
            whereStatments.add(InfoDatabase.COD_PRODUS_DETALII_COMANDA + " = '" + cod_produs + "'");
        }
        
        if (nr_buc_comandate != 0 && valueLargerThan && partialMatch) {
            whereStatments.add(InfoDatabase.NR_BUC_COMANDATE_DETALII_COMANDA + " > " + nr_buc_comandate);
        } else if (nr_buc_comandate != 0 && partialMatch == false) {
            whereStatments.add(InfoDatabase.NR_BUC_COMANDATE_DETALII_COMANDA + " = " + nr_buc_comandate);
        } else if (nr_buc_comandate != 0) {
            whereStatments.add(InfoDatabase.NR_BUC_COMANDATE_DETALII_COMANDA + " < " + nr_buc_comandate);
        }
        

        Iterator<String> iter = whereStatments.iterator();
        boolean first = true;
        while (iter.hasNext()) {
            if (!first) {
                sbSql.append(" AND ");
            } else {
                first = false;
            }
            sbSql.append(iter.next());          
        }


        try {
        	connect();
        	preparedStatement = connection.prepareStatement(sbSql.toString());
        	preparedStatement.executeUpdate();
        	JOptionPane.showMessageDialog(null, "Stergerea a fost efecutata ! :) ");
        	readAllDetaliiComandaTable();
            connection.close();
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, "Stergerea nu a fost aprobata ! :( ");
            System.out.println("SQL: " + sbSql.toString());
            e.printStackTrace();
        }
    }
    
    public void InterogareComplexaDetaliiComanda(String text) throws SQLException {
    	
    	int ok = 0;

        if (text == null || text.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Interogare inexistenta ! :( ");
            return;
        }

        try {
        	
        	ok = 0;
        	connect();
        	String firstLetter = text.substring(0, 1);  
        	
        	if(firstLetter.equals("s") || firstLetter.equals("S") ) {
        		
                statement = connection.createStatement();
                resultSet = statement.executeQuery(text);
                
                lista_detalii_comenzi.clear();

                while (resultSet.next())
                {
              	  lista_detalii_comenzi.add(new DetaliiComanda(resultSet.getInt(1), resultSet.getInt(2), 
            			  resultSet.getString(3), resultSet.getInt(4)));
                }
                
        	}else {
        	
        		preparedStatement = connection.prepareStatement(text);
        		preparedStatement.executeUpdate();
        		readAllDetaliiComandaTable();
        		preparedStatement.close();
        	}
        
        } catch (Exception e) {
        	++ok;
        	JOptionPane.showMessageDialog(null, "Interogarea introdusa nu a generat niciun rezultat ! :( ");
            System.out.println(e);
            e.printStackTrace();
        } finally {
        	
        	if(ok == 0) {
        		JOptionPane.showMessageDialog(null, "Interogarea este valida si va fi executata ! ^_^ ");
        	}   	
            connection.close();
        }

    }
    
    private void connect() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String myUrl = "jdbc:mysql://localhost:3306/evidentaproduselorhypermarket";
        connection = DriverManager.getConnection(myUrl, "root", "");
    }
    
}