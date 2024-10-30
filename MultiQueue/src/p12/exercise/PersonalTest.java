package p12.exercise;

public class PersonalTest {
    public static void main(String[] args) {
        final MultiQueue<Integer, String> supermarket = new MultiQueueImpl<>();

        supermarket.openNewQueue("Q1");
        supermarket.openNewQueue("Q2");
        supermarket.openNewQueue("Q3");
        supermarket.openNewQueue("Q4");
        supermarket.openNewQueue("Q5");
        
        supermarket.enqueue(1000, "Q3");
        supermarket.enqueue(2000, "Q3");
        supermarket.enqueue(3000, "Q1");
        supermarket.enqueue(4000, "Q1");
        supermarket.enqueue(5000, "Q2");
        supermarket.enqueue(6000, "Q2");
        supermarket.enqueue(7000, "Q4");
        supermarket.enqueue(8000, "Q4");
        supermarket.enqueue(9000, "Q4");
        supermarket.enqueue(10000, "Q5");

        System.out.println(supermarket.availableQueues());
        System.out.println(supermarket.allEnqueuedElements());
        supermarket.closeQueueAndReallocate("Q4");

        supermarket.openNewQueue("Q6");
        supermarket.printMultiQueue();

    }
    
        
    
}
