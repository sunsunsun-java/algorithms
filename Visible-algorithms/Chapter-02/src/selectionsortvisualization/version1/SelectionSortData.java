package selectionsortvisualization.version1;

public class SelectionSortData {
    
    private final int[] numbers;
    
    public SelectionSortData(int N, int randomBound) {
        numbers = new int[N];
        for (int i = 0; i < N; i++)
            numbers[i] = (int) (Math.random() * randomBound) + 1;
    }
    
    public int N() {
        return numbers.length;
    }
    
    public int get(int index) {
        if (index < 0 || index >= numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data.");
        
        return numbers[index];
    }
    
    public void swap(int i, int j) {
        if (i < 0 || i >= numbers.length || j < 0 || j >= numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data.");
        
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
}
