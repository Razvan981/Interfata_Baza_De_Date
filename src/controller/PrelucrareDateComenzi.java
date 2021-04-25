package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import model.*;


public class PrelucrareDateComenzi {

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private List<Comanda> lista_comenzi;

    public PrelucrareDateComenzi (List<Comanda> lista_comenzi) {   	
        this.lista_comenzi = lista_comenzi;
    }
    

    private void readComandaTable(String SQL) throws SQLException {
        try {
          connect();
          statement = connection.createStatement();
          resultSet = statement.executeQuery(SQL);

          lista_comenzi.clear();

          while (resultSet.next())
          {
        	  lista_comenzi.add(new Comanda(resultSet.getInt(1), resultSet.getInt(2), 
        			  resultSet.getString(3), resultSet.getString(4)));
          } 

        } catch (Exception e) {
            System.out.println(e);
            System.err.println("SQL: " + SQL);
        } finally {
          connection.close();
        }

      }

    public void readAllComandaTable() {
        try {
        	readComandaTable("SELECT * FROM " + InfoDatabase.TABLE_COMANDA);
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void searchInComandaTable(int id_comanda, int nr_comanda, String cnp_client, String data_comanda, boolean partialMatch, boolean valueLargerThan) {
       
    	if (id_comanda == 0 && nr_comanda ==0 && cnp_client.length() ==0 && data_comanda.length() ==0 ) {
    		readAllComandaTable();
            return;
            
        }

        StringBuilder sbSql = new StringBuilder();

        sbSql.append("SELECT * FROM ");
        sbSql.append(InfoDatabase.TABLE_COMANDA);
        sbSql.append(" WHERE ");

        List<String> whereStatments = new ArrayList<String>();

        if (id_comanda != 0 && valueLargerThan && partialMatch) {
            whereStatments.add(InfoDatabase.ID_COMANDA + " > " + id_comanda);
        } else if (id_comanda != 0 && partialMatch == false) {
            whereStatments.add(InfoDatabase.ID_COMANDA + " = " + id_comanda);
        } else if (id_comanda != 0) {
            whereStatments.add(InfoDatabase.ID_COMANDA + " < " + id_comanda);
        }

        if (nr_comanda != 0 && valueLargerThan && partialMatch) {
            whereStatments.add(InfoDatabase.NR_COMANDA + " > " + nr_comanda);
        } else if (nr_comanda != 0 && partialMatch == false) {
            whereStatments.add(InfoDatabase.NR_COMANDA + " = " + nr_comanda);
        } else if (nr_comanda != 0) {
            whereStatments.add(InfoDatabase.NR_COMANDA + " < " + nr_comanda);
        }
        
        if (cnp_client.length() > 0 && partialMatch) {
            whereStatments.add(InfoDatabase.CNP_CLIENT_COMANDA + " LIKE '%" + cnp_client + "%'");
        } else if (cnp_client.length() > 0) {
            whereStatments.add(InfoDatabase.CNP_CLIENT_COMANDA + " = '" + cnp_client + "'");
        }
        
        if (data_comanda.length() > 0 && partialMatch) {
            whereStatments.add(InfoDatabase.DATA_COMANDA + " LIKE '%" + data_comanda + "%'");
        } else if (data_comanda.length() > 0) {
            whereStatments.add(InfoDatabase.DATA_COMANDA + " = '" + data_comanda + "'");
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
        	readComandaTable(sbSql.toString());
        } catch (SQLException e) {
            System.out.println("SQL: " + sbSql.toString());
            e.printStackTrace();
        }
    }

    public void addComanda(Comanda comanda) throws SQLException {
        String sql = "INSERT INTO " + InfoDatabase.TABLE_COMANDA + " (" + InfoDatabase.ID_COMANDA +" , " + InfoDatabase.NR_COMANDA
        		+ ", " + InfoDatabase.CNP_CLIENT_COMANDA  + ", " + InfoDatabase.DATA_COMANDA  + ") " + "VALUES (?, ? , ?, ?)";

        if (comanda == null) {
        	JOptionPane.showMessageDialog(null, "  Au fost gasite campuri necompletate. \nVa rugam sa completati toate campurile !!");
            return;
        }

        try {
            connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, comanda.getIdComanda());
            preparedStatement.setInt(2, comanda.getNrComanda());
            preparedStatement.setString(3, comanda.getCnpClient());
            preparedStatement.setString(4, comanda.getDataComandaComanda());
            preparedStatement.executeUpdate();
            lista_comenzi.add(comanda);
        } catch (Exception e) {
           	JOptionPane.showMessageDialog(null, "Cererea de adaugare nu a fost aprobata !! :( ");
            System.out.println(e);
            e.printStackTrace();
        } finally {
            connection.close();
            preparedStatement.close();
        }

    }
    
    public void deleteFromComandaTable(int id_comanda, int nr_comanda, String cnp_client, String data_comanda,
    		boolean partialMatch, boolean valueLargerThan) throws Exception {
        
    	if (id_comanda == 0 && nr_comanda ==0 && cnp_client.length() ==0 && data_comanda.length() ==0 ) {
    		JOptionPane.showMessageDialog(null, "Precizati conditiile de stergere !!");
            return;
            
        }

        StringBuilder sbSql = new StringBuilder();

        sbSql.append("DELETE FROM ");
        sbSql.append(InfoDatabase.TABLE_COMANDA);
        sbSql.append(" WHERE ");

        List<String> whereStatments = new ArrayList<String>();

        if (id_comanda != 0 && valueLargerThan && partialMatch) {
            whereStatments.add(InfoDatabase.ID_COMANDA + " > " + id_comanda);
        } else if (id_comanda != 0 && partialMatch == false) {
            whereStatments.add(InfoDatabase.ID_COMANDA + " = " + id_comanda);
        } else if (id_comanda != 0) {
            whereStatments.add(InfoDatabase.ID_COMANDA + " < " + id_comanda);
        }

        if (nr_comanda != 0 && valueLargerThan && partialMatch) {
            whereStatments.add(InfoDatabase.NR_COMANDA + " > " + nr_comanda);
        } else if (nr_comanda != 0 && partialMatch == false) {
            whereStatments.add(InfoDatabase.NR_COMANDA + " = " + nr_comanda);
        } else if (nr_comanda != 0) {
            whereStatments.add(InfoDatabase.NR_COMANDA + " < " + nr_comanda);
        }
        
        if (cnp_client.length() > 0 && partialMatch) {
            whereStatments.add(InfoDatabase.CNP_CLIENT_COMANDA + " LIKE '%" + cnp_client + "%'");
        } else if (cnp_client.length() > 0) {
            whereStatments.add(InfoDatabase.CNP_CLIENT_COMANDA + " = '" + cnp_client + "'");
        }
        
        if (data_comanda.length() > 0 && partialMatch) {
            whereStatments.add(InfoDatabase.DATA_COMANDA + " LIKE '%" + data_comanda + "%'");
        } else if (data_comanda.length() > 0) {
            whereStatments.add(InfoDatabase.DATA_COMANDA + " = '" + data_comanda + "'");
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
        	readAllComandaTable();
            connection.close();
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, "Stergerea nu a fost aprobata ! :( ");
            System.out.println("SQL: " + sbSql.toString());
            e.printStackTrace();
        }
    }
    
    public void InterogareComplexaComanda(String text) throws SQLException {
    	
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
                
                lista_comenzi.clear();

                while (resultSet.next())
                {
              	  lista_comenzi.add(new Comanda(resultSet.getInt(1), resultSet.getInt(2), 
            			  resultSet.getString(3), resultSet.getString(4)));
                }
                
        	}else {
        	
        		preparedStatement = connection.prepareStatement(text);
        		preparedStatement.executeUpdate();
        		readAllComandaTable();
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