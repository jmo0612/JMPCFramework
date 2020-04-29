/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thowo.jmpcframework.component;

import com.thowo.jmjavaframework.JMFunctions;
import com.thowo.jmjavaframework.JMVec2;
import com.thowo.jmpcframework.JMPCFunctions;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author jimi
 */
public class JMPCButton extends JButton {
    private String text;
    private JLabel icon;
    private JLabel bg;
    private JLabel bgHover;
    
    public JMPCButton(String text, String imgIconPath, String imgBgPath, String imgBgHoverPath, JMVec2 btnSize){
        this.setProp(text, imgIconPath, imgBgPath, imgBgHoverPath, btnSize);
    }
    public JMPCButton(String text){
        this.setProp(text, "", "", "", null);
    }
    public JMPCButton(String text, String imgIconPath, JMVec2 btnSize){
        this.setProp(text, imgIconPath, "", "", btnSize);
    }
    public JMPCButton(String text, String imgBgPath, String imgBgHoverPath, JMVec2 btnSize){
        this.setProp(text, "", imgBgPath, imgBgHoverPath, btnSize);
    }
    public JMPCButton(JButton button, JMPCTheme theme){
        if(theme!=null && button!=null)this.setProp(button.getText(), new JLabel(button.getIcon()), theme.getBackgroundForButtons(), theme.getBackgroundHoverForButtons(), new JMVec2(button.getWidth(),button.getHeight()));
    }
    public JMPCButton(JButton button){
        if(button!=null)this.setProp(button.getText(), new JLabel(button.getIcon()), null, null, new JMVec2(button.getWidth(),button.getHeight()));
    }
    
    private void setProp(String text, String imgIconPath, String imgBgPath, String imgBgHoverPath, JMVec2 btnSize){
        if(!text.equals(""))this.text=text;
        if(JMFunctions.fileExist(new File(imgIconPath))){
            int pad=2;
            JMVec2 icoSize=new JMVec2(btnSize.getIntY()-pad*2,btnSize.getIntY()-pad*2);
            this.icon=JMPCFunctions.getImageWithSize(imgIconPath, icoSize);
        }
        if(JMFunctions.fileExist(new File(imgBgPath)))this.bg=JMPCFunctions.getImageWithSize(imgBgPath, btnSize);
        if(JMFunctions.fileExist(new File(imgBgHoverPath)))this.bgHover=JMPCFunctions.getImageWithSize(imgBgHoverPath, btnSize);
    }
    private void setProp(String text, JLabel icon, JLabel bg, JLabel bgHover, JMVec2 btnSize){
        if(!text.equals(""))this.text=text;
        if(icon!=null)this.icon=icon;
        if(bg!=null)this.bg=bg;
        if(bgHover!=null)this.bgHover=bgHover;
    }
    
}
