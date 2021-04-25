package model;

public class Produs {

	private String cod_produs;
	private String nume;
	private int nr_buc_stoc;
	private double pret;
	
	public Produs() {
		super();
	}
	
	/**
	 * @param cod_produs
	 * @param nume
	 * @param nr_buc_stoc
	 * @param pret
	 */
	
	public Produs(String cod_produs, String nume, int nr_buc_stoc, double pret) {
		
		this.cod_produs = cod_produs;
		this.nume = nume;
		this.nr_buc_stoc = nr_buc_stoc;
		this.pret = pret;
		
	}
	
	public String getCodProdus() {
		return cod_produs;
	}
	
	public void setCodProdus(String cod_produs) {
		this.cod_produs = cod_produs;
	}
	
	public String getNumeProdus() {
		return nume;
	}
	
	public void setNumeProdus(String nume) {
		this.nume = nume;
	}
	
	public int getNrBucStoc() {
		return nr_buc_stoc;
	}
	
	public void setNrBucStoc(int nr_buc_stoc) {
		this.nr_buc_stoc = nr_buc_stoc;
	}
	
	public double getPret() {
		return pret;
	}
	
	public void setPret(double pret) {
		this.pret = pret;
	}
	
}
