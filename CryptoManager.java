public class CryptoManager {
	
	private static final char LOWER_BOUND = 32;
	private static final char UPPER_BOUND = 95;
	private static final int RANGE = UPPER_BOUND - LOWER_BOUND + 1;
	
	
	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes 
	 * according to the LOWER_BOUND and UPPER_BOUND characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean stringInBounds (String plainText) {
		//checks to see if each character fits within the bounds 
		// if character does not fit within the bounds returns false
		boolean answer = true;
		for(int i = 0; i < plainText.length(); i++) {
			if (!((int)plainText.charAt(i) <= UPPER_BOUND && (int)plainText.charAt(i) >= LOWER_BOUND)) {
				answer =  false;
				break;
			}
			
		}
		return answer;
	}

	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it 
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String encryptCaesar(String plainText, int key) {
		String encryptedString = "";
		int encryptedValue = 0;
		// encrypts each character in the string based on the key
		for (int i = 0; i < plainText.length(); i++) {
			//calculates the encrypted ASCII number for the letter based on the key
			encryptedValue = (int)plainText.charAt(i) + key;
			//makes sure that the encrypted value is not bigger than the upper bound value
			//subtracts the range from the encrypted value until it is less than upper bound
			while (encryptedValue > UPPER_BOUND) {
				encryptedValue -=RANGE;
			}
			//Adds the encrypted character at the ASCII value to the string
			encryptedString += Character.toString((char)encryptedValue);
		}
		//return the string once all the characters have been encrypted
		return encryptedString;
		
	}
	
	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset 
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String encryptBellaso(String plainText, String bellasoStr) {
		String encrString ="";
		String newKey ="";
		int value = 0;
		
		//adds the characters from bellaso string into the newKey string in repeating order  
		//until the length of the string equals the length of the plainText string
		for ( int i = 0; i>=0 ; ++i ) {
			//if the value of i equals the length of the bellasoStr string
			//it sets i back to the 0 so the bellasoStr values can be repeated into newKey
			if (i == bellasoStr.length()) {
				i = 0;
				
			}
			//if the length of the two strings is the same then the loop breaks
			if (newKey.length() == plainText.length()) {
				break;
			}
			//adds the character to the newKey string
			newKey+= (bellasoStr.charAt(i));
			
		}
		
		
		//encrypts the plainText string
		for (int j = 0; j < plainText.length(); j++) {
			//calculating the ASCII value
			value = (plainText.charAt(j) + newKey.charAt(j));
			
			//make sure the value is not greater than the upper bound
			while(value > UPPER_BOUND) {
				value-= RANGE;
			}
			
			//adding the ASCII character of the value to the encrypted string
			encrString+= (char)(value);
		}
		return encrString;
		
	
		
		
		
		
	}
	
	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String decryptCaesar(String encryptedText, int key) {
		String decryptedString = "";
		int decryptedValue = 0;
		//decrypts each character from the encrypted string based on the key
		for (int i=0; i< encryptedText.length(); i++) {
			//calculates the decrypted ASCII number for the letter based on the key
			decryptedValue = (int)encryptedText.charAt(i) - key;
			//Adds the decrypted character at the ASCII value to the string
			//makes sure that the decrypted value is not smaller than the lower bound value
			//adds the range from the decrypted value until it is greater than lower bound
			while (decryptedValue < LOWER_BOUND) {
				decryptedValue +=RANGE;
			}
			decryptedString += Character.toString((char)decryptedValue);
		}
		return decryptedString;
	}
	
	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String decryptBellaso(String encryptedText, String bellasoStr) {
		String decrString ="";
		String newKey ="";
		int value = 0;
		//adds the characters from bellaso string into the newKey string in repeating order  
		//until the length of the string equals the length of the plainText string
		for ( int i = 0; i>=0 ; ++i ) {
		//if the value of i equals the length of the bellasoStr string
		//it sets i back to the 0 so the bellasoStr values can be repeated into newKey
			if (i == bellasoStr.length()) {
				i = 0;
					
			}
		//if the length of the two strings is the same then the loop breaks
			if (newKey.length() == encryptedText.length()) {
				break;
			}
		//adds the character to the newKey string
			newKey+= (bellasoStr.charAt(i));
		}
				
		
		for (int i = 0, j =0; i< encryptedText.length(); i++, j++) {
			value = (encryptedText.charAt(i) - newKey.charAt(j));
			// add range to value is it is less than lower bound
			while (value < LOWER_BOUND) {
				value+= RANGE;
			}
			// adding the character at the value to the string 
			decrString +=(char)value;
			
		}
		return decrString;
	}
	
}