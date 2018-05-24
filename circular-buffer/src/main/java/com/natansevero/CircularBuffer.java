/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author natan
 */
public class CircularBuffer implements Buffer {
    private Lock reentrantLock = new ReentrantLock();
    
    private Condition canWrite = reentrantLock.newCondition();
    private Condition canRead = reentrantLock.newCondition();
    
    private int[] buffer = {-1,-1,-1};
    
    private int occupiedBuffers = 0;
    private int writeIndex = 0;
    private int readIndex = 0;

    @Override
    public void set(int value) {
        reentrantLock.lock();
        
        try {
            while(occupiedBuffers == buffer.length) {
                System.out.println("Buffer is full. Producer await");
                canWrite.await();
            }
            
            buffer[writeIndex] = value;
            writeIndex = (writeIndex + 1) % buffer.length;
            ++occupiedBuffers;
            
            System.out.println("Producer do it: " + value);
            
            canRead.signal();
            
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }

    }

    @Override
    public int get() {
        int readValue = 0;
        reentrantLock.lock();
        
        try {
            while(occupiedBuffers == 0) {
                System.out.println("Buffer is empty. Consumer await");
                
                canRead.await();
            }
            
            readValue = buffer[readIndex];
            readIndex = (readIndex + 1) % buffer.length;    
            --occupiedBuffers;
            
            System.out.println("Cosumer do it: " + readValue);
            
            canWrite.signal();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
        
        return readValue;
    }
    
    
}
