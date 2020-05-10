/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thowo.jmpcframework.component.form;

import com.thowo.jmjavaframework.JMFormInterface;
import com.thowo.jmjavaframework.JMFunctions;
import com.thowo.jmjavaframework.JMInputInterface;
import com.thowo.jmjavaframework.JMVec2;
import com.thowo.jmjavaframework.lang.JMConstMessage;
import com.thowo.jmjavaframework.table.JMCell;
import com.thowo.jmjavaframework.table.JMRow;
import com.thowo.jmjavaframework.table.JMTable;
import com.thowo.jmpcframework.component.JMPCButton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author jimi
 */
public class JMPCDBButtonGroup implements JMFormInterface{
    private JPanel op=new JPanel();
    private JPanel rec=new JPanel();
    private JMTable table;
    private String formTitle;
    
    private JMPCButton btnAdd;
    private JMPCButton btnEdit;
    private JMPCButton btnDelete;
    private JMPCButton btnSave;
    private JMPCButton btnPrint;
    private JMPCButton btnNext;
    private JMPCButton btnPrev;
    private JMPCButton btnFirst;
    private JMPCButton btnLast;
    private JMPCButton btnView;
    private JMPCButton btnRefresh;
    private JMPCButton btnCancel;
    private int defWidth=50;
    private int defHeight=10;
    private List<Boolean> befFilter=new ArrayList();
    
    
    public static JMPCDBButtonGroup create(JMTable table, String formTitle,boolean editing,boolean adding){
        return new JMPCDBButtonGroup(table, formTitle,editing,adding);
    }
    
    public JPanel getNavigationPanel(){
        return this.rec;
    }
    public JPanel getEditorPanel(){
        return this.op;
    }
    
    private void setProp(boolean editing,boolean adding){
        this.btnAdd=JMPCButton.create("Add", JMVec2.create(this.defWidth, this.defHeight));
        this.btnEdit=JMPCButton.create("Edit", JMVec2.create(this.defWidth, this.defHeight));
        this.btnDelete=JMPCButton.create("Delete", JMVec2.create(this.defWidth, this.defHeight));
        this.btnSave=JMPCButton.create("Save", JMVec2.create(this.defWidth, this.defHeight));
        this.btnPrint=JMPCButton.create("Print", JMVec2.create(this.defWidth, this.defHeight));
        this.btnNext=JMPCButton.create("Next", JMVec2.create(this.defWidth, this.defHeight));
        this.btnPrev=JMPCButton.create("Prev", JMVec2.create(this.defWidth, this.defHeight));
        this.btnFirst=JMPCButton.create("First", JMVec2.create(this.defWidth, this.defHeight));
        this.btnLast=JMPCButton.create("Last", JMVec2.create(this.defWidth, this.defHeight));
        this.btnView=JMPCButton.create("View", JMVec2.create(this.defWidth, this.defHeight));
        this.btnRefresh=JMPCButton.create("Refresh", JMVec2.create(this.defWidth, this.defHeight));
        this.btnCancel=JMPCButton.create("Cancel", JMVec2.create(this.defWidth, this.defHeight));
        this.view(editing,adding);
    }
    private void display(boolean editing,boolean adding){
        if(editing){
            if(adding)this.stateAdd();
            else this.stateEdit();
        }else{
            this.stateInit();
        }
    }
    
    private void view(boolean editing,boolean adding){
        
        op.setOpaque(false);
        op.add(this.btnAdd);
        op.add(this.btnDelete);
        op.add(this.btnEdit);
        op.add(this.btnSave);
        op.add(this.btnCancel);
        op.add(this.btnView);
        op.add(this.btnRefresh);
        op.add(this.btnPrint);
        
        rec.setOpaque(false);
        rec.add(this.btnFirst);
        rec.add(this.btnPrev);
        rec.add(this.btnNext);
        rec.add(this.btnLast);
        this.addListener();
        this.display(editing, adding);
    }
    
    public JMPCDBButtonGroup(JMTable table, String formTitle,boolean editing,boolean adding){
        this.table=table;
        
        this.formTitle=formTitle;
        this.table.addInterface(this);
        this.setProp(editing,adding);
    }
    
    
    private void addListener(){
        this.btnAdd.setAction(new Runnable(){
            @Override
            public void run() {
                JMPCDBButtonGroup.this.btnAddClick();
            }
        });
        this.btnEdit.setAction(new Runnable(){
            @Override
            public void run() {
                JMPCDBButtonGroup.this.table.editRow();
            }
        });
        this.btnDelete.setAction(new Runnable(){
            @Override
            public void run() {
                JMPCDBButtonGroup.this.btnDeleteClick();
            }
        });
        this.btnSave.setAction(new Runnable(){
            @Override
            public void run() {
                JMPCDBButtonGroup.this.table.save();
            }
        });
        this.btnPrint.setAction(new Runnable(){
            @Override
            public void run() {
                
            }
        });
        this.btnNext.setAction(new Runnable(){
            @Override
            public void run() {
                JMPCDBButtonGroup.this.table.nextRow(true);
            }
        });
        this.btnPrev.setAction(new Runnable(){
            @Override
            public void run() {
                JMPCDBButtonGroup.this.table.prevRow(true);
            }
        });
        this.btnFirst.setAction(new Runnable(){
            @Override
            public void run() {
                JMPCDBButtonGroup.this.table.firstRow(true);
            }
        });
        this.btnLast.setAction(new Runnable(){
            @Override
            public void run() {
                JMPCDBButtonGroup.this.table.lastRow(true);
            }
        });
        this.btnView.setAction(new Runnable(){
            @Override
            public void run() {
                JMPCDBButtonGroup.this.btnViewClick();
            }
        });
        this.btnRefresh.setAction(new Runnable(){
            @Override
            public void run() {
                JMPCDBButtonGroup.this.btnRefreshClick();
            }
        });
        this.btnCancel.setAction(new Runnable(){
            @Override
            public void run() {
                JMPCDBButtonGroup.this.btnCancelClick();
            }
        });
    }
    
    
    
    public void btnCancelClick(){
        this.table.cancelEdit(JMFunctions.getMessege(JMConstMessage.MSG_ELSE+JMConstMessage.MSG_ELSE_CANCEL_EDITING)+" "+formTitle+"?", JOptionPane.YES_OPTION);
    }
    public void btnDeleteClick(){
        this.table.deleteRow(this.table.getCurrentRow(), JMFunctions.getMessege(JMConstMessage.MSG_ELSE+JMConstMessage.MSG_ELSE_DELETE)+" "+formTitle+"?", JOptionPane.YES_OPTION);
    }
    public void btnAddClick(){
        //List<String> fieldNames=this.table.getStyle().getFieldNames();
        //List<JMInputInterface> objs=new ArrayList();
        //for(String fieldName:fieldNames)objs.add(JMPCCellObject.create());
        
        this.table.addNewRow();
        
    }
    public void btnRefreshClick(){
        JMPCDBButtonGroup.this.table.refresh();
    }
    public void btnViewClick(){
        JMPCDBButtonGroup.this.table.viewRow();
    }
    
    private void backUpState(){
        this.befFilter.clear();
        this.befFilter.add(this.btnAdd.isLocked());
        this.befFilter.add(this.btnSave.isLocked());
        this.befFilter.add(this.btnRefresh.isLocked());
        this.befFilter.add(this.btnCancel.isLocked());
        this.befFilter.add(this.btnPrev.isLocked());
        this.befFilter.add(this.btnNext.isLocked());
        this.befFilter.add(this.btnLast.isLocked());
        this.befFilter.add(this.btnFirst.isLocked());
        this.befFilter.add(this.btnView.isLocked());
        this.befFilter.add(this.btnPrint.isLocked());
        this.befFilter.add(this.btnEdit.isLocked());
        this.befFilter.add(this.btnDelete.isLocked());
    }
    private void stateFilter(){
        if(this.befFilter.isEmpty())this.backUpState();
        String filter=this.table.getFilter();
        this.btnAdd.setLocked(this.befFilter.get(0)||!filter.equals(""));
        //this.btnSave.setLocked(this.befFilter.get(1));
        //this.btnRefresh.setLocked(this.befFilter.get(2));
        //this.btnCancel.setLocked(this.befFilter.get(3));
        this.btnPrev.setLocked(this.befFilter.get(4)||!filter.equals(""));
        this.btnNext.setLocked(this.befFilter.get(5)||!filter.equals(""));
        this.btnLast.setLocked(this.befFilter.get(6)||!filter.equals(""));
        this.btnFirst.setLocked(this.befFilter.get(7)||!filter.equals(""));
        //this.btnView.setLocked(this.befFilter.get(8));
        //this.btnPrint.setLocked(this.befFilter.get(9));
        //this.btnEdit.setLocked(this.befFilter.get(10));
        //this.btnDelete.setLocked(this.befFilter.get(11));
        if(filter.equals(""))this.befFilter.clear();
    }
    public void stateInit(){
        boolean on=false;//NEGATE
        //if(this.table.isAddingRow())on=true;
        //else if(this.table.isEditingRow())on=true;
        this.btnAdd.setLocked(on);
        this.btnDelete.setLocked(on);
        this.btnEdit.setLocked(on);
        this.btnPrint.setLocked(on);
        this.btnSave.setLocked(!on);
        this.btnView.setLocked(on);
        this.btnRefresh.setLocked(on);
        this.btnCancel.setLocked(!on);
        this.stateNav();
    }
    public void stateAdd(){
        boolean on=false;//NEGATE
        this.btnAdd.setLocked(!on);
        this.btnDelete.setLocked(!on);
        this.btnEdit.setLocked(!on);
        this.btnPrint.setLocked(!on);
        this.btnSave.setLocked(on);
        this.btnView.setLocked(!on);
        this.btnRefresh.setLocked(!on);
        this.btnCancel.setLocked(on);
        this.btnPrev.setLocked(true);
        this.btnNext.setLocked(true);
        this.btnLast.setLocked(true);
        this.btnFirst.setLocked(true);
        this.stateFilter();
    }
    public void stateDelete(){
        this.stateInit();
    }
    public void stateEdit(){
        boolean on=false;//NEGATE
        this.btnAdd.setLocked(!on);
        this.btnDelete.setLocked(!on);
        this.btnEdit.setLocked(!on);
        this.btnPrint.setLocked(!on);
        this.btnSave.setLocked(on);
        this.btnView.setLocked(!on);
        this.btnRefresh.setLocked(!on);
        this.btnCancel.setLocked(on);
        this.btnPrev.setLocked(true);
        this.btnNext.setLocked(true);
        this.btnLast.setLocked(true);
        this.btnFirst.setLocked(true);
        this.stateFilter();
    }
    private void statePrint(){
        /*boolean on=false;//NEGATE
        this.btnAdd.setLocked(on);
        this.btnDelete.setLocked(on);
        this.btnEdit.setLocked(!on);
        this.btnPrint.setLocked(!on);
        this.btnSave.setLocked(on);
        this.btnPrev.setLocked(!on);
        this.btnNext.setLocked(!on);
        this.btnLast.setLocked(!on);
        this.btnFirst.setLocked(!on);
        this.btnView.setLocked(!on);
        this.btnRefresh.setLocked(!on);*/
    }
    private void stateSave(){
        this.stateInit();
    }
    public void stateNav(){
        if(this.table==null){
            this.btnPrev.setLocked(true);
            this.btnNext.setLocked(true);
            this.btnLast.setLocked(true);
            this.btnFirst.setLocked(true);
            this.btnView.setLocked(true);
            this.btnPrint.setLocked(true);
            this.btnEdit.setLocked(true);
            this.btnDelete.setLocked(true);
        }else{
            if(this.table.getCurrentRow()==null){
                this.btnPrev.setLocked(true);
                this.btnNext.setLocked(true);
                this.btnLast.setLocked(true);
                this.btnFirst.setLocked(true);
                this.btnView.setLocked(true);
                this.btnPrint.setLocked(true);
                this.btnEdit.setLocked(true);
                this.btnDelete.setLocked(true);
            }else{
                boolean f=!this.table.isFirstRecord();//NEGATE
                boolean l=!this.table.isLastRecord();//NEGATE
                this.btnPrev.setLocked(!f);
                this.btnNext.setLocked(!l);
                this.btnLast.setLocked(!l);
                this.btnFirst.setLocked(!f);
            }
        }
        this.stateFilter();
    }
    public void stateView(){
        this.stateInit();
    }
    private void stateRefresh(){
        this.stateInit();
    }
    
    

    public JMPCButton getBtnCancel() {
        return btnCancel;
    }
    public void setBtnCancel(JMPCButton btnCancel) {
        this.btnCancel = btnCancel;
    }
    public JMPCButton getBtnRefresh() {
        return btnRefresh;
    }
    public void setBtnRefresh(JMPCButton btnRefresh) {
        this.btnRefresh = btnRefresh;
    }
    public JMPCButton getBtnView() {
        return btnView;
    }
    public void setBtnView(JMPCButton btnView) {
        this.btnView = btnView;
    }
    public JMPCButton getBtnAdd() {
        return btnAdd;
    }
    public void setBtnAdd(JMPCButton btnAdd) {
        this.btnAdd = btnAdd;
    }
    public JMPCButton getBtnEdit() {
        return btnEdit;
    }
    public void setBtnEdit(JMPCButton btnEdit) {
        this.btnEdit = btnEdit;
    }
    public JMPCButton getBtnDelete() {
        return btnDelete;
    }
    public void setBtnDelete(JMPCButton btnDelete) {
        this.btnDelete = btnDelete;
    }
    public JMPCButton getBtnSave() {
        return btnSave;
    }
    public void setBtnSave(JMPCButton btnSave) {
        this.btnSave = btnSave;
    }
    public JMPCButton getBtnPrint() {
        return btnPrint;
    }
    public void setBtnPrint(JMPCButton btnPrint) {
        this.btnPrint = btnPrint;
    }
    public JMPCButton getBtnNext() {
        return btnNext;
    }
    public void setBtnNext(JMPCButton btnNext) {
        this.btnNext = btnNext;
    }
    public JMPCButton getBtnPrev() {
        return btnPrev;
    }
    public void setBtnPrev(JMPCButton btnPrev) {
        this.btnPrev = btnPrev;
    }
    public JMPCButton getBtnFirst() {
        return btnFirst;
    }
    public void setBtnFirst(JMPCButton btnFirst) {
        this.btnFirst = btnFirst;
    }
    public JMPCButton getBtnLast() {
        return btnLast;
    }
    public void setBtnLast(JMPCButton btnLast) {
        this.btnLast = btnLast;
    }

    
    
    @Override
    public void actionAfterAdded(JMRow rowAdded) {
        JMFunctions.trace("ADDED RESPONSE FROM BUTTONS");
        this.table.gotoRow(rowAdded, true);
        this.stateAdd();
    }

    @Override
    public void actionAfterDeleted(JMRow rowDeleted, boolean deleted) {
        this.stateDelete();
    }

    @Override
    public void actionAfterSaved(String updateQuery,boolean saved) {
        if(saved)this.stateSave();
    }

    @Override
    public void actionAfterEdited(JMRow rowEdited) {
        this.stateEdit();
    }

    @Override
    public void actionAfterPrinted(JMRow rowPrinted) {
        this.statePrint();
    }

    @Override
    public void actionAfterRefreshed(JMRow rowRefreshed) {
        this.stateRefresh();
    }

    @Override
    public void actionAfterViewed(JMRow rowViewed) {
        this.stateView();
    }

    @Override
    public void actionAfterMovedNext(JMRow nextRow) {
        this.stateNav();
    }

    @Override
    public void actionAfterMovedPrev(JMRow prevRow) {
        this.stateNav();
    }

    @Override
    public void actionAfterMovedFirst(JMRow firstRow) {
        this.stateNav();
    }

    @Override
    public void actionAfterMovedLast(JMRow lastRow) {
        this.stateNav();
    }

    @Override
    public void actionAfterMovedtoRecord(JMRow currentRow) {
        this.stateNav();
    }

    @Override
    public void actionAfterCanceled(JMRow rowCanceled, boolean canceled) {
        if(canceled)this.stateInit();
    }

    @Override
    public void actionBeforeRefresh(JMRow rowRefreshed) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionAfterFiltered(String filter) {
        this.stateFilter();
    }

    @Override
    public void actionBeforeFilter(String filter) {
        this.backUpState();
    }
    
}
