public class HeapSort{
    public void sort(int array[]){
        int length = array.length;

        for (int i = length / 2 - 1; i >= 0; i--)
            heapify(array, length, i);

        for (int i = length - 1; i >= 0; i--){
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            heapify(array, i, 0);
        }	
    }

    void heapify(int array[], int size, int root){
        int largest = root;
        int left = 2 * root + 1; // left = 2*i + 1
        int right = 2 * root + 2; // right = 2*i + 2

        // If left child is larger than root
        if (left < size && array[left] > array[largest])
            largest = left;

        // If right child is larger than largest so far
        if (right < size && array[right] > array[largest])
            largest = right;

        // If largest is not root
        if (largest != root) {
            int temp = array[root];
            array[root] = array[largest];
            array[largest] = temp;

            // Recursively heapify the affected sub-tree
            heapify(array, size, largest);
        }
    }

}