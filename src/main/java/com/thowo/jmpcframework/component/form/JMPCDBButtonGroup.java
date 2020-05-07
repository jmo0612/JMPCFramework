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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
    
    
    public static JMPCDBButtonGroup create(JMTable table){
        return new JMPCDBButtonGroup(table);
    }
    
    public JPanel getNavigationPanel(){
        return this.rec;
    }
    public JPanel getEditorPanel(){
        return this.op;
    }
    
    private void setProp(){
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
        this.view();
    }
    
    private void view(){
        
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
        this.stateInit();
    }
    
    public JMPCDBButtonGroup(JMTable table){
        this.table=table;
        this.table.addInterface(this);
        this.setProp();
    }
    
    public void btnCancelClick(){
        this.table.cancelEdit(JMFunctions.getMessege(JMConstMessage.MSG_ELSE+JMConstMessage.MSG_ELSE_CANCEL_EDITING)+" "+formTitle, JOptionPane.YES_OPTION);
    }
    public void btnAddClick(){
        List<JMCell> cells=this.table.getCurrentRow().getCells();
        List<JMInputInterface> objs=new ArrayList();
        for(JMCell cell:cells)objs.add(JMPCCellObject.create());
        this.table.addNewRow(objs);
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
                //JMPCDBButtonGroup.this.stateDelete();
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
                //JMPCDBButtonGroup.this.statePrint();
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
                JMPCDBButtonGroup.this.stateView();
            }
        });
        this.btnRefresh.setAction(new Runnable(){
            @Override
            public void run() {
                //JMPCDBButtonGroup.this.stateRefresh();
            }
        });
        this.btnCancel.setAction(new Runnable(){
            @Override
            public void run() {
                JMPCDBButtonGroup.this.btnCancelClick();
            }
        });
    }
    
    public void stateInit(){
        boolean on=false;//NEGATE
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
        //this.stateNav();
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
        //this.stateNav();
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
        }else{
            boolean f=!this.table.isFirstRecord();//NEGATE
            boolean l=!this.table.isLastRecord();//NEGATE
            this.btnPrev.setLocked(!f);
            this.btnNext.setLocked(!l);
            this.btnLast.setLocked(!l);
            this.btnFirst.setLocked(!f);
        }
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
    public void actionAdd(JMRow rowAdded) {
        this.stateAdd();
    }

    @Override
    public void actionDelete(JMRow rowDeleted) {
        this.stateDelete();
    }

    @Override
    public void actionSave(String updateQuery) {
        this.stateSave();
    }

    @Override
    public void actionEdit(JMRow rowEdited) {
        this.stateEdit();
    }

    @Override
    public void actionPrint(JMRow rowPrinted) {
        this.statePrint();
    }

    @Override
    public void actionRefresh(JMRow rowRefreshed) {
        this.stateRefresh();
    }

    @Override
    public void actionView(JMRow rowViewed) {
        this.stateView();
    }

    @Override
    public void actionNext(JMRow nextRow) {
        this.stateNav();
    }

    @Override
    public void actionPrev(JMRow prevRow) {
        this.stateNav();
    }

    @Override
    public void actionFirst(JMRow firstRow) {
        this.stateNav();
    }

    @Override
    public void actionLast(JMRow lastRow) {
        this.stateNav();
    }

    @Override
    public void gotoRecord(JMRow currentRow) {
        this.stateNav();
    }

    @Override
    public void actionCancel(JMRow rowCanceled, boolean canceled) {
        if(canceled)this.stateInit();
    }
    
}
