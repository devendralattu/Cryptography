package rc4;

import java.util.Scanner;

public class RC4 {

	int[] S = new int[256];

	public static void main(String[] args) {

		int i = 0, j = 0, k = 0, temp = 0;
		String str, keyStr;
		int cipherText[] = new int[256];
		int S[] = new int[256];
		int K[] = new int[256];
		char[] key = new char[256];
		int keylength = 0, strlength = 0;
		char[] plainText = new char[256];

		// Get String to encrypt
		Scanner in = new Scanner(System.in);

		while (true) {
			System.out.println("Enter a string to encrypt");
			str = in.nextLine();

			// Remove unwanted spaces from sides
			str = str.trim();
			System.out.println("Entered string = " + str);

			strlength = str.length();
			if (strlength == 0) {
				System.err.println("Length of string = " + keylength + ". It should be > 0");
			} else {
				break;
			}
		}
		// generate plainText array
		plainText = str.toCharArray();

		while (true) {
			// Get key
			System.out.println("Enter key");
			keyStr = in.nextLine();

			// Remove unwanted spaces from sides
			keyStr = keyStr.trim();
			System.out.println("Entered key = " + keyStr);

			key = keyStr.toCharArray();

			// keylength
			keylength = keyStr.length();

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
			System.out.println("cipherText[" + i + "] = " + cipherText[i]);
		}

		System.out.println();

		// Generate plain text
		for (i = 0; i < str.length(); i++) {
			plainText[i] = (char) (K[i] ^ cipherText[i]);
			System.out.println("plainText[" + i + "] = " + plainText[i]);
		}

	}
}
