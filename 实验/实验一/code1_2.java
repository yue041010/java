public class code1_2 {
    public static void main(String[] args) {
        System.out.println("100到1000之间的水仙花数有：");
        for (int i = 100; i < 1000; i++) {
            if (isNarcissistic(i)) {
                System.out.println(i);
            }
        }
    }

    public static boolean isNarcissistic(int number) {
        int hundreds = number / 100;
        int tens = (number / 10) % 10;
        int units = number % 10;
        int sum = (int) (Math.pow(hundreds, 3) + Math.pow(tens, 3) + Math.pow(units, 3));
        return sum == number;
    }
}
