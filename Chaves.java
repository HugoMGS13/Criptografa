import java.security.KeyPairGenerator;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.PrivateKey;

public class Chaves {

    private KeyPair keypair;

    public Chaves() throws Exception{
        this.keypair = gerarChaves();
    }

    private KeyPair gerarChaves() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(3072);
        this.keypair = keyPairGenerator.generateKeyPair();
        return keyPairGenerator.generateKeyPair();
    }

    public PublicKey getPublicKey() {
        return this.keypair.getPublic();
    }

    public PrivateKey getPrivateKey() {
        return this.keypair.getPrivate();
    }
 

}
