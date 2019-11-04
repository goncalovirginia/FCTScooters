import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		RentSystem rentSystem = new RentSystem();

		Scanner in = new Scanner(System.in);

		String option = "";

		while (!option.equals("SAIR")) {
			option = readOption(in);
			executeOption(in, option, client, trot);
		}
		
		System.out.println("Saindo...");
		systemStatus(in, client, trot);

		in.close();
	}

	private static String readOption(Scanner in) {
		return in.next().toUpperCase();
	}

	private static void executeOption(Scanner in, String option, Client client, Trot trot) {

		switch (option) {
		case "ADCLIENTE":
			addClient(in, client);
			break;
		case "REMCLIENTE":
			removeClient(in, client);
			break;
		case "ADTROT":
			addTrotinete(in, trot);
			break;
		case "DADOSCLIENTE":
			clientInfo(in, client);
			break;
		case "TROT":
			getTrotineteFromClient(in, client, trot);
			break;
		case "DADOSTROT":
			trotineteInfo(in, trot);
			break;
		case "CLIENTE":
			getClientFromTrotinete(in, trot, client);
			break;
		case "CARRSALDO":
			loadSaldo(in, client);
			break;
		case "ALUGAR":
			rentTrotinete(in, client, trot);
			break;
		case "LIBERTAR":
			releaseTrotinete(in, trot, client);
			break;
		case "ESTADOSISTEMA":
			systemStatus(in, client, trot);
			break;
		case "SAIR":
			break;
		default:
			System.out.println("Comando invalido.");
			in.nextLine();
			break;
		}
	}

	private static void addClient(Scanner in, Client cliente) {
		int nif = in.nextInt();
		String email = in.next();
		int telefone = in.nextInt();
		String nome = in.nextLine();

		if (cliente == null || (cliente.getNif() != nif && !cliente.getEmail().equals(email) && cliente.getTelefone() != telefone && cliente.getNome().equals(nome))) {
			cliente = new Client(nif, email, telefone, nome);
			System.out.println("Insercao de cliente com sucesso.");
		}
		else {
			System.out.println("Cliente existente.");
		}
	}

	private static void removeClient(Scanner in, Client cliente) {
		int nif = in.nextInt();
		String email = in.next();
		int telefone = in.nextInt();
		String nome = in.nextLine();

		if (cliente.getNif() == nif && cliente.getEmail().equals(email) && telefone == cliente.getTelefone()
				&& cliente.getNome().equals(nome)) {
			cliente.deactivate();
			System.out.println("Cliente removido com sucesso.");
		} 
		else {
			System.out.println("Cliente inexistente.");
		}
	}

	private static void addTrotinete(Scanner in, Trot trotinete) {
		int idTrot = in.nextInt();
		String matricula = in.next();
		in.nextLine();

		if (trotinete == null) {
			trotinete = new Trot(idTrot, matricula);
			System.out.println("Insercao de trotinete com sucesso.");
		} 
		else {
			System.out.println("Trotinete existente.");
		}
	}

	private static void clientInfo(Scanner in, Client cliente) {
		int nif = in.nextInt();
		in.nextLine();

		if (cliente.isActive() == true && cliente.getNif() == nif) {
			System.out.println(cliente.getNome() + ": " + cliente.getNif() + ", " + cliente.getEmail() + ", "
					+ cliente.getTelefone() + ", " + cliente.getSaldo() + ", " + cliente.getTotalMinutos() + ", "
					+ cliente.getAlugueres() + ", " + cliente.getMaxMinutos() + ", " + cliente.getMedMinutos() + ", "
					+ cliente.getTotalCentimos());
		} 
		else {
			System.out.println("Cliente inexistente.");
		}
	}

	private static void getTrotineteFromClient(Scanner in, Client cliente, Trot trotinete) {
		int nif = in.nextInt();
		in.nextLine();

		if (cliente.isActive() == true && cliente.getNif() == nif) {
			if (cliente.trotineteStatus() == true) {
				System.out.println(trotinete.getId() + ", " + trotinete.getMatricula());
			} 
			else {
				System.out.println("Cliente sem trotinete.");
			}
		} 
		else {
			System.out.println("Cliente inexistente.");
		}
	}

	private static void trotineteInfo(Scanner in, Trot trotinete) {
		int idTrot = in.nextInt();
		in.nextLine();

		if (idTrot == trotinete.getId()) {
			System.out.println(trotinete.getMatricula() + ": " + trotinete.getStatus() + ", " + trotinete.getAlugueres()
					+ ", " + trotinete.getTotalMinutos());
		} 
		else {
			System.out.println("Trotinete inexistente.");
		}
	}

	private static void getClientFromTrotinete(Scanner in, Trot trotinete, Client cliente) {
		int idTrot = in.nextInt();
		in.nextLine();

		if (trotinete.getId() == idTrot) {
			if (cliente.getIdTrot() == idTrot) {
				System.out.println(cliente.getNif() + ", " + cliente.getNome());
			} 
			else {
				System.out.println("Trotinete nao alugada.");
			}
		} 
		else {
			System.out.println("Trotinete inexistente.");
		}
	}

	private static void loadSaldo(Scanner in, Client cliente) {
		int nif = in.nextInt();
		int amount = in.nextInt();
		in.nextLine();

		if (amount > 0) {
			if (cliente.getNif() == nif) {
				cliente.loadSaldo(amount);
				System.out.println("Carregamento efetuado.");
			} 
			else {
				System.out.println("Cliente inexistente.");
			}
		} 
		else {
			System.out.println("Valor invalido.");
		}
	}

	private static void rentTrotinete(Scanner in, Client cliente, Trot trotinete) {
		int nif = in.nextInt();
		int idTrot = in.nextInt();
		in.nextLine();

		if (cliente.getNif() == nif) {
			if (trotinete.getId() == idTrot) {
				if (trotinete.getStatus() == "parada") {
					if (cliente.getSaldo() >= 100) {
						cliente.setIdTrot(idTrot);
						cliente.rentTrotinete();
						cliente.unloadSaldo(100);
						trotinete.setIdClient(nif);
						trotinete.rent();
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
	
	public static void releaseTrotinete(Scanner in, Trot trotinete, Client cliente) {
		int idTrot = in.nextInt();
		int minutos = in.nextInt();
		in.nextLine();

		if (minutos > 0) {
			if (trotinete.getId() == idTrot) {
				if (trotinete.getStatus() == "alugada") {
					cliente.deactivateTrotinete();
					cliente.addMinutos(minutos);
					cliente.setMaxMinutos(minutos);
					
					int minAtrasos = (minutos - 60);
					while (minAtrasos > 0) {
						cliente.unloadSaldo(100);
						minAtrasos -= 30;
					}
					
					trotinete.deactivate();
					trotinete.addMinutos(minutos);
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

	public static void systemStatus(Scanner in, Client cliente, Trot trotinete) {
		System.out.println("Estado actual: " + cliente.getAlugueres() + ", " + cliente.getTotalCentimos() + "," + (trotinete.getTotalMinutos() - 60));
	}

}
