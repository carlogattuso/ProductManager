package edu.upc.dsa;

import edu.upc.dsa.models.QueueEmptyException;
import edu.upc.dsa.models.QueueFullException;
import org.apache.log4j.Logger;

public class QueueImpl<E> implements Queue<E> {
    //Attributes
    E[] data;       //Queue
    int p;          //Number of elements

    final static Logger logger = Logger.getLogger(QueueImpl.class);

    //Constructor
    public QueueImpl(int len) {
        this.data = (E[]) new Object[len];
    }

    //Push Method
    public void push(E e) throws QueueFullException {
        if (this.isFull()){
            logger.warn("Full queue");
            throw new QueueFullException();
        }
        this.data[this.p++] = e;
    }

    //Pop Method
    public E pop() throws QueueEmptyException {
        if (this.isEmpty()){
            logger.warn("Empty queue");
            throw new QueueEmptyException();
        }
        E first = this.data[0];
        shift();
        this.p--;
        return first;
    }

    //Size Method
    public int size() {
        return this.p;
    }

    //isFull
    private boolean isFull() {
        return (this.size() == this.data.length);
    }

    //isEmpty
    private boolean isEmpty() {
        return (this.p == 0);
    }

    //shift
    private void shift() {
        for (int i = 0; i < this.p-1; i++) {
            this.data[i] = this.data[i + 1];
        }
    }

    public void clear(){
        for (int i = 0; i < this.p; i++) {
            this.data[i] = null;
        }
        this.p=0;
    }
}
