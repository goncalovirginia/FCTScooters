
public class Manager {

	Client client;
	Trot trot;
	int totalRents, totalSpent, totalMinutesLate;

	public Manager() {
		client = null;
		trot = null;
		totalRents = 0;
		totalSpent = 0;
		totalMinutesLate = 0;
	}
	
	public void addClient(String nif, String email, String phone, String name) {
		client = new Client(nif, email, phone, name);
	}

	public void removeClient(String nif) {
		client = null;
	}

	public void addTrot(String idTrot, String licensePlate) {
		trot = new Trot(idTrot, licensePlate);
	}

	public void loadBalance(String nif, int amount) {
		client.loadBalance(amount);
	}

	public void rentTrot(String nif, String idTrot) {
		client.setIdTrot(idTrot);
		trot.rent(nif);
	}
	
	public void releaseTrot(String idTrot, int minutes) {
		client.setIdTrot("");
		client.addMinutes(minutes);
		client.setMaxMinutes(minutes);
		client.unloadBalance(100);
		trot.release(minutes);
		totalRents++;
		totalSpent += 100;
					
		int minutesLate = (minutes - 60);
					
		if (minutesLate > 0) {
			totalMinutesLate += minutesLate;
		}
				
		while (minutesLate > 0) {
			client.unloadBalance(100);
			totalSpent += 100;
			minutesLate -= 30;
		}
	}
	
	public Client getClient() {
		return client;
	}
	
	public Trot getTrot() {
		return trot;
	}
	
	public int getTotalRents() {
		return totalRents;
	}
	
	public int getTotalSpent() {
		return totalSpent;
	}
	
	public int getTotalMinutesLate() {
		return totalMinutesLate;
	}

}
