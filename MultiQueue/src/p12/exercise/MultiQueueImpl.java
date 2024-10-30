package p12.exercise;

import java.util.*;

public class MultiQueueImpl<T, Q> implements MultiQueue<T, Q>{

    private Map <Q, LinkedList<T>> queues = new HashMap<>();

    @Override
    public Set<Q> availableQueues() {
        return queues.keySet();
    }

    @Override
    public void openNewQueue(Q queue) {
             if( queues.containsKey(queue)) {
               throw new IllegalArgumentException("Cannot open a new queue with the same name of one that already exists");
             }
             queues.put(queue, new LinkedList<T>());
    }

    @Override
    public boolean isQueueEmpty(Q queue) {
            if(! queues.containsKey(queue)) {
                throw new IllegalArgumentException("Cannot check if the queue is empty because " + queue + " does not exist");
            }
            
            if( queues.get(queue).size() == 0) {
                    return true;
                }
                  return false;
        }
    

    @Override
    public void enqueue(T elem, Q queue) {
        if(! queues.containsKey(queue)) {
            throw new IllegalArgumentException("Cannot check if the queue is empty because " + queue + " does not exist");
        }
        
        queues.get(queue).addLast(elem);
        
    }

    @Override
    public T dequeue(Q queue) {
        if(! queues.containsKey(queue)) {
            throw new IllegalArgumentException("Cannot dequeue any element because " + queue + " does not exist");
        }
        
        if(queues.get(queue).isEmpty()) {
            return null;
        }
            return queues.get(queue).removeFirst();
       
        } 

    @Override
    public Map<Q, T> dequeueOneFromAllQueues() {
        Map <Q, T> dequedElements = new HashMap<>();
        Iterator<Q> queuesIter = queues.keySet().iterator();
        Set<T> seenElements = new HashSet<>();

        while(queuesIter.hasNext()) {
            Q key = queuesIter.next();
            if(! queues.get(key).isEmpty()) {
                T removedElement = queues.get(key).removeFirst();
                if(seenElements.add(removedElement)) {
                    dequedElements.put(key, removedElement);
                }
            } else {
                dequedElements.put(key, null);
            }
        }
        return dequedElements;
    }

    @Override
    public Set<T> allEnqueuedElements() {
        Set<T> temporarySet = new HashSet<>();
        Iterator<Q> queuesIter = queues.keySet().iterator();
        while(queuesIter.hasNext()) {
            temporarySet.addAll(queues.get(queuesIter.next()));
        }
        
        return temporarySet;
    }

    @Override
    public List<T> dequeueAllFromQueue(Q queue) {

        if(! queues.containsKey(queue)) {
            throw new IllegalArgumentException("Cannot dequeue all the elements because " + queue + " does not exist");
        }
        List<T> removedQueue = new LinkedList<>();

        while(!queues.get(queue).isEmpty()) {
                removedQueue.addLast(queues.get(queue).removeFirst());
        }
        return removedQueue;
    }

    @Override
    public void closeQueueAndReallocate(Q queue) {
        if(! queues.containsKey(queue)) {
            throw new IllegalArgumentException("Cannot close any queue because " + queue + " does not exist");
        }
             Set<Q> temporarySet = queues.keySet(); 
             Iterator<Q> iter = temporarySet.iterator();
             boolean done = false;
             while(iter.hasNext() && done == false){
                Q key = iter.next();
                if(key != queue) {
                    Iterator <T> queueIter = queues.get(queue).listIterator();
                    while(queueIter.hasNext()) {
                        queues.get(key).addLast(queueIter.next());
                    }

                    queues.get(queue).clear();
                    queues.remove(queue);
                    done = true;
                }
            }

            if(! iter.hasNext() && done == false){
                throw new IllegalStateException("Cannot reallocate any element because" + queue + " is the only queue existing");
            }
       
       
    }


    public void  printMultiQueue() {
        Iterator<Q> queuesIter = queues.keySet().iterator();
        while(queuesIter.hasNext()) {
            Q key = queuesIter.next();
            System.out.println(key + ":     " + queues.get(key).toString() + "\n");
        }
    }

}


