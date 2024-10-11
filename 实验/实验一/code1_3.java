class code1_3 {
    // 静态成员变量
    static int staticVar1 = initializeStaticVar1();
    static int staticVar2;
    static {
        staticVar2 = initializeStaticVar2();
    }

    // 普通成员变量
    int instanceVar1 = initializeInstanceVar1();
    int instanceVar2;
    {
        instanceVar2 = initializeInstanceVar2();
    }

    // 构造方法
    public code1_3() {
        System.out.println("构造方法执行");
    }

    // 初始化静态变量的方法
    private static int initializeStaticVar1() {
        System.out.println("静态变量 staticVar1 初始化");
        return 1;
    }

    private static int initializeStaticVar2() {
        System.out.println("静态变量 staticVar2 初始化");
        return 2;
    }

    // 初始化普通变量的方法
    private int initializeInstanceVar1() {
        System.out.println("普通变量 instanceVar1 初始化");
        return 10;
    }

    private int initializeInstanceVar2() {
        System.out.println("普通变量 instanceVar2 初始化");
        return 20;
    }

    public static void main(String[] args) {
        System.out.println("开始创建对象");
        code1_3 demo = new code1_3();
    }
}
