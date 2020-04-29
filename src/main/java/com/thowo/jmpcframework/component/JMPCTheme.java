/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thowo.jmpcframework.component;

import com.thowo.jmpcframework.JMPCFunctions;
import javax.swing.JLabel;

/**
 *
 * @author jimi
 */
public class JMPCTheme {
    private JLabel bgForButtons;
    private JLabel bgHoverForButtons;
    
    public JLabel getBackgroundForButtons(){
        return this.bgForButtons;
    }
    public JLabel getBackgroundHoverForButtons(){
        return this.bgHoverForButtons;
    }
    
    public void setBackgroundForButtons(String imgPath){
        this.bgForButtons=JMPCFunctions.getImage(imgPath);
    }
    public void setBackgroundHoverForButtons(String imgPath){
        this.bgHoverForButtons=JMPCFunctions.getImage(imgPath);
    }
    
}
