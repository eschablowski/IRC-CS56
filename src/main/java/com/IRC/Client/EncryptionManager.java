package com.IRC.Client;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyFactory;
import java.security
  .PublicKey;
import java.security
  .PrivateKey;
import java.security
  .SecureRandom;
import java.security
  .spec.X509EncodedKeySpec;

import java.util.Base64;

import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.NoSuchPaddingException;

import javax.crypto.Cipher;

public class EncryptionManager {
  private PublicKey publicKey;
  private PrivateKey privateKey;


  private void getEncryptionCipher() {
    Cipher encryptionCipher = Cipher.getInstance("RSA");
    encryptionCipher.init(Cipher.ENCRYPT_MODE, publicKey);
    return encryptionCipher;
  }
  private void getDecryptionCipher() {
    Cipher decryptionCipher = Cipher.getInstance("RSA");
    decryptionCipher.init(Cipher.DECRYPT_MODE, privateKey);
    return decryptionCipher;
  }

  public EncryptionManager() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
    SecureRandom secureRandom
      = new SecureRandom();
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    keyPairGenerator.initialize(
      2048, secureRandom);
    KeyPair keyPair = keyPairGenerator
      .generateKeyPair();

    // Assign Keys
    this.publicKey = keyPair.getPublic();
    this.privateKey = keyPair.getPrivate();
  }

  EncryptionManager(KeyPair keyPair)  throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
    // Assign Keys
    this.publicKey = keyPair.getPublic();
    this.privateKey = keyPair.getPrivate();
  }
  
  public EncryptionManager(X509EncodedKeySpec certificate) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    // Assign Keys
    this.publicKey = keyFactory.generatePublic(certificate);
    try {
      this.privateKey = keyFactory.generatePrivate(certificate);
    } catch (InvalidKeySpecException exception){
      // Ignore exception if we have a public only key
    }
  }

  public EncryptionManager(byte[] certificate) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
    this(new X509EncodedKeySpec(certificate));
  }
  public EncryptionManager(String certificate) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
    this(new X509EncodedKeySpec(Base64.getDecoder().decode(certificate)));
  }

  public boolean canDecrypt() {
    return this.privateKey != null;
  }
  public boolean canEncrypt() {
    return this.publicKey != null;
  }
  
  public byte[] encrypt(byte[] data) {
    return this.getEncryptionCipher().doFinal(data);
  }
  public byte[] encrypt(String data) {
    return this.getEncryptionCipher().doFinal(data.getBytes());
  }

  public byte[] decrypt(byte[] data) {
    return this.getDecryptionCipher().doFinal(data);
  }
  public byte[] decrypt(String data) {
    return this.getDecryptionCipher().doFinal(data.getBytes());
  }

  public X509EncodedKeySpec certificate() {
    
  }
}