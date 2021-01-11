/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thowo.jmpcframework.component;

import com.thowo.jmjavaframework.JMButtonInterface;
import com.thowo.jmjavaframework.JMPanelInterface;
import java.awt.Component;
import javax.swing.JPanel;

/**
 *
 * @author jimi
 */
public class JMPCPanel extends JPanel implements JMPanelInterface {


    @Override
    public void addComponent(Object component, Object... params) {
        if(params==null){
            super.add((Component)component);
        }else{
            //NOT YET
        }
        
    }

    @Override
    public void clear() {
        this.removeAll();
    }
    
}
