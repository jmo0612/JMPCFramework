/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thowo.jmpcframework.component.form;

import com.thowo.jmjavaframework.JMDataContainer;
import com.thowo.jmjavaframework.JMFunctions;
import com.thowo.jmjavaframework.JMVec2;
import com.thowo.jmpcframework.JMPCFunctions;
import java.awt.Component;
import java.net.URL;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author jimi
 */
public class JMPCCellRenderer extends DefaultTableCellRenderer{
    //private JLabel lbl;
    
    
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //if(value==null)return null;
        /*JMDataContainer dc=(JMDataContainer) value;
        if(dc.getDataDisplay()==JMDataContainer.DATA_IMAGE){
            JMVec2 size=JMVec2.create(table.getRowHeight(), table.getRowHeight());
            this.lbl=JMPCFunctions.getImageWithSize(dc.getText(), size);
        }else{
            this.lbl=new JLabel(dc.getText());
        }*/
        
        JLabel lbl=(JLabel)value;
        lbl.setOpaque(true);
        if(isSelected)lbl.setBackground(table.getSelectionBackground());
        else lbl.setBackground(table.getBackground());
        /*
        if(this.isDBValue){
            if(this.isRes){
                URL tmp=JMFunctions.getResourcePath((String) value, this.resClass);
                if(tmp!=null)this.url=tmp.getPath();
            }else{
                this.url=(String)value;
            }
        }
        this.url=this.prefix+this.url+this.suffix;
        if(this.url!=null){
            int h=table.getRowHeight(row);
            this.lbl=JMPCFunctions.getImageWithSize(this.url, JMVec2.create(h, h));
        }else{
            this.lbl=new JLabel();
        }
        this.lbl.setOpaque(true);
        if(isSelected)this.lbl.setBackground(table.getSelectionBackground());
        else this.lbl.setBackground(table.getBackground());*/
        return lbl;
    }
}
