package com.encryption.jce;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.SecureRandom;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AESCipherTest {

	private static final Logger LOG = LoggerFactory.getLogger(AESCipherTest.class);

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void encryptMessage() throws UnsupportedEncodingException {

		String key = "770A8A65DA156D24EE2A093277530142";
		AESCipher cipher = new AESCipher(key.getBytes("UTF-8"));

		String encryptedMessage = cipher.getEncryptedMessage("this is message");
		LOG.debug("Message is: {}", encryptedMessage);
		System.out.println(encryptedMessage);

		assertThat(encryptedMessage, is(notNullValue()));
		assertThat(encryptedMessage, is(not("this is message")));
	}

	@Test
	public void decryptMessage() throws UnsupportedEncodingException {

		// String key = "770A8A65DA156D24EE2A093277530142";
		String key = "PEPSICOSWIFTIMPLEMENTATION2015TS";
		AESCipher cipher = new AESCipher(key.getBytes("UTF-8"));

		// String messageToEncrypt = "this is the secret message I want to encode";

		// String encryptedMessage = cipher.getEncryptedMessage(messageToEncrypt);
		final String encryptedMessage = "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";
		String decryptedMessage = cipher.getDecryptedMessage(encryptedMessage);

		LOG.info("Encrypted Message: {}, Decrypted Message: {}", encryptedMessage, decryptedMessage);
		System.out.println(decryptedMessage);
		// assertThat(decryptedMessage, is(messageToEncrypt));
	}

	public static byte[] generateIV() {
		byte[] iv = new byte[16];
		new SecureRandom().nextBytes(iv);
		LOG.info("\n*** IV: [{}]", iv);
		return iv;
	}

	@Test
	public void encryptMessageFromKeystore() {

		Key key = KeystoreUtil.getKeyFromKeyStore("src/test/resources/aes-keystore.jck", "mystorepass",
				"jceksaes128Bits", "mykeypass");

		AESCipher cipher = new AESCipher(key);

		String encryptedMessage = cipher.getEncryptedMessage("this is message");
		LOG.debug("Message is: {}", encryptedMessage);

		assertThat(encryptedMessage, is(notNullValue()));
		assertThat(encryptedMessage, is(not("this is message")));
	}

	@Test
	public void decryptMessageFromKeystore() {

		Key key = KeystoreUtil.getKeyFromKeyStore("src/test/resources/aes-keystore.jck", "mystorepass",
				"jceksaes128Bits", "mykeypass");
		AESCipher cipher = new AESCipher(key);

		String messageToEncrypt = "this is the secret message I want to encode";

		String encryptedMessage = cipher.getEncryptedMessage(messageToEncrypt);
		String decryptedMessage = cipher.getDecryptedMessage(encryptedMessage);

		LOG.debug("Original Message: {}, Encrypted Message: {}, Decrypted Message: {}", messageToEncrypt,
				encryptedMessage, decryptedMessage);
		assertThat(decryptedMessage, is(messageToEncrypt));
	}

	@Test
	public void usingStringBasedIV() {

		Key key = KeystoreUtil.getKeyFromKeyStore("src/test/resources/aes-keystore.jck", "mystorepass",
				"jceksaes128Bits", "mykeypass");
		String iv = "1111111111111111";

		AESCipher cipher = new AESCipher(key, iv.getBytes());

		String encryptedMessage = cipher.getEncryptedMessage("this is message");
		LOG.debug("Message is: {}", encryptedMessage);

		assertThat(encryptedMessage, is(notNullValue()));
		assertThat(encryptedMessage, is(not("this is message")));
	}

	@Test
	public void usingStringBasedIVWithUTF8ExtendedCharacter() throws UnsupportedEncodingException {

		thrown.expectCause(isA(InvalidAlgorithmParameterException.class));
		thrown.expectMessage("Wrong IV length: must be 16 bytes long");

		Key key = KeystoreUtil.getKeyFromKeyStore("src/test/resources/aes-keystore.jck", "mystorepass",
				"jceksaes128Bits", "mykeypass");
		String iv = "111111111111111\u4111";

		AESCipher cipher = new AESCipher(key, iv.getBytes("UTF-8"));

		String encryptedMessage = cipher.getEncryptedMessage("this is message");
		LOG.debug("Message is: {}", encryptedMessage);

		assertThat(encryptedMessage, is(notNullValue()));
		assertThat(encryptedMessage, is(not("this is message")));
	}

	@Test
	public void usingStringBasedIVWithIncorrectLength() throws UnsupportedEncodingException {

		thrown.expectCause(isA(InvalidAlgorithmParameterException.class));
		thrown.expectMessage("Wrong IV length: must be 16 bytes long");

		Key key = KeystoreUtil.getKeyFromKeyStore("src/test/resources/aes-keystore.jck", "mystorepass",
				"jceksaes128Bits", "mykeypass");
		String iv = "11111111";

		AESCipher cipher = new AESCipher(key, iv.getBytes("UTF-8"));

		cipher.getEncryptedMessage("this is message");
	}

	@Test
	public void encryptMessageFromKeystoreWithIv() {

		Key key = KeystoreUtil.getKeyFromKeyStore("src/test/resources/aes-keystore.jck", "mystorepass",
				"jceksaes128Bits", "mykeypass");
		byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		AESCipher cipher = new AESCipher(key, iv);

		String encryptedMessage = cipher.getEncryptedMessage("this is message");
		LOG.debug("Message is: {}", encryptedMessage);

		assertThat(encryptedMessage, is(notNullValue()));
		assertThat(encryptedMessage, is(not("this is message")));
	}

	@Test
	public void decryptMessageFromKeystoreWithIv() {

		Key key = KeystoreUtil.getKeyFromKeyStore("src/test/resources/aes-keystore.jck", "mystorepass",
				"jceksaes128Bits", "mykeypass");
		byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		AESCipher cipher = new AESCipher(key, iv);

		String messageToEncrypt = "this is the secret message I want to encode";

		String encryptedMessage = cipher.getEncryptedMessage(messageToEncrypt);
		String decryptedMessage = cipher.getDecryptedMessage(encryptedMessage);

		LOG.debug("Original Message: {}, Encrypted Message: {}, Decrypted Message: {}", messageToEncrypt,
				encryptedMessage, decryptedMessage);
		assertThat(decryptedMessage, is(messageToEncrypt));
	}

	@Test
	public void encryptDecryptUsingDifferentCiphersSameIV() {

		Key key = KeystoreUtil.getKeyFromKeyStore("src/test/resources/aes-keystore.jck", "mystorepass",
				"jceksaes128Bits", "mykeypass");
		byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		byte[] differentIV = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		AESCipher cipher = new AESCipher(key, iv);
		AESCipher differentCipher = new AESCipher(key, differentIV);

		String messageToEncrypt = "this is the secret message I want to encode";

		String encryptedMessage = cipher.getEncryptedMessage(messageToEncrypt);
		String decryptedMessage = differentCipher.getDecryptedMessage(encryptedMessage);

		LOG.debug("Original Message: {}, Encrypted Message: {}, Decrypted Message: {}", messageToEncrypt,
				encryptedMessage, decryptedMessage);
		assertThat(decryptedMessage, is(messageToEncrypt));
	}

	@Test
	public void encryptDecryptUsingDifferentCiphersDifferentIV() {

		Key key = KeystoreUtil.getKeyFromKeyStore("src/test/resources/aes-keystore.jck", "mystorepass",
				"jceksaes128Bits", "mykeypass");
		byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		byte[] differentIV = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };

		AESCipher cipher = new AESCipher(key, iv);
		AESCipher differentCipher = new AESCipher(key, differentIV);

		String messageToEncrypt = "this is the secret message I want to encode";

		String encryptedMessage = cipher.getEncryptedMessage(messageToEncrypt);
		String decryptedMessage = differentCipher.getDecryptedMessage(encryptedMessage);

		LOG.debug("Encrypted: [{}], Decrypted[{}]", encryptedMessage, decryptedMessage);

		assertThat("Original message should have not been the same after decoding with a different IV",
				decryptedMessage, is(not(messageToEncrypt)));
	}

}
