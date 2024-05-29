/**
 * Name: Xi Wang
 * Student  Number: 251241272
 * Question 7b
 */

public class asn1_b {

    // The string representation of the value.
    private String value;

    // Constructor to initialize the class with an integer.
    public asn1_b(int value) {
        this.value = Integer.toString(value);
    }

    // Constructor to initialize the class with a string.
    public asn1_b(String value) {
        this.value = value;
    }

    // Getter method to retrieve the value.
    public String getValue() {
        return value;
    }

    // Helper method to convert the value to a char array.
    public char[] chars() {
        return this.value.toCharArray();
    }

    // Helper method to ensure two asn1_b objects have equal length strings.
    private static String[] equalLengths(asn1_b x, asn1_b y) {
        String yVal = y.getValue();
        String xVal = x.getValue();

        while (xVal.length() < yVal.length()) {
            xVal = "0" + xVal;
        }
        while (yVal.length() < xVal.length()) {
            yVal = "0" + yVal;
        }

        return new String[]{xVal, yVal};
    }

    // Method to add the value of another asn1_b object to this one.
    public void plus(asn1_b x) {
        String[] values = equalLengths(this, x);
        char[] thisChars = values[0].toCharArray();
        char[] xChars = values[1].toCharArray();

        int overflow = 0;
        StringBuilder solution = new StringBuilder();

        for (int i = thisChars.length - 1; i >= 0; i--) {
            int digitSum = Character.getNumericValue(thisChars[i]) + Character.getNumericValue(xChars[i]) + overflow;
            solution.append(digitSum % 10);
            overflow = digitSum / 10;
        }

        if (overflow > 0) {
            solution.append(overflow);
        }

        this.value = solution.reverse().toString();
    }

    // Method to multiply the value of another asn1_b object with this one.
    public void times(asn1_b x) {
        asn1_b result = new asn1_b(0);
        char[] xChars = x.chars();
        String num = this.getValue();

        for (int i = 0; i < xChars.length; i++) {
            int digit = Character.getNumericValue(xChars[xChars.length - 1 - i]);
            result.plus(new asn1_b(multiplyByInt(digit, num, i)));
        }

        this.value = result.getValue();
    }

    // Helper method to multiply a string representation of a number by an integer.
    private static String multiplyByInt(int x, String num, int zeroes) {
        StringBuilder product = new StringBuilder();
        int overflow = 0;

        for (int i = num.length() - 1; i >= 0; i--) {
            int temp = (num.charAt(i) - '0') * x + overflow;
            product.append(temp % 10);
            overflow = temp / 10;
        }

        if (overflow > 0) {
            product.append(overflow);
        }

        product.reverse();
        for (int i = 0; i < zeroes; i++) {
            product.append('0');
        }

        return product.toString();
    }

    // Method to compute the n-th Lucas number.
    public static asn1_b lucasNum(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Lucas number not defined for negative index.");
        } else if (n == 0) {
            return new asn1_b(2);
        } else if (n == 1) {
            return new asn1_b(1);
        }

        asn1_b[][] A = new asn1_b[][]{{new asn1_b(1),new asn1_b(1) },{new asn1_b(1),new asn1_b(0)}};
        asn1_b[][] K1 = new asn1_b[][] {{new asn1_b(1),new asn1_b(0) },{new asn1_b(2),new asn1_b(0)}};

        matrixPower(A, n - 1);
        multiplyMatrix(A,K1);

        return A[0][0];
    }

    // Method to compute matrix exponentiation
    public static void matrixPower(asn1_b[][] A, int N) {
        asn1_b[][] B = new asn1_b[][] { { new asn1_b(1), new asn1_b(1) }, { new asn1_b(1), new asn1_b(0) } };

        for (int i = 2; i <= N; i++) {
            multiplyMatrix(A, B);
        }
    }

    // Method to compute multiplication of 2 2X2 matrices
    public static void multiplyMatrix(asn1_b[][] A, asn1_b[][] B) {
        asn1_b a00 = new asn1_b(A[0][0].getValue());
        asn1_b a01 = new asn1_b(A[0][1].getValue());
        asn1_b a10 = new asn1_b(A[1][0].getValue());
        asn1_b a11 = new asn1_b(A[1][1].getValue());

        asn1_b b00 = new asn1_b(B[0][0].getValue());
        asn1_b b01 = new asn1_b(B[0][1].getValue());
        asn1_b b10 = new asn1_b(B[1][0].getValue());
        asn1_b b11 = new asn1_b(B[1][1].getValue());

        // Compute new values for the resulting matrix
        asn1_b x1 = new asn1_b(a00.getValue());
        x1.times(b00);
        asn1_b x2 = new asn1_b(a01.getValue());
        x2.times(b10);
        x1.plus(x2);

        asn1_b y1 = new asn1_b(a00.getValue());
        y1.times(b01);
        asn1_b y2 = new asn1_b(a01.getValue());
        y2.times(b11);
        y1.plus(y2);

        asn1_b z1 = new asn1_b(a10.getValue());
        z1.times(b00);
        asn1_b z2 = new asn1_b(a11.getValue());
        z2.times(b10);
        z1.plus(z2);

        asn1_b w1 = new asn1_b(a10.getValue());
        w1.times(b01);
        asn1_b w2 = new asn1_b(a11.getValue());
        w2.times(b11);
        w1.plus(w2);

        // Set the new values to the matrix A
        A[0][0] = x1;
        A[0][1] = y1;
        A[1][0] = z1;
        A[1][1] = w1;
    }


    public static void main(String[] args) {
        for (int i = 0; i <= 25; i++) {
            System.out.println("L(" + i*20 + ") = " +lucasNum(i*20).getValue());
        }
    }
}