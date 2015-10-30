package cipher;

/*
 * @author : Devendra Lattu
 */
import java.util.Scanner;
import helper.Helper;

public class VigenereCipher {

	public static void main(String[] args) {

		int arr[] = new int[26];
		int i = 0, j = 0, k = 0;
		int intValue = 0;
		boolean bool = true;
		String key = "";
		String finalKey = "";
		String finalStr = "";
		String str = "";
		String strCaps = "";
		char strValue;
		char keyValue;
		int strLength = 0;
		int keyLenInitial = 0;
		int keyDupFactorMod = 0;
		int keyDupFactorDivide = 0;

		/** Generating and printing array **/
		for (i = 0; i < 26; i++) {
			arr[i] = 65 + i;
		}

		int multiArr[][] = new int[26][26];
		char multiCharArr[][] = new char[26][26];

		// Generate array of ASCII integers for A-Z and array table for A-Z
		for (i = 0; i < arr.length; i++) {
			for (j = 0; j < arr.length; j++) {
				intValue = arr[i] + j;
				if (intValue > 90) {
					intValue = 64 + (intValue - 90);
				}
				multiArr[i][j] = intValue;
				multiCharArr[i][j] = (char) (intValue);
			}
		}

		for (i = 0; i < multiArr.length; i++) {
			for (j = 0; j < multiArr.length; j++) {
				System.out.print(multiArr[i][j] + "-");
			}
			System.out.println();
		}

		System.out.println("\nArray = ");

		for (i = 0; i < multiCharArr.length; i++) {
			for (j = 0; j < multiCharArr.length; j++) {
				System.out.print(multiCharArr[i][j] + "-");
			}
			System.out.println();
		}

		System.out.println();

		/** Done with generation and printing array **/

		Scanner in = new Scanner(System.in);

		// Keep prompting user to reenter valid info until he gives it.
		bool = true;
		while (bool) {
			System.out.println("Enter a string to encrypt");
			str = in.nextLine();

			// Remove unwanted spaces from sides
			str = str.trim();

			// Convert to upper case
			strCaps = str.toUpperCase();
			strLength = strCaps.length();

			// remove spaces between lines
			for (i = 0; i < strLength; i++) {
				strValue = strCaps.charAt(i);
				// check for alphabets only
				if (Helper.is_a_zA_Z(strValue + "")) {
					finalStr = finalStr + strValue;
				}
			}
			strLength = finalStr.length();

			/*
			 * if(Helper.is_a_zA_Z_Sp(finalStr)) //Condition to accept only
			 * Alphabets and Spaces as Input string
			 */

			// Condition to accept anything (the question's paragraph) as Input
			// Just check if something is entered hence length should be > 0
			if (strLength > 0) {
				bool = false;
			} else {
				System.out.println("You entered string = " + str + " which is not as per the format [A-Z].");
			}
		}

		// Keep prompting user to reenter valid info until he gives it.
		bool = true;
		while (bool) {
			System.out.println("Enter the key [Alphabets A-Z]");
			key = in.nextLine();
			key = key.trim();

			// check if key entered contains anything other than alphabets.
			// Ask user to enter again if its doesn't satisfy the condition.
			if (Helper.is_a_zA_Z(key)) {
				keyLenInitial = key.length();

				// Key size shouldn't be greater than the input stream size
				if (keyLenInitial <= strLength) {
					bool = false;
				} else {
					System.out.println(
							"You entered key = " + key + " who's length is greater than that of input string = " + str);
				}
			} else {
				System.out.println("You entered key = " + key + " which is not as per the format.");
			}
		}

		// Calculating key length
		keyDupFactorMod = strLength % keyLenInitial;
		keyDupFactorDivide = strLength / keyLenInitial;

		// multiple keys
		for (i = 0; i < keyDupFactorDivide; i++) {
			finalKey = finalKey + key;
		}
		// adding part of the same key to fill the input string's length.
		for (i = 0; i < keyDupFactorMod; i++) {
			finalKey = finalKey + key.charAt(i);
		}

		// Key to Uppercase
		finalKey = finalKey.toUpperCase();

		// Printing values for calculation.
		System.out.println("\nMessage to be encrypted = " + finalStr);
		System.out.println("Key = " + finalKey + "\n");

		System.out.println("Starting encryption process...");

		String encryptedStr = "";
		String decryptedStr = "";

		// ENCRYPTION PROCESS : iterate over input string.
		// Note - now size of key is same as that of input string.
		for (k = 0; k < finalStr.length(); k++) {

			// As per encryption mechanism we take input string first and then
			// the key.
			strValue = finalStr.charAt(k);
			keyValue = finalKey.charAt(k);

			// go to row from array
			i = (int) strValue - 65; // row number
			j = (int) keyValue - 65; // column number

			encryptedStr = encryptedStr + (char) multiArr[i][j];
		}

		System.out.println("\nEncrypted string = " + encryptedStr);

		System.out.println("Starting decryption process...");

		// DECRYPTION PROCESS : matching alphabets from character array
		int rowNum = 0, colNum = 0;

		for (k = 0; k < encryptedStr.length(); k++) {

			// As per decryption mechanism we take key first and then the
			// encrypted string.
			keyValue = finalKey.charAt(k);
			strValue = encryptedStr.charAt(k);

			rowNum = (int) keyValue - 65;
			// Search for the alphabet in the key's row
			for (colNum = 0; colNum < multiCharArr[0].length; colNum++) {
				if (strValue == multiCharArr[rowNum][colNum]) {
					// column number found
					break;
				}
			}

			decryptedStr = decryptedStr + (char) (65 + colNum);
		}

		System.out.println("\nDecrypted string = " + decryptedStr);

		// Input : ATTACK AT DAWN
		// Key : LEMON
	}
}