/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thowo.jmpcframework;

import com.thowo.jmjavaframework.JMButtonInterface;
import com.thowo.jmjavaframework.JMColor;
import com.thowo.jmjavaframework.JMFieldInterface;
import com.thowo.jmjavaframework.JMPanelInterface;
import com.thowo.jmjavaframework.JMVec2;
import com.thowo.jmjavaframework.form.JMComponentWrapperInterface;
import com.thowo.jmjavaframework.form.JMFormInterface;
import com.thowo.jmjavaframework.table.JMDBListInterface;
import com.thowo.jmpcframework.component.JMPCButtonSimple;
import com.thowo.jmpcframework.component.JMPCPanel;
import com.thowo.jmpcframework.component.JMPCTable;
import com.thowo.jmpcframework.component.form.JMPCInputStringTFWeblaf;
import com.thowo.jmpcframework.component.form.JMPCInputStringTFWeblafBtn;
import com.thowo.jmpcframework.component.form.JMPCSwitchWeblaf;
import com.thowo.jmpcframework.defaults.JMPCFormDetail;
import com.thowo.jmpcframework.defaults.JMPCFormInput;
import com.thowo.jmpcframework.defaults.JMPCFormTable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jimi
 */
public class JMPCDBComponentWrapper implements JMComponentWrapperInterface{

    @Override
    public JMFieldInterface newTextFields(String label,String prompt) {
        JMPCInputStringTFWeblaf ret=JMPCInputStringTFWeblaf.create(label,prompt, 20, 400, true).setEditable(true);
        ret.setVisible(true);
        return ret;
    }

    @Override
    public JMFieldInterface newLookupFields(String label,String prompt) {
        JMPCInputStringTFWeblafBtn ret=JMPCInputStringTFWeblafBtn.create(label,prompt, 20, 400, true).setEditable(true);
        ret.setVisible(true);
        return ret;
    }

    @Override
    public JMFieldInterface newSwitchFields(String trueLabel,String falseLabel) {
        JMPCSwitchWeblaf ret=JMPCSwitchWeblaf.create(trueLabel,falseLabel, 20, 400, true).setEditable(true);
        ret.setVisible(true);
        return ret;
    }

    @Override
    public List<JMPanelInterface> newDBButtonPanels() {
        List<JMPanelInterface> ret=new ArrayList();
        ret.add(new JMPCPanel());
        ret.add(new JMPCPanel());
        return ret;
    }

    @Override
    public List<JMButtonInterface> newDBButtons(JMVec2 size) {
        if(size==null)size=JMVec2.create(40, 10);
        List<JMButtonInterface> ret=new ArrayList();
        ret.add(JMPCButtonSimple.create("Add","img/buttons/db/add.png", size).setFontColor(JMColor.decode("#234e79")).increaseFontSize(2));
        ret.add(JMPCButtonSimple.create("Edit","img/buttons/db/edit.png", size).setFontColor(JMColor.decode("#234e79")).increaseFontSize(2));
        ret.add(JMPCButtonSimple.create("Delete","img/buttons/db/delete.png", size).setFontColor(JMColor.decode("#234e79")).increaseFontSize(2));
        ret.add(JMPCButtonSimple.create("Save","img/buttons/db/save.png", size).setFontColor(JMColor.decode("#234e79")).increaseFontSize(2));
        ret.add(JMPCButtonSimple.create("Print","img/buttons/db/print.png", size).setFontColor(JMColor.decode("#234e79")).increaseFontSize(2));
        ret.add(JMPCButtonSimple.create("Next","img/buttons/db/next.png", size).setFontColor(JMColor.decode("#234e79")).increaseFontSize(2));
        ret.add(JMPCButtonSimple.create("Prev","img/buttons/db/prev.png", size).setFontColor(JMColor.decode("#234e79")).increaseFontSize(2));
        ret.add(JMPCButtonSimple.create("First","img/buttons/db/first.png", size).setFontColor(JMColor.decode("#234e79")).increaseFontSize(2));
        ret.add(JMPCButtonSimple.create("Last","img/buttons/db/last.png", size).setFontColor(JMColor.decode("#234e79")).increaseFontSize(2));
        ret.add(JMPCButtonSimple.create("View","img/buttons/db/view.png", size).setFontColor(JMColor.decode("#234e79")).increaseFontSize(2));
        ret.add(JMPCButtonSimple.create("Refresh","img/buttons/db/refresh.png", size).setFontColor(JMColor.decode("#234e79")).increaseFontSize(2));
        ret.add(JMPCButtonSimple.create("Cancel","img/buttons/db/cancel.png", size).setFontColor(JMColor.decode("#234e79")).increaseFontSize(2));
        return ret;
    }

    @Override
    public JMFormInterface newFormTable() {
        return new JMPCFormTable(null, true);
    }

    @Override
    public JMFormInterface newFormInput() {
        return new JMPCFormInput(null, true);
    }

    @Override
    public JMFormInterface newFormMasterDetail() {
        return new JMPCFormDetail(null, true);
    }

    @Override
    public JMDBListInterface newDBTableList() {
        return JMPCTable.create();
    }
}
