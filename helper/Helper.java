package helper;
/*
 * @author : Devendra Lattu
 */
public class Helper {

	public static boolean isInteger(String num) {
		try {
			int number = Integer.parseInt(num);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static int getIntValue(String num) {
		int number = Integer.parseInt(num);
		return number;
	}

	public static boolean is_a_zA_Z(String key) {
		 return key.matches("[a-zA-Z]+");
	}
	
	public static boolean is_a_zA_Z_Sp(String key) {
		 return key.matches("[a-zA-Z]+(\\s+[a-zA-Z]+)*");
	}
}
