package ceasarCipher;
import java.io.IOException;
/*
 * Title: HaydenZeller_CaeserCipher.java
 * Purpose: To encipher and decipher strings
 * Author: Hayden Zeller
 * Date:  
 */
import java.util.Scanner;
public class HZ_Regular_Caesar extends HZ_Project_Methods{
	public static void main(String[] args) throws IOException
	{
		int option;
		Scanner scan = new Scanner(System.in);
		// Print menu
		System.out.println("\nThis program will help you encipher a message or decipher a coded message");
		HZ_Project_Methods.divider();
		System.out.print("If enciphering enter 1 and press ENTER.");
		System.out.println("\nIf deciphering enter 2 and press ENTER. ");
		option = HZ_Project_Methods.validateMenuSelection(scan);
		// call method
		if (option == 1)
		{
			HZ_Project_Methods.encipherSelection(scan);
		}
		else if (option == 2)
		{
			HZ_Project_Methods.decipherSelection(scan);
		}
		else
		{
			System.out.print("Unprecidented error.");
		}
		scan.close();
	} // end main
} // End program

























