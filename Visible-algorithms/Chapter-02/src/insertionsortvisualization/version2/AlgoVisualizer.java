package insertionsortvisualization.version2;

import java.awt.*;

/**
 * 对于近乎有序的数组，插入排序算法优化成 O(n) 了。
 * 在 n 比较小的时候，插入排序比 O(nlogn) 的排序算法有优势。（比如 n <= 16）
 * 插入排序经常用作是高级排序算法在处理到小样本时的一个优化。
 */
public class AlgoVisualizer {
    
    private static final int DELAY = 200;
    
    private final InsertionSortData data;
    private AlgoFrame frame;
    
    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N, InsertionSortData.Type dataType) {
        
        // 初始化数据
        data = new InsertionSortData(N, sceneHeight, dataType);
        
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Insertion Sort Visualization", sceneWidth, sceneHeight);
            new Thread(this::run).start();
        });
    }
    
    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {
        this(sceneWidth, sceneHeight, N, InsertionSortData.Type.Default);
    }
    
    public void run() {
        
        setData(0, -1);
        
        for (int i = 0; i < data.N(); i++) {
            setData(i, i);
            for (int j = i; j > 0 && data.get(j) < data.get(j - 1); j--) {
                data.swap(j, j - 1);
                setData(i + 1, j - 1);
            }
        }
        
        setData(data.N(), -1);
    }
    
    private void setData(int orderedIndex, int currentIndex) {
        data.orderedIndex = orderedIndex;
        data.currentIndex = currentIndex;
        
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }
    
    public static void main(String[] args) {
        
        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;
        
        AlgoVisualizer vis = new AlgoVisualizer(sceneWidth, sceneHeight, N, InsertionSortData.Type.NearlyOrdered);
    }
}
