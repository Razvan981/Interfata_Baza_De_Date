package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import model.*;


public class PrelucrareDateProduse {

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private List<Produs> lista_produse;

    public PrelucrareDateProduse(List<Produs> lista_produse) {   	
        this.lista_produse = lista_produse;
    }
    

    private void readProdusTable(String SQL) throws SQLException {
        try {
          connect();
          statement = connection.createStatement();
          resultSet = statement.executeQuery(SQL);

          lista_produse.clear();

          while (resultSet.next())
          {
        	  lista_produse.add(new Produs(resultSet.getString(1), resultSet.getString(2), 
        			  resultSet.getInt(3), resultSet.getDouble(4)));
          } 

        } catch (Exception e) {
            System.out.println(e);
            System.err.println("SQL: " + SQL);
        } finally {
          connection.close();
        }

      }

    public void readAllProdusTable() {
        try {
        	readProdusTable("SELECT * FROM " + InfoDatabase.TABLE_PRODUS);
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void searchInProdusTable(String cod_produs, String nume, int nr_buc_stoc, double pret, boolean partialMatch, boolean valueLargerThan) {
       
    	if (cod_produs.length() == 0 && nume.length() ==0 && nr_buc_stoc ==0 && pret ==0) {
    		readAllProdusTable();
            return;
            
        }

        StringBuilder sbSql = new StringBuilder();

        sbSql.append("SELECT * FROM ");
        sbSql.append(InfoDatabase.TABLE_PRODUS);
        sbSql.append(" WHERE ");

        List<String> whereStatments = new ArrayList<String>();

        if (cod_produs.length() > 0 && partialMatch) {
            whereStatments.add(InfoDatabase.COD_PRODUS + " LIKE '%" + cod_produs + "%'");
        } else if (cod_produs.length() > 0) {
            whereStatments.add(InfoDatabase.COD_PRODUS + " = '" + cod_produs + "'");
        }

        if (nume.length() > 0 && partialMatch) {
            whereStatments.add(InfoDatabase.NUME_PRODUS + " LIKE '%" + nume + "%'");
        } else if (nume.length() > 0) {
            whereStatments.add(InfoDatabase.NUME_PRODUS + " = '" + nume + "'");
        }
        
        if (nr_buc_stoc != 0 && valueLargerThan && partialMatch) {
            whereStatments.add(InfoDatabase.NR_BUC_STOC + " > " + nr_buc_stoc);
        } else if (nr_buc_stoc != 0 && partialMatch == false) {
            whereStatments.add(InfoDatabase.NR_BUC_STOC + " = " + nr_buc_stoc);
        } else if (nr_buc_stoc != 0) {
            whereStatments.add(InfoDatabase.NR_BUC_STOC + " < " + nr_buc_stoc);
        }
        
        if (pret != 0 && valueLargerThan && partialMatch) {
            whereStatments.add(InfoDatabase.PRET + " > " + pret);
        } else if (pret != 0 && partialMatch == false) {
            whereStatments.add(InfoDatabase.PRET + " = " + pret);
        } else if (pret != 0) {
            whereStatments.add(InfoDatabase.PRET + " < " + pret);
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
        	readProdusTable(sbSql.toString());
        } catch (SQLException e) {
            System.out.println("SQL: " + sbSql.toString());
            e.printStackTrace();
        }
    }

    public void addProdus(Produs client) throws SQLException {
        String sql = "INSERT INTO " + InfoDatabase.TABLE_PRODUS + " (" + InfoDatabase.COD_PRODUS +" , " 
        		+ InfoDatabase.NUME_PRODUS + ", " + InfoDatabase.NR_BUC_STOC  + ", " + InfoDatabase.PRET 
        		+ ") " + "VALUES (?, ? , ?, ?)";

        if (client == null) {
        	JOptionPane.showMessageDialog(null, "  Au fost gasite campuri necompletate. \nVa rugam sa completati toate campurile !!");
            return;
        }

        try {
            connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, client.getCodProdus());
            preparedStatement.setString(2, client.getNumeProdus());
            preparedStatement.setInt(3, client.getNrBucStoc());
            preparedStatement.setDouble(4, client.getPret());
            preparedStatement.executeUpdate();
            lista_produse.add(client);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {
        	JOptionPane.showMessageDialog(null, "A fost adaugata o noua inregistrare ! :) ");
            connection.close();
            preparedStatement.close();
        }

    }
    
    public void deleteFromProdusTable(String cod_produs, String nume, int nr_buc_stoc, double pret,
    		boolean partialMatch, boolean valueLargerThan) throws Exception {
        
    	if (cod_produs.length() == 0 && nume.length() ==0 && nr_buc_stoc ==0 && pret ==0) {
    		JOptionPane.showMessageDialog(null, "Precizati conditiile de stergere !!");
            return;
            
        }

        StringBuilder sbSql = new StringBuilder();

        sbSql.append("DELETE FROM ");
        sbSql.append(InfoDatabase.TABLE_PRODUS);
        sbSql.append(" WHERE ");

        List<String> whereStatments = new ArrayList<String>();

        if (cod_produs.length() > 0 && partialMatch) {
            whereStatments.add(InfoDatabase.COD_PRODUS + " LIKE '%" + cod_produs + "%'");
        } else if (cod_produs.length() > 0) {
            whereStatments.add(InfoDatabase.COD_PRODUS + " = '" + cod_produs + "'");
        }

        if (nume.length() > 0 && partialMatch) {
            whereStatments.add(InfoDatabase.NUME_PRODUS + " LIKE '%" + nume + "%'");
        } else if (nume.length() > 0) {
            whereStatments.add(InfoDatabase.NUME_PRODUS + " = '" + nume + "'");
        }
        
        if (nr_buc_stoc != 0 && valueLargerThan && partialMatch) {
            whereStatments.add(InfoDatabase.NR_BUC_STOC + " > " + nr_buc_stoc);
        } else if (nr_buc_stoc != 0 && partialMatch == false) {
            whereStatments.add(InfoDatabase.NR_BUC_STOC + " = " + nr_buc_stoc);
        } else if (nr_buc_stoc != 0) {
            whereStatments.add(InfoDatabase.NR_BUC_STOC + " < " + nr_buc_stoc);
        }
        
        if (pret != 0 && valueLargerThan && partialMatch) {
            whereStatments.add(InfoDatabase.PRET + " > " + pret);
        } else if (pret != 0 && partialMatch == false) {
            whereStatments.add(InfoDatabase.PRET + " = " + pret);
        } else if (pret != 0) {
            whereStatments.add(InfoDatabase.PRET + " < " + pret);
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
        	readAllProdusTable();
            connection.close();
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, "Stergerea nu a fost aprobata ! :( ");
            System.out.println("SQL: " + sbSql.toString());
            e.printStackTrace();
        }
    }
    
    public void InterogareComplexaProdus(String text) throws SQLException {
    	
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
                
                lista_produse.clear();

                while (resultSet.next())
                {
              	  lista_produse.add(new Produs(resultSet.getString(1), resultSet.getString(2), 
            			  resultSet.getInt(3), resultSet.getDouble(4)));
                }
                
        	}else {
        	
        		preparedStatement = connection.prepareStatement(text);
        		preparedStatement.executeUpdate();
        		readAllProdusTable();
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