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

    @Override
    public int confirmBoxYN(String title, String message, String yes, String no, boolean defaultNo) {
        String[] options={yes,no};
        int def=0;
        if(defaultNo)def=1;
        int result = JOptionPane.showOptionDialog(
               null,
               message, 
               title,            
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE,
               null,     //no custom icon
               options,  //button titles
               options[def] //default button
            );
        return result;
    }
    
}
