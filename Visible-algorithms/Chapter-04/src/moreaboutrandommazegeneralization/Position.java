package moreaboutrandommazegeneralization;

public class Position {
    private final int x;
    private final int y;
    private final Position prev;
    
    public Position(int x, int y, Position prev) {
        this.x = x;
        this.y = y;
        this.prev = prev;
    }
    
    public Position(int x, int y) {
        this(x, y, null);
    }
    
    public int X() {
        return x;
    }
    
    public int Y() {
        return y;
    }
    
    public Position getPrev() {
        return prev;
    }
}
