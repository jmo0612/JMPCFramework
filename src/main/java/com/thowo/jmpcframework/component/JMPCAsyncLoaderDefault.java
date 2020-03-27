/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thowo.jmpcframework.component;

import com.thowo.jmjavaframework.JMAsyncListener;
import com.thowo.jmjavaframework.JMFunctions;
import com.thowo.jmjavaframework.JMGlobalJava;
import com.thowo.jmjavaframework.db.JMConnection;
import com.thowo.jmjavaframework.lang.JMConstMessage;
import com.thowo.jmjavaframework.lang.JMMessage;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author jimi
 */
public class JMPCAsyncLoaderDefault implements JMAsyncListener {
    private JLabel txtMsg;
    private JPanel pnlLocker;
    
    public JMPCAsyncLoaderDefault(JLabel txtMsg,JPanel pnlLocker){
        this.txtMsg=txtMsg;
        this.pnlLocker=pnlLocker;
    }
    
    private void lock(){
        for(Component a:this.pnlLocker.getComponents()){
            a.setEnabled(false);
        }
    }
    
    private void unlock(){
        for(Component a:this.pnlLocker.getComponents()){
            a.setEnabled(true);
        }
    }
    
    private String getMessage(String id, String asyncType){
        String ret="";
        String msgId=JMConstMessage.MSG_ASYNC+asyncType;
        if(id.equals(JMConnection.JM_ASYNC_CONNECT)){
            msgId+=JMConstMessage.MSG_ASYNC_STATE_CONNECT_DB;
        }else if(id.equals(JMConnection.JM_ASYNC_FETCH)){
            msgId+=JMConstMessage.MSG_ASYNC_STATE_FETCH;
        }else if(id.equals(JMConnection.JM_ASYNC_UPDATE)){
            msgId+=JMConstMessage.MSG_ASYNC_STATE_UPDATE;
        }else if(id.equals(JMConnection.JM_ASYNC_DELETE)){
            msgId+=JMConstMessage.MSG_ASYNC_STATE_DELETE;
        }
        //ret=JMMessage.getMessage(msgId);
        ret=JMFunctions.getMessege(msgId);
        return ret;
    }

    @Override
    public void onJMStart(String id) {
        this.txtMsg.setText(this.getMessage(id, JMConstMessage.MSG_ASYNC_STARTED));
        this.lock();
    }

    @Override
    public void onJMProcess(String id) {
        this.txtMsg.setText(this.getMessage(id, JMConstMessage.MSG_ASYNC_PROCESSING));
    }

    @Override
    public void onJMComplete(Object result, String id) {
        this.txtMsg.setText(this.getMessage(id, JMConstMessage.MSG_ASYNC_COMPLETED));
        this.unlock();
    }

    @Override
    public void onJMError(String errorMessage, String id) {
        this.txtMsg.setText(this.getMessage(id, JMConstMessage.MSG_ASYNC_ERROR));
        JMFunctions.traceAndShow(errorMessage);
        this.unlock();
    }
    
}
