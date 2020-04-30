package com.thowo.jmpcframework;

import com.alee.laf.WebLookAndFeel;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;
import com.thowo.jmjavaframework.JMFunctions;
import com.thowo.jmjavaframework.JMVec2;
import com.thowo.jmjavaframework.table.JMCell;
import com.thowo.jmjavaframework.table.JMTable;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.synth.SynthLookAndFeel;
import javax.swing.table.DefaultTableModel;




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
        
        try {
            UIManager.setLookAndFeel ( new WebLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(JMPCFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        //BasicTextFieldUI a;
    }
    public static void init(File languageExcelFile){
        String myAppCacheDir=System.getProperty("user.dir")+"/.jmcache";
        File cache=new File(myAppCacheDir+"/readme.jm");
        if(!JMFunctions.fileExist(cache)){
            JMFunctions.createFile(cache);
        }
        JMFunctions.init(languageExcelFile,myAppCacheDir);
    }
    
    public static void panelBGImage(JPanel panel,String imgResPath){
        JMVec2 panelSize=new JMVec2(panel.getWidth(),panel.getHeight());
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Image img=new ImageIcon(classLoader.getResource(imgResPath).getFile()).getImage();
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
    
    public static URL getResourcePath(String resId, Class<?> CLASS) {
        return CLASS.getClassLoader().getResource(resId);
    }
    public static URL getResourcePath(String resId){
        return ClassLoader.getSystemClassLoader().getResource(resId);
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
    public static JLabel getImage(String path){
        return getImageWithSize(path,null);
    }
    public static JLabel getImage(String resId, Class<?> CLASS){
        URL tmp=null;
        if(CLASS==null){
            tmp=getResourcePath(resId);
        }else{
            tmp=getResourcePath(resId,CLASS);
        }
        if(tmp==null){
            return getImage("");
        }else return getImage(tmp.getPath());
    }
    public static Image getImageImg(String resId, Class<?> CLASS){
        URL tmp=null;
        if(CLASS==null){
            tmp=getResourcePath(resId);
        }else{
            tmp=getResourcePath(resId,CLASS);
        }
        if(tmp==null){
            return null;
        }else return new ImageIcon(tmp.getPath()).getImage();
    }
    public static void linkTable(JTable jTable, JMTable table){
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        table.firstRow();
        do{
            model.addRow(table.getTableData());
        }while(table.nextRow()!=null);
    }
}
