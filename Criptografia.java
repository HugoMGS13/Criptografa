import java.security.PublicKey;
import java.security.PrivateKey;
import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;


public class Criptografia{

    public static byte[] encripta(String mensagem, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(mensagem.getBytes(StandardCharsets.UTF_8));
    }

    public static String decripta(byte[] mensagemCifrada, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] mensagemAberta = cipher.doFinal(mensagemCifrada);
        return new String(mensagemAberta);
    }
}