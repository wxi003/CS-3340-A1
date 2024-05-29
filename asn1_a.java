/**
 * Name: Xi Wang
 * Student Number: 251241272
 * Question 7a
 */
public class asn1_a {

    // method to compute Lucas numbers at position n
    // Lucas numbers: Ln =  Ln-1 + Ln-2, n > 1; L1 = 1; L0 = 2
    public static long lucasNumRecursion(int n) {

        // not a valid position
        if (n < 1) {
            // L0
            if (n == 0){return 2;}
            return -1;
        //L1
        } else if (n == 1) {
            return 1;
        }
        //Ln =  Ln-1 + Ln-2
        return lucasNumRecursion(n - 1) + lucasNumRecursion(n - 2);
    }

    public static void main(String[] args) {
        for(int i = 0; i <= 10; i++) {
            System.out.println("L" + i*5 + " = " + lucasNumRecursion(i*5));
        }
    }
}
