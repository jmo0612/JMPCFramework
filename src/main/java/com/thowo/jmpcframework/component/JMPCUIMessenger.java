/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thowo.jmpcframework.component;

import com.thowo.jmjavaframework.JMUIListener;
import javax.swing.JOptionPane;

/**
 *
 * @author jimi
 */
public class JMPCUIMessenger implements JMUIListener {

    @Override
    public void trace(String message) {
        System.out.println(message);
    }

    @Override
    public void messageBox(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
    
}
