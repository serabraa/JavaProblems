package Chapter1;

import java.util.Random;

public class UnbreakableEncryption {
    //basic implementation of a One Time Pad which works with the symmetry of XOR operation
    //we have the principle of Plaintext = Key1 ^ Key2

    private static byte[] randomKey(int length){
        byte[] dummy = new byte[length];
        Random random = new Random();
        random.nextBytes(dummy);
        return dummy;
    }

    public static KeyPair encrypt(String original){
        byte [] originalBytes = original.getBytes();    //plaintext
        byte [] dummmyKey = randomKey(original.length()); //dummyKey, pseudorandom
        byte [] encryptedKey = new byte[originalBytes.length];
        for (int i = 0; i < originalBytes.length; i++) {
            //XORing eveything
            encryptedKey[i] = (byte) (originalBytes[i] ^ dummmyKey[i]);
        }
        return new KeyPair(dummmyKey,encryptedKey);
    }

    public static String decrypt(KeyPair kp){
        byte[] decrypted = new byte[kp.key1.length];
        for (int i = 0; i < kp.key1.length; i++) {
            decrypted[i] = (byte) (kp.key1[i] ^ kp.key2[i]);
        }
        return new String(decrypted);
    }

    public static void main(String[] args) {
        KeyPair kp = encrypt("All work and no play makes Sergo a dull boy");
        String result = decrypt(kp);
        System.out.println(result);
    }
}
