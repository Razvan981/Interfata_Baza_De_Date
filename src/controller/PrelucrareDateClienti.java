package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import model.*;


public class PrelucrareDateClienti {

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private List<Client> lista_clienti;

    public PrelucrareDateClienti(List<Client> lista_clienti) {   	
        this.lista_clienti = lista_clienti;
    }
    

    private void readClientTable(String SQL) throws SQLException {
        try {
          connect();
          statement = connection.createStatement();
          resultSet = statement.executeQuery(SQL);

          lista_clienti.clear();

          while (resultSet.next())
          {
        	  lista_clienti.add(new Client(resultSet.getString(1), resultSet.getString(2), 
        			  resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
          } 

        } catch (Exception e) {
            System.out.println(e);
            System.err.println("SQL: " + SQL);
        } finally {
          connection.close();
        }

      }

    public void readAllClientTable() {
        try {
        	readClientTable("SELECT * FROM " + InfoDatabase.TABLE_CLIENT);
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void searchInClientTable(String cnp, String nume, String prenume, String adresa, String nr_telefon, boolean partialMatch) {
       
    	if (cnp.length() == 0 && nume.length() ==0 && prenume.length() ==0 && adresa.length() ==0 && nr_telefon.length() ==0) {
    		readAllClientTable();
            return;
            
        }

        StringBuilder sbSql = new StringBuilder();

        sbSql.append("SELECT * FROM ");
        sbSql.append(InfoDatabase.TABLE_CLIENT);
        sbSql.append(" WHERE ");

        List<String> whereStatments = new ArrayList<String>();

        if (cnp.length() > 0 && partialMatch) {
            whereStatments.add(InfoDatabase.CNP_CLIENT + " LIKE '%" + cnp + "%'");
        } else if (cnp.length() > 0) {
            whereStatments.add(InfoDatabase.CNP_CLIENT + " = '" + cnp + "'");
        }

        if (nume.length() > 0 && partialMatch) {
            whereStatments.add(InfoDatabase.NUME_CLIENT + " LIKE '%" + nume + "%'");
        } else if (nume.length() > 0) {
            whereStatments.add(InfoDatabase.NUME_CLIENT + " = '" + nume + "'");
        }
        
        if (prenume.length() > 0 && partialMatch) {
            whereStatments.add(InfoDatabase.PRENUME_CLIENT + " LIKE '%" + prenume + "%'");
        } else if (prenume.length() > 0) {
            whereStatments.add(InfoDatabase.PRENUME_CLIENT + " = '" + prenume + "'");
        }
        
        if (adresa.length() > 0 && partialMatch) {
            whereStatments.add(InfoDatabase.ADRESA_CLIENT + " LIKE '%" + adresa + "%'");
        } else if (adresa.length() > 0) {
            whereStatments.add(InfoDatabase.ADRESA_CLIENT + " = '" + adresa + "'");
        }
        
        if (nr_telefon.length() > 0 && partialMatch) {
            whereStatments.add(InfoDatabase.NR_TELEFON_CLIENT + " LIKE '%" + nr_telefon + "%'");
        } else if (nr_telefon.length() > 0) {
            whereStatments.add(InfoDatabase.NR_TELEFON_CLIENT + " = '" + nr_telefon + "'");
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
        	readClientTable(sbSql.toString());
        } catch (SQLException e) {
            System.out.println("SQL: " + sbSql.toString());
            e.printStackTrace();
        }
    }

    public void addClient(Client client) throws SQLException {
        String sql = "INSERT INTO " + InfoDatabase.TABLE_CLIENT + " (" + InfoDatabase.CNP_CLIENT +" , " 
        		+ InfoDatabase.NUME_CLIENT + ", " + InfoDatabase.PRENUME_CLIENT  + ", " + InfoDatabase.ADRESA_CLIENT 
        		+ ", " + InfoDatabase.NR_TELEFON_CLIENT + ") " + "VALUES (?, ? , ?, ?, ?)";

        if (client == null) {
            JOptionPane.showMessageDialog(null, "  Au fost gasite campuri necompletate. \nVa rugam sa completati toate campurile !!");
            return;
        }

        try {
            connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, client.getCNP());
            preparedStatement.setString(2, client.getNume());
            preparedStatement.setString(3, client.getPrenume());
            preparedStatement.setString(4, client.getAdresa());
            preparedStatement.setString(5, client.getNrTelefon());
            preparedStatement.executeUpdate();
            lista_clienti.add(client);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {
            JOptionPane.showMessageDialog(null, "A fost adaugata o noua inregistrare ! :) ");
            connection.close();
            preparedStatement.close();
        }

    }
    
    public void deleteFromClientTable(String cnp, String nume, String prenume, String adresa,
    		String nr_telefon, boolean partialMatch) throws Exception {
        
    	if (cnp.length() == 0 && nume.length() ==0 && prenume.length() ==0 && adresa.length() ==0 && nr_telefon.length() ==0) {
    		JOptionPane.showMessageDialog(null, "Precizati conditiile de stergere !!");
            return;
            
        }

        StringBuilder sbSql = new StringBuilder();

        sbSql.append("DELETE FROM ");
        sbSql.append(InfoDatabase.TABLE_CLIENT);
        sbSql.append(" WHERE ");

        List<String> whereStatments = new ArrayList<String>();

        if (cnp.length() > 0 && partialMatch) {
            whereStatments.add(InfoDatabase.CNP_CLIENT + " LIKE '%" + cnp + "%'");
        } else if (cnp.length() > 0) {
            whereStatments.add(InfoDatabase.CNP_CLIENT + " = '" + cnp + "'");
        }

        if (nume.length() > 0 && partialMatch) {
            whereStatments.add(InfoDatabase.NUME_CLIENT + " LIKE '%" + nume + "%'");
        } else if (nume.length() > 0) {
            whereStatments.add(InfoDatabase.NUME_CLIENT + " = '" + nume + "'");
        }
        
        if (prenume.length() > 0 && partialMatch) {
            whereStatments.add(InfoDatabase.PRENUME_CLIENT + " LIKE '%" + prenume + "%'");
        } else if (prenume.length() > 0) {
            whereStatments.add(InfoDatabase.PRENUME_CLIENT + " = '" + prenume + "'");
        }
        
        if (adresa.length() > 0 && partialMatch) {
            whereStatments.add(InfoDatabase.ADRESA_CLIENT + " LIKE '%" + adresa + "%'");
        } else if (adresa.length() > 0) {
            whereStatments.add(InfoDatabase.ADRESA_CLIENT + " = '" + adresa + "'");
        }
        
        if (nr_telefon.length() > 0 && partialMatch) {
            whereStatments.add(InfoDatabase.NR_TELEFON_CLIENT + " LIKE '%" + nr_telefon + "%'");
        } else if (nr_telefon.length() > 0) {
            whereStatments.add(InfoDatabase.NR_TELEFON_CLIENT + " = '" + nr_telefon + "'");
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
        	readAllClientTable();
            connection.close();
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, "Stergerea nu a fost aprobata ! :( ");
            System.out.println("SQL: " + sbSql.toString());
            e.printStackTrace();
        }
    }
    
    public void InterogareComplexaClient(String text) throws SQLException {
    	
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
                
                lista_clienti.clear();

                while (resultSet.next())
                {
              	  lista_clienti.add(new Client(resultSet.getString(1), resultSet.getString(2), 
              			  resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
                }
                
        	}else {
        	
        		preparedStatement = connection.prepareStatement(text);
        		preparedStatement.executeUpdate();
        		readAllClientTable();
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