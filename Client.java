
public class Client {

	private String nif, email, phone, name, idTrot;
	private int balance, totalMinutes, rents, maxMinutes, totalSpent;
	
	public Client(String nif, String email, String phone, String name) {
		this.nif = nif;
		this.email = email;
		this.phone = phone;
		this.name = name;
		balance = 200;
		totalSpent = 0;
		rents = 0;
		totalMinutes = 0;
		idTrot = "";
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getNif() {
		return nif;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void loadBalance(int amount) {
		balance += amount;
	}
	
	public void unloadBalance(int amount) {
		balance -= amount;
		totalSpent += amount;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public int getTotalSpent() {
		return totalSpent;
	}
	
	public int getRents() {
		return rents;
	}
	
	public void addMinutes(int minutes) {
		totalMinutes += minutes;
	}
	
	public int getTotalMinutes() {
		return totalMinutes;
	}
	
	public void setMaxMinutes(int minutes) {
		if (minutes > maxMinutes) {
			maxMinutes = minutes;
		}
	}
	
	public int getMaxMinutes() {
		return maxMinutes;
	}

	public int getAvgMinutes() {
		if (rents == 0) {
			return 0;
		}
		else {
			return (totalMinutes/rents);
		}
	}
	
	public void setIdTrot(String idTrot) {
		this.idTrot = idTrot;
		
		if (idTrot.equals("")) {
			rents++;
		}
	}
	
	public String getIdTrot() {
		return idTrot;
	}

}
