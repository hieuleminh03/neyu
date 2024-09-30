package zen8.hieule.common;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {

    public static byte[] hashToken(String token) {
        try {
            // Convert the token (string) to bytes
            byte[] tokenBytes = token.getBytes(StandardCharsets.UTF_8);

            // Hash the bytes using SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(tokenBytes);

            // Extract the first 5 bytes from the hashed bytes
            byte[] result = new byte[5];
            System.arraycopy(hashedBytes, 0, result, 0, 5);

            return result;

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }
}
