import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

public class code5_1 {

    private static final int Ra = 4; // A矩阵的行数
    private static final int Ca = 3; // A矩阵的列数，同时也是B矩阵的行数
    private static final int Cb = 4; // B矩阵的列数
    private static final int P = 4; // 线程数

    public static void main(String[] args) {
        float[][] A = {
                { 1, 0, 2 },
                { 0, 3, 0 },
                { 4, 0, 0 },
                { 0, 0, 5 }
        };

        float[][] B = {
                { 1, 0, 0, 0 },
                { 0, 3, 0, 0 },
                { 0, 0, 4, 0 }
        };

        float[][] C = new float[Ra][Cb];

        multiply(A, B, C);

        // 打印结果矩阵C
        for (int i = 0; i < Ra; i++) {
            for (int j = 0; j < Cb; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void multiply(float[][] A, float[][] B, float[][] C) {
        BlockingQueue<Task> tasks = new LinkedBlockingQueue<>();

        // 初始化任务队列
        for (int i = 0; i < Ra; i++) {
            for (int j = 0; j < Cb; j++) {
                tasks.add(new Task(i, j));
            }
        }

        // 创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(P);
        List<Future<?>> futures = new ArrayList<>();

        // 启动线程执行计算任务
        for (int k = 0; k < P; k++) {
            futures.add(executor.submit(new Worker(A, B, C, tasks)));
        }

        // 等待所有线程完成
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        // 关闭线程池
        executor.shutdown();
    }

    // 定义任务类
    static class Task {
        int row;
        int col;

        Task(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    // 定义工作线程
    static class Worker implements Runnable {
        private final float[][] A;
        private final float[][] B;
        private final float[][] C;
        private final BlockingQueue<Task> tasks;

        Worker(float[][] A, float[][] B, float[][] C, BlockingQueue<Task> tasks) {
            this.A = A;
            this.B = B;
            this.C = C;
            this.tasks = tasks;
        }

        @Override
        public void run() {
            try {
                Task task;
                while ((task = tasks.poll()) != null) {
                    int i = task.row;
                    int j = task.col;
                    C[i][j] = computeElement(A, B, i, j);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private float computeElement(float[][] A, float[][] B, int row, int col) {
            float sum = 0;
            for (int k = 0; k < Ca; k++) {
                sum += A[row][k] * B[k][col];
            }
            return sum;
        }
    }
}
