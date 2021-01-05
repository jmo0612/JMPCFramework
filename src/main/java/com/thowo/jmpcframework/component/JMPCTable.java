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
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

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
            //if(table.isEmpty())return;
            //model.setColumnCount(0);
            List<String> lbls=table.getLabelTitles();
            for(String lbl:lbls)model.addColumn(lbl);
            
            List<TableColumn> hiddens=new ArrayList();
            for(int i=0;i<table.getStyle().getFieldNames().size();i++){
                if(!table.getStyle().getVisible(i))hiddens.add(this.getColumnModel().getColumn(i));
            }
            for(TableColumn c:hiddens){
                this.removeColumn(c);
            }
            
            if(!table.isEmpty()){
                table.firstRow(false);
                do{
                    model.addRow(this.getRowData(table.getCurrentRowDatas(),true));
                    //JMFunctions.trace(this.table.getCurrentRow().getRowNum()+"");
                }while(table.nextRow(false)!=null);
            }
            
            
            this.refreshLayout();
            if(!table.isEmpty())this.setRowSelectionInterval(0, 0);
        }
        this.addListener();
    }
    private void refreshLayout(){
        this.setDefaultEditor(Object.class, null);
        int w=0;
        for(int i=0;i<table.getStyle().getFieldNames().size();i++){
            if(table.getStyle().getVisible(i)){
                this.getColumnModel().getColumn(w++).setCellRenderer(new JMPCCellRenderer());
            }
            //this.getColumnModel().getColumn(i).setCellRenderer(new JMPCCellRenderer());
            //if(table.getCurrentRow().getCells().get(i).getDataContainer().getDataDisplay()==JMDataContainer.DATA_IMAGE)this.getColumnModel().getColumn(i).setCellRenderer(new JMPCCellRenderer());
            
        }
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setRowSelectionAllowed(true);
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
                if(e.getKeyCode()==e.VK_DOWN || e.getKeyCode()==e.VK_UP){
                    int ind=JMPCTable.this.getSelectedRow();
                    if(!JMPCTable.this.table.getFilter().equals(""))ind=JMPCTable.this.getRowSorter().convertRowIndexToModel(JMPCTable.this.getSelectedRow());
                    List<Integer> keys=JMPCTable.this.table.getKeyColumns();
                    List<String> vals=new ArrayList();
                    for(Integer key:keys){
                        JMPCCellObject tmp=(JMPCCellObject) JMPCTable.this.model.getValueAt(ind, key);
                        vals.add(tmp.getValueString());
                    }
                    JMPCTable.this.table.gotoRow(vals, true);
                }
                /*if(e.getKeyCode()==e.VK_DOWN){
                    if(JMPCTable.this.table.getFilter().equals("")){
                        JMPCTable.this.table.nextRow(true);
                    }else{
                        List<Integer> keys=JMPCTable.this.table.getKeyColumns();
                        List<String> vals=new ArrayList();
                        for(Integer key:keys){
                            JMPCCellObject tmp=(JMPCCellObject) JMPCTable.this.getValueAt(JMPCTable.this.getSelectedRow(), key);
                            vals.add(tmp.getValueString());
                        }
                        JMPCTable.this.table.gotoRow(vals, true);
                    }
                }
                if(e.getKeyCode()==e.VK_UP){
                    if(JMPCTable.this.table.getFilter().equals("")){
                        JMPCTable.this.table.prevRow(true);
                    }else{
                        List<Integer> keys=JMPCTable.this.table.getKeyColumns();
                        List<String> vals=new ArrayList();
                        for(Integer key:keys){
                            JMPCCellObject tmp=(JMPCCellObject) JMPCTable.this.getValueAt(JMPCTable.this.getSelectedRow(), key);
                            vals.add(tmp.getValueString());
                        }
                        JMPCTable.this.table.gotoRow(vals, true);
                    }
                }*/
            }
        });
        
        this.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                int ind=JMPCTable.this.getSelectedRow();
                if(!JMPCTable.this.table.getFilter().equals(""))ind=JMPCTable.this.getRowSorter().convertRowIndexToModel(JMPCTable.this.getSelectedRow());
                List<Integer> keys=JMPCTable.this.table.getKeyColumns();
                List<String> vals=new ArrayList();
                for(Integer key:keys){
                    JMPCCellObject tmp=(JMPCCellObject) JMPCTable.this.model.getValueAt(ind, key);
                    vals.add(tmp.getValueString());
                }
                JMPCTable.this.table.gotoRow(vals, true);
                /*if(JMPCTable.this.table.getFilter().equals(""))JMPCTable.this.table.gotoRow(JMPCTable.this.getSelectedRow(), true);
                else{
                    List<Integer> keys=JMPCTable.this.table.getKeyColumns();
                    List<String> vals=new ArrayList();
                    for(Integer key:keys){
                        JMPCCellObject tmp=(JMPCCellObject) JMPCTable.this.getValueAt(JMPCTable.this.getSelectedRow(), key);
                        vals.add(tmp.getValueString());
                    }
                    JMPCTable.this.table.gotoRow(vals, true);
                }*/
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
    private void filter(String str){
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        RowFilter<TableModel, Object> rf = null;
        try {
            rf = RowFilter.regexFilter(str);
        } catch (java.util.regex.PatternSyntaxException ee) {
            return;
        }
        sorter.setRowFilter(rf);
        this.setRowSorter(sorter);
    }

    

    @Override
    public void actionAfterAdded(JMRow rowAdded) {
        model.addRow(this.getRowData(rowAdded.getDataContainers(),true));
        this.currentRow=rowAdded;
    }

    @Override
    public void actionAfterDeleted(JMRow rowDeleted, boolean deleted, String extra) {
        if(deleted){
            this.table.filter("");
            if(rowDeleted.getRowNum()<this.getRowCount())model.removeRow(rowDeleted.getRowNum());
            //model.removeRow(rowDeleted.getRowNum());
            this.currentRow=table.getCurrentRow();
        }
    }

    @Override
    public void actionAfterSaved(String updateQuery,boolean saved) {
        this.table.filter("");
    }

    @Override
    public void actionAfterEdited(JMRow rowEdited) {
        
    }

    @Override
    public void actionAfterPrinted(JMRow rowPrinted) {
        
    }

    @Override
    public void actionAfterRefreshed(JMRow rowRefreshed) {
        
    }

    @Override
    public void actionAfterViewed(JMRow rowViewed) {
        
    }

    @Override
    public void actionAfterMovedNext(JMRow nextRow) {
        this.currentRow=nextRow;
        if(nextRow!=null){
            if(this.table.getFilter().equals("")){
                if(nextRow.getRowNum()>=this.getRowCount()){
                    model.addRow(this.getRowData(table.getCurrentRowDatas(),true));
                }
                if(currentRow.getRowNum()<this.getRowCount())this.setRowSelectionInterval(nextRow.getRowNum(), nextRow.getRowNum());
            }
            
            /*if(nextRow.getRowNum()>=this.getRowCount() && this.table.getFilter().equals("")){
                model.addRow(this.getRowData(table.getCurrentRowDatas(),true));
            }
            if(currentRow.getRowNum()<this.getRowCount())this.setRowSelectionInterval(nextRow.getRowNum(), nextRow.getRowNum());*/
        }
    }

    @Override
    public void actionAfterMovedPrev(JMRow prevRow) {
        this.currentRow=prevRow;
        if(prevRow!=null){
            if(this.table.getFilter().equals("")){
                if(prevRow.getRowNum()>=this.getRowCount()){
                    model.addRow(this.getRowData(table.getCurrentRowDatas(),true));
                }
                if(currentRow.getRowNum()<this.getRowCount())this.setRowSelectionInterval(prevRow.getRowNum(), prevRow.getRowNum());
            }
            
            /*if(prevRow.getRowNum()>=this.getRowCount() && this.table.getFilter().equals("")){
                model.addRow(this.getRowData(table.getCurrentRowDatas(),true));
            }
            if(currentRow.getRowNum()<this.getRowCount())this.setRowSelectionInterval(prevRow.getRowNum(), prevRow.getRowNum());*/
        }
    }

    @Override
    public void actionAfterMovedFirst(JMRow firstRow) {
        this.currentRow=firstRow;
        if(firstRow!=null){
            if(this.table.getFilter().equals("")){
                if(firstRow.getRowNum()>=this.getRowCount()){
                    model.addRow(this.getRowData(table.getCurrentRowDatas(),true));
                }
                if(currentRow.getRowNum()<this.getRowCount())this.setRowSelectionInterval(firstRow.getRowNum(), firstRow.getRowNum());
            }
            
            /*if(firstRow.getRowNum()>=this.getRowCount() && this.table.getFilter().equals("")){
                model.addRow(this.getRowData(table.getCurrentRowDatas(),true));
            }
            if(currentRow.getRowNum()<this.getRowCount())this.setRowSelectionInterval(firstRow.getRowNum(), firstRow.getRowNum());*/
        }
    }

    @Override
    public void actionAfterMovedLast(JMRow lastRow) {
        this.currentRow=lastRow;
        if(lastRow!=null){
            if(this.table.getFilter().equals("")){
                if(lastRow.getRowNum()>=this.getRowCount()){
                    model.addRow(this.getRowData(table.getCurrentRowDatas(),true));
                }
                if(currentRow.getRowNum()<this.getRowCount())this.setRowSelectionInterval(lastRow.getRowNum(), lastRow.getRowNum());
            }
            
            /*if(lastRow.getRowNum()>=this.getRowCount() && this.table.getFilter().equals("")){
                model.addRow(this.getRowData(table.getCurrentRowDatas(),true));
            }
            if(currentRow.getRowNum()<this.getRowCount())this.setRowSelectionInterval(lastRow.getRowNum(), lastRow.getRowNum());*/
        }
    }

    @Override
    public void actionAfterMovedtoRecord(JMRow currentRow) {
        this.currentRow=currentRow;
        if(currentRow!=null){
            if(this.table.getFilter().equals("")){
                if(currentRow.getRowNum()>=this.getRowCount()){
                    model.addRow(this.getRowData(table.getCurrentRowDatas(),true));
                }
                if(currentRow.getRowNum()<this.getRowCount())this.setRowSelectionInterval(currentRow.getRowNum(), currentRow.getRowNum());
            }
            
            /*if(currentRow.getRowNum()>=this.getRowCount() && this.table.getFilter().equals("")){
                model.addRow(this.getRowData(table.getCurrentRowDatas(),true));
            }
            if(currentRow.getRowNum()<this.getRowCount())this.setRowSelectionInterval(currentRow.getRowNum(), currentRow.getRowNum());*/
        }
    }

    @Override
    public void actionAfterCanceled(JMRow newCurrentRow, boolean canceled, JMRow canceledRow) {
        //if(canceled)this.currentRow=rowCanceled;
    }

    @Override
    public void actionBeforeRefresh(JMRow rowRefreshed) {
        this.table.filter("");
    }

    @Override
    public void actionAfterFiltered(String filter) {
        this.filter(filter);
    }

    @Override
    public void actionBeforeFilter(String filter) {
        
    }

    
    
}
