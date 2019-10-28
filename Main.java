import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		String option = "";

		while (!option.equals(EXIT)) {
			option = readOption(in);
			executeOption(in, ws, option);
		}

		in.close();
	}
	
	private static String readOption(Scanner in) {
		return in.next().toUpperCase();
	}

	private static void executeOption(Scanner in, WaterStation station, String option) {

		switch (option) {
		case REGISTER_SAMPLE:
			processRegister(in, station);
			in.nextLine();
			break;
		case NUMBER_SAMPLES:
			processNumberSamples(station);
			in.nextLine();
			break;
		case AVERAGE:
			processAverage(station);
			in.nextLine();
			break;
		case MAXIMUM:
			processMaximum(station);
			in.nextLine();
			break;
		case MINIMUM:
			processMinimum(station);
			in.nextLine();
			break;
		case Q: 
			processQ(in, station); 
			break;
		case EXIT:
			break;
		default:
			System.out.println("Comando invalido.");
			break;
		}
	}
	
}
