package model;

public class Client {
	
	private String cnp;
	private String nume;
	private String prenume;
	private String adresa;
	private String nr_telefon;
	
	public Client() {
		super();
	}
	
	/**
	 * @param cnp
	 * @param nume
	 * @param prenume
	 * @param adresa
	 * @param nr_telefon
	 */
	
	public Client(String cnp, String nume, String prenume, String adresa, String nr_telefon) {
		
		this.cnp = cnp;
		this.nume = nume;
		this.prenume = prenume;
		this.adresa = adresa;
		this.nr_telefon = nr_telefon;
	}
	
	public String getCNP() {
		return cnp;
	}
	
	public void setCNP(String cnp) {
		this.cnp = cnp;
	}
	
	public String getNume() {
		return nume;
	}
	
	public void setNume(String nume) {
		this.nume = nume;
	}
	
	public String getPrenume() {
		return prenume;
	}
	
	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}
	
	public String getAdresa() {
		return adresa;
	}
	
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	
	public String getNrTelefon() {
		return nr_telefon;
	}
	
	public void setNrTelefon(String nr_telefon) {
		this.nr_telefon = nr_telefon;
	}
	
}
