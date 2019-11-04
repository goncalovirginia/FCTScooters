
public class Client {

	private String nome, email;
	private int nif, telefone, saldo, totalGastos, alugueres, totalMinutos, maxMinutos, idTrot;
	private boolean trotineteAlugada, ativo;
	
	public Client(int nif, String email, int telefone, String nome) {
		this.nif = nif;
		this.email = email;
		this.telefone = telefone;
		this.nome = nome;
		saldo = 200;
		totalGastos = 0;
		alugueres = 0;
		totalMinutos = 0;
		trotineteAlugada = false;
		ativo = true;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public int getNif() {
		return nif;
	}
	
	public int getTelefone() {
		return telefone;
	}
	
	public void loadSaldo(int amount) {
		saldo += amount;
	}
	
	public void unloadSaldo(int amount) {
		saldo -= amount;
		totalGastos += amount;
	}
	
	public int getSaldo() {
		return saldo;
	}
	
	public int getTotalCentimos() {
		return totalGastos;
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
	
	public void setMaxMinutos(int minutos) {
		if (minutos > maxMinutos) {
			maxMinutos = minutos;
		}
	}
	
	public int getMaxMinutos() {
		return maxMinutos;
	}

	public int getMedMinutos() {
		return (totalMinutos/alugueres);
	}
	
	public void rentTrotinete() {
		trotineteAlugada = true;
	}
	
	public void deactivateTrotinete() {
		trotineteAlugada = false;
	}
	
	public boolean trotineteStatus() {
		return trotineteAlugada;
	}
	
	public void deactivate() {
		ativo = false;
	}
	
	public boolean isActive() {
		return ativo;
	}
	
	public void setIdTrot(int idTrot) {
		this.idTrot = idTrot;
	}
	
	public int getIdTrot() {
		return idTrot;
	}

}
