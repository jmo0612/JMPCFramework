/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thowo.jmpcframework.component;

import com.thowo.jmjavaframework.JMFunctions;
import com.thowo.jmjavaframework.JMVec2;
import com.thowo.jmpcframework.JMPCFunctions;
import com.thowo.jmpcframework.others.ComponentResizer;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import static java.awt.Frame.NORMAL;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.OverlayLayout;
import javax.swing.SwingConstants;

/**
 *
 * @author jimi
 */
public class JMPCForm extends JFrame {
    private JMPCAsyncLoaderPanel asyncLoader;
    private JMPCLoadingSprite loadingSprite;
    private JMPCButtonSmall min;
    private JMPCToggleButton full;
    private JMPCButtonSmall close;
    
    
    protected void toggleFullscreen(boolean fullscreen){
        this.full.setSelected(fullscreen);
        boolean selected=this.full.isSelected();
        if(!selected)JMPCForm.this.setExtendedState(NORMAL);
        else JMPCForm.this.setExtendedState(MAXIMIZED_BOTH);
    }
    protected void toggleFullscreen(){
        this.toggleFullscreen(this.full.isSelected());
    }
    protected void minimize(){
        this.setExtendedState(ICONIFIED);
    }
    protected void close(){
        this.dispatchEvent(new WindowEvent(JMPCForm.this,WindowEvent.WINDOW_CLOSING));
    }
    
    protected void setContent(JPanel mainPanel, JMPCLoadingSprite loadingSprite){
        Container root=mainPanel.getParent();
        JPanel bg=new JPanel();
        OpacityPanel content=new OpacityPanel();
        content.setLayout(new BorderLayout());
        content.add(mainPanel,BorderLayout.CENTER);
        JLabel dummy=new JLabel("");
        dummy.setOpaque(false);
        content.add(dummy,BorderLayout.EAST);
        bg.setLayout(new OverlayLayout(bg));
        this.loadingSprite=loadingSprite;
        
        JPanel p=new JPanel();
        p.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        //p.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        p.setOpaque(false);
        this.close=JMPCButtonSmall.create("","img/buttons/small/close.png", JMVec2.create(30, 30));
        this.min=JMPCButtonSmall.create("","img/buttons/small/min.png", JMVec2.create(30, 30));
        this.full=JMPCToggleButton.create("","img/buttons/small/max.png", JMVec2.create(30, 30));
        p.add(this.min);
        p.add(this.full);
        p.add(this.close);
        bg.add(p);
        bg.add(this.loadingSprite);
        bg.add(content);
        content.setOpacity(1.0f);
        
        this.loadingSprite.setVisible(false);
        root.setLayout(new BorderLayout());
        root.add(bg,BorderLayout.CENTER);
        
        this.asyncLoader=new JMPCAsyncLoaderPanel(this.loadingSprite,content);
        
        this.addListener();
        ComponentResizer cr=new ComponentResizer();
        cr.setSnapSize(new Dimension(10,10));
        cr.registerComponent(this);
    }
    
    private void addListener(){
        this.full.setAction(new Runnable(){
            @Override
            public void run() {
                JMPCForm.this.toggleFullscreen();
            }
        });
        this.min.setAction(new Runnable(){
            @Override
            public void run() {
                JMPCForm.this.minimize();
            }
        });
        this.close.setAction(new Runnable(){
            @Override
            public void run() {
                JMPCForm.this.close();
            }
        });
        this.addWindowListener(new WindowListener(){
            @Override
            public void windowOpened(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowClosing(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowClosed(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowIconified(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                //JMPCForm.this.toggleFullscreen();
            }

            @Override
            public void windowActivated(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                JMFunctions.setAsyncListener(JMPCForm.this.asyncLoader);
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                //System.out.println("PARENT LOST");
            }
        });
    }
}
