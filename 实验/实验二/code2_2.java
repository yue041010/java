import java.util.Scanner;

class code2_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        System.out.println("请输入表达式，并以999结束：");

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("999")) {
                break;
            }
            try {
                int result = evaluateExpression(input);
                sum += result;
                System.out.println("当前总和为：" + sum);
            } catch (Exception e) {
                System.out.println("输入的表达式有误，请重新输入：");
            }
        }

        System.out.println("总和为：" + sum);
        scanner.close();
    }

    private static int evaluateExpression(String expression) {
        String[] tokens = expression.split(" ");
        int result = Integer.parseInt(tokens[0]);
        for (int i = 1; i < tokens.length; i += 2) {
            String operator = tokens[i];
            int operand = Integer.parseInt(tokens[i + 1]);
            switch (operator) {
                case "+":
                    result += operand;
                    break;
                case "-":
                    result -= operand;
                    break;
                case "*":
                    result *= operand;
                    break;
                case "/":
                    result /= operand;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operator: " + operator);
            }
        }
        return result;
    }
}
