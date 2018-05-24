/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author natan
 */
public class Consumer implements Runnable {

    private static Random generator = new Random();
    private Buffer sharedLocation;
    
    public Consumer(Buffer shared) {
        this.sharedLocation = shared;
    }
    
    @Override
    public void run() {
        int sum = 0;
        for(int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(generator.nextInt(3000));
                sum += sharedLocation.get();
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.printf("\n%s\n%s\n", "end Consumer", "Sum: " + sum);
    }
    
}
