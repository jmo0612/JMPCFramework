/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thowo.jmpcframework.component;

import com.thowo.jmjavaframework.JMAsyncTask;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.thowo.jmjavaframework.JMVec2;
import com.thowo.jmjavaframework.JMFunctions;
import com.thowo.jmjavaframework.db.JMConnection;
import com.thowo.jmjavaframework.db.JMDBMySQL;
import com.thowo.jmpcframework.JMPCFunctions;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author jimi
 */
public class JMPCSplashForm extends javax.swing.JFrame {
    public static final String JM_ASYNC_LOAD_CONFIG="LOADCONFIG";
    private JFrame mainForm;
    private List<Object> dBs;

    /**
     * Creates new form JMPCSplashForm
     */
    
    
    public JMPCSplashForm(JFrame mainForm, List<Object> databases) {
        this.mainForm=mainForm;
        this.dBs=databases;
        init();
    }
    
    private void init(){
        JMFunctions.setUIListener(new JMPCUIMessenger());
        initSplash();
    }
    
    private void initFramework(){
        JMFunctions.setAsyncListener(new JMPCAsyncLoaderDefault( this.jLabel1 , null ));
        String languageExcelFileName = "raw/jmlanguagepack.xls";
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File languageExcelFile = new File(classLoader.getResource(languageExcelFileName).getFile());
        JMPCFunctions.init();
        
    }
    
    public void initSplash(){
        JMVec2 splashSize=new JMVec2(553,339);
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Image img=new ImageIcon(classLoader.getResource("img/splash.jpg").getFile()).getImage();
        JMVec2 imgSize=new JMVec2(img.getWidth(rootPane),img.getHeight(rootPane));
        imgSize.trace();
        List<JMVec2> scaled=JMFunctions.scaledSize(imgSize, splashSize, JMFunctions.SCALE_FIT);
        scaled.get(0).trace();
        scaled.get(1).trace();
        ImageIcon ico=new ImageIcon(img.getScaledInstance(scaled.get(0).getIntX(), scaled.get(0).getIntY(), Image.SCALE_SMOOTH));
        JLabel lblImg=new JLabel(ico);
        this.setContentPane(lblImg);
        lblImg.setBounds(scaled.get(1).getIntX(), scaled.get(1).getIntY(), scaled.get(0).getIntX(), scaled.get(0).getIntY());
        
        initComponents();
        
        this.setSize(splashSize.getIntX(), splashSize.getIntY());
        this.setLocationRelativeTo(null);
        this.jLabel1.setForeground(Color.WHITE);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Loading");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(287, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentShown

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        // TODO add your handling code here:
        initFramework();
        Thread t=new Thread(new Runnable(){
            @Override
            public void run() {
                JMFunctions.setConnection(new JMConnection((File)JMPCSplashForm.this.dBs.get(0),(JMDBMySQL)JMPCSplashForm.this.dBs.get(1)));
                //JMPCSplashForm.this.mainForm.setExtendedState(JFrame.MAXIMIZED_BOTH);
                JMPCSplashForm.this.mainForm.setVisible(true);
                JMPCSplashForm.this.setVisible(false);
            }
        });
        t.start();
        //this.setVisible(false);
        
        
    }//GEN-LAST:event_formFocusGained

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JMPCSplashForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JMPCSplashForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JMPCSplashForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JMPCSplashForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JMPCSplashForm().setVisible(true);
            }
        });*/
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
