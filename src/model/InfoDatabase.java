package model;

public class InfoDatabase {
	
	public static final String TABLE_CLIENT = "client";

	public static final String CNP_CLIENT = "CNP";
	public static final String NUME_CLIENT = "Nume";
	public static final String PRENUME_CLIENT = "Prenume";
	public static final String ADRESA_CLIENT = "Adresa";
	public static final String NR_TELEFON_CLIENT = "Nr_telefon";
	
	
	public static final String TABLE_COMANDA = "comanda";
	
	public static final String ID_COMANDA = "ID_comanda";
	public static final String NR_COMANDA = "Nr_comanda";
	public static final String CNP_CLIENT_COMANDA = "CNP_client";
	public static final String DATA_COMANDA = "data_comanda";
	
	
	public static final String TABLE_DETALII_COMANDA = "detalii_comanda";
	
	public static final String ID_DETALII_COMANDA = "ID_Detalii_comanda";
	public static final String ID_COMANDA_DETALII_COMANDA = "ID_comanda";
	public static final String COD_PRODUS_DETALII_COMANDA = "Cod_produs";
	public static final String NR_BUC_COMANDATE_DETALII_COMANDA = "Nr_buc_comandate";
	
	
	public static final String TABLE_PRODUS = "produs";
	
	public static final String COD_PRODUS = "Cod_produs";
	public static final String NUME_PRODUS = "Nume";
	public static final String NR_BUC_STOC = "Nr_buc_stoc";
	public static final String PRET = "Pret";

}