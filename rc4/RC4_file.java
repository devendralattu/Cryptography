package rc4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RC4_file {

	int[] S = new int[256];

	public static void main(String[] args) throws IOException {

		int i = 0, j = 0, k = 0, temp = 0;
		String str = "", keyStr = "", cipherTextStr = "", plainTextStr = "";
		int cipherText[];
		char plainText[];
		int S[];
		int K[];
		char[] key = new char[256];
		int keylength = 0, strlength = 0;

		String fileName = "F:\\Computer Security\\10-12-2015_RC4_assignment\\sonnet.txt";
		String fileCipher = "F:\\Computer Security\\10-12-2015_RC4_assignment\\CipherText.txt";
		String filePlain = "F:\\Computer Security\\10-12-2015_RC4_assignment\\PlainText.txt";
		String line = null;

		try {
			FileReader fileReader = new FileReader(fileName);

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				str = str + line + " \n";
				System.out.println(line);
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.err.println("Error opening file '" + fileName + "'");
			return;
		} catch (IOException ex) {
			System.err.println("Error reading file '" + fileName + "'");
			return;
		}

		System.out.println("str = " + str);
		strlength = str.length();

		// check for blank files
		if (str.length() == 0) {
			System.err.println("File " + fileName + " is blank. Please type in something and run again");
			return;
		}

		// initialize integer arrays
		cipherText = new int[strlength];
		S = new int[strlength];
		K = new int[strlength];
		// generate plainText array
		plainText = str.toCharArray();

		// Get String to encrypt
		Scanner scanner = new Scanner(System.in);

		while (true) {
			// Get key
			System.out.println("Enter key");
			keyStr = scanner.nextLine();

			// Remove unwanted spaces from sides
			keyStr = keyStr.trim();
			System.out.println("Entered key = " + keyStr);

			// keylength
			keylength = keyStr.length();

			if (keylength >= 256) {
				System.err.println("Key length cannot exceed 256 characters");
				return;
			}
			key = keyStr.toCharArray();

			if (keylength == 0 || keylength > strlength) {
				System.err.println(
						"Key length = " + keylength + ". Enter key of length > 0 and < plainText string length");
			} else {
				break;
			}
		}

		for (i = 0; i < 256; i++) {
			S[i] = i;
		}

		j = 0;
		for (i = 0; i < 256; i++) {
			j = (j + S[i] + key[i % keylength]) % 256;

			// swap
			temp = S[j];
			S[j] = S[i];
			S[i] = temp;
		}

		System.out.println();

		// print S[]
		for (i = 0; i < 256; i++) {
			System.out.println("S[" + i + "] = " + S[i]);
		}

		// PRGA - Pseudo Random Number Generation Algorithm
		i = 0;
		j = 0;
		while (strlength > 0) {
			i = (i + 1) % 256;
			j = (j + S[i]) % 256;
			temp = S[j];
			S[j] = S[i];
			S[i] = temp;
			K[k++] = S[(S[i] + S[j]) % 256];

			strlength--;
		}

		System.out.println();

		// print K
		for (k = 0; k < str.length(); k++) {
			System.out.println("K[" + k + "] = " + K[k]);
		}

		System.out.println();

		// Generate cipher text
		for (i = 0; i < str.length(); i++) {
			cipherText[i] = plainText[i] ^ K[i];
			cipherTextStr = cipherTextStr + cipherText[i];
			System.out.println("cipherText[" + i + "] = " + cipherText[i]);
		}

		// write to file;
		writeToFile(fileCipher, cipherTextStr);
		System.out.println();

		// Generate plain text
		for (i = 0; i < str.length(); i++) {
			plainText[i] = (char) (K[i] ^ cipherText[i]);
			plainTextStr = plainTextStr + plainText[i];
			System.out.println("plainText[" + i + "] = " + plainText[i]);
		}

		writeToFile(filePlain, plainTextStr);

		///////////////////////////////////////////
		System.out.println("Completed...");
		System.out.println("CipherText file = " + fileCipher);
		System.out.println("PlainText file = " + filePlain);
	}

	public static void writeToFile(String fileName, String StrContent) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			writer.write(StrContent);
		} catch (IOException e) {
			System.err.println(e);
			return;
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
			}
		}
	}
}
