class Main {
    static void insertElement(int arr[], int n, int x, int pos) {
        for (int i = n - 1; i >= pos; i--) {
            arr[i + 1] = arr[i];
        }
        arr[pos] = x;
    }

    public static void main(String[] args) {
        int arr[] = new int[15]; 
        arr[0] = 2; 
        arr[1] = 4; 
        arr[2] = 1; 
        arr[3] = 8; 
        arr[4] = 5; 
        int n = 5;
        int x = 10, pos = 3;

        System.out.print("Before Insertion: "); 
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }

        insertElement(arr, n, x, pos); 
        n += 1;

        System.out.print("\n\nAfter Insertion: "); 
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}