class code4_1 {
    private static int ticketCount = 100;

    public static void main(String[] args) {
        TicketSeller seller = new TicketSeller();

        // 创建并启动5个售票线程
        Thread t1 = new Thread(seller, "售票点1");
        Thread t2 = new Thread(seller, "售票点2");
        Thread t3 = new Thread(seller, "售票点3");
        Thread t4 = new Thread(seller, "售票点4");
        Thread t5 = new Thread(seller, "售票点5");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

    // 定义一个内部类来实现售票逻辑
    static class TicketSeller implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (Ticket.class) {
                    if (ticketCount > 0) {
                        System.out.println(Thread.currentThread().getName() + " 卖出了票号 " + ticketCount);
                        ticketCount--;
                        try {
                            // 为了更好地模拟实际情况，加入一点延迟
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        break;
                    }
                }
            }
        }
    }
}
