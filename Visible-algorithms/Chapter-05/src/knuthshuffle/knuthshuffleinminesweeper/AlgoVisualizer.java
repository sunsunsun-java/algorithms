package knuthshuffle.knuthshuffleinminesweeper;

import java.awt.*;

public class AlgoVisualizer {
    
    private static final int DELAY = 5;
    private static final int blockSide = 32;
    
    private final MineSweeperData data;
    private AlgoFrame frame;
    
    public AlgoVisualizer(int N, int M, int mineNumber) {
        
        data = new MineSweeperData(N, M, mineNumber);
        int sceneWidth = M * blockSide;
        int sceneHeight = N * blockSide;
        
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Mine Sweeper", sceneWidth, sceneHeight);
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
        
        int N = 20;
        int M = 20;
        int mineNumber = 20;
        
        AlgoVisualizer vis = new AlgoVisualizer(N, M, mineNumber);
    }
}
