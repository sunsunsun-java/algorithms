package winningtheprize;

/**
 * 游戏中，有一种宝箱，该宝箱获得传奇武器的概率是 20%。现打开5个这样的宝箱，问获得传奇武器的概率是多少？
 * 1 - 0.8^5 = 0.67232
 */
public class WinningPrize {
    
    private double chance;
    private int playTime;
    private int N;
    
    public WinningPrize(double chance, int playTime, int N) {
        
        if (chance < 0.0 || chance > 1.0)
            throw new IllegalArgumentException("chance must be between 0 and 1");
        
        if (playTime <= 0 || N <= 0)
            throw new IllegalArgumentException("playTime or N must be larger than 0!");
        
        this.chance = chance;
        this.playTime = playTime;
        this.N = N;
    }
    
    public void run() {
        int wins = 0;
        for (int i = 0; i < N; i++)
            if (play())
                wins++;
    
        System.out.println("winning rate: " + (double) wins / N);
    }
    
    private boolean play() {
        for (int i = 0; i < playTime; i++)
            if (Math.random() < chance)
                return true;
        return false;
    }
    
    public static void main(String[] args) {
        double change = 0.2;
        int playTime = 5;
        int N = 10000000;
        
        WinningPrize exp = new WinningPrize(change, playTime, N);
        exp.run();
    }
}
