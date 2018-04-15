
/**
 * Created by bogdantodasca on 15/04/18.
 */
public class Vigenere {
    public static void main(String[] args) {
        final Vigenere cipher = new Vigenere();
        final String key = "CRYPTO";
        final String message = "History of Cryptography";
        final String encrypted = cipher.encrypt(message, key);
        System.out.println(String.format("Encrypted: %s", encrypted));
        final String decrypted = cipher.decrypt(encrypted, key);
        System.out.println(String.format("Decrypted: %s", decrypted));

    }

    private String encrypt(final String m, final String k) {
        int c = 0;
        final StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < m.length(); i++) {
            final char current = m.charAt(i);
            final char upper;
            if (current >= 'a' && current <= 'z') {
                upper = (char) (current - ('a' - 'A'));
            } else if (current >= 'A' && current <= 'Z') {
                upper = current;
            } else {
                upper = 0;
            }
            switch (upper) {
                case 0:
                    encrypted.append(current);
                    break;
                default:
                    encrypted.append((char) ((upper + k.charAt(c % k.length())) % ('Z' - 'A' + 1) + 'A'));
                    c++;
                    break;
            }
        }
        return encrypted.toString();
    }

    private String decrypt(final String e, final String k) {
        int c = 0;
        final StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < e.length(); i++) {
            final char current = e.charAt(i);
            final char upper;
            if (current >= 'A' && current <= 'Z') {
                upper = current;
            } else {
                upper = 0;
            }
            switch (upper) {
                case 0:
                    decrypted.append(current);
                    break;
                default:
                    final int delta = e.charAt(i) - k.charAt(c % k.length());
                    c++;
                    if (delta >= 0) {
                        decrypted.append((char) (delta + 'A'));
                    } else {
                        decrypted.append((char) ('Z' - 'A' + 1 + delta + 'A'));
                    }
                    break;
            }
        }
        return decrypted.toString();
    }
}
