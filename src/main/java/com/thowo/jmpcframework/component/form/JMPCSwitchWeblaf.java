/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thowo.jmpcframework.component.form;

import com.alee.extended.button.WebSwitch;
import com.thowo.jmjavaframework.JMDataContainer;
import com.thowo.jmjavaframework.JMFunctions;
import com.thowo.jmjavaframework.JMFieldInterface;
import com.thowo.jmjavaframework.table.JMRow;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author jimi
 */
public class JMPCSwitchWeblaf extends JPanel implements JMFieldInterface{
    private JLabel label;
    private JLabel error;
    private JPanel errPanel;
    private WebSwitch text;
    private String value;
    private Object valueObject;
    private LineBorder border;
    private JMDataContainer dc;
    private boolean editMode;
    private boolean editable;
    private boolean editingText=false;
    private KeyListener onType;
    
    public static JMPCSwitchWeblaf create(String labelTrue,String labelFalse, int maxChar, int maxWidth, boolean horizontal){
        return new JMPCSwitchWeblaf("","","",labelTrue,labelFalse,maxChar,maxWidth,horizontal);
    }
    public static JMPCSwitchWeblaf create(String value, String text, String error, String label, String prompt, int maxChar, int maxWidth, boolean horizontal){
        return new JMPCSwitchWeblaf(value,text,error,label,prompt,maxChar,maxWidth,horizontal);
    }
    
    public JMPCSwitchWeblaf(String value, String text, String error, String labelTrue, String labelFalse, int maxChar, int maxWidth, boolean horizontal){
        if(horizontal){
            this.setProp(value, text, error, labelTrue, labelFalse, maxChar, maxWidth);
        }else{
            this.setPropVertical(value, text, error, labelTrue, labelFalse, maxChar, maxWidth);
        }
    }
    public void setLabel(String label){
        this.label.setText("");
    }
    private void setProp(String value, String text, String error, String labelTrue, String labelFalse, int maxChar, int maxWidth){
        JLabel dummy=new JLabel();
        for(int i=0;i<maxWidth/2;i++){
            dummy.setText(dummy.getText()+" ");
        }
        dummy.setFont(new Font("Dialog", Font.PLAIN,2));
        
        this.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        this.label=new JLabel("");
        this.error=new JLabel(error);
        this.value=value;
        if(text.equals(""))text=this.value;
        //this.text=new WebSwitch(text,maxChar);
        this.text=new WebSwitch();
        this.text.setSwitchComponents(new JLabel(labelTrue), new JLabel(labelFalse));
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
        
        //XXXthis.text.setInputPrompt(prompt);
        this.errPanel.setVisible(false);
        this.addListeners();
        this.text.setAnimate(false);
        //this.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
    }
    private void setPropVertical(String value, String text, String error, String labelTrue, String labelFalse, int maxChar, int maxWidth){
        JLabel dummy=new JLabel();
        for(int i=0;i<maxWidth;i++){
            dummy.setText(dummy.getText()+" ");
        }
        dummy.setFont(new Font("Dialog", Font.PLAIN,2));
        
        //this.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        this.label=new JLabel("");
        this.error=new JLabel(error);
        this.value=value;
        if(text.equals(""))text=this.value;
        //XXXthis.text=new WebSwitch(text,maxChar);
        this.text=new WebSwitch();
        this.text.setSwitchComponents(new JLabel(labelTrue), new JLabel(labelFalse));
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
        
        //XXXthis.text.setInputPrompt(prompt);
        this.errPanel.setVisible(false);
        this.addListeners();
        this.text.setAnimate(false);
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
    public boolean onEditMode(){
        return this.editMode;
    }
    
    public boolean editable(){
        return this.editable;
    }
    public JMPCSwitchWeblaf setEditable(boolean editable){
        this.editable=editable;
        return this;
    }
    public void setSelected(boolean selected){
        //XXXthis.text.setText(text);
        this.text.setSelected(selected);
    }
    public boolean getSelected(){
        return this.text.isSelected();
    }
    public void setAction(Runnable action){
        if(this.onType!=null)this.text.removeKeyListener(onType);
        this.onType=new KeyListener(){
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
                action.run();
            }
        };
        this.text.addKeyListener(this.onType);
    }
    
    public String getValueString(){
        JMFunctions.trace(this.value + ",   " + (boolean)this.valueObject);
        boolean tmp=this.text.isSelected();
        return String.valueOf(tmp);
    }
    public void lock(){
        this.text.setEnabled(false);
    }
    public void unlock(){
        this.text.setEnabled(true);
    }
    
    private void addListeners(){
        this.text.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(JMPCSwitchWeblaf.this.valueObject!=null)JMPCSwitchWeblaf.this.dc.setValueString(String.valueOf(JMPCSwitchWeblaf.this.getSelected()),true,false);
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        
    }
    

    private void alignText(int align){
        //XXXif(align==JMDataContainer.ALIGN_LEFT)this.text.setHorizontalAlignment(JTextField.LEFT);
        //XXXelse if(align==JMDataContainer.ALIGN_CENTER)this.text.setHorizontalAlignment(JTextField.CENTER);
        //XXXelse this.text.setHorizontalAlignment(JTextField.RIGHT);
    }
    @Override
    public void displayText(String text, int JMDataContainerConstantAlign) {
        
        //JMFunctions.trace(text);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.editingText=false;
        this.hideError();
        
        //this.alignText(JMDataContainerConstantAlign);
    }

    @Override
    public void displayError(String errMsg) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.showError(errMsg);
    }

    @Override
    public void displayHint(String hint) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.text.addToolTip(hint).setVisible(true);
    }

    @Override
    public void setDataContainer(JMDataContainer dataContainer) {
        this.dc=dataContainer;
        //this.value=this.dc.getValueAsString();
    }
    @Override
    public void setHidden(boolean hidden) {
        this.setVisible(!hidden);
    }

    @Override
    public void setValueString(String value) {
        //this.value=value;
        //this.hideError();
    }

    @Override
    public void setValueObject(Object value) {
        this.valueObject=value;
        boolean tmp=(boolean)value;
        this.text.setSelected(tmp);
    }

    @Override
    public void setEditMode(boolean editMode, JMRow currentRow, int column) {
        if(currentRow!=null){
            int col=column;
            //this.dc=currentRow.getDataContainers().get(col);
            this.dc=currentRow.getCells().get(col).getDataContainer();
            if(!this.dc.isInterfaceRegistered(this))this.dc.addInterface(this, true);
        }else{
            this.text.setSelected(false);
        }
        this.editMode=editMode;
        //this.text.setEditable(this.editable && editMode);
        this.text.setEnabled(this.editable && editMode);
    }

    @Override
    public void setDisabled(boolean disabled) {
        this.setEditable(!disabled);
    }

    @Override
    public void setLookUpAction(Runnable action) {
        
    }

}
