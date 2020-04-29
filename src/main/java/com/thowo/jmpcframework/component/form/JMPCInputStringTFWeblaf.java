/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thowo.jmpcframework.component.form;

import com.alee.laf.text.WebTextField;
import com.thowo.jmjavaframework.JMFunctions;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author jimi
 */
public class JMPCInputStringTFWeblaf extends JPanel {
    private JLabel label;
    private JLabel error;
    private JPanel errPanel;
    private WebTextField text;
    private String value;
    private LineBorder border;
    
    public static JMPCInputStringTFWeblaf create(String value, String text, String error, String label, String prompt, int maxChar, int maxWidth, boolean horizontal){
        return new JMPCInputStringTFWeblaf(value,text,error,label,prompt,maxChar,maxWidth,horizontal);
    }
    
    public JMPCInputStringTFWeblaf(String value, String text, String error, String label, String prompt, int maxChar, int maxWidth, boolean horizontal){
        if(horizontal){
            this.setProp(value, text, error, label, prompt, maxChar, maxWidth);
        }else{
            this.setPropVertical(value, text, error, label, prompt, maxChar, maxWidth);
        }
    }
    
    private void setProp(String value, String text, String error, String label, String prompt, int maxChar, int maxWidth){
        JLabel dummy=new JLabel();
        for(int i=0;i<maxWidth/2;i++){
            dummy.setText(dummy.getText()+" ");
        }
        dummy.setFont(new Font("Dialog", Font.PLAIN,2));
        
        this.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        this.label=new JLabel(label);
        this.error=new JLabel(error);
        this.value=value;
        if(text.equals(""))text=this.value;
        this.text=new WebTextField(text,maxChar);
        this.errPanel=new JPanel();
        
        JPanel lblPanel=new JPanel();
        //lblPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        lblPanel.setOpaque(false);
        lblPanel.setLayout(new BorderLayout());
        lblPanel.add(dummy,BorderLayout.NORTH);
        lblPanel.add(this.label,BorderLayout.SOUTH);
        
        
        JPanel txtPanel=new JPanel();
        txtPanel.setOpaque(false);
        txtPanel.setLayout(new BorderLayout());
        //txtPanel.add(new JLabel(" "),BorderLayout.NORTH);
        txtPanel.add(this.text,BorderLayout.SOUTH);
        
        this.errPanel.setOpaque(false);
        this.errPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        this.errPanel.add(this.error);
        this.error.setForeground(new Color(181, 36, 36,255));
        
        JPanel inpPanel=new JPanel();
        inpPanel.setOpaque(false);
        inpPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        inpPanel.add(lblPanel);
        inpPanel.add(txtPanel);
        
        Box main=Box.createVerticalBox();
        main.setOpaque(false);
        main.add(inpPanel);
        main.add(this.errPanel);
        this.add(main);
        
        this.text.setInputPrompt(prompt);
        this.errPanel.setVisible(false);
        this.addListeners();
        //this.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
    }
    private void setPropVertical(String value, String text, String error, String label, String prompt, int maxChar, int maxWidth){
        JLabel dummy=new JLabel();
        for(int i=0;i<maxWidth;i++){
            dummy.setText(dummy.getText()+" ");
        }
        dummy.setFont(new Font("Dialog", Font.PLAIN,2));
        
        //this.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        this.label=new JLabel(label);
        this.error=new JLabel(error);
        this.value=value;
        if(text.equals(""))text=this.value;
        this.text=new WebTextField(text,maxChar);
        this.errPanel=new JPanel();
        
        JPanel dummyPanel=new JPanel();
        dummyPanel.setOpaque(false);
        dummyPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        dummyPanel.add(dummy);
        //dummyPanel.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        
        JPanel lblPanel=new JPanel();
        lblPanel.setOpaque(false);
        lblPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        lblPanel.add(this.label);
        //lblPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        this.errPanel.setOpaque(false);
        this.errPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        this.errPanel.add(this.error);
        this.error.setForeground(new Color(181, 36, 36,255));
        
        JPanel txtPanel=new JPanel();
        txtPanel.setOpaque(false);
        txtPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        txtPanel.add(this.text);
        //txtPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        
        Box theBox=Box.createVerticalBox();
        theBox.add(dummyPanel);
        theBox.add(lblPanel);
        theBox.add(this.errPanel);
        theBox.add(txtPanel);
        this.add(theBox);
        
        this.text.setInputPrompt(prompt);
        this.errPanel.setVisible(false);
        this.addListeners();
        //this.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        
    }
    public void showError(String error){
        this.error.setText(error);
        this.errPanel.setVisible(true);
        this.border = new LineBorder(new Color(181, 36, 36,255), 2, true);
        this.text.setForeground(new Color(181, 36, 36,255));
        this.setBackground(new Color(181, 36, 36,20));
        this.setBorder(this.border);
    }
    public void hideError(){
        this.setBorder(null);
        this.setBackground(null);
        this.text.setForeground(null);
        this.errPanel.setVisible(false);
    }
    
    private void addListeners(){
        this.text.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if(JMPCInputStringTFWeblaf.this.text.getText().equals("")){
                    JMPCInputStringTFWeblaf.this.hideError();
                }
            }
        });
        
    }
}
