package extras;

import java.util.Scanner;

public class Decrement {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		for (int i=0; i<16; i++) {
			for (int j=0; j<16; j++) {
				int temp = scanner.nextInt();
				if (temp != 19) temp--;
				System.out.print(temp + " ");
				if ((j+1)%4==0) System.out.print(" ");
			}
			scanner.nextLine();
			System.out.println();
			if ((i+1)%4==0) System.out.println();
		}
		scanner.close();

	}

}
