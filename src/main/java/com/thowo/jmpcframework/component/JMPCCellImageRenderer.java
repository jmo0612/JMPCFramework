/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thowo.jmpcframework.component;

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
public class JMPCCellImageRenderer extends DefaultTableCellRenderer{
    private boolean isDBValue;
    private JLabel lbl;
    private String url;
    private boolean isRes;
    private Class<?> resClass;
    private String prefix;
    private String suffix;
    
    public static JMPCCellImageRenderer create(){
        return new JMPCCellImageRenderer();
    }
    public static JMPCCellImageRenderer create(String prefix, String suffix){
        return new JMPCCellImageRenderer(prefix, suffix);
    }
    public static JMPCCellImageRenderer create(Class<?> CLASS){
        return new JMPCCellImageRenderer(CLASS);
    }
    public static JMPCCellImageRenderer create(Class<?> CLASS,String prefix, String suffix){
        return new JMPCCellImageRenderer(CLASS,prefix, suffix);
    }
    public static JMPCCellImageRenderer create(String url){
        return new JMPCCellImageRenderer(url);
    }
    
    public JMPCCellImageRenderer(){
        this.isDBValue=true;
        this.isRes=false;
        this.resClass=null;
        this.url="";
        this.prefix="";
        this.suffix="";
    }
    public JMPCCellImageRenderer(String prefix, String suffix){
        this.isDBValue=true;
        this.isRes=false;
        this.resClass=null;
        this.url="";
        this.prefix=prefix;
        this.suffix=suffix;
    }
    public JMPCCellImageRenderer(Class<?> CLASS){
        this.isDBValue=true;
        this.isRes=true;
        this.resClass=CLASS;
        this.url="";
        this.prefix="";
        this.suffix="";
    }
    public JMPCCellImageRenderer(Class<?> CLASS,String prefix, String suffix){
        this.isDBValue=true;
        this.isRes=true;
        this.resClass=CLASS;
        this.url="";
        this.prefix=prefix;
        this.suffix=suffix;
    }
    public JMPCCellImageRenderer(String url){
        this.isDBValue=false;
        this.isRes=false;
        this.resClass=null;
        this.url=url;
        this.prefix="";
        this.suffix="";
    }
    public JMPCCellImageRenderer(String resId, Class<?> CLASS){
        this.isDBValue=false;
        this.isRes=true;
        this.resClass=CLASS;
        this.url=resId;
        this.prefix="";
        this.suffix="";
    }
    
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
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
        else this.lbl.setBackground(table.getBackground());
        return this.lbl;
    }
}
