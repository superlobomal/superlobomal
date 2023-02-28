
package Poo2;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
  public static String MD5(String input) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes());
            byte[] messageDigest = digest.digest();
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);

            // Add leading zeros to make it 32 bits long
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String originalPassword = "password123";
        String encryptedPassword = MD5(originalPassword);
        System.out.println("Original Password: " + originalPassword);
        System.out.println("Encrypted Password: " + encryptedPassword);
    }
}
