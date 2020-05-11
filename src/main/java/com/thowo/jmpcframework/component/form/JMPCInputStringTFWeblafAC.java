/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thowo.jmpcframework.component.form;

import com.alee.laf.text.WebTextField;
import com.thowo.jmjavaframework.JMDataContainer;
import com.thowo.jmjavaframework.JMFunctions;
import com.thowo.jmjavaframework.JMInputInterface;
import com.thowo.jmjavaframework.table.JMRow;
import com.thowo.jmpcframework.others.JMPCAutoComplete;
import com.thowo.jmpcframework.others.JMPCAutoComplete.CommitAction;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author jimi
 */
public class JMPCInputStringTFWeblafAC extends JPanel implements JMInputInterface{
    private JLabel label;
    private JLabel error;
    private JPanel errPanel;
    private JTextField text;
    private String value;
    private Object valueObject;
    private LineBorder border;
    private JMDataContainer dc;
    private boolean editMode;
    private boolean editable;
    private boolean editingText=false;
    private KeyListener onType;
    private List<String> keywords;
    private JMPCAutoComplete auto;
    
    public static JMPCInputStringTFWeblafAC create(String label,String prompt, int maxChar, int maxWidth, boolean horizontal){
        return new JMPCInputStringTFWeblafAC("","","",label,prompt,maxChar,maxWidth,horizontal);
    }
    public static JMPCInputStringTFWeblafAC create(String value, String text, String error, String label, String prompt, int maxChar, int maxWidth, boolean horizontal){
        return new JMPCInputStringTFWeblafAC(value,text,error,label,prompt,maxChar,maxWidth,horizontal);
    }
    
    public JMPCInputStringTFWeblafAC(String value, String text, String error, String label, String prompt, int maxChar, int maxWidth, boolean horizontal){
        if(horizontal){
            this.setProp(value, text, error, label, prompt, maxChar, maxWidth);
        }else{
            this.setPropVertical(value, text, error, label, prompt, maxChar, maxWidth);
        }
    }
    public void setLabel(String label){
        this.label.setText(label);
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
        
        //XXXthis.text.setInputPrompt(prompt);
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
        
        //XXXthis.text.setInputPrompt(prompt);
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
    public boolean onEditMode(){
        return this.editMode;
    }
    public void setEditMode(boolean editMode, JMRow currentRow, int column){
        if(currentRow!=null){
            int col=column;
            //this.dc=currentRow.getDataContainers().get(col);
            this.dc=currentRow.getCells().get(col).getDataContainer();
            if(!this.dc.isInterfaceRegistered(this))this.dc.addInterface(this, true);
        }else{
            this.text.setText("");
        }
        this.editMode=editMode;
        this.text.setEditable(this.editable && editMode);
        
    }
    public boolean editable(){
        return this.editable;
    }
    public JMPCInputStringTFWeblafAC setEditable(boolean editable){
        this.editable=editable;
        return this;
    }
    public void setText(String text){
        JMFunctions.trace(text);
        this.text.setText(text);
    }
    public String getText(){
        return this.text.getText();
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
        return this.value;
    }
    public void lock(){
        this.text.setEditable(false);
    }
    public void unlock(){
        this.text.setEditable(true);
    }
    
    private void addListeners(){
        /*this.text.addKeyListener(new KeyListener() {
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
                if(JMPCInputStringTFWeblaf.this.editMode){
                    if(JMPCInputStringTFWeblaf.this.text.getText().equals("")){
                        JMPCInputStringTFWeblaf.this.hideError();
                    }
                    JMPCInputStringTFWeblaf.this.hideError();
                    if(JMPCInputStringTFWeblaf.this.dc==null)return;
                    JMPCInputStringTFWeblaf.this.dc.setValueString(JMPCInputStringTFWeblaf.this.text.getText());
                }
            }//EXIST
        });*/
        
        /*this.text.getDocument().addDocumentListener(new DocumentListener(){
            private void update(){
                if(JMPCInputStringTFWeblafAC.this.editingText){
                    if(JMPCInputStringTFWeblafAC.this.editMode){
                        JMPCInputStringTFWeblafAC.this.hideError();
                        if(JMPCInputStringTFWeblafAC.this.dc==null)return;
                        JMPCInputStringTFWeblafAC.this.dc.setValueString(JMPCInputStringTFWeblafAC.this.text.getText(),false,false);
                    }
                }
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                this.update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                this.update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                this.update();
            }
        });*/
        /*this.text.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(JMPCInputStringTFWeblafAC.this.editMode){
                    if(JMPCInputStringTFWeblafAC.this.dc==null)return;
                    if(JMPCInputStringTFWeblafAC.this.valueObject!=null)JMPCInputStringTFWeblafAC.this.text.setText(JMPCInputStringTFWeblafAC.this.value);
                    JMPCInputStringTFWeblafAC.this.text.selectAll();
                    JMPCInputStringTFWeblafAC.this.editingText=true;
                }
            }//EXIST

            @Override
            public void focusLost(FocusEvent e) {
                if(JMPCInputStringTFWeblafAC.this.editMode){
                    JMPCInputStringTFWeblafAC.this.editingText=false;
                    if(JMPCInputStringTFWeblafAC.this.text.getText().equals(""))JMPCInputStringTFWeblafAC.this.hideError();
                    if(JMPCInputStringTFWeblafAC.this.dc==null)return;
                    if(JMPCInputStringTFWeblafAC.this.valueObject!=null)JMPCInputStringTFWeblafAC.this.dc.setValueString(JMPCInputStringTFWeblafAC.this.value,true,false);
                }
            }//EXIST
        });*/
        
    }
    public void setKeyword(List<String> keywords){
        this.keywords=keywords;
        if(this.auto!=null){
            //this.text.getDocument().removeDocumentListener(this.auto);
        }
        //this.text.setFocusTraversalKeysEnabled(false);
        this.auto=new JMPCAutoComplete(this.text, keywords);
        this.text.getDocument().addDocumentListener(this.auto);
        //this.text.getInputMap().put(KeyStroke.getKeyStroke("TAB"), "commit");
        //this.text.getActionMap().put("commit", this.auto.new CommitAction());
        JMFunctions.trace("ADA MASO ++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
    

    private void alignText(int align){
        if(align==JMDataContainer.ALIGN_LEFT)this.text.setHorizontalAlignment(JTextField.LEFT);
        else if(align==JMDataContainer.ALIGN_CENTER)this.text.setHorizontalAlignment(JTextField.CENTER);
        else this.text.setHorizontalAlignment(JTextField.RIGHT);
    }
    @Override
    public void displayText(String text, int JMDataContainerConstantAlign) {
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //this.editingText=false;
        //this.hideError();
        //this.text.setText(text);
        //this.alignText(JMDataContainerConstantAlign);
        
    }

    @Override
    public void displayError(String errMsg) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //this.showError(errMsg);
    }

    @Override
    public void displayHint(String hint) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //XXXthis.text.addToolTip(hint).setVisible(true);
    }

    @Override
    public void setDataContainer(JMDataContainer dataContainer) {
        //this.dc=dataContainer;
        //this.value=this.dc.getValueAsString();
    }
    @Override
    public void setHidden(boolean hidden) {
        //this.setVisible(!hidden);
    }

    @Override
    public void setValueString(String value) {
        //this.value=value;
        //this.hideError();
    }

    @Override
    public void setValueObject(Object value) {
        //this.valueObject=value;
    }
}
