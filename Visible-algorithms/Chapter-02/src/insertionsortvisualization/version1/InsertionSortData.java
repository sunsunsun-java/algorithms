package insertionsortvisualization.version1;

public class InsertionSortData {
    
    private final int[] numbers;
    public int orderedIndex = -1; // [0...orderedIndex) 是有序的
    public int currentIndex = -1; // 当前正在比较的元素索引
    
    public InsertionSortData(int N, int randomBound) {
        numbers = new int[N];
        
        for (int i = 0; i < N; i++)
            numbers[i] = (int) (Math.random() * randomBound) + 1;
    }
    
    public int N() {
        return numbers.length;
    }
    
    public int get(int index) {
        if( index < 0 || index >= numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data.");
    
        return numbers[index];
    }
    
    public void set(int index, int x) {
        if( index < 0 || index >= numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data.");
        
        numbers[index] = x;
    }
    
    public void swap(int i, int j) {
        if( i < 0 || i >= numbers.length || j < 0 || j >= numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data.");
        
        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }
}
