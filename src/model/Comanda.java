package model;

public class Comanda {

	private int id_comanda;
	private int nr_comanda;
	private String cnp_client;
	private String data_comanda;
	
	public Comanda() {
		super();
	}
	
	/**
	 * @param id_comanda
	 * @param nr_comanda
	 * @param cnp_client
	 * @param data_comanda
	 */
	
	public Comanda(int id_comanda, int nr_comanda, String cnp_client, String data_comanda) {
		
		this.id_comanda = id_comanda;
		this.nr_comanda = nr_comanda;
		this.cnp_client = cnp_client;
		this.data_comanda = data_comanda;
	}
	
	public int getIdComanda() {
		return id_comanda;
	}
	
	public void setIdComanda(int id_comanda) {
		this.id_comanda = id_comanda;
	}
	
	public int getNrComanda() {
		return nr_comanda;
	}
	
	public void setNrComanda(int nr_comanda) {
		this.nr_comanda = nr_comanda;
	}
	
	public String getCnpClient() {
		return cnp_client;
	}
	
	public void setCnpClient(String cnp_client) {
		this.cnp_client = cnp_client;
	}
	
	public String getDataComandaComanda() {
		return data_comanda;
	}
	
	public void setDataComandaComanda(String data_comanda) {
		this.data_comanda = data_comanda;
	}
	
}
