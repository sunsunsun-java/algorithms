package moreaboutrandommazegeneralization;

/**
 * 迷宫定义：
 * 1.只有一个入口，一个出口
 * 2.只有一个解
 * 3.路径是连续的
 * 4.绘制在一个方形画布上
 * 5.墙和路径都占有一个单元格
 */
public class MazeData {
    
    public static final char ROAD = ' ';
    public static final char WALL = '#';
    
    private final int N;
    private final int M;
    public char[][] maze;
    public boolean[][] visited;
    public boolean[][] inMist;
    public boolean[][] path;
    
    private final int entranceX;
    private final int entranceY;
    private final int exitX;
    private final int exitY;
    
    public MazeData(int N, int M) {
        if (N % 2 == 0 || M % 2 == 0)
            throw new IllegalArgumentException("Our Maze Generalization Algorithm requires the width and height of the maze are odd numbers");
        
        this.N = N;
        this.M = M;
        
        maze = new char[N][M];
        visited = new boolean[N][M];
        inMist = new boolean[N][M];
        path = new boolean[N][M];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++) {
                if (i % 2 == 1 && j % 2 == 1)
                    maze[i][j] = ROAD;
                else
                    maze[i][j] = WALL;
                inMist[i][j] = true;
            }
        
        entranceX = 1;
        entranceY = 0;
        exitX = N - 2;
        exitY = M - 1;
        
        maze[entranceX][entranceY] = ROAD;
        maze[exitX][exitY] = ROAD;
    }
    
    public int N() {
        return N;
    }
    
    public int M() {
        return M;
    }
    
    public int entranceX() {
        return entranceX;
    }
    
    public int entranceY() {
        return entranceY;
    }
    
    public int exitX() {
        return exitX;
    }
    
    public int exitY() {
        return exitY;
    }
    
    public boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
    
    public void openMist(int x, int y) {
        if (!inArea(x, y))
            throw new IllegalArgumentException("x or y is out of index in openMist function!");
        
        for (int i = x - 1; i <= x + 1; i++)
            for (int j = y - 1; j <= y + 1; j++)
                if (inArea(i, j))
                    inMist[i][j] = false;
    }
}
