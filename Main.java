import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Main {

    // Sequential implementation
    public static int findMaxSequential(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    // Parallel implementation using ForkJoinPool
    public static int findMaxParallel(int[] array) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new MaxTask(array, 0, array.length));
    }

    // RecursiveTask to split the array and find max in parallel
    static class MaxTask extends RecursiveTask<Integer> {
        private final int[] array;
        private final int start;
        private final int end;

        public MaxTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start <= 1000) {
                int max = array[start];
                for (int i = start + 1; i < end; i++) {
                    if (array[i] > max) {
                        max = array[i];
                    }
                }
                return max;
            } else {
                int mid = (start + end) / 2;
                MaxTask leftTask = new MaxTask(array, start, mid);
                MaxTask rightTask = new MaxTask(array, mid, end);

                leftTask.fork();
                int rightResult = rightTask.compute();
                int leftResult = leftTask.join();

                return Math.max(leftResult, rightResult);
            }
        }
    }

    public static void main(String[] args) {
        int[] array;
        int max;
        long startTime, endTime;

        System.out.println("N\t\tSequential Time (ms)\t\tParallel Time (ms)\t\tPerformance");

        for (int N = 100000; N <= 1000000; N += 100000) {
            // Generating an array of size N with random values
            array = new int[N];
            for (int i = 0; i < N; i++) {
                array[i] = (int) (Math.random() * 1000);
            }

            // Sequential execution
            startTime = System.currentTimeMillis();
            max = findMaxSequential(array);
            endTime = System.currentTimeMillis();
            long sequentialTime = endTime - startTime;

            // Parallel execution
            startTime = System.currentTimeMillis();
            max = findMaxParallel(array);
            endTime = System.currentTimeMillis();
            long parallelTime = endTime - startTime;

            // Calculate and display performance
            double performance = (double) sequentialTime / parallelTime;
            System.out.println(N + "\t\t\t" + sequentialTime + "\t\t\t\t\t\t" + parallelTime + "\t\t\t\t\t\t" + performance);
        }
    }
}