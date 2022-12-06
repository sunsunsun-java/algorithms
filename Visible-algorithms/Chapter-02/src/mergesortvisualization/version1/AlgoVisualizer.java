package mergesortvisualization.version1;

import java.awt.*;
import java.util.Arrays;

public class AlgoVisualizer {
    
    private static final int DELAY = 50;
    
    private final MergeSortData data;
    private AlgoFrame frame;
    
    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {
        
        // 初始化数据
        data = new MergeSortData(N, sceneHeight);
        
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Merge Sort Visualization", sceneWidth, sceneHeight);
            new Thread(this::run).start();
        });
    }
    
    public void run() {
        setData(-1, -1, -1);
        mergeSort(0, data.N() - 1);
        setData(0, data.N() - 1, data.N() - 1);
    }
    
    private void mergeSort(int left, int right) {
        if (left >= right)
            return;
        
        setData(left, right, -1);
        
        int mid = (left + right) / 2;
        mergeSort(left, mid);
        mergeSort(mid + 1, right);
        merge(left, mid, right);
    }
    
    private void merge(int left, int mid, int right) {
        int[] aux = Arrays.copyOfRange(data.numbers, left, right + 1);
        
        int i = left, j = mid + 1;
        for (int k = i; k <= right; k++) {
            if (i > mid) {
                data.numbers[k] = aux[j - left];
                j++;
            } else if (j > right) {
                data.numbers[k] = aux[i - left];
                i++;
            } else if (aux[i - left] < aux[j - left]) {
                data.numbers[k] = aux[i - left];
                i++;
            } else {
                data.numbers[k] = aux[j - left];
                j++;
            }
            
            setData(left, right, k);
        }
    }
    
    private void setData(int left, int right, int mergeIndex) {
        data.left = left;
        data.right = right;
        data.mergeIndex = mergeIndex;
        
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }
    
    public static void main(String[] args) {
        
        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;
        
        AlgoVisualizer vis = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}
