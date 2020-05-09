/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thowo.jmpcframework.component;

import com.thowo.jmjavaframework.JMFunctions;
import com.thowo.jmpcframework.others.ComponentResizer;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

/**
 *
 * @author jimi
 */
public class JMPCFormModal extends JDialog{
    private JMPCAsyncLoaderPanel asyncLoader;
    private JMPCLoadingSprite loadingSprite;
    private JButton close=new JButton("Close");
    
    public JMPCFormModal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    }
    
    protected void close(){
        this.dispatchEvent(new WindowEvent(JMPCFormModal.this,WindowEvent.WINDOW_CLOSING));
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
        this.close.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JMPCFormModal.this.close();
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
                JMFunctions.setAsyncListener(JMPCFormModal.this.asyncLoader);
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                //System.out.println("PARENT LOST");
            }
        });
    }
}
