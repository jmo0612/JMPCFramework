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
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
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
public class JMPCButton extends JPanel {
    private JPanel content=new JPanel();
    private JLabel text=new JLabel();
    private JPanel iconLeft;
    private JPanel iconRight;
    private JPanel iconTop;
    private JPanel iconBottom;
    private JPanel bg;
    private JPanel bgHover;
    private JPanel bgDisabled;
    private JPanel bgClicked;
    private JMVec2 size;
    private List<MouseListener> listeners=new ArrayList();
    private MouseListener uiListener;
    private boolean locked;
    private Color fontColor=Color.WHITE;
    private int fontSize=0;
    private String fontFamily="Dialog";
    
    
    public static JMPCButton create(String text){
        return new JMPCButton(text);
    }
    public static JMPCButton create(String text, JMVec2 size){
        return new JMPCButton(text,size);
    }
    
    public JMPCButton setFontColor(Color color){
        this.fontColor=color;
        this.refreshContent();
        return this;
    }
    public JMPCButton increaseFontSize(int inc){
        this.fontSize+=inc;
        this.refreshContent();
        return this;
    }
    public JMPCButton decreaseFontSize(int dec){
        this.fontSize-=dec;
        this.refreshContent();
        return this;
    }
    public JMPCButton setFont(String font){
        this.fontFamily=font;
        this.refreshContent();
        return this;
    }
    public JMPCButton addIconLeft(String resId, Class<?> CLASS){
        this.iconLeft=JMPCFunctions.getImageOpaque(resId, CLASS, JMVec2.create(this.size.getIntY()-2, this.size.getIntY()-2));
        this.refreshContent();
        return this;
    }
    public JMPCButton addIconRight(String resId, Class<?> CLASS){
        this.iconRight=JMPCFunctions.getImageOpaque(resId, CLASS, JMVec2.create(this.size.getIntY()-2, this.size.getIntY()-2));
        this.refreshContent();
        return this;
    }
    public JMPCButton addIconTop(String resId, Class<?> CLASS){
        int h=this.size.getIntY();
        if(this.iconTop==null){
            if(this.iconBottom!=null)h=this.size.getIntY()/2;
            this.size=JMVec2.create(this.size.getIntX(), this.size.getIntY()+h);
        }
        this.iconTop=JMPCFunctions.getImageOpaque(resId, CLASS, JMVec2.create(h-2, h-2));
        this.reArrange();
        return this;
    }
    public JMPCButton addIconBottom(String resId, Class<?> CLASS){
        int h=this.size.getIntY();
        if(this.iconBottom==null){
            if(this.iconTop!=null)h=this.size.getIntY()/2;
            this.size=JMVec2.create(this.size.getIntX(), this.size.getIntY()+h);
        }
        this.iconBottom=JMPCFunctions.getImageOpaque(resId, CLASS, JMVec2.create(h-2, h-2));
        this.reArrange();
        return this;
    }
    
    public JMPCButton(String text){
        this.setProp(text, null);
    }
    public JMPCButton(String text, JMVec2 size){
        this.setProp(text, size);
    }
    private void setProp(String text, JMVec2 btnSize){
        this.size=btnSize;
        if(!text.equals(""))this.text.setText(text);
        this.view();
        this.setFocusable(true);
    }
    
    private void refreshContent(){
        this.content.removeAll();
        this.content.setLayout(new BorderLayout());
        this.content.setOpaque(false);
        if(this.iconTop!=null)this.content.add(this.iconTop,BorderLayout.NORTH);
        if(this.iconBottom!=null)this.content.add(this.iconBottom,BorderLayout.SOUTH);
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
        if(this.iconLeft!=null)p.add(this.iconLeft);
        p.add(this.text);
        if(this.iconRight!=null)p.add(this.iconRight);
        this.content.add(p,BorderLayout.CENTER);
        //this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    }
    
    private void view(){
        if(this.bg==null)this.bg=JMPCFunctions.getImageOpaque("img/buttons/default/bg.png", this.getClass(), this.size);
        if(this.bgHover==null)this.bgHover=JMPCFunctions.getImageOpaque("img/buttons/default/bgHover.png", this.getClass(), this.size);
        if(this.bgDisabled==null)this.bgDisabled=JMPCFunctions.getImageOpaque("img/buttons/default/bgDisabled.png", this.getClass(), this.size);
        if(this.bgClicked==null)this.bgClicked=JMPCFunctions.getImageOpaque("img/buttons/default/bgClicked.png", this.getClass(), this.size);
        if(this.text==null)this.text=new JLabel();
        
        this.bgDisabled.setVisible(false);
        this.bgDisabled.setOpaque(false);
        this.setLayout(new OverlayLayout(this));
        this.add(this.bgDisabled);
        this.add(this.content);
        this.add(this.bg);
        this.add(this.bgHover);
        this.add(this.bgClicked);
        this.addListener();
        this.refreshContent();
    }
    
    private void reArrange(){
        this.removeAll();
        if(this.bg==null)this.bg=JMPCFunctions.getImageOpaque("img/buttons/default/bg.png", this.getClass(), this.size);
        if(this.bgHover==null)this.bgHover=JMPCFunctions.getImageOpaque("img/buttons/default/bgHover.png", this.getClass(), this.size);
        if(this.bgDisabled==null)this.bgDisabled=JMPCFunctions.getImageOpaque("img/buttons/default/bgDisabled.png", this.getClass(), this.size);
        if(this.bgClicked==null)this.bgClicked=JMPCFunctions.getImageOpaque("img/buttons/default/bgClicked.png", this.getClass(), this.size);
        if(this.text==null)this.text=new JLabel();
        
        this.bgDisabled.setVisible(false);
        this.bgDisabled.setOpaque(false);
        this.setLayout(new OverlayLayout(this));
        this.add(this.bgDisabled);
        this.add(this.content);
        this.add(this.bg);
        this.add(this.bgHover);
        this.add(this.bgClicked);
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
            this.bg.setVisible(true);
            this.bgHover.setVisible(!locked);
            this.bgClicked.setVisible(!locked);
            this.bgDisabled.setVisible(locked);

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
                JMPCButton.this.grabFocus();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                JMPCButton.this.bgClicked.setVisible(true);
                JMPCButton.this.bg.setVisible(false);
                JMPCButton.this.bgHover.setVisible(false);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                JMPCButton.this.bgClicked.setVisible(false);
                JMPCButton.this.bg.setVisible(true);
                JMPCButton.this.bgHover.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                //JMFunctions.trace("entered");
                JMPCButton.this.bg.setVisible(false);
                JMPCButton.this.bgHover.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                //JMFunctions.trace("exited");
                JMPCButton.this.bg.setVisible(true);
                JMPCButton.this.bgHover.setVisible(false);
            }
        };
        this.addMouseListener(this.uiListener);
    }
    
}
