//实验一 --------------------------------------------------

//1
class Problem1 {
	public static void main(String[] args) {
		System.out.printf("姓名：岳兴彦\n身高：170cm\n体重：55kg\n年龄：19\n");
	}
}

// 2
class Problem2 {
	// 判断一个数是否是水仙花数（阿姆斯特朗数）
	static boolean judge(int n) {
		int a = n, m = 0;
		while (a != 0) {
			int digit = a % 10;
			m += digit * digit * digit;
			a /= 10;
		}
		return m == n;
	}

	// 打印所有的三位数水仙花数
	public static void main(String[] args) {

		System.out.println("所有的三位数水仙花数有：");
		for (int i = 100; i < 1000; i++) { // 上限应该是1000而不是1001
			if (judge(i)) {
				System.out.println(i);
			}
		}
	}
}

// 3
class Problem3 {
	static String staticVariable1 = "staticVariable1";
	static String staticVariable2;

	String variable1 = "variable1";
	String variable2;

	public Problem3(String a, String b) {
		staticVariable2 = a;
		variable2 = b;
	}

	public static void main(String[] args) {
		Problem3 m = new Problem3("staticVariable2", "variable2");
		System.out.printf("%s\n%s\n%s\n%s\n", m.staticVariable1, m.staticVariable2, m.variable1, m.variable2);
	}
}
