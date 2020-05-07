/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thowo.jmpcframework.component;

import com.thowo.jmjavaframework.JMDataContainer;
import com.thowo.jmjavaframework.JMFormInterface;
import com.thowo.jmjavaframework.JMFunctions;
import com.thowo.jmjavaframework.JMVec2;
import com.thowo.jmjavaframework.table.JMCell;
import com.thowo.jmjavaframework.table.JMRow;
import com.thowo.jmjavaframework.table.JMTable;
import com.thowo.jmpcframework.JMPCFunctions;
import com.thowo.jmpcframework.component.form.JMPCCellObject;
import com.thowo.jmpcframework.component.form.JMPCCellRenderer;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jimi
 */
public class JMPCTable extends JTable implements JMFormInterface{
    private JMTable table;
    private DefaultTableModel model;
    private JMRow currentRow;
    
    
    public static JMPCTable create(JMTable table){
        return new JMPCTable(table);
    }
    public JMPCTable(JMTable table){
        this.setProp(table);
        if(table!=null){
            if(table.isEmpty())return;
            //model.setColumnCount(0);
            List<String> lbls=table.getLabelTitles();
            for(String lbl:lbls)model.addColumn(lbl);
            table.firstRow(false);
            do{
                model.addRow(this.getRowData(table.getCurrentRowDatas(),true));
                //JMFunctions.trace(this.table.getCurrentRow().getRowNum()+"");
            }while(table.nextRow(false)!=null);
            
            this.setDefaultEditor(Object.class, null);
            
            for(int i=0;i<table.getCurrentRow().getCells().size();i++){
                this.getColumnModel().getColumn(i).setCellRenderer(new JMPCCellRenderer());
                //if(!table.getCurrentRow().getCells().get(i).getVisible())this.removeColumn(this.getColumnModel().getColumn(i));
                //if(table.getCurrentRow().getCells().get(i).getDataContainer().getDataDisplay()==JMDataContainer.DATA_IMAGE)this.getColumnModel().getColumn(i).setCellRenderer(new JMPCCellRenderer());
                
            }
            this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            this.setRowSelectionAllowed(true);
            this.setRowSelectionInterval(0, 0);
        }
        this.addListener();
    }
    private void setProp(JMTable table){
        this.table=table;
        this.table.addInterface(this);
        this.model=(DefaultTableModel)this.getModel();
        this.setModel(this.model);
        
    }
    private void addListener(){
        this.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==e.VK_ENTER)e.consume();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==e.VK_ENTER){
                    /*JMPCDBButtonGroup tmp=new JMPCDBButtonGroup(TableTes.this.dbObject);
                    tmp.stateView();
                    TableTes.this.openForm(false,tmp);*/
                }
                if(e.getKeyCode()==e.VK_DOWN)JMPCTable.this.table.nextRow(true);
                if(e.getKeyCode()==e.VK_UP)JMPCTable.this.table.prevRow(true);
            }
        });
        
        this.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                JMPCTable.this.table.gotoRow(JMPCTable.this.getSelectedRow(), true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        });
    }
    private Object[] getRowData(List<JMDataContainer> dcs, boolean defaultValue){
        Object[] ret=null;
        if(dcs==null)return null;
        List<JMPCCellObject> cells=new ArrayList();
        for(JMDataContainer dc:dcs){
            //JMPCCellObject tmp=JMPCCellObject.create(dc, JMVec2.create(this.getRowHeight(), this.getRowHeight()));
            JMPCCellObject tmp=JMPCCellObject.create(dc, JMVec2.create(0, 0),defaultValue);
            //dc.addInterface(tmp);
            cells.add(tmp);
        }
        ret=cells.toArray();
        //ret=dcs.toArray();
        return ret;
    }

    @Override
    public void actionAdd(JMRow rowAdded) {
        model.addRow(this.getRowData(rowAdded.getDataContainers(),true));
        this.currentRow=rowAdded;
    }

    @Override
    public void actionDelete(JMRow rowDeleted) {
        JMFunctions.trace("Table delete row: "+rowDeleted.getRowNum());
        model.removeRow(rowDeleted.getRowNum());
        this.currentRow=table.getCurrentRow();
    }

    @Override
    public void actionSave(String updateQuery) {
        
    }

    @Override
    public void actionEdit(JMRow rowEdited) {
        
    }

    @Override
    public void actionPrint(JMRow rowPrinted) {
        
    }

    @Override
    public void actionRefresh(JMRow rowRefreshed) {
        model.fireTableDataChanged();
    }

    @Override
    public void actionView(JMRow rowViewed) {
        
    }

    @Override
    public void actionNext(JMRow nextRow) {
        this.setRowSelectionInterval(nextRow.getRowNum(), nextRow.getRowNum());
        this.currentRow=nextRow;
    }

    @Override
    public void actionPrev(JMRow prevRow) {
        this.setRowSelectionInterval(prevRow.getRowNum(), prevRow.getRowNum());
        this.currentRow=prevRow;
    }

    @Override
    public void actionFirst(JMRow firstRow) {
        this.setRowSelectionInterval(firstRow.getRowNum(), firstRow.getRowNum());
        this.currentRow=firstRow;
    }

    @Override
    public void actionLast(JMRow lastRow) {
        this.setRowSelectionInterval(lastRow.getRowNum(), lastRow.getRowNum());
        this.currentRow=lastRow;
    }

    @Override
    public void gotoRecord(JMRow currentRow) {
        this.setRowSelectionInterval(currentRow.getRowNum(), currentRow.getRowNum());
        this.currentRow=currentRow;
    }

    @Override
    public void actionCancel(JMRow rowCanceled, boolean canceled) {
        if(canceled)this.currentRow=rowCanceled;
    }

    
    
}
