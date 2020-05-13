/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thowo.jmpcframework.component;

import com.alee.extended.button.WebSwitch;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jimi
 */
public class JMPCWebSwitch extends JPanel{
    private WebSwitch ws;
    private MouseListener clicked;
    
    public static JMPCWebSwitch create(String labelTrue, String labelFalse){
        return new JMPCWebSwitch(labelTrue,labelFalse);
    }
    
    public JMPCWebSwitch(String labelTrue, String labelFalse){
        JLabel lTrue=new JLabel(labelTrue);
        JLabel lFalse=new JLabel(labelFalse);
        this.ws=new WebSwitch();
        this.ws.setSwitchComponents(lTrue, lFalse);
        this.add(this.ws);
    }
    
    public JMPCWebSwitch setColors(Color colorTrue, Color colorFalse){
        JLabel lTrue=(JLabel) this.ws.getSelectedComponent();
        JLabel lFalse=(JLabel) this.ws.getDeselectedComponent();
        lTrue.setBackground(colorTrue);
        lFalse.setBackground(colorFalse);
        return this;
    }
    
    public void setSelected(boolean selected){
        this.ws.setSelected(selected);
    }
    
    public void setLocked(boolean locked){
        this.ws.setEnabled(!locked);
    }
    
    public boolean getLocked(){
        return !this.ws.isEnabled();
    }
    
    public boolean isSelected(){
        return this.ws.isSelected();
    }
    public void setAnimate(boolean animate){
        this.ws.setAnimate(animate);
    }
    public void setAction(ActionListener actionListener){
        this.ws.addActionListener(actionListener);
    }
    public void setOnClicked(Runnable action){
        if(this.clicked!=null)this.ws.removeMouseListener(this.clicked);
        this.clicked=new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                action.run();
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        };
        this.ws.addMouseListener(this.clicked);
    }
}
