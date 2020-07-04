package com.encryption.bouncycastle;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.junit.Test;

import com.encryption.jce.AESCipher;
import com.google.common.base.Charsets;

public class AESBouncyCastleTest {

	@Test
	public void testEncryptDecrypt() throws UnsupportedEncodingException, DataLengthException, InvalidCipherTextException, NoSuchAlgorithmException {
		KeyGenerator kg = KeyGenerator.getInstance("AES");
		kg.init(256);
		SecretKey sk = kg.generateKey();

		AESBouncyCastle abc = new AESBouncyCastle();
		abc.setPadding(new PKCS7Padding());
		abc.setKey(sk.getEncoded());

		String secret = "This is a secret message!";
		byte[] ba = secret.getBytes(Charsets.UTF_8);

		byte[] encr = abc.encrypt(ba);
		byte[] retr = abc.decrypt(encr);

		if (retr.length == ba.length) {
			ba = retr;
		} else {
			System.arraycopy(retr, 0, ba, 0, ba.length);
		}

		assertEquals(secret, new String(ba, Charsets.UTF_8));
	}
	
	@Test
	public void testEncryptJCEdecryptBouncy() throws UnsupportedEncodingException, DataLengthException, InvalidCipherTextException, NoSuchAlgorithmException {
		
		String key = "770A8A65DA156D24";
        AESCipher cipher = new AESCipher(key.getBytes("UTF-8"));

        final String messageToEncrypt = "this is message";
        
        //encrypt using JCE
		byte[] encryptedMessage = cipher.getEncrypted(messageToEncrypt);
		
		AESBouncyCastle abc = new AESBouncyCastle();
		abc.setPadding(new PKCS7Padding());
		abc.setKey(key.getBytes("UTF-8"));
		// decrypted using Bouncy Castle
		byte[] decryptedMessage = abc.decrypt(encryptedMessage);
		
        assertEquals(messageToEncrypt, new String(decryptedMessage, "UTF-8"));

	}

}
