
package com.natansevero;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author natan
 */
public class Main {
    public static void main(String[] args) {
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);
        Buffer sharedBuffer = new SynchronizedBuffer();
        
        newFixedThreadPool.execute(new Producer(sharedBuffer));
        newFixedThreadPool.execute(new Consumer(sharedBuffer));
        
        newFixedThreadPool.shutdown();
    }
}
