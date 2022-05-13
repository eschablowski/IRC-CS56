package com.IRC.Encryption;

import java.security.KeyPair;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.X509EncodedKeySpec;

import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
import java.lang.UnsupportedOperationException;

import javax.crypto.Cipher;

public class DecryptionManager {
  private PrivateKey privateKey;

  private Cipher getCipher() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
    Cipher decryptionCipher = Cipher.getInstance("RSA");
    decryptionCipher.init(Cipher.DECRYPT_MODE, privateKey);
    return decryptionCipher;
  }

  public DecryptionManager(KeyPair keyPair)
      throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
    // Assign Key
    this.privateKey = keyPair.getPrivate();
  }

  public DecryptionManager(X509EncodedKeySpec certificate)
      throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    // Assign Key
    this.privateKey = keyFactory.generatePrivate(certificate);
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