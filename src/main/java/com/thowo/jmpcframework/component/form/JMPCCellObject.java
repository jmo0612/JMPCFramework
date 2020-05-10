/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thowo.jmpcframework.component.form;

import com.thowo.jmjavaframework.JMDataContainer;
import com.thowo.jmjavaframework.JMFunctions;
import com.thowo.jmjavaframework.JMInputInterface;
import com.thowo.jmjavaframework.JMVec2;
import com.thowo.jmpcframework.JMPCFunctions;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author jimi
 */
public class JMPCCellObject extends JLabel implements JMInputInterface{
    private Object value;
    private String valueString;
    private String text;
    private JMDataContainer dc;
    private int dataDisplay;
    private JMVec2 size;
    
    public static JMPCCellObject create(JMDataContainer dc,JMVec2 size, boolean defaultValue){
        return new JMPCCellObject(dc,size, defaultValue);
    }
    public static JMPCCellObject create(){
        return new JMPCCellObject();
    }
    
    public JMPCCellObject(JMDataContainer dc,JMVec2 size, boolean defaultValue){
        this.dc=dc;
        if(dc==null)return;
        this.value=dc.getValue();
        this.text=dc.getText();
        this.dataDisplay=dc.getDataDisplay();
        this.size=size;
        this.setOpaque(false);
        dc.addInterface(this,defaultValue);
    }
    public JMPCCellObject(){
        this.dc=null;
        this.size=JMVec2.create(0, 0);
        this.setOpaque(false);
        if(dc==null)return;
        this.value=dc.getValue();
        this.text=dc.getText();
        this.dataDisplay=dc.getDataDisplay();
    }
    
    public String getValueString(){
        return this.valueString;
    }

    private void alignText(int align){
        if(align==JMDataContainer.ALIGN_LEFT)this.setHorizontalAlignment(SwingConstants.LEFT);
        else if(align==JMDataContainer.ALIGN_CENTER)this.setHorizontalAlignment(SwingConstants.CENTER);
        else this.setHorizontalAlignment(SwingConstants.RIGHT);
    }
    
    @Override
    public void displayText(String text, int JMDataContainerConstantAlign) {
        //JMFunctions.trace("FIRED : "+text);
        this.text=text;
        if(this.dataDisplay==JMDataContainer.DATA_IMAGE){
            JLabel tmp=JMPCFunctions.getImageWithSize(this.text, JMVec2.create(20, 20));
            this.setIcon(tmp.getIcon());
        }else{
            this.setIcon(null);
            this.setText(this.text);
        }
        this.alignText(JMDataContainerConstantAlign);
    }

    @Override
    public void displayError(String errMsg) {
        this.setIcon(null);
        this.setText(errMsg);
    }

    @Override
    public void displayHint(String hint) {
        
    }

    @Override
    public void setDataContainer(JMDataContainer dataContainer) {
        this.dc=dataContainer;
    }

    @Override
    public void setHidden(boolean hidden) {
        this.setVisible(!hidden);
    }

    @Override
    public void setValueString(String value) {
        this.valueString=value;
    }

    @Override
    public void setValueObject(Object value) {
        this.value=value;
    }
}
