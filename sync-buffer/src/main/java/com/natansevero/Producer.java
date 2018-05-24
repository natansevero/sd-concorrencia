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
public class Producer implements Runnable {

    private static Random generator = new Random();
    private Buffer sharedLocation;
    
    public Producer(Buffer shared) {
        this.sharedLocation = shared;
    }
    
    @Override
    public void run() {
        for(int count = 1; count <= 10; count++) {
            try {
                Thread.sleep(generator.nextInt(3000));
                sharedLocation.set(count);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.printf("\n%s\n%s\n", "Producer done", "end producer");
    }
    
}
