
public class Trot {

	private String idTrot, licensePlate, idClient;
	private int rents, totalMinutes;
	
	public Trot(String idTrot, String licensePlate) {
		this.idTrot = idTrot;
		this.licensePlate = licensePlate;
		rents = 0;
		totalMinutes = 0;
		idClient = "";
	}
	
	public String getId() {
		return idTrot;
	}
	
	public int getRents() {
		return rents;
	}
	
	public int getTotalMinutes() {
		return totalMinutes;
	}
	
	public String getLicensePlate() {
		return licensePlate;
	}
	
	public void rent(String idClient) {
		this.idClient = idClient;
	}
	
	public void release(int minutos) {
		idClient = "";
		totalMinutes += minutos;
		rents++;
	}
	
	public String getStatus() {
		if (idClient.equals("")) {
			return "parada";
		}
		else {
			return "alugada";
		}
	}
	
	public String getIdClient() {
		return idClient;
	}
	
}
