import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CryptoManagerTestStudent {

    @Test
    public void testVigenere() {
        String plaintext = "HELLOWORLD";
        String key = "KEY";
        String encrypted = CryptoManager.vigenereEncryption(plaintext, key);
        String decrypted = CryptoManager.vigenereDecryption(encrypted, key);
        assertEquals(plaintext, decrypted);
    }

    @Test
    public void testCaesar() {
        String plaintext = "JAVA";
        int key = 3;
        String encrypted = CryptoManager.caesarEncryption(plaintext, key);
        String decrypted = CryptoManager.caesarDecryption(encrypted, key);
        assertEquals(plaintext, decrypted);
    }

    @Test
    public void testPlayfair() {
        String plaintext = "HELLO";
        String key = "KEYWORD";
        String encrypted = CryptoManager.playfairEncryption(plaintext, key);
        String decrypted = CryptoManager.playfairDecryption(encrypted, key);
        assertEquals(plaintext.length(), decrypted.length()); // length must match
    }

    @Test
    public void testOutOfBounds() {
        String text = "hello"; // lowercase out of bounds
        assertEquals("The selected string is not in bounds, Try again.", CryptoManager.caesarEncryption(text, 3));
    }
}
