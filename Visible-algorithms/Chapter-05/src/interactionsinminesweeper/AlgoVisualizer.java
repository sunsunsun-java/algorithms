package interactionsinminesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(this::run).start();
        });
    }
    
    private void run() {
        setData(false, -1, -1);
    }
    
    private void setData(boolean isLeftClicked, int x, int y) {
        if (data.inArea(x, y)) {
            if (isLeftClicked)
                data.open[x][y] = true;
            else
                data.flags[x][y] = !data.flags[x][y];
        }
        
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }
    
    public void addAlgoMouseListener() {
        frame.addMouseListener(new AlgoMouseListener());
    }
    
    private class AlgoMouseListener extends MouseAdapter {
        
        @Override
        public void mouseReleased(MouseEvent event) {
            event.translatePoint(
                - (int) (frame.getBounds().width - frame.getCanvasWidth()),
                - (int) (frame.getBounds().height - frame.getCanvasHeight())
            );
            
            Point pos = event.getPoint();
            
            int w = frame.getCanvasWidth() / data.M();
            int h = frame.getCanvasHeight() / data.N();
            
            int x = pos.y / h;
            int y = pos.x / w;
    
            if(SwingUtilities.isLeftMouseButton(event))
                setData(true, x, y);
            else if(SwingUtilities.isRightMouseButton(event))
                setData(false, x, y);
        }
        
    }
    
    public static void main(String[] args) {
        
        int N = 20;
        int M = 20;
        int mineNumber = 50;
        
        AlgoVisualizer vis = new AlgoVisualizer(N, M, mineNumber);
    }
}
