/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thowo.jmpcframework.component.form;

import com.thowo.jmjavaframework.JMDate;
import com.thowo.jmjavaframework.JMFilterListener;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author jimi
 */
public class JMPCFilterBox extends JPanel implements JMFilterListener {
    private JMPCInputStringTFWeblaf search;
    private JLabel searchIndicator;
    private Timer timer;
    private boolean filtered=false;
    
    
    public static JMPCFilterBox create(JPanel parent, String prompt, String onFilteredMessage){
        return new JMPCFilterBox(parent,prompt,onFilteredMessage);
    }
    
    public JMPCFilterBox(JPanel parent, String prompt, String onFilteredMessage){
        parent.setLayout(new BorderLayout());
        this.search=JMPCInputStringTFWeblaf.create("", prompt, 15, 20, true);
        this.searchIndicator=new JLabel(onFilteredMessage);
        parent.add(this.search,BorderLayout.NORTH);
        parent.add(this.search,BorderLayout.SOUTH);
        
        this.timer=new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JMPCFilterBox.this.searchIndicator.setVisible(!JMPCFilterBox.this.searchIndicator.isVisible()&&JMPCFilterBox.this.filtered);
            }
        });
        this.timer.start();
    }

    @Override
    public void setFiltered(boolean filtered) {
        this.filtered=filtered;
    }
    
}
