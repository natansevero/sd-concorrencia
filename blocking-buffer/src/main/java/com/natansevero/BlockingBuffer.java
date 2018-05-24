/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author natan
 */
public class BlockingBuffer implements Buffer {

    private ArrayBlockingQueue<Integer> buffer;
    
    public BlockingBuffer() {
        buffer = new ArrayBlockingQueue<Integer>(3);
    }
    
    @Override
    public void set(int value) {
        try {
            buffer.put(value);
            System.out.println("Producer do it. value: " + value + ". Buffer size: " + value);
        } catch (InterruptedException ex) {
            Logger.getLogger(BlockingBuffer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int get() {
        int readValue = 0;
        
        try {
            readValue = buffer.take();
            System.out.println("Consumer do it. value: " + readValue + ". Buffer size: " + readValue);
        } catch (InterruptedException ex) {
            Logger.getLogger(BlockingBuffer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return readValue;
    }
    
}
