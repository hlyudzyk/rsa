import java.math.BigDecimal;
import java.math.BigInteger;

public class Rsa {

    public static void main(String args[]) {
        testRsa(34);
        testRsa(1920);
        testRsa(97651);
    }

    public static void testRsa(int msg){
        int prk = 0, pk;
        int message = msg;
        int prime1 = 881;
        int prime2 = 983;

        int n = prime1 * prime2;
        System.out.println("n is equal to: " + n);

        int factorN = (prime1 - 1) * (prime2 - 1);
        System.out.println("factorN is equal to: " + factorN);

        for (pk = 2; pk < factorN; pk++) {
            if (getGCD(pk, factorN) == 1) {
                break;
            }
        }

        System.out.println("Public key is = " + pk);

        prk = modInverse(pk, factorN);
        System.out.println("Private key is : " + prk);

        double encryptedMsg;
        BigInteger d_message;
        encryptedMsg = (Math.pow(message, pk)) % n;
        System.out.println("Encrypted message is : " + encryptedMsg);

        BigInteger bigN = BigInteger.valueOf(n);

        BigInteger bigC = BigDecimal.valueOf(encryptedMsg).toBigInteger();

        d_message = (bigC.pow(prk)).mod(bigN);

        System.out.println("Decrypted message is : " + d_message + "\n");
    }

    public static int getGCD(int mod, int num) {
        if (mod == 0)
            return num;
        else
            return getGCD(num % mod, mod);
    }

    public static int modInverse(int a, int m) {
        int m0 = m;
        int y = 0, x = 1;

        if (m == 1)
            return 0;

        while (a > 1) {
            int q = a / m;
            int t = m;

            m = a % m;
            a = t;
            t = y;

            y = x - q * y;
            x = t;
        }
        if (x < 0)
            x += m0;

        return x;
    }
}
