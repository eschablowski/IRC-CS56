package com.IRC.Encryption;

import java.security.KeyPair;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import java.util.Base64;

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

  EncryptionManager(KeyPair keyPair) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
    // Assign Key
    this.publicKey = keyPair.getPublic();
  }

  public EncryptionManager(X509EncodedKeySpec certificate)
      throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    // Assign Key
    this.publicKey = keyFactory.generatePublic(certificate);
  }

  public EncryptionManager(byte[] certificate)
      throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
    this(new X509EncodedKeySpec(certificate));
  }

  public EncryptionManager(String certificate)
      throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
    this(new X509EncodedKeySpec(Base64.getDecoder().decode(certificate)));
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