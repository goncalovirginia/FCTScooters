
public class Trot {

	private int idTrot, alugueres, totalMinutos, idClient;
	private String matricula;
	private boolean status;
	
	public Trot(int idTrot, String matricula) {
		this.idTrot = idTrot;
		this.matricula = matricula;
		alugueres = 0;
		totalMinutos = 0;
		status = false;
	}
	
	public int getId() {
		return idTrot;
	}
	
	public int getAlugueres() {
		return alugueres;
	}
	
	public void addMinutos(int minutos) {
		totalMinutos += minutos;
	}
	
	public int getTotalMinutos() {
		return totalMinutos;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public void rent() {
		status = true;
		alugueres++;
	}
	
	public void deactivate() {
		status = false;
	}
	
	public String getStatus() {
		if (status == true) {
			return "alugada";
		}
		else {
			return "parada";
		}
	}
	
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	
	public int getIdClient() {
		return idClient;
	}
	
}
