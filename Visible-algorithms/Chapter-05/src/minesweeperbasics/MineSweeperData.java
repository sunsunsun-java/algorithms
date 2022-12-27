package minesweeperbasics;

public class MineSweeperData {
    
    public static final String blockImageURL = "Visible-algorithms/Chapter-05/resources/block.png";
    public static final String flagImageURL = "Visible-algorithms/Chapter-05/resources/flag.png";
    public static final String mineImageURL = "Visible-algorithms/Chapter-05/resources/mine.png";
    
    public static String numberImageURL(int num) {
        if (num < 0 || num > 8)
            throw new IllegalArgumentException("No such a number image!");
        return "Visible-algorithms/Chapter-05/resources/" + num + ".png";
    }
    
    private final int N, M;
    private final boolean[][] mines;
    
    public MineSweeperData(int N, int M, int mineNumber) {
        if (N <= 0 || M <= 0)
            throw new IllegalArgumentException("Mine sweeper size is invalid!");
        
        if (mineNumber < 0 || mineNumber > N * M)
            throw new IllegalArgumentException("Mine number is larger than the size of mine sweeper board!");
        
        this.N = N;
        this.M = M;
    
        mines = new boolean[N][M];
        generateMines(mineNumber);
    }
    
    public int N() {
        return N;
    }
    
    public int M() {
        return M;
    }
    
    public boolean isMine(int x,int y) {
        if (!inArea(x, y))
            throw new IllegalArgumentException("Out of index in isMine function!");
        
        return mines[x][y];
    }
    
    private boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
    
    private void generateMines(int mineNumber) {
        for (int i = 0; i < mineNumber; i++) {
            int x = i / M;
            int y = i % M;
            mines[x][y] = true;
        }
        
        for (int i = 0; i < mineNumber; i++) {
            int x1 = i / M;
            int y1 = i % M;
            
            int x2 = (int) (Math.random() * N);
            int y2 = (int) (Math.random() * M);
            
            swap(x1, y1, x2, y2);
        }
    }
    
    private void swap(int x1, int y1, int x2, int y2) {
        boolean temp = mines[x1][y1];
        mines[x1][y1] = mines[x2][y2];
        mines[x2][y2] = temp;
    }
}
