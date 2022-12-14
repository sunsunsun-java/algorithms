package mazegeneralizationbasics;

import java.awt.*;

public class AlgoVisualizer {
    
    private static final int DELAY = 5;
    private static final int blockSide = 8;
    
    private final MazeData data;
    private AlgoFrame frame;
    
    public AlgoVisualizer(int N, int M) {
        
        // 初始化数据
        data = new MazeData(N, M);
        int sceneHeight = data.N() * blockSide;
        int sceneWidth = data.M() * blockSide;
        
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Random Maze Generation Visualization", sceneWidth, sceneHeight);
            new Thread(this::run).start();
        });
    }
    
    private void run() {
        setData();
    }
    
    private void setData() {
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }
    
    public static void main(String[] args) {
        
        int N = 101;
        int M = 101;
        
        AlgoVisualizer vis = new AlgoVisualizer(N, M);
        
    }
}