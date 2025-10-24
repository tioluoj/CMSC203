/*
 * Class: CMSC203
 * Description: Provides static methods for Vigenere, Playfair, and Caesar ciphers
 
 * Due: MM/DD/YYYY
 * Platform/compiler: Java 17 / Eclipse
 * Author: Tioluwoni Ojo
 */

import java.util.ArrayList;

public class CryptoManager {

    public static final char LOWER_RANGE = 'A';
    public static final char UPPER_RANGE = 'Z';

    // Check if string is within bounds
    public static boolean isStringInBounds(String plainText) {
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            if (c < LOWER_RANGE || c > UPPER_RANGE) {
                return false;
            }
        }
        return true;
    }

    // ================= VIGENERE CIPHER =================
    public static String vigenereEncryption(String plainText, String key) {
        if (!isStringInBounds(plainText)) return "The selected string is not in bounds, Try again.";
        StringBuilder result = new StringBuilder();
        key = key.toUpperCase();
        int keyIndex = 0;
        for (int i = 0; i < plainText.length(); i++) {
            char p = plainText.charAt(i);
            int shift = key.charAt(keyIndex) - 'A';
            char c = (char) ((p - 'A' + shift) % 26 + 'A');
            result.append(c);
            keyIndex = (keyIndex + 1) % key.length();
        }
        return result.toString();
    }

    public static String vigenereDecryption(String encryptedText, String key) {
        if (!isStringInBounds(encryptedText)) return "The selected string is not in bounds, Try again.";
        StringBuilder result = new StringBuilder();
        key = key.toUpperCase();
        int keyIndex = 0;
        for (int i = 0; i < encryptedText.length(); i++) {
            char c = encryptedText.charAt(i);
            int shift = key.charAt(keyIndex) - 'A';
            char p = (char) ((c - 'A' - shift + 26) % 26 + 'A');
            result.append(p);
            keyIndex = (keyIndex + 1) % key.length();
        }
        return result.toString();
    }

    // ================= CAESAR CIPHER =================
    public static String caesarEncryption(String plainText, int key) {
        if (!isStringInBounds(plainText)) return "The selected string is not in bounds, Try again.";
        StringBuilder result = new StringBuilder();
        key = key % 26;
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            char encryptedChar = (char) ((c - 'A' + key + 26) % 26 + 'A');
            result.append(encryptedChar);
        }
        return result.toString();
    }

    public static String caesarDecryption(String encryptedText, int key) {
        if (!isStringInBounds(encryptedText)) return "The selected string is not in bounds, Try again.";
        StringBuilder result = new StringBuilder();
        key = key % 26;
        for (int i = 0; i < encryptedText.length(); i++) {
            char c = encryptedText.charAt(i);
            char decryptedChar = (char) ((c - 'A' - key + 26) % 26 + 'A');
            result.append(decryptedChar);
        }
        return result.toString();
    }

    // ================= PLAYFAIR CIPHER =================
    // Build 8x8 Playfair matrix
    private static char[][] buildPlayfairMatrix(String key) {
        key = key.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder sb = new StringBuilder();
        ArrayList<Character> used = new ArrayList<>();
        for (char c : key.toCharArray()) {
            if (!used.contains(c)) {
                sb.append(c);
                used.add(c);
            }
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            if (!used.contains(c)) {
                sb.append(c);
                used.add(c);
            }
        }
        char[][] matrix = new char[8][8];
        int idx = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (idx < sb.length()) matrix[i][j] = sb.charAt(idx++);
            }
        }
        return matrix;
    }

    private static int[] findPosition(char[][] matrix, char c) {
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                if (matrix[i][j] == c) return new int[]{i, j};
        return null;
    }

    private static String processPlayfair(String text, char[][] matrix, boolean encrypt) {
        if (!isStringInBounds(text)) return "The selected string is not in bounds, Try again.";
        text = text.toUpperCase();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = (i + 1 < text.length()) ? text.charAt(i + 1) : 'X';
            int[] posA = findPosition(matrix, a);
            int[] posB = findPosition(matrix, b);
            if (posA[0] == posB[0]) { // same row
                result.append(matrix[posA[0]][(posA[1] + (encrypt ? 1 : 7)) % 8]);
                result.append(matrix[posB[0]][(posB[1] + (encrypt ? 1 : 7)) % 8]);
            } else if (posA[1] == posB[1]) { // same column
                result.append(matrix[(posA[0] + (encrypt ? 1 : 7)) % 8][posA[1]]);
                result.append(matrix[(posB[0] + (encrypt ? 1 : 7)) % 8][posB[1]]);
            } else { // rectangle
                result.append(matrix[posA[0]][posB[1]]);
                result.append(matrix[posB[0]][posA[1]]);
            }
        }
        return result.toString();
    }

    public static String playfairEncryption(String plainText, String key) {
        char[][] matrix = buildPlayfairMatrix(key);
        return processPlayfair(plainText, matrix, true);
    }

    public static String playfairDecryption(String encryptedText, String key) {
        char[][] matrix = buildPlayfairMatrix(key);
        return processPlayfair(encryptedText, matrix, false);
    }
}
