package randommazegeneralization;

import java.util.ArrayList;
import java.util.List;

public class RandomQueue<E> {
    
    private final List<E> queue;
    
    public RandomQueue() {
        queue = new ArrayList<>();
    }
    
    public void add(E e) {
        queue.add(e);
    }
    
    public E remove() {
        if (queue.size() == 0)
            throw new IllegalArgumentException("There's no element to remove in Random Queue");
        
        int randIndex = (int) (Math.random() * queue.size());
        E randElement = queue.get(randIndex);
        queue.set(randIndex, queue.get(queue.size() - 1));
        queue.remove(queue.size() - 1);
        
        return randElement;
    }
    
    public int size() {
        return queue.size();
    }
    
    public boolean empty() {
        return size() == 0;
    }
}
