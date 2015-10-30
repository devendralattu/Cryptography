package cipher;
/*
 * @author : Devendra Lattu
 */

import java.util.Scanner;
import helper.Helper;

public class CaesarCipher {

	public static void main(String[] args) {

		String str = "";
		String strCaps = "";
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

		// Convert to upper case
		strCaps = str.toUpperCase();

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

		System.out.println("\nMessage to be encrypted = " + strCaps);
		System.out.println("Shift amount calculated on scale of 26 = " + shiftAmt + "\n");

		System.out.println("Starting encryption process...");

		// Note - ASCII value of A = 65 and Z = 90
		for (i = 0; i < strCaps.length(); i++) {
			// Get character from index i and its ASCII value
			strValue = strCaps.charAt(i);
			charValue = (int) strValue;

			// check for special characters in string. Don't encode them.
			if (!(charValue >= 65 && charValue <= 90)) {
				// special character found. Do nothing
				encryptedStr = encryptedStr + strValue;
				continue;
			}
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

		System.out.println("Encrypted messgae = " + encryptedStr + "\n");
		System.out.println("Starting decryption process...");

		for (i = 0; i < encryptedStr.length(); i++) {
			// Get character from index i and its ASCII value
			strValue = encryptedStr.charAt(i);
			charValue = (int) strValue;

			// check for special characters in string. Don't encode them.
			if (!(charValue >= 65 && charValue <= 90)) {
				// special character found. Do nothing
				decryptedStr = decryptedStr + strValue;
				continue;
			}

			// delete shift amount
			charValue = charValue - shiftAmt;

			// Shift amount was positive
			if (charValue < 65) {
				charValue = 91 - (65 - charValue); // Rotating from Z again
			}

			// Shift amount was negative
			if (charValue > 90) {
				charValue = 64 + (charValue - 90); // Rotating from A again
			}

			// get character for the ASCII value
			strValue = (char) (charValue);

			// Append decrypted value to new string
			decryptedStr = decryptedStr + strValue;
		}

		System.out.println("Decrypted messgae = " + decryptedStr + "\n");

		/*
		 * Devendra Lattu {" 1234 "} ~ AAZZ ...
		 */

	}

}
