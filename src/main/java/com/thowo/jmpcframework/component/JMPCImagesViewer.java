/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thowo.jmpcframework.component;

import com.thowo.jmjavaframework.JMFunctions;
import com.thowo.jmjavaframework.JMVec2;
import com.thowo.jmpcframework.JMPCFunctions;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author jimi
 */
public class JMPCImagesViewer extends JPanel{
    private List<Image> images;
    private List<JLabel> lblImgs;
    private List<JPanel> pnlImgs;
    private JPanel pnlList;
    private JPanel pnlView;
    private JLabel lblView;
    private JButton btnAdd;
    private int width;
    private int height;
    
    public static JMPCImagesViewer create(){
        return new JMPCImagesViewer(200,300);
    }
    public static JMPCImagesViewer create(int width, int height){
        return new JMPCImagesViewer(width, height);
    }
    public static JMPCImagesViewer create(List<String> paths, int width, int height){
        return new JMPCImagesViewer(paths, width, height);
    }
    
    public JMPCImagesViewer(int width, int height){
        this.setProp(width, height);
    }
    public JMPCImagesViewer(List<String> paths, int width, int height){
        int lWidth=width/5;
        this.setProp(width,height);
        for(String path:paths){
            Image tmpImg=JMPCFunctions.getImageFromPath(path);
            this.images.add(tmpImg);
            
            JMVec2 imgSize=new JMVec2(tmpImg.getWidth(null),tmpImg.getHeight(null));
            JMVec2 size=new JMVec2(lWidth,lWidth);
            List<JMVec2> scaled=JMFunctions.scaledSize(imgSize, size, JMFunctions.SCALE_FIT);
            ImageIcon ico=new ImageIcon(tmpImg.getScaledInstance(scaled.get(0).getIntX(), scaled.get(0).getIntY(), Image.SCALE_SMOOTH));
            JLabel lblImg=new JLabel(ico);
            lblImg.setBounds(scaled.get(1).getIntX(), scaled.get(1).getIntY(), scaled.get(0).getIntX(), scaled.get(0).getIntY());
            this.lblImgs.add(lblImg);
            JPanel pnlImg=new JPanel();
            pnlImg.add(lblImg);
            this.pnlImgs.add(pnlImg);
            this.pnlList.add(pnlImg);
            
        }
        this.view(0);
    }
    
    private void setProp(int width, int height){
        this.images=new ArrayList();
        this.lblImgs=new ArrayList();
        this.pnlImgs=new ArrayList();
        this.pnlList=new JPanel();
        this.pnlView=new JPanel();
        this.lblView=new JLabel();
        this.btnAdd=new JButton("+");
        this.width=width;
        this.height=height;
        this.setContainer();
    }
    private void setContainer(){
        int scrolBarHeight=30;
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.yellow));
        
        JScrollPane scrollThumbs=new JScrollPane(this.pnlList);
        scrollThumbs.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollThumbs.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollThumbs.setBounds(0, 0, this.width+scrolBarHeight, this.width/5+scrolBarHeight);
        JPanel pnlThumbs=new JPanel(null);
        pnlThumbs.setPreferredSize(new Dimension(this.width+scrolBarHeight,this.width/5+scrolBarHeight));
        pnlThumbs.add(scrollThumbs);
        
        JScrollPane scrollView=new JScrollPane(this.pnlView);
        scrollView.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollView.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollView.setBounds(0, 0, this.width+scrolBarHeight, this.height+scrolBarHeight);
        JPanel pnlMain=new JPanel(null);
        pnlMain.setPreferredSize(new Dimension(this.width+scrolBarHeight,this.height+scrolBarHeight));
        pnlMain.add(scrollView);
        
        
        this.pnlView.add(this.lblView);
        
        
        JPanel pnlAdd=new JPanel();
        pnlAdd.add(this.btnAdd);
        
        
        this.add(pnlThumbs,BorderLayout.NORTH);
        this.add(pnlMain,BorderLayout.CENTER);
        this.add(pnlAdd,BorderLayout.SOUTH);
    }
    private void view(int index){
        JMVec2 imgSize=new JMVec2(this.images.get(index).getWidth(null),this.images.get(index).getHeight(null));
        JMVec2 size=new JMVec2(this.width,this.height);
        List<JMVec2> scaled=JMFunctions.scaledSize(imgSize, size, JMFunctions.SCALE_FIT);
        ImageIcon ico=new ImageIcon(this.images.get(index).getScaledInstance(scaled.get(0).getIntX(), scaled.get(0).getIntY(), Image.SCALE_SMOOTH));
        this.lblView.setIcon(ico);
        this.lblView.setBounds(scaled.get(1).getIntX(), scaled.get(1).getIntY(), scaled.get(0).getIntX(), scaled.get(0).getIntY());
    }
}
