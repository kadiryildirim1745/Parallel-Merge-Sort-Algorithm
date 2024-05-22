import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
public class DataParallelismExample {
    public static void main(String[] args) {
        int[] lengths = { 100000,250000,500000, 1000000,2500000, 5000000 };
        int numThreads = 4;
        try (FileWriter writer = new FileWriter("sort_times.csv")) {
            writer.write("Length,ParallelTime,Oran,SerialTime\n");

            for (int length : lengths) {
                System.out.println("Array Length: " + length);

                Random random = new Random();
                int[] arr = new int[length];
                for (int i = 0; i < length; i++) {
                    arr[i] = random.nextInt();
                }

                int[] parallelArr = arr.clone();
                double parallelStart = System.currentTimeMillis();
                ForkJoinPool pool = new ForkJoinPool(numThreads);
                ParallelMergeSort parallelMergeSort = new ParallelMergeSort(parallelArr, 0, parallelArr.length - 1);
                pool.invoke(parallelMergeSort);
                double parallelFinish = System.currentTimeMillis();
                double parallelTime = parallelFinish - parallelStart;
                System.out.println("Parallel Merge Sort Time: " + parallelTime + " ms");

                int[] serialArr = arr.clone();
                long serialStart = System.currentTimeMillis();
                SerialMergeSort serialMergeSort = new SerialMergeSort(serialArr, 0, serialArr.length - 1);
                serialMergeSort.sort();
                long serialFinish = System.currentTimeMillis();
                long serialTime = serialFinish - serialStart;
                System.out.println("Serial Merge Sort Time: " + serialTime + " ms");
                
                
                double paralel=parallelTime-parallelMergeSort.gsayac;
                System.out.println("seri "+ parallelMergeSort.gsayac);
                System.out.println("paralel"+" "+paralel);
                double oran = parallelMergeSort.gsayac/paralel;
                System.out.println("oran===== "+oran);
                writer.write(length + "," + parallelTime + "," +oran+ "," + serialTime + "\n");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
