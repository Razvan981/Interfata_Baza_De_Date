package model;

public class DetaliiComanda {

	private int id_detalii_comanda;
	private int id_comanda;
	private String cod_produs;
	private int nr_buc_comandate;
	
	public DetaliiComanda() {
		super();
	}
	
	/**
	 * @param id_detalii_comanda
	 * @param id_comanda
	 * @param cod_produs
	 * @param nr_buc_comandate
	 */
	
	public DetaliiComanda (int id_detalii_comanda, int id_comanda, String cod_produs, int nr_buc_comandate) {
		
		this.id_detalii_comanda = id_detalii_comanda;
		this.id_comanda = id_comanda;
		this.cod_produs = cod_produs;
		this.nr_buc_comandate = nr_buc_comandate;
	}
	
	public int getIdDetaliiComanda() {
		return id_detalii_comanda;
	}
	
	public void setDetaliiComanda(int id_detalii_comanda) {
		this.id_detalii_comanda = id_detalii_comanda;
	}
	
	public int getIdComanda() {
		return id_comanda;
	}
	
	public void setIdComanda(int id_comanda) {
		this.id_comanda = id_comanda;
	}
	
	public String getCodProdus() {
		return cod_produs;
	}
	public void setCodProdus(String cod_produs) {
		this.cod_produs = cod_produs;
	}
	
	public int getNrBucComandate() {
		return nr_buc_comandate;
	}
	
	public void setNrBucComandate(int nr_buc_comandate) {
		this.nr_buc_comandate = nr_buc_comandate;
	}
	
}
