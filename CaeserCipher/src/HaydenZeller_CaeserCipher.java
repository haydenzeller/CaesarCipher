/*
 * Title: HaydenZeller_CaeserCipher.java
 * Purpose: To encipher and decipher strings
 * Author: Hayden Zeller
 * Date:  
 */
import java.io.*;
import java.util.*;
public class HaydenZeller_CaeserCipher {
	public static void main(String[] args) throws IOException {
		int option;
		Scanner scan = new Scanner(System.in);
		// Print menu
		System.out.println("\nThis program will help you encipher a message or decipher a coded message");
		divider();
		System.out.print("If enciphering enter 1 and press ENTER.");
		System.out.println("\nIf deciphering enter 2 and press ENTER. ");
		option = validateMenuSelection(scan);
		// call method
		if (option == 1)
		{
			encipherSelection(scan);
		}
		else if (option == 2)
		{
			//decipher();
		}
		else
		{
			System.out.print("Unprecidented error.");
		}
		scan.close();
	} // end main
	/*
	 * Method Name: validateMenuSelection
	 * Purpose: To validate if user inputs 1 or 2
	 * Accepts: Main Scanner
	 * Returns: Integer
	 */
	private static int validateMenuSelection(Scanner scan)
	{
		// declare variable
		int number;
		do 
		{
			System.out.print("\nEnter 1 or 2: "); // print prompt
			while(!scan.hasNextInt()) // if the input is not an integer, notify the user and loop until just a number is entered 
			{
				String userIn = scan.next();
				System.out.printf("\n\"%s\" is not a valid input.\nSelect a NUMBER, either 1 or 2: ", userIn);
			} // End While
			number = scan.nextInt();
			if (number < 1 || number > 2) // if number is not 1 or 2, notify user of the correct selection and loop back to input
			{
				System.out.print("\nYou must enter a number between 1 and 2.");
			} // end if 
		}
		while(number != 1 && number != 2); // End Do-While
		return number;
	} // End validateMenueSelection
	/* 
	 * Method Name: encipher
	 * Purpose: encipher a user inputed string and writing the enciphered string to a file.
	 * Excepts: Main Scanner
	 * Returns: Nothing
	 */
	private static void encipherSelection(Scanner scan) throws IOException
	{
		String userMessage;
		String keyword;
		String cipheredMessage;
		int shiftValue;
		String fileName;
		String cont;
		String writeResult;
		System.out.print("\nEnter a plaintext message to be enciphered: ");
		scan.nextLine(); // Scanner buffer flush
		userMessage = scan.nextLine().toUpperCase(); // converts message to upper case
		keyword = keyword(scan); // calls method to validate keyword
		shiftValue = shiftValue(scan, keyword); // calls method to generate the shift value
		cipheredMessage = encipher(userMessage, shiftValue); // calls method to generate the ciphered message
		divider(); // prints divider 
		System.out.printf("Message: %s\nKeyword: %s\nShift Value: %d\nEnciphered Message: %s\n", userMessage, keyword, shiftValue, cipheredMessage);
		divider(); // prints divider
		do // to get the filename from user
		{
			System.out.print("Enter a filename to write the enciphered message to (file type will be .txt): ");
			fileName = scan.next(); 
			writeResult = fileWriter(fileName, cipheredMessage); // calls the file writer methode and stores the return result in var writeResult
			if (writeResult.equals("created")) // if the fileWriter method returns 'created' break the for loop
			{
				break;
			}
		}
		while(true);
		do // give user the chance to exit or to loop back to main
		{
			divider();
			System.out.print("Do you want to decipher or encipher another message? (Y/n): ");
			cont = scan.next();
				if (cont.matches("[yY]")) // if user answers yes (y,Y) call main method withh null value
				{
					divider();
					main(null);
				}
				else if (cont.matches("[nN]")) // if user answers no (n,N) end the program
				{
					System.out.print("Goodbye.");
					break;
				}
				else
				{
					System.out.println("\nInvalid choice.");
		
				}
		}
		while(true);
	} // end encipherSselection
	/* 
	 * Method Name: keyword
	 * Purpose: Asks user to input a keyword phrase, the keyword is validated to only contain A-Z a-z
	 * Excepts: Main Scanner
	 * Returns: String keyword
	 */
	private static String keyword(Scanner scan)
	{
		// declare method variables
		boolean valid = false;
		String keyword;
		do 
		{
			System.out.print("\nEnter a keyword (Only letters will be accepted): ");
			keyword = scan.nextLine();
			if (!(keyword.matches("[a-zA-Z]*"))) // if keyword doesn't match a-z or A-Z notify user and loop back to keyword input
			{
				System.out.printf("\n\"%s\" is not valid.", keyword);
			}
			else if (keyword.matches("[a-zA-Z]*")) // if keyword matches a-z or A-Z valid = true
			{
				valid = true;
			} // end if statement
		}
		while(!valid); // end do while
		return keyword.toUpperCase(); // return keyword as upper case
		
	} // end keyword
	/* 
	 * Method Name: shiftValue
	 * Purpose: take the user keyword and generate a shift value by adding the ASCII values and modulus by 26 to stay in range of the -
	 * 			alphabet. if the shift value is 0, add 1.
	 * Excepts: Main Scanner, String keyword
	 * Returns: Integer asciiSum (shift value)
	 */
	private static int shiftValue(Scanner scan, String keyword)
	{
		int asciiSum = 0; // set initial sum to 0
		int i; 
		// calculate sum of ASCII characters, iterate through the keyword string, type cast char to int, add to asciiSum.
		for (i = 0; i < keyword.length(); i ++)
		{
			asciiSum += (int)keyword.charAt(i);
		} // end for loop
		asciiSum %= 26; // do modulus division by 26 to get a shift value between 0-25 (add 1 if zero)
		if (asciiSum == 0) // if sum is 0, add 1
		{
			asciiSum = 1;
		} // end if
		return asciiSum;
		
	} // end shiftValue
	/* 
	 * Method Name: encipher
	 * Purpose: convert the plain text message into an enciphered form
	 * Excepts: String plainText, int shiftValue
	 * Returns: string enciphered
	 */
	private static String encipher(String plainText, int shiftValue)

	{
		// declare variables
		String enciphered = ""; // Initialize string with "" value
		char position; 
		int positionIndex;
		int i;
		// declare constants
		final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // initialize alphabet ALPHA with the alphabet
		for (i = 0; i < plainText.length(); i++) // iterate through plainText
		{
			position = plainText.charAt(i); // set position variable to the current char in the loop
			if (!Character.isLetter(plainText.charAt(i))) // if the char is not a letter (!^$@&...) concatenate to enciphered string as is
			{
				if (Character.isDigit(i)) // if the character that is not a letter is an integer, add the shift value to the integer and concatenate to enciphered string 
				{
					position += ((int)plainText.charAt(i)) + shiftValue;
				}
				else 
				{
					enciphered += plainText.charAt(i);
				}
			}
			else // otherwise set the value of positionIndex (index of the ALPHA constant) to the index of the plainText (position) + shiftValue
			{
				positionIndex = ALPHA.indexOf(position) + shiftValue; // sets positionIndex to current plainText char and its integer value in the ALPHA constant, add shiftValue
				if (positionIndex > 25) // if the positionIndex is out of range (not between 0-25) subtract 25 to loop back to the start of the ALPHA constant
				{
					positionIndex -= 25;
				}
				enciphered += ALPHA.charAt(positionIndex); // add the new enciphered character to the enciphered string
			}

		}
		return enciphered;
	} // end encipher
	/* 
	 * Method Name: divider
	 * Purpose: to print a divider to the terminal for better readability
	 * Excepts: nothing
	 * Returns: nothing
	 */
	private static void divider()
	{
		System.out.println("\n**************************************************************************\n");
	} // end divider
	/* 
	 * Method Name: fileWriter
	 * Purpose: To take the enciphered text and write it to a file specified by the user
	 * Excepts: String fileName, String cipherText
	 * Returns: String (to send the status of the method back to the caller)
	 */
	private static String fileWriter(String fileName, String cipherText) throws IOException
	{
		String result = "";
		try
		{
			File outFile = new File(fileName);
			if (outFile.createNewFile())
			{
				System.out.printf("\nFile created: %s", fileName);
				result = "created";
			}
			else
			{
				System.out.println("\nFile already exists\n");
				result = "exists";
			}
		}
		catch (Exception e)
		{
			result = "error";
		}
		if (result.equals("created"))
		{
			PrintWriter out = new PrintWriter(fileName);
			out.println(cipherText);
			out.close();
		}
		return result;
	}
} // End program
