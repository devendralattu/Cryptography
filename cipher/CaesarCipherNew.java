package cipher;
/*
 * @author : Devendra Lattu
 */

import java.util.Scanner;
import helper.Helper;

public class CaesarCipherNew {

	public static void main(String[] args) {

		// In this program we are not making input string to upper/lower case
		String str = "";
		String shift = "";
		char strValue;
		int charValue = 0;
		int shiftAmt = 0;
		int i = 0;
		boolean bool = true;

		String encryptedStr = "";
		String decryptedStr = "";

		// Get String to encrypt and the shift amount
		Scanner in = new Scanner(System.in);
		System.out.println("Enter a string to encrypt");
		str = in.nextLine();

		// Remove unwanted spaces from sides
		str = str.trim();

		// Keep prompting user to reenter valid info until he gives it.
		while (bool) {
			System.out.println("Enter the shift amount in integer");
			shift = in.nextLine();
			// check if shift amount entered is an integer or not. Ask user to
			// enter again if its not an integer.
			if (Helper.isInteger(shift)) {
				bool = false;
				shiftAmt = Helper.getIntValue(shift);
			} else {
				System.out.println("You entered " + shift + " which is not an integer");
			}
		}

		// User can enter amount in greater than 26 (for example : 53)
		// In this case, we need to rotate the shift amount in multiples of 26
		shiftAmt = shiftAmt % 26;

		System.out.println("\nMessage to be encrypted = " + str);
		System.out.println("Shift amount calculated on scale of 26 = " + shiftAmt + "\n");

		System.out.println("Starting encryption process...");

		// Encrypting ...

		// Note - ASCII value of A = 65 and Z = 90 and a = 97 and z = 122
		for (i = 0; i < str.length(); i++) {
			// Get character from index i and its ASCII value
			strValue = str.charAt(i);
			charValue = (int) strValue;

			// check if letter if in Upper case or Lower case
			if (charValue >= 65 && charValue <= 90) {
				// Upper case letter found

				// add shift amount
				charValue = charValue + shiftAmt;

				// Shift amount was positive
				if (charValue > 90) {
					charValue = 64 + (charValue - 90); // Rotating from A again
				}

				// Shift amount was negative
				else if (charValue < 65) {
					charValue = 91 - (65 - charValue);
				}

				// get character for the ASCII value
				strValue = (char) (charValue);

				// Append encrypted value to new string
				encryptedStr = encryptedStr + strValue;
			}

			else if (charValue >= 97 && charValue <= 122) {
				// Lower case letter found

				// add shift amount
				charValue = charValue + shiftAmt;

				// Shift amount was positive
				if (charValue > 122) {
					charValue = 96 + (charValue - 122); // Rotating from a again
				}

				// Shift amount was negative
				else if (charValue < 97) {
					charValue = 123 - (97 - charValue);
				}

				// get character for the ASCII value
				strValue = (char) (charValue);

				// Append encrypted value to new string
				encryptedStr = encryptedStr + strValue;
			} else {
				// Do nothing... Just append
				encryptedStr = encryptedStr + strValue;
			}

		}

		System.out.println("Encrypted messgae = " + encryptedStr + "\n");
		System.out.println("Starting decryption process...");

		// Decrypting ...

		for (i = 0; i < encryptedStr.length(); i++) {
			// Get character from index i and its ASCII value
			strValue = encryptedStr.charAt(i);
			charValue = (int) strValue;

			// check if letter if in Upper case or Lower case
			if (charValue >= 65 && charValue <= 90) {
				// Upper case letter found

				// delete shift amount
				charValue = charValue - shiftAmt;

				// Shift amount was positive
				if (charValue < 65) {
					charValue = 91 - (65 - charValue);
				}

				// Shift amount was negative
				else if (charValue > 90) {
					charValue = 64 + (charValue - 90);
				}

				// get character for the ASCII value
				strValue = (char) (charValue);

				// Append decrypted value to new string
				decryptedStr = decryptedStr + strValue;
			}

			else if (charValue >= 97 && charValue <= 122) {
				// Lower case letter found

				// delete shift amount
				charValue = charValue - shiftAmt;

				// Shift amount was positive
				if (charValue < 97) {
					charValue = 123 - (97 - charValue);
				}

				// Shift amount was negative
				else if (charValue > 122) {
					charValue = 96 + (charValue - 122);
				}

				// get character for the ASCII value
				strValue = (char) (charValue);

				// Append decrypted value to new string
				decryptedStr = decryptedStr + strValue;
			} else {
				// Do nothing... Just append
				decryptedStr = decryptedStr + strValue;
			}
		}

		System.out.println("Decrypted messgae = " + decryptedStr + "\n");

/*

Enter a string to encrypt
Devendra Lattu {" 1234 "} ~ AAZZ ...
Enter the shift amount in integer
2

Message to be encrypted = Devendra Lattu {" 1234 "} ~ AAZZ ...
Shift amount calculated on scale of 26 = 2

Starting encryption process...
Encrypted messgae = Fgxgpftc Ncvvw {" 1234 "} ~ CCBB ...

Starting decryption process...
Decrypted messgae = Devendra Lattu {" 1234 "} ~ AAZZ ...

*/

	}

}
