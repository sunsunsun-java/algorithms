package nonrecursivedfsmazegeneralization;

import java.awt.*;
import java.util.Stack;

public class AlgoVisualizer {
    
    private static final int DELAY = 5;
    private static final int blockSide = 8;
    
    private final MazeData data;
    private AlgoFrame frame;
    private static final int[][] D = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    
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
        setData(-1, -1);
    
        Stack<Position> stack = new Stack<>();
        Position first = new Position(data.entranceX(), data.entranceY() + 1);
        stack.push(first);
        data.visited[first.X()][first.Y()] = true;
        
        while (!stack.empty()) {
            Position curPos = stack.pop();
            for (int i = 0; i < 4; i++) {
                int newX = curPos.X() + D[i][0] * 2;
                int newY = curPos.Y() + D[i][1] * 2;
                if (data.inArea(newX, newY) && !data.visited[newX][newY]) {
                    stack.push(new Position(newX, newY));
                    data.visited[newX][newY] = true;
                    setData(curPos.X() + D[i][0], curPos.Y() + D[i][1]);
                }
            }
        }
    
        setData(-1, -1);
    }
    
    private void setData(int x, int y) {
        if (data.inArea(x, y))
            data.maze[x][y] = MazeData.ROAD;
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }
    
    public static void main(String[] args) {
        
        int N = 101;
        int M = 101;
        
        AlgoVisualizer vis = new AlgoVisualizer(N, M);
        
    }
}