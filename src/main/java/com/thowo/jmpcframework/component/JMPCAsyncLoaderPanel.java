/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thowo.jmpcframework.component;

import com.thowo.jmjavaframework.JMAsyncListener;
import com.thowo.jmjavaframework.JMFunctions;
import com.thowo.jmjavaframework.db.JMConnection;
import com.thowo.jmjavaframework.lang.JMConstMessage;

/**
 *
 * @author jimi
 */
public class JMPCAsyncLoaderPanel implements JMAsyncListener{
    private JMPCLoadingSprite loading;
    private OpacityPanel content;
    
    public JMPCAsyncLoaderPanel(JMPCLoadingSprite loading, OpacityPanel contentPanel){
        this.loading=loading;
        this.content=contentPanel;
    }
    
    public JMPCAsyncLoaderPanel(OpacityPanel contentPanel){
        this.loading=new JMPCLoadingSprite();
        this.content=contentPanel;
    }
    
    public JMPCLoadingSprite getLoadingSprite(){
        return this.loading;
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
        }else if(id.equals(JMPCSplashForm.JM_ASYNC_LOAD_CONFIG)){
            msgId+=JMConstMessage.MSG_ASYNC_STATE_LOAD_CONFIG;
            JMFunctions.trace(msgId);
        }
        //ret=JMMessage.getMessage(msgId);
        ret=JMFunctions.getMessege(msgId);
        return ret;
    }
    
    private void lock(){
        this.content.setOpacity(0.4f);
        this.loading.setVisible(true);
    }
    private void unlock(){
        this.content.setOpacity(1.0f);
        this.loading.setVisible(false);
    }

    @Override
    public void onJMStart(String id) {
        this.loading.setText(this.getMessage(id, JMConstMessage.MSG_ASYNC_STARTED));
        this.lock();
    }

    @Override
    public void onJMProcess(String id) {
        this.loading.setText(this.getMessage(id, JMConstMessage.MSG_ASYNC_PROCESSING));
    }

    @Override
    public void onJMComplete(Object result, String id) {
        this.loading.setText(this.getMessage(id, JMConstMessage.MSG_ASYNC_COMPLETED));
        this.unlock();
    }

    @Override
    public void onJMError(String errorMessage, String id) {
        this.loading.setText(this.getMessage(id, JMConstMessage.MSG_ASYNC_ERROR));
        JMFunctions.traceAndShow(errorMessage);
        this.unlock();
    }
    
}
