package com.IRC.Encryption;

import java.security.KeyPair;
import java.security.PublicKey;
import java.security.cert.Certificate;

import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
import java.lang.UnsupportedOperationException;

import javax.crypto.Cipher;

public class EncryptionManager {
  private PublicKey publicKey;

  private Cipher getCipher() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
    Cipher decryptionCipher = Cipher.getInstance("RSA");
    decryptionCipher.init(Cipher.ENCRYPT_MODE, publicKey);
    return decryptionCipher;
  }

  public EncryptionManager(PublicKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
    // Assign Key
    this.publicKey = key;
  }

  public EncryptionManager(KeyPair keyPair) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
    // Assign Key
    this.publicKey = keyPair.getPublic();
  }

  public EncryptionManager(Certificate certificate)
      throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
    // Assign Key
    this.publicKey = certificate.getPublicKey();
  }

  public byte[] encrypt(byte[] data)  throws IllegalBlockSizeException, UnsupportedOperationException {
    try {
      return this.getCipher().doFinal(data);
      // Catch all setup related exceptions
    } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
        | BadPaddingException nsaException) {
      throw new UnsupportedOperationException();
    }
  }

  public byte[] encrypt(String data)  throws IllegalBlockSizeException, UnsupportedOperationException {
    return this.encrypt(data.getBytes());
  }
}