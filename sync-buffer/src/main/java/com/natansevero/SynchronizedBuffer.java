/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author natan
 */
public class SynchronizedBuffer implements Buffer {

    private Lock reentrantLock = new ReentrantLock();
    private Condition canWrite = reentrantLock.newCondition();
    private Condition canRead = reentrantLock.newCondition();
    
    private int buffer = -1;
    
    private boolean occupied = false;
    
    @Override
    public void set(int value) {
        reentrantLock.lock();
        
        try {
            while(occupied) {
                System.out.println("Producer tries to write");
                System.out.println("Status: Buffer is full. Producer await. " + buffer + " " + occupied );
                canWrite.await();
            }
            
            buffer = value;
            
            occupied = true;
            
            System.out.println("Producer do it: buffer: " + buffer);
            
            canRead.signal();
            
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            this.reentrantLock.unlock();
        }
        
    }

    @Override
    public int get() {
        int readValue = 0;
        reentrantLock.lock();
        
        try {
            
            while(!occupied) {
                System.out.println("Consumer tries to read");
                System.out.println("Status: Buffer is empty. Consumer await. " + buffer + " " + occupied);
                canRead.await();
            }
            
            occupied = false;
            readValue = buffer;
            
            System.out.println("Consumer do it: readValue: " + readValue);
            
            canWrite.signal();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
        
        return readValue;
    }
    
}
