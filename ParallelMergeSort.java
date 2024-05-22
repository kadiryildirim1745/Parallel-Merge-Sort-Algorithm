import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort extends RecursiveAction {
    int[] arr;
    int left, right;
    public double gsayac=0;
    public ParallelMergeSort(int[] arr, int left, int right) {
        this.arr = arr;
        this.left = left;
        this.right = right;
    }

    @Override
    protected void compute() {
        if (left < right) {
            double serialStart = System.currentTimeMillis();
            int mid = (left + right) / 2;
            double serialFinish = System.currentTimeMillis();
            
            double serialTime = serialFinish - serialStart;
            ParallelMergeSort leftSort = new ParallelMergeSort(arr, left, mid);
            ParallelMergeSort rightSort = new ParallelMergeSort(arr, mid + 1, right);
            invokeAll(leftSort, rightSort);
            double sayac=merge(left, mid, right);
            gsayac+=serialTime+sayac;            
        }
    }

    private double merge(int left, int mid, int right) {
        double serialStart = System.currentTimeMillis();
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = arr[left + i];
        }
        for (int i = 0; i < n2; i++) {
            R[i] = arr[mid + 1 + i];
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        while (i < n1) {
            arr[k++] = L[i++];
        }
        while (j < n2) {
            arr[k++] = R[j++];
        }
        double serialFinish = System.currentTimeMillis();
        double serialTime = serialFinish - serialStart;
        return serialTime;
    }
}
