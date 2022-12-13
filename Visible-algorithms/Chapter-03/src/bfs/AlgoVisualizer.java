package bfs;

import java.awt.*;
import java.util.LinkedList;
import java.util.Stack;

public class AlgoVisualizer {
    
    private static final int DELAY = 5;
    private static final int blockSide = 8;
    
    private final MazeData data;
    private AlgoFrame frame;
    private static final int[][] d = {{-1,0},{0,1},{1,0},{0,-1}};
    
    public AlgoVisualizer(String mazeFile){
        
        // 初始化数据
        data = new MazeData(mazeFile);
        int sceneHeight = data.N() * blockSide;
        int sceneWidth = data.M() * blockSide;
        
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Maze Solver Visualization", sceneWidth, sceneHeight);
            new Thread(this::run).start();
        });
    }
    
    // non-recursive dfs(Stack:LIFO) and bfs(Queue:FIFO) 两者非常相似
    public void run(){
        
        setData(-1, -1, false);
    
        LinkedList<Position> queue = new LinkedList<>();
        Position entrance = new Position(data.entranceX(), data.entranceY());
        queue.offerLast(entrance);
        data.visited[entrance.X()][entrance.Y()] = true;
        
        boolean isSolved = false;
        while (queue.size() != 0) {
            Position curPos = queue.pollFirst();
            setData(curPos.X(), curPos.Y(), true);
            
            if (curPos.X() == data.exitX() && curPos.Y() == data.exitY()) {
                isSolved = true;
                findPath(curPos);
                break;
            }
            
            for (int i = 0; i < 4; i++) {
                int newX = curPos.X() + d[i][0];
                int newY = curPos.Y() + d[i][1];
                if (data.inArea(newX, newY)
                        && !data.visited[newX][newY]
                        && data.getMaze(newX, newY) == MazeData.ROAD) {
                    queue.offerLast(new Position(newX, newY, curPos));
                    data.visited[newX][newY] = true;
                }
            }
        }
        
        if (!isSolved)
            System.out.println("The maze has no Solution!");
        
        setData(-1, -1, false);
    }
    
    private void findPath(Position des) {
        Position cur = des;
        while (cur != null) {
            data.result[cur.X()][cur.Y()] = true;
            cur = cur.getPrev();
        }
    }
    
    private void setData(int x, int y, boolean isPath){
        if(data.inArea(x, y))
            data.path[x][y] = isPath;
        
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }
    
    public static void main(String[] args) {
        
        String mazeFile = "Visible-algorithms/Chapter-03/maze_101_101.txt";
        
        AlgoVisualizer vis = new AlgoVisualizer(mazeFile);
    }
}