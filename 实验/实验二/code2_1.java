import java.util.Scanner;

class code2_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        System.out.println("请输入整数，并以999结束：");

        while (true) {
            if (scanner.hasNextInt()) {
                int num = scanner.nextInt();
                if (num == 999) {
                    break;
                }
                sum += num;
            } else {
                scanner.next(); // 清空非整数输入
                System.out.println("输入的不是整数，请重新输入：");
            }
        }

        System.out.println("总和为：" + sum);
        scanner.close();
    }
}
