/**
 * @author Elias Schablowski
 */
package com.IRC.Encryption;

import java.security.KeyPair;
import java.security.PrivateKey;

import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
import java.lang.UnsupportedOperationException;

import javax.crypto.Cipher;

/**
 * A utility class for encrypting strings and byte arrays with RSA
 */
public class DecryptionManager {
  /**
   * The private RSA key used for decryption
   */
  private PrivateKey privateKey;

  /**
   * Get a Cipher object to encrypy
   * @return A new Cipher object with the private key and config.
   * @throws NoSuchAlgorithmException Basically if the computer cannot do RSA (impossible)
   * @throws NoSuchPaddingException  Basically if the computer cannot do RSA (impossible)
   * @throws InvalidKeyException If the key provided during setup is not decryption capable
   */
  private Cipher getCipher() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
    Cipher decryptionCipher = Cipher.getInstance("RSA");
    decryptionCipher.init(Cipher.DECRYPT_MODE, privateKey);
    return decryptionCipher;
  }

  public DecryptionManager(PrivateKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
    // Assign Key
    this.privateKey = key;
  }

  public DecryptionManager(KeyPair keyPair)
      throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
    // Assign Key
    this.privateKey = keyPair.getPrivate();
  }

  public byte[] decrypt(byte[] data) throws IllegalBlockSizeException, UnsupportedOperationException {
    try {
      return this.getCipher().doFinal(data);
    } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
        | BadPaddingException nsaException) {
      throw new UnsupportedOperationException();
    }
  }

  public byte[] decrypt(String data) throws IllegalBlockSizeException, UnsupportedOperationException {
    return this.decrypt(data.getBytes());
  }
}