import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Client client = null;
		Trot trot = null;
		Manager manager = new Manager();
		Scanner in = new Scanner(System.in);
		String option = "";

		while (!option.equals("SAIR")) {
			option = readOption(in);
			executeOption(in, option, client, trot, manager);
		}
		
		System.out.println("Saindo...");
		managerStatus(in, manager);

		in.close();
	}

	private static String readOption(Scanner in) {
		return in.next().toUpperCase();
	}

	private static void executeOption(Scanner in, String option, Client client, Trot trot, Manager manager) {

		switch (option) {
		case "ADCLIENTE":
			addClient(in, manager);
			break;
		case "REMCLIENTE":
			removeClient(in, manager);
			break;
		case "ADTROT":
			addTrot(in, manager);
			break;
		case "DADOSCLIENTE":
			clientInfo(in, manager);
			break;
		case "TROT":
			getTrotFromClient(in, manager);
			break;
		case "DADOSTROT":
			trotInfo(in, manager);
			break;
		case "CLIENTE":
			getClientFromTrot(in, manager);
			break;
		case "CARRSALDO":
			loadBalance(in, manager);
			break;
		case "ALUGAR":
			rentTrot(in, manager);
			break;
		case "LIBERTAR":
			releaseTrot(in, manager);
			break;
		case "ESTADOSISTEMA":
			managerStatus(in, manager);
			break;
		case "SAIR":
			break;
		default:
			System.out.println("Comando invalido.");
			in.nextLine();
			break;
		}
	}

	private static void addClient(Scanner in, Manager manager) {
		String nif = in.next();
		String email = in.next();
		String phone = in.next();
		String name = in.next() + in.nextLine();
		
		Client client = manager.getClient();

		if (client == null) {
			manager.addClient(nif, email, phone, name);
			System.out.println("Insercao de cliente com sucesso.");
		}
		else if (client.getNif().toUpperCase().equals(nif.toUpperCase())) {
			System.out.println("Cliente existente.");
		}
		else {
			System.out.println("Cliente existente.");
		}
	}

	private static void removeClient(Scanner in, Manager manager) {
		String nif = in.next();
		in.nextLine();

		Client client = manager.getClient();
		
		if (client != null && client.getNif().toUpperCase().equals(nif.toUpperCase())) {
			if (client.getIdTrot().equals("")) {
				manager.removeClient(nif);
				System.out.println("Cliente removido com sucesso.");
			}
			else {
				System.out.println("Cliente em movimento.");
			}
		}
		else {
			System.out.println("Cliente inexistente.");
		}
	}

	private static void addTrot(Scanner in, Manager manager) {
		String idTrot = in.next();
		String licensePlate = in.next();
		in.nextLine();
		
		Trot trot = manager.getTrot();
		
		if (trot == null) {
			manager.addTrot(idTrot, licensePlate);
			System.out.println("Insercao de trotinete com sucesso.");
		} 
		else if (trot.getId().toUpperCase().equals(idTrot.toUpperCase())) {
			System.out.println("Trotinete existente.");
		}
		else {
			System.out.println("Trotinete existente.");
		}
	}

	private static void clientInfo(Scanner in, Manager manager) {
		String nif = in.next();
		in.nextLine();
		
		Client client = manager.getClient();
		
		if (client.getNif().toUpperCase().equals(nif.toUpperCase())) {
			System.out.println(client.getName() + ": " + client.getNif() + ", " + client.getEmail() + ", "
					+ client.getPhone() + ", " + client.getBalance() + ", " + client.getTotalMinutes() + ", "
					+ client.getRents() + ", " + client.getMaxMinutes() + ", " + client.getAvgMinutes() + ", "
					+ client.getTotalSpent());
		} 
		else {
			System.out.println("Cliente inexistente.");
		}
	}

	private static void getTrotFromClient(Scanner in, Manager manager) {
		String nif = in.next();
		in.nextLine();
		
		Client client = manager.getClient();
		Trot trot = manager.getTrot();

		if (client.getNif().toUpperCase().equals(nif.toUpperCase())) {
			if (!client.getIdTrot().equals("")) {
				System.out.println(trot.getId() + ", " + trot.getLicensePlate());
			} 
			else {
				System.out.println("Cliente sem trotinete.");
			}
		}
		else {
			System.out.println("Cliente inexistente.");
		}
	}

	private static void trotInfo(Scanner in, Manager manager) {
		String idTrot = in.next().toUpperCase();
		in.nextLine();
		
		Trot trot = manager.getTrot();
		
		if (trot.getId().toUpperCase().equals(idTrot.toUpperCase())) {
			System.out.println(trot.getLicensePlate() + ": " + trot.getStatus() + ", " + trot.getRents()
					+ ", " + trot.getTotalMinutes());
		} 
		else {
			System.out.println("Trotinete inexistente.");
		}
	}

	private static void getClientFromTrot(Scanner in, Manager manager) {
		String idTrot = in.next().toUpperCase();
		in.nextLine();
		
		Client client = manager.getClient();
		Trot trot = manager.getTrot();

		if (trot.getId().toUpperCase().equals(idTrot.toUpperCase())) {
			if (client.getIdTrot().toUpperCase().equals(idTrot.toUpperCase())) {
				System.out.println(client.getNif() + ", " + client.getName());
			} 
			else {
				System.out.println("Trotinete nao alugada.");
			}
		}
		else {
			System.out.println("Trotinete inexistente.");
		}
	}

	private static void loadBalance(Scanner in, Manager manager) {
		String nif = in.next();
		int amount = in.nextInt();
		in.nextLine();
		
		Client client = manager.getClient();
		
		if (amount > 0) {
			if (client.getNif().toUpperCase().equals(nif.toUpperCase())) {
				manager.loadBalance(nif, amount);
				System.out.println("Carregamento efectuado.");
			} 
			else {
				System.out.println("Cliente inexistente.");
			}
		} 
		else {
			System.out.println("Valor invalido.");
		}
		
	}

	private static void rentTrot(Scanner in, Manager manager) {
		String nif = in.next();
		String idTrot = in.next();
		in.nextLine();
		
		Client client = manager.getClient();
		Trot trot = manager.getTrot();
		
		if (client.getNif().toUpperCase().equals(nif.toUpperCase())) {
			if (trot.getId().toUpperCase().equals(idTrot.toUpperCase())) {
				if (trot.getStatus().equals("parada")) {
					if (client.getBalance() >= 100) {
						manager.rentTrot(nif, idTrot);
						System.out.println("Aluguer efectuado com sucesso.");
					} 
					else {
						System.out.println("Cliente sem saldo suficiente.");
					}
				} 
				else {
					System.out.println("Trotinete nao pode ser alugada.");
				}
			} 
			else {
				System.out.println("Trotinete inexistente.");
			}
		} 
		else {
			System.out.println("Cliente inexistente.");
		}
	}
	
	public static void releaseTrot(Scanner in, Manager manager) {
		String idTrot = in.next();
		int minutes = in.nextInt();
		in.nextLine();
		
		Trot trot = manager.getTrot();
		
		if (minutes > 0) {
			if (trot.getId().toUpperCase().equals(idTrot.toUpperCase())) {
				if (trot.getStatus() == "alugada") {
					manager.releaseTrot(idTrot, minutes);
					System.out.println("Aluguer terminado.");
				} 
				else {
					System.out.println("Trotinete nao alugada.");
				}
			}
			else {
				System.out.println("Trotinete inexistente.");
			}
		}
		else {
			System.out.println("Valor invalido.");
		}
	}

	public static void managerStatus(Scanner in, Manager manager) {
		System.out.println("Estado actual: " + manager.getTotalRents() + ", " + manager.getTotalSpent() + ", " + manager.getTotalMinutesLate());
	}

}
