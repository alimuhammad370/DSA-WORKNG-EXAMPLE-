lass Main {
    public static void main(String[] args) {
        int arr[] = {12, 34, 10, 6, 40};
        int key = 40;
        int position = findElement(arr, arr.length, key);
        
        if (position == -1) {
            System.out.println("ELNF");
        } else {
            System.out.println("ELFAP: " + (position + 1));  // +1 for 1-based index output
        }
    }

    static int findElement(int[] arr, int n, int key) 
    {
        for (int i = 0; i < n; i++)
        { 
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }
}