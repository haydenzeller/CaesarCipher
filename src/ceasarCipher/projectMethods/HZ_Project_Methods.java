package ceasarCipher.projectMethods;
import ceasarCipher.HZ_Regular_Caesar;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class HZ_Project_Methods {
	public static void main(String[] args)
	{
		
	}
	/*
	 * Method Name: validateMenuSelection
	 * Purpose: To validate if user inputs 1 or 2
	 * Accepts: Main Scanner
	 * Returns: Integer
	 */
	public static int validateMenuSelection(Scanner scan)
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
	 * Method Name: encipherSelection
	 * Purpose: encipher a user inputed string and writing the enciphered string to a file.
	 * Excepts: Main Scanner
	 * Returns: Nothing
	 */
	public static void encipherSelection(Scanner scan) throws IOException
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
		shiftValue = shiftValue(keyword); // calls method to generate the shift value
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
			scan.nextLine();
			System.out.print("Do you want to decipher or encipher another message? (Y/n): ");
			cont = scan.nextLine();
				if (cont.matches("[yY]")) // if user answers yes (y,Y) call main method with null value
				{
					divider();
					HZ_Regular_Caesar.main(null);
				}
				else if (cont.matches("[nN]")) // if user answers no (n,N) end the program
				{
					System.out.println("Goodbye.");
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
	public static String keyword(Scanner scan)
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
	 * Excepts: String keyword
	 * Returns: Integer asciiSum (shift value)
	 */
	public static int shiftValue(String keyword)
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
	public static String encipher(String plainText, int shiftValue)

	{
		// declare variables
		String enciphered = ""; // Initialize string with "" value
		char position; 
		int alphaIndex;
		int i;
		// declare constants
		final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // initialize alphabet ALPHA with the alphabet
		for (i = 0; i < plainText.length(); i++) // iterate through plainText
		{
			position = plainText.charAt(i); // set position variable to the current char in the loop
			if (!Character.isLetter(plainText.charAt(i))) // if the char is not a letter (!^$@&...) concatenate to enciphered string as is
			{
					enciphered += plainText.charAt(i);
			}
			else // otherwise set the value of alphaIndex to the index of the plainText (position) + shiftValue
			{
				alphaIndex = ALPHA.indexOf(position) + shiftValue; // sets positionIndex to current plainText char and its integer value in the ALPHA constant, add shiftValue
				if (alphaIndex > 25) // if the positionIndex is out of range (not between 0-25) subtract 25 to loop back to the start of the ALPHA constant
				{
					alphaIndex -= 26;
				}
				enciphered += ALPHA.charAt(alphaIndex); // add the new enciphered character to the enciphered string
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
	public static void divider()
	{
		System.out.println("\n**************************************************************************\n");
	} // end divider
	/* 
	 * Method Name: fileWriter
	 * Purpose: To take the enciphered text and write it to a file specified by the user
	 * Excepts: String fileName, String cipherText
	 * Returns: String (to send the status of the method back to the caller)
	 */
	public static String fileWriter(String fileName, String cipherText) throws IOException
	{
		String result = "";
		try
		{
			File outFile = new File(fileName);
			if (outFile.createNewFile())
			{
				System.out.printf("\nFile created: %s\n", fileName);
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
	/*
	 * Method Name: decipherSelection
	 * Purpose: Handles calling of other methods if user chose the decipher option in the main method
	 * Accepts: Main Scanner
	 * Returns: Nothing
	 */
	public static void decipherSelection(Scanner scan) throws IOException
	{
		String encipheredMessage, key, decipheredMessage, cont; // declare variables
		int shiftValue; // declare variables
		while(true) // while loop to confirm if user is reading from a file
		{
			System.out.print("\nAre you reading the enciphered message from a file?(y/n): "); 
			String selection = scan.next();
			if (selection.matches("[yY]")) // if user inputs y or Y point them to the fileReader class
			{
				encipheredMessage = fileReader(scan);
				break;
			}
			else if (selection.matches("[nN]"))// else ask user to input the enciphered message using the keyboard
			{
				scan.nextLine();
				System.out.print("\nEnter the enciphered message: ");
				encipheredMessage = scan.nextLine().toUpperCase();
				break;
			}
			else
			{
				System.out.println("\nInvalid choice");
			}
		}
		System.out.print("Enter the key: "); // ask user for key
		key = scan.next().toUpperCase(); // initialize key variable
		shiftValue = shiftValue(key); // call the shift value function and pass the users key word
		decipheredMessage = decipher(encipheredMessage, shiftValue); // decipher the message by calling the decipher method and passing the enciphered message and the shift value
		divider();
		System.out.printf("Enciphered Message: %s\nKey: %s\nShift Value: %d\nDeciphered Message: %s\n", encipheredMessage, key, shiftValue, decipheredMessage); // print a summary of what the program did
		divider();
		do // give user the chance to exit or to loop back to main
		{
			System.out.print("Do you want to decipher or encipher another message? (Y/n): ");
			scan.nextLine();
			cont = scan.nextLine();
				if (cont.matches("[yY]")) // if user answers yes (y,Y) call main method with null value
				{
					divider();
					HZ_Regular_Caesar.main(null);
				}
				else if (cont.matches("[nN]")) // if user answers no (n,N) end the program
				{
					System.out.println("Goodbye.");
					break;
				}
				else
				{
					System.out.println("\nInvalid choice.");
				}
		}
		while(true);
	} // end decipher selection
	/*
	 * Method name: file reader
	 * Purpose: to read the first line from a TXT file
	 * Accepts: main scanner
	 * returns: enciphered message as a String
	 */
	public static String fileReader(Scanner scan) throws FileNotFoundException
	{
		String fileName, message = ""; // declare variables and initialize if needed
		while(true)
		{
			System.out.print("\nEnter the path to the TXT file: "); // get file name from user
			fileName = scan.next(); // Initialize file name with input
			try // try reading the file
			{
				File messageFile = new File(fileName); // create a file object with the filename 
				Scanner reader = new Scanner(messageFile); // create a scanner object for the file object
				if (reader.hasNextLine()) // if the file exists and has a line
				{
					message = reader.nextLine(); // Initialize the message variable with the first line
					reader.close(); // close the reader
					break; // break the loop
				}
				else // else close the reader and ask user for the correct file name
				{
					reader.close(); // close reader
				}
			}
			catch (FileNotFoundException e) // print file not found exception
			{
				System.out.println("\nFile not found."); // message
			}
		}
		return message; // return the line read from file
		
	} // end file reader
	
	public static String decipher(String encipheredMessage, int shiftValue)
	{
		// declare variables
		String deciphered = ""; // Initialize string with "" value
		char position; 
		int positionIndex;
		int i;
		// declare constants
		final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // initialize alphabet ALPHA with the alphabet
		for (i = 0; i < encipheredMessage.length(); i++) // iterate through encipheredMessage
		{
			position = encipheredMessage.charAt(i); // set position variable to the current char in the loop
			if (!Character.isLetter(encipheredMessage.charAt(i))) // if the char is not a letter (!^$@&...) concatenate to enciphered string as is
			{
				deciphered += encipheredMessage.charAt(i);
			}
			else // otherwise set the value of positionIndex (index of the ALPHA constant) to the index of the plainText (position) + shiftValue
			{
				positionIndex = ALPHA.indexOf(position) - shiftValue; // sets positionIndex to current plainText char and its integer value in the ALPHA constant, add shiftValue
				if (positionIndex < 0) // if the positionIndex is out of range (not between 0-25) subtract 25 to loop back to the start of the ALPHA constant
				{
					positionIndex += 26;
				}
				
				deciphered += ALPHA.charAt(positionIndex); // add the new enciphered character to the enciphered string
			}
		}
		return deciphered;
	}
}
