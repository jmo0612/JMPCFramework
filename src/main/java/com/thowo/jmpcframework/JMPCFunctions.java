package com.thowo.jmpcframework;

import com.alee.extended.button.WebSwitch;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.button.WebToggleButton;
import com.alee.laf.checkbox.WebCheckBox;
import com.alee.laf.optionpane.WebOptionPane;
import com.alee.laf.spinner.WebSpinner;
import com.alee.managers.style.StyleId;
import com.alee.managers.style.StyleManager;
import com.alee.skin.dark.DarkSkin;
import com.alee.skin.modena.ModenaSkin;
import com.alee.skin.web.WebSkin;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;
import com.thowo.jmjavaframework.JMFunctions;
import com.thowo.jmjavaframework.JMVec2;
import com.thowo.jmjavaframework.table.JMCell;
import com.thowo.jmjavaframework.table.JMTable;
import com.thowo.jmpcframework.component.JMPCForm;
import com.thowo.jmpcframework.component.JMPCUIMessenger;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.synth.SynthLookAndFeel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;




//import static android.content.ContentValues.TAG;

/**
 * Created by jimi on 8/2/2017.
 */

public class JMPCFunctions{
    //private static String tes;
    
    public static void initLookAndFeel(Class<?> CLASS){
        /*SynthLookAndFeel synth = new SynthLookAndFeel();
        try {
            synth.load(CLASS.getResourceAsStream("/styles/buttons.xml"), CLASS);
            UIManager.setLookAndFeel ( synth);
        } catch (ParseException ex) {
            Logger.getLogger(JMPCFunctions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(JMPCFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        /*try {
            UIManager.setLookAndFeel ( new WebLookAndFeel());
            
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(JMPCFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        //BasicTextFieldUI a;
        WebLookAndFeel.install();
        
        //StyleManager.addExtensions ( null);
    }
    public static void init(String localeId){
        JMFunctions.setUIListener(new JMPCUIMessenger());
        String myAppCacheDir=System.getProperty("user.dir")+"/._cache";
        File cache=new File(myAppCacheDir+"/readme.jm");
        if(!JMFunctions.fileExist(cache)){
            JMFunctions.createFile(cache);
        }
        String myAppDocDir=System.getProperty("user.dir")+"/_appData";
        File doc=new File(myAppDocDir+"/readme.jm");
        if(!JMFunctions.fileExist(doc)){
            JMFunctions.createFile(doc);
        }
        //File languageExcelFile = new File(JMPCFunctions.class.getClassLoader().getResource("raw/jmlanguagepack.xls").getFile());
        JMFunctions.init(null,myAppCacheDir,myAppDocDir,localeId);
    }
    
    public static void panelBGImage(JPanel panel,String imgResPath){
        JMVec2 panelSize=new JMVec2(panel.getWidth(),panel.getHeight());
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Image img=new ImageIcon(classLoader.getResource(imgResPath)).getImage();
        JMVec2 imgSize=new JMVec2(img.getWidth(panel),img.getHeight(panel));
        List<JMVec2> scaled=JMFunctions.scaledSize(imgSize, panelSize, JMFunctions.SCALE_FIT);
        ImageIcon ico=new ImageIcon(img.getScaledInstance(scaled.get(0).getIntX(), scaled.get(0).getIntY(), Image.SCALE_SMOOTH));
        JLabel lblImg=new JLabel(ico);
        panel.add(lblImg);
        lblImg.setBounds(scaled.get(1).getIntX(), scaled.get(1).getIntY(), scaled.get(0).getIntX(), scaled.get(0).getIntY());
    }
    
    public static void horizontalButton(JButton button, String text, String hint, String imgResPath, JMVec2 buttonSize){
        int pad=10;
        button.getParent().setLayout(null);
        if(buttonSize==null)buttonSize=new JMVec2(180+pad,30+pad);
        JMVec2 defSize=new JMVec2(buttonSize.getIntY()-pad,buttonSize.getIntY()-pad);
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Image img=new ImageIcon(classLoader.getResource(imgResPath).getFile()).getImage();
        JMVec2 imgSize=new JMVec2(img.getWidth(button),img.getHeight(button));
        List<JMVec2> scaled=JMFunctions.scaledSize(imgSize, defSize, JMFunctions.SCALE_FIT);
        ImageIcon ico=new ImageIcon(img.getScaledInstance(scaled.get(0).getIntX(), scaled.get(0).getIntY(), Image.SCALE_SMOOTH));
        //ico.paintIcon(button, img.getGraphics(), scaled.get(1).getIntX(), scaled.get(1).getIntY());
        button.setIcon(ico);
        if(!text.equals(""))button.setText(text);
        if(!hint.equals(""))button.setToolTipText(hint);
        button.setSize(buttonSize.getIntX(), buttonSize.getIntY());
        button.setLocation((button.getParent().getWidth()/2)-(button.getWidth()/2), button.getLocation().y);
        
    }
    public static void horizontalButton(JButton button, String imgResPath){
        horizontalButton(button,"","",imgResPath,null);
    }
    
    public static Image getScaledAnimatedGif(Image animatedGif, JMVec2 newSize){
        Image ret=animatedGif;
        //FileInputStream fiStream=new FileInputStream(new File(ret));
        return ret;
    }
    
    
    public static JLabel getImageWithSize(String path, JMVec2 size){
        JLabel ret=new JLabel("NULL");
        Image img=new ImageIcon(path).getImage();
        JMVec2 imgSize=new JMVec2(img.getWidth(null),img.getHeight(null));
        if(size==null)size=imgSize;
        List<JMVec2> scaled=JMFunctions.scaledSize(imgSize, size, JMFunctions.SCALE_FIT);
        ImageIcon ico=new ImageIcon(img.getScaledInstance(scaled.get(0).getIntX(), scaled.get(0).getIntY(), Image.SCALE_SMOOTH));
        ret=new JLabel(ico);
        ret.setBounds(scaled.get(1).getIntX(), scaled.get(1).getIntY(), scaled.get(0).getIntX(), scaled.get(0).getIntY());
        return ret;
    }
    
    public static JLabel getImageWithSize(String resId, Class<?> CLASS, JMVec2 size){
        URL tmp=null;
        if(CLASS==null){
            tmp=JMFunctions.getResourcePath(resId);
        }else{
            tmp=JMFunctions.getResourcePath(resId,CLASS);
        }
        if(tmp==null){
            return getImage("");
        }else{
            JLabel ret=new JLabel("NULL");
            Image img=new ImageIcon(tmp.getPath()).getImage();
            JMVec2 imgSize=new JMVec2(img.getWidth(null),img.getHeight(null));
            if(size==null)size=imgSize;
            List<JMVec2> scaled=JMFunctions.scaledSize(imgSize, size, JMFunctions.SCALE_FIT);
            ImageIcon ico=new ImageIcon(img.getScaledInstance(scaled.get(0).getIntX(), scaled.get(0).getIntY(), Image.SCALE_SMOOTH));
            ret=new JLabel(ico);
            ret.setBounds(scaled.get(1).getIntX(), scaled.get(1).getIntY(), scaled.get(0).getIntX(), scaled.get(0).getIntY());
            return ret;
        }
    }
    
    private static void resizeImage(URL tmp, JPanel panel){
        JLabel ret=new JLabel("NULL");
        Image img=new ImageIcon(tmp).getImage();
        JMVec2 imgSize=new JMVec2(img.getWidth(null),img.getHeight(null));
        JMVec2 size=JMVec2.create(panel.getWidth(), panel.getHeight());
        List<JMVec2> scaled=JMFunctions.scaledSize(imgSize, size, JMFunctions.SCALE_FIT);
        ImageIcon ico=new ImageIcon(img.getScaledInstance(scaled.get(0).getIntX(), scaled.get(0).getIntY(), Image.SCALE_SMOOTH));
        ret=new JLabel(ico);
        ret.setBounds(scaled.get(1).getIntX(), scaled.get(1).getIntY(), scaled.get(0).getIntX(), scaled.get(0).getIntY());
        panel.removeAll();
        panel.add(ret);
    }
    
    public static void attachImageToAnEmptyPanelOnForm(String resId, Class<?> CLASS, JPanel panel, JMPCForm form){
        if(panel==null || form==null)return;
        URL tmp=null;
        if(CLASS==null){
            tmp=JMFunctions.getResourcePath(resId);
        }else{
            tmp=JMFunctions.getResourcePath(resId,CLASS);
        }
        if(tmp==null){
            return ;
        }else{
            JMPCFunctions.resizeImage(tmp, panel);
            final URL tmp2=tmp;
            panel.addComponentListener(new ComponentListener() {
                @Override
                public void componentResized(ComponentEvent e) {
                    JMPCFunctions.resizeImage(tmp2, panel);
                    form.repaint();
                }

                @Override
                public void componentMoved(ComponentEvent e) {
                    
                }

                @Override
                public void componentShown(ComponentEvent e) {
                    
                }

                @Override
                public void componentHidden(ComponentEvent e) {
                    
                }
            });
            
        }
    }
    
    public static JLabel getImage(String path){
        return getImageWithSize(path,null);
    }
    public static JLabel getImage(String resId, Class<?> CLASS){
        URL tmp=null;
        if(CLASS==null){
            tmp=JMFunctions.getResourcePath(resId);
        }else{
            tmp=JMFunctions.getResourcePath(resId,CLASS);
        }
        if(tmp==null){
            return getImage("");
        }else return getImage(tmp.getPath());
    }
    public static JPanel getImageOpaque(String resId, Class<?> CLASS, JMVec2 size){
        Image img=new ImageIcon(CLASS.getClassLoader().getResource(resId)).getImage();
        JMVec2 imgSize=new JMVec2(img.getWidth(null),img.getHeight(null));
        if(size==null)size=imgSize;
        List<JMVec2> scaled=JMFunctions.scaledSize(imgSize, size, JMFunctions.SCALE_FIT);
        ImageIcon ico=new ImageIcon(img.getScaledInstance(scaled.get(0).getIntX(), scaled.get(0).getIntY(), Image.SCALE_SMOOTH));
        JLabel lblImg=new JLabel(ico);
        JPanel panel=new JPanel();
        panel.setOpaque(false);
        panel.add(lblImg);
        lblImg.setBounds(scaled.get(1).getIntX(), scaled.get(1).getIntY(), scaled.get(0).getIntX(), scaled.get(0).getIntY());
        return panel;
    }
    public static Image getImageImg(String resId, Class<?> CLASS){
        URL tmp=null;
        if(CLASS==null){
            tmp=JMFunctions.getResourcePath(resId);
        }else{
            tmp=JMFunctions.getResourcePath(resId,CLASS);
        }
        if(tmp==null){
            return null;
        }else return new ImageIcon(tmp.getPath()).getImage();
    }
    public static Image getImageFromPath(String path){
        return new ImageIcon(path).getImage();
    }
    public static JPanel tes(){
        WebSwitch t=new WebSwitch();
        t.setSwitchComponents(new JLabel("jimi"), new JLabel("jeno"));
        
        
        JPanel ret=new JPanel();
        ret.add(t);
        return ret;
    }
    public static String encrypt(String text){
        String ret="";
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(text.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            ret = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        JMFunctions.trace("PASSWORD : "+ret);
        return ret;
    }
    
}
