/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thowo.jmpcframework.component;

import com.thowo.jmjavaframework.JMFunctions;
import com.thowo.jmjavaframework.JMVec2;
import com.thowo.jmpcframework.JMPCFunctions;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jimi
 */
public class JMPCLoadingSprite extends JPanel{
    private JLabel img=new JLabel();
    private JLabel text=new JLabel("text");
    
    public JMPCLoadingSprite(){
        JMVec2 frameSize=new JMVec2(50,50);
        Image img=new ImageIcon(JMPCFunctions.getResourcePath("img/loading.gif", this.getClass())).getImage();
        JMVec2 imgSize=new JMVec2(img.getWidth(this),img.getHeight(this));
        List<JMVec2> scaled=JMFunctions.scaledSize(imgSize, frameSize, JMFunctions.SCALE_FIT);
        ImageIcon ico=new ImageIcon(img.getScaledInstance(scaled.get(0).getIntX(), scaled.get(0).getIntY(), Image.SCALE_DEFAULT));
        this.img.setIcon(ico);
        this.setLayout();
    }
    public JMPCLoadingSprite(Image loadingAnimationGif){
        JMVec2 frameSize=new JMVec2(50,50);
        JMVec2 imgSize=new JMVec2(loadingAnimationGif.getWidth(this),loadingAnimationGif.getHeight(this));
        List<JMVec2> scaled=JMFunctions.scaledSize(imgSize, frameSize, JMFunctions.SCALE_FIT);
        ImageIcon ico=new ImageIcon(loadingAnimationGif.getScaledInstance(scaled.get(0).getIntX(), scaled.get(0).getIntY(), Image.SCALE_DEFAULT));
        this.img.setIcon(ico);
        this.setLayout();
    }
    
    private void setLayout(){
        GridLayout l=new GridLayout();
        l.setColumns(1);
        l.setRows(2);
        this.setLayout(l);
        this.img.setHorizontalAlignment(JLabel.CENTER);
        this.img.setVerticalAlignment(JLabel.BOTTOM);
        this.text.setHorizontalAlignment(JLabel.CENTER);
        this.text.setVerticalAlignment(JLabel.TOP);
        this.add(this.img);
        this.add(this.text);
        this.setOpaque(false);
        this.addMouseListener();
    }
    
    private void addMouseListener(){
        this.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    
    public void setText(String txt){
        this.text.setText(txt);
    }
    
}
