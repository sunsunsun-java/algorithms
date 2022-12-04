package moneyproblem;

import java.awt.*;
import java.util.Arrays;

public class AlgoVisualizer {
    
    private static final int DELAY = 40;
    private int[] money;
    private AlgoFrame frame;
    
    public AlgoVisualizer(int sceneWidth, int sceneHeight){
        
        // 初始化数据
        money = new int[100];
        Arrays.fill(money, 100);
        
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Money Problem", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }
    
    // 动画逻辑
    private void run() {
        while (true) {
            Arrays.sort(money);
            frame.render(money);
            AlgoVisHelper.pause(DELAY);
            
            for (int k = 0; k < 50; k ++) {
                for (int i = 0; i < money.length; i++) {
                    int j = (int) (Math.random() * money.length);
                    money[i] -= 1;
                    money[j] += 1;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        
        int sceneWidth = 800;
        int sceneHeight = 800;
        
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight);
    }
}
