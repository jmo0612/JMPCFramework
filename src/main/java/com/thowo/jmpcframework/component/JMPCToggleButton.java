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
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.SwingConstants;

/**
 *
 * @author jimi
 */
public class JMPCToggleButton extends JPanel {
    private JPanel content=new JPanel();
    private JLabel text=new JLabel();
    private JPanel normal;
    private JPanel select;
    private JPanel hover;
    private JPanel disabled;
    private JPanel clicked;
    private JMVec2 size;
    private List<MouseListener> listeners=new ArrayList();
    private MouseListener uiListener;
    private boolean locked;
    private Color fontColor=Color.WHITE;
    private int fontSize=0;
    private String fontFamily="Dialog";
    private boolean selected=false;
    
    
    public static JMPCToggleButton create(String text, String resId){
        return new JMPCToggleButton(text, resId);
    }
    public static JMPCToggleButton create(String text, String resId, JMVec2 size){
        return new JMPCToggleButton(text,resId,size);
    }
    
    public JMPCToggleButton setFontColor(Color color){
        this.fontColor=color;
        this.refreshContent();
        return this;
    }
    public JMPCToggleButton increaseFontSize(int inc){
        this.fontSize+=inc;
        this.refreshContent();
        return this;
    }
    public JMPCToggleButton decreaseFontSize(int dec){
        this.fontSize-=dec;
        this.refreshContent();
        return this;
    }
    public JMPCToggleButton setFont(String font){
        this.fontFamily=font;
        this.refreshContent();
        return this;
    }
    private JMPCToggleButton setText(String text){
        this.text.setText(text);
        return this;
    }
    
    public JMPCToggleButton(String text, String resId){
        this.setProp(text,resId, null);
    }
    public JMPCToggleButton(String text, String resId, JMVec2 size){
        this.setProp(text,resId, size);
    }
    private void setProp(String text, String resId, JMVec2 btnSize){
        this.size=btnSize;
        if(!text.equals(""))this.text.setText(text);
        this.view(resId);
        this.setFocusable(true);
    }
    
    private void refreshContent(){
        this.content.removeAll();
        //this.content.setLayout(new BorderLayout());
        this.content.setLayout(new FlowLayout());
        this.content.setOpaque(false);
        if(this.text!=null){
            this.text.setVerticalAlignment(SwingConstants.CENTER);
            this.text.setHorizontalAlignment(SwingConstants.CENTER);
            if(this.fontSize<=0){
                if(this.size!=null){
                    float fontScale=15f/20f;
                    this.fontSize=Math.round(fontScale*this.size.getIntY());
                }
            }
            this.text.setForeground(this.fontColor);
            this.text.setFont(new Font(this.fontFamily,Font.BOLD,this.fontSize));
            this.text.setForeground(this.fontColor);
        }
        JPanel p=new JPanel();
        p.setOpaque(false);
        p.add(this.text);
        Box box=Box.createVerticalBox();
        box.add(p);
        this.content.add(box);
        //this.content.add(new JLabel(" "),BorderLayout.SOUTH);
        //this.content.add(new JLabel(" "),BorderLayout.SOUTH);
        //this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    }
    
    private void view(String resId){
        String img=JMFunctions.removeExtension(resId);
        String ext=JMFunctions.getExtension(resId);
        JMFunctions.trace(img+"_hover"+ext);
        if(this.normal==null)this.normal=JMPCFunctions.getImageOpaque(resId, this.getClass(), this.size);
        if(this.select==null)this.select=JMPCFunctions.getImageOpaque(img+"_selected"+ext, this.getClass(), this.size);
        if(this.hover==null)this.hover=JMPCFunctions.getImageOpaque(img+"_hover"+ext, this.getClass(), this.size);
        if(this.disabled==null)this.disabled=JMPCFunctions.getImageOpaque(img+"_disabled"+ext, this.getClass(), this.size);
        if(this.clicked==null)this.clicked=JMPCFunctions.getImageOpaque(img+"_clicked"+ext, this.getClass(), this.size);
        if(this.text==null)this.text=new JLabel();
        
        
        
        this.disabled.setVisible(false);
        this.disabled.setOpaque(false);
        this.setLayout(new OverlayLayout(this));
        this.add(this.disabled);
        this.add(this.content);
        this.add(this.normal);
        this.add(this.select);
        this.add(this.hover);
        this.add(this.clicked);
        this.addListener();
        this.refreshContent();
        this.setOpaque(false);
    }
    
    private void reArrange(){
        this.removeAll();
        if(this.normal==null)this.normal=JMPCFunctions.getImageOpaque("img/buttons/default/bg.png", this.getClass(), this.size);
        if(this.hover==null)this.hover=JMPCFunctions.getImageOpaque("img/buttons/default/bgHover.png", this.getClass(), this.size);
        if(this.disabled==null)this.disabled=JMPCFunctions.getImageOpaque("img/buttons/default/bgDisabled.png", this.getClass(), this.size);
        if(this.clicked==null)this.clicked=JMPCFunctions.getImageOpaque("img/buttons/default/bgClicked.png", this.getClass(), this.size);
        if(this.text==null)this.text=new JLabel();
        
        this.disabled.setVisible(false);
        this.disabled.setOpaque(false);
        this.setLayout(new OverlayLayout(this));
        this.add(this.disabled);
        this.add(this.content);
        this.add(this.normal);
        this.add(this.hover);
        this.add(this.clicked);
        this.refreshContent();
    }
    
    public void setLocked(boolean locked){
        //if(this.locked!=locked){
            this.locked=locked;
            this.removeMouseListener(this.uiListener);
            for(MouseListener l:this.listeners)this.removeMouseListener(l);
            
            if(!locked){
                this.addMouseListener(this.uiListener);
                for(MouseListener l:this.listeners)this.addMouseListener(l);
            }
            this.normal.setVisible(true);
            this.hover.setVisible(!locked);
            this.clicked.setVisible(!locked);
            this.disabled.setVisible(locked);

            this.setEnabled(!locked);
            //this.refreshContent();
        //}
    }
    public boolean isLocked(){
        return this.locked;
    }
    
    public void setAction(Runnable action){
        if(action==null)return;
        if(this.listeners!=null){
            for(MouseListener l:this.listeners){
                this.removeMouseListener(l);
            }
        }else this.listeners=new ArrayList();
        
        MouseListener newL=new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                action.run();
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
        };
        this.listeners.clear();
        this.listeners.add(newL);
        if(!this.locked)this.addMouseListener(this.listeners.get(0));
    }
    
    public void addAction(Runnable action){
        if(action==null)return;
        if(this.listeners==null){
            this.listeners=new ArrayList();
        }
        
        MouseListener newL=new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                action.run();
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
        };
        this.listeners.add(newL);
        if(!this.locked)this.addMouseListener(newL);
    }
    
    private void addListener(){
        this.uiListener=new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JMPCToggleButton.this.grabFocus();
                JMPCToggleButton.this.setSelected(!JMPCToggleButton.this.selected);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                JMPCToggleButton.this.clicked.setVisible(true);
                JMPCToggleButton.this.normal.setVisible(false);
                JMPCToggleButton.this.select.setVisible(false);
                JMPCToggleButton.this.hover.setVisible(false);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                JMPCToggleButton.this.clicked.setVisible(false);
                JMPCToggleButton.this.normal.setVisible(!JMPCToggleButton.this.selected);
                JMPCToggleButton.this.select.setVisible(JMPCToggleButton.this.selected);
                JMPCToggleButton.this.hover.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                //JMFunctions.trace("entered");
                JMPCToggleButton.this.normal.setVisible(false);
                JMPCToggleButton.this.select.setVisible(false);
                JMPCToggleButton.this.hover.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                //JMFunctions.trace("exited");
                JMPCToggleButton.this.normal.setVisible(!JMPCToggleButton.this.selected);
                JMPCToggleButton.this.select.setVisible(JMPCToggleButton.this.selected);
                JMPCToggleButton.this.hover.setVisible(false);
            }
        };
        this.addMouseListener(this.uiListener);
    }
    
    public void setSelected(boolean selected){
        this.selected=selected;
        this.normal.setVisible(!JMPCToggleButton.this.selected);
        this.select.setVisible(JMPCToggleButton.this.selected);
        
    }
    public boolean isSelected(){
        return this.selected;
    }
    
    
    
}
