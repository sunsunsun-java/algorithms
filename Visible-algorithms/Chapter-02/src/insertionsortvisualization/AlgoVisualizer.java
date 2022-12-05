package insertionsortvisualization;

import java.awt.*;

public class AlgoVisualizer {
    
    private static final int DELAY = 200;
    
    private final InsertionSortData data;
    private AlgoFrame frame;
    
    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {
        
        // 初始化数据
        data = new InsertionSortData(N, sceneHeight);
        
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Insertion Sort Visualization", sceneWidth, sceneHeight);
            new Thread(this::run).start();
        });
    }
    
    public void run() {
        
        setData(0, -1);
        
        // 频繁交换
        for (int i = 0; i < data.N(); i++) {
            // [i, n) 未排序; [0, i) 已排序
            setData(i, i);
            for (int j = i; j > 0 && data.get(j) < data.get(j - 1); j--) {
                data.swap(j, j - 1);
                setData(i + 1, j - 1);
            }
        }
        setData(data.N(), -1);
    
        /**
         * 优化排序：赋值操作
         *
         * for (int i = 0; i < data.N(); i++) {
         *     int temp = data.get(i);
         *     int j;
         *     for (j = i; j > 0 && temp < data.get(j - 1) ; j--)
         *         data[j] = data[j - 1];
         *     data.set(j, temp);
         * }
         */
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
        
        AlgoVisualizer vis = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}
