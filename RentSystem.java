
public class RentSystem {
	
	boolean clientExists, trotExists;
	Client client;
	Trot trot;
	
	
	public RentSystem() {
		clientExists = false;
		trotExists = false;
	}
	
	public void addClient(int nif, String email, int telefone, String nome) {
		clientExists = true;
		client = new Client(nif, email, telefone, nome);
	}
	
	public void addTrot(int idTrot, String matricula) {
		trotExists = true;
		trot = new Trot(idTrot, matricula);
	}
}
