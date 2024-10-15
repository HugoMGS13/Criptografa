import java.util.Scanner;
import java.util.InputMismatchException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class Cli {
    public void init() throws Exception{
        System.out.println("\r\n" + //
        " ________  ________  ___  ________  _________  ________  ________  ________  ________  ________ ________     \r\n" + //
        "|\\   ____\\|\\   __  \\|\\  \\|\\   __  \\|\\___   ___\\\\   __  \\|\\   ____\\|\\   __  \\|\\   __  \\|\\  _____\\\\   __  \\    \r\n" + //
        "\\ \\  \\___|\\ \\  \\|\\  \\ \\  \\ \\  \\|\\  \\|___ \\  \\_\\ \\  \\|\\  \\ \\  \\___|\\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\__/\\ \\  \\|\\  \\   \r\n" + //
        " \\ \\  \\    \\ \\   _  _\\ \\  \\ \\   ____\\   \\ \\  \\ \\ \\  \\\\\\  \\ \\  \\  __\\ \\   _  _\\ \\   __  \\ \\   __\\\\ \\   __  \\  \r\n" + //
        "  \\ \\  \\____\\ \\  \\\\  \\\\ \\  \\ \\  \\___|    \\ \\  \\ \\ \\  \\\\\\  \\ \\  \\|\\  \\ \\  \\\\  \\\\ \\  \\ \\  \\ \\  \\_| \\ \\  \\ \\  \\ \r\n" + //
        "   \\ \\_______\\ \\__\\\\ _\\\\ \\__\\ \\__\\        \\ \\__\\ \\ \\_______\\ \\_______\\ \\__\\\\ _\\\\ \\__\\ \\__\\ \\__\\   \\ \\__\\ \\__\\\r\n" + //
        "    \\|_______|\\|__|\\|__|\\|__|\\|__|         \\|__|  \\|_______|\\|_______|\\|__|\\|__|\\|__|\\|__|\\|__|    \\|__|\\|__|\r\n" + //
        "                                                                                                             \r\n" + //
        "");
        try{
            while(true){
                Chaves keyPair = new Chaves();

                @SuppressWarnings("resource")
                Scanner scan = new Scanner(System.in);

                byte[] mensagemCifrada;
                
                System.out.println("What do you want to do?\n0 - Exit\n1 - Encrypt a message\n2 - Decrypt a message");

                int choice = scan.nextInt();
                scan.nextLine();

                if (choice == 0){
                    break;
                }
                else if (choice == 1){
                    System.out.println("Digite uma mensagem para ser criptografada: ");

                    String message = scan.nextLine();

                    mensagemCifrada = Criptografia.encripta(message, keyPair.getPublicKey());

                    String mensagemCifradaBase64 = Base64.getEncoder().encodeToString(mensagemCifrada); //Transformando a mensagem criptografada (bytes) em Base64 (É usado para transmitir dados binários através de textos)

                    System.out.println("A mensagem foi cifrada (Retorno em Base64): " + mensagemCifradaBase64 + "\n************************************************************"); //Retornando a mensagem cifrada em Base64

                    byte[] privateKeyBytes = keyPair.getPrivateKey().getEncoded(); //Transformando a chave privada em Byte (PrivateKey -> Byte)

                    String privateKey64 = Base64.getEncoder().encodeToString(privateKeyBytes); //Transformando a chave em Base64

                    System.out.println("A privateKey é: " + privateKey64 + "\n************************************************************"); //Retornando a chave em Base64

                }
                else if (choice == 2){
                    System.out.println("Digite sua mensagem cifrada: "); //Solicitando entrada do usuário em base64

                    String msguser =  scan.nextLine(); //scan

                    byte[] mensagemCifradabyte = Base64.getDecoder().decode(msguser); //Transformando a mensagem de Base64 para byte

                    System.out.println("Digite sua chave: ");

                    String keyuser =  scan.nextLine(); //Solicitando a entrada da chave em base64

                    byte[] chavePrivadaBytes = Base64.getDecoder().decode(keyuser); //Transformando a chave em byte
                    
                    PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(chavePrivadaBytes); //Codificando a chave privada seguindo a PKCS#8 (Dizendo ao java que isso é uma PrivateKey)

                    KeyFactory keyFactory = KeyFactory.getInstance("RSA"); //Transformando a chave em uma privatekey utilizável

                    PrivateKey chavePrivada = keyFactory.generatePrivate(spec); //Criando a variável chavePrivada que vai armazenar a privatekey retornada

                    System.out.println("A mensagem é: " + Criptografia.decripta(mensagemCifradabyte, chavePrivada));
                }
            }
        }
        catch (InputMismatchException e){
            System.out.println("Sua entrada foi inválida, tente novamente por favor.");
        }
        catch (RuntimeException e){
            System.out.println("Ocorreu um erro inesperado. Por favor, tente novamente.");
        }
    }
}
