public class SerialMergeSort {
    int[] arr;
    int left, right;

    public SerialMergeSort(int[] arr, int left, int right) {
        this.arr = arr;
        this.left = left;
        this.right = right;
    }

    public void sort() {
        if (left < right) {
            int mid = (left + right) / 2;
            SerialMergeSort leftSort = new SerialMergeSort(arr, left, mid);
            SerialMergeSort rightSort = new SerialMergeSort(arr, mid + 1, right);
            leftSort.sort();
            rightSort.sort();
            merge(left, mid, right);
        }
    }

    private void merge(int left, int mid, int right) {
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
    }
}
