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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
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
    private List<String> paths;
    private List<Image> images;
    private List<JLabel> lblImgs;
    private List<JPanel> pnlImgs;
    private JPanel pnlList;
    private JPanel pnlView;
    private JLabel lblView;
    private Image imgView;
    private JButton btnAdd;
    private int width;
    private int height;
    private List<Integer> selected;
    private JMVec2 imgSize;
    
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
        this.setProp(null,width, height);
    }
    public JMPCImagesViewer(List<String> paths, int width, int height){
        this.setProp(paths,width,height);
        this.fillFromPaths();
        this.select(0);
        this.btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JMPCImagesViewer.this.moveRight(0);
            }
        });
    }
    private void fillFromPaths(){
        int lWidth=this.width/5;
        for(String path:this.paths){
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
        this.addThumbsListener();
        this.addViewListener();
    }
    
    private void setProp(List<String> paths, int width, int height){
        this.paths=paths;
        this.width=width;
        this.height=height;
        this.images=new ArrayList();
        this.lblImgs=new ArrayList();
        this.pnlImgs=new ArrayList();
        this.selected=new ArrayList();
        this.pnlList=new JPanel();
        this.pnlView=new JPanel();
        this.lblView=new JLabel();
        this.btnAdd=new JButton("+");
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
        //scrollView.setWheelScrollingEnabled(false);
        scrollView.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollView.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
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
        this.imgView=this.images.get(index);
        JMVec2 imgSize=new JMVec2(this.images.get(index).getWidth(null),this.images.get(index).getHeight(null));
        JMVec2 size=new JMVec2(this.width,this.height);
        List<JMVec2> scaled=JMFunctions.scaledSize(imgSize, size, JMFunctions.SCALE_FIT);
        ImageIcon ico=new ImageIcon(this.images.get(index).getScaledInstance(scaled.get(0).getIntX(), scaled.get(0).getIntY(), Image.SCALE_SMOOTH));
        this.lblView.setIcon(ico);
        this.lblView.setBounds(scaled.get(1).getIntX(), scaled.get(1).getIntY(), scaled.get(0).getIntX(), scaled.get(0).getIntY());
    }
    private void select(int index){
        this.clearSelection();
        this.addSelection(index);
    }
    private void addSelection(int index){
        this.pnlImgs.get(index).setBorder(BorderFactory.createEtchedBorder());
        this.pnlImgs.get(index).setFocusable(true);
        this.pnlImgs.get(index).requestFocus();
        this.view(index);
        this.selected.add(index);
    }
    private Integer removeAllButLast(){
        int last=this.selected.get(this.selected.size()-1);
        this.select(last);
        return last;
    }
    private void removeSelection(int index){
        if(this.selected.size()>1){
            for(int i=0;i<this.selected.size();i++){
                if(this.selected.get(i)==index){
                    this.selected.remove(i);
                    this.unselect(index);
                }
            }
        }
        this.pnlImgs.get(this.selected.get(this.selected.size()-1)).setFocusable(true);
        this.pnlImgs.get(this.selected.get(this.selected.size()-1)).requestFocus();
    }
    private void selectRange(int from, int to){
        this.clearSelection();
        int r0,r1;
        if(from<to){
            r0=from;
            r1=to;
        }else{
            r1=from;
            r0=to;
        }
        for(int i=r0;i<=r1;i++){
            this.addSelection(i);
        }
    }
    
    private void unselect(int index){
        this.pnlImgs.get(index).setBorder(null);
    }
    
    private void clearSelection(){
        for(int i=0;i<this.images.size();i++){
            this.unselect(i);
        }
        this.selected=new ArrayList();
    }
    private boolean isSelected(int index){
        for(Integer i:this.selected){
            if(i==index)return true;
        }
        return false;
    }
    
    private void addThumbsListener(){
        for(int i=0;i<this.pnlImgs.size();i++){
            JPanel p=this.pnlImgs.get(i);
            final int ind=i;
            p.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    if(e.isShiftDown()){
                        int last=JMPCImagesViewer.this.removeAllButLast();
                        JMPCImagesViewer.this.selectRange(last, ind);
                    }else if(e.isControlDown()){
                        if(!JMPCImagesViewer.this.isSelected(ind))JMPCImagesViewer.this.addSelection(ind);
                        else JMPCImagesViewer.this.removeSelection(ind);
                    }else JMPCImagesViewer.this.select(ind);
                    
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            p.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    int r=39;
                    int l=37;
                    /*if(e.getKeyCode()==r && JMPCImagesViewer.this.selected+1<JMPCImagesViewer.this.images.size()){
                        JMPCImagesViewer.this.select(JMPCImagesViewer.this.selected+1);
                    }else if(e.getKeyCode()==l && JMPCImagesViewer.this.selected-1>=0){
                        JMPCImagesViewer.this.select(JMPCImagesViewer.this.selected-1);
                    }*/
                    
                    int toSelect=ind;
                    if(e.getKeyCode()==r && ind+1<JMPCImagesViewer.this.images.size()){
                        toSelect=ind+1;
                        if(e.isControlDown()&& !e.isShiftDown()){
                            JMPCImagesViewer.this.moveRight(toSelect);
                        }else{
                            if(!e.isShiftDown())JMPCImagesViewer.this.select(toSelect);
                            else {
                                if(!JMPCImagesViewer.this.isSelected(toSelect))JMPCImagesViewer.this.addSelection(toSelect);
                                else JMPCImagesViewer.this.removeSelection(ind);
                            }
                        }
                        
                    }else if(e.getKeyCode()==l && ind-1>=0){
                        toSelect=ind-1;
                        if(e.isControlDown()&& !e.isShiftDown()){
                            JMFunctions.trace("NOT YET");
                        }else{
                            if(!e.isShiftDown())JMPCImagesViewer.this.select(toSelect);
                            else {
                                if(!JMPCImagesViewer.this.isSelected(toSelect))JMPCImagesViewer.this.addSelection(toSelect);
                                else JMPCImagesViewer.this.removeSelection(ind);
                            }
                        }
                    }
                    
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
    }
    private void addViewListener(){
        this.pnlView.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                //JMPCImagesViewer.this.lblView.setBounds(0, WIDTH, WIDTH, HEIGHT);
                
                //JMFunctions.trace(e.getPreciseWheelRotation()+"");
                JMVec2 imgSize=new JMVec2(JMPCImagesViewer.this.lblView.getIcon().getIconWidth(),JMPCImagesViewer.this.lblView.getIcon().getIconHeight());
                float zoom=0.1f*(float)e.getPreciseWheelRotation();
                int w=imgSize.getIntX();
                int h=imgSize.getIntY();
                w-=Math.ceil(w*zoom);
                h-=Math.ceil(h*zoom);
                JMFunctions.trace(zoom+"");
                JMVec2 size=new JMVec2(w,h);
                int minW=JMPCImagesViewer.this.width-JMPCImagesViewer.this.width/4;
                int minH=JMPCImagesViewer.this.height-JMPCImagesViewer.this.height/4;
                if(size.getIntX()<minW || size.getIntX()>3000 || size.getIntY()<minH || size.getIntX()>3000)return;
                List<JMVec2> scaled=JMFunctions.scaledSize(imgSize, size, JMFunctions.SCALE_FIT);
                ImageIcon ico=new ImageIcon(JMPCImagesViewer.this.imgView.getScaledInstance(scaled.get(0).getIntX(), scaled.get(0).getIntY(), Image.SCALE_SMOOTH));
                JMPCImagesViewer.this.lblView.setIcon(ico);
                JMPCImagesViewer.this.lblView.setBounds(scaled.get(1).getIntX(), scaled.get(1).getIntY(), scaled.get(0).getIntX(), scaled.get(0).getIntY());
            }
        });
    }
    
    private void sortSelection(){
        boolean isSorted;
        for (int i=0; i<this.selected.size();i++) {
            isSorted = true;
            for (int j=1;j<(this.selected.size()-i);j++) {
                if (this.selected.get(j-1) > this.selected.get(j)) {
                    this.swapSelection(this.selected.get(j-1), this.selected.get(j));
                    isSorted = false;
                }
            }
            // is sorted? then break it, avoid useless loop.
            if (isSorted) break;
        }
    }
    private void swapSelection(int indexValue, int indexValue2){
        int tmp=indexValue;
        int i1=this.selectionIndex(indexValue);
        int i2=this.selectionIndex(indexValue2);
        if(i1==-1 || i2==-1)return;
        this.selected.set(i1, this.selected.get(i2));
        this.selected.set(i2, tmp);
        
    }
    private int selectionIndex(int indexValue){
        for(int i=0;i<this.selected.size();i++ ){
            if(this.selected.get(i)==indexValue)return i;
        }
        return -1;
    }
    private boolean selectionContains(int indexValue){
        for(int i=0;i<this.selected.size();i++){
            if(this.selected.get(i)==indexValue)return true;
        }
        return false;
    }
    
    private void moveRight(int to){
        List<String> newPaths=null;
        if(this.paths!=null){
            newPaths=new ArrayList();
            for(int i=0;i<this.paths.size();i++){
                if(i==to+1){
                    for(int j=0;j<this.selected.size();j++){
                        newPaths.add(this.paths.get(this.selected.get(j)));
                    }
                }
                if(!this.selectionContains(i))newPaths.add(this.paths.get(i));
            }
        }
        List<Image> newImages=new ArrayList();
        for(int i=0;i<this.images.size();i++){
            if(i==to+1){
                for(int j=0;j<this.selected.size();j++){
                    newImages.add(this.images.get(this.selected.get(j)));
                }
            }
            if(!this.selectionContains(i))newImages.add(this.images.get(i));
        }
        
        List<JLabel> newLblImgs=new ArrayList();
        for(int i=0;i<this.lblImgs.size();i++){
            if(i==to+1){
                for(int j=0;j<this.selected.size();j++){
                    newLblImgs.add(this.lblImgs.get(this.selected.get(j)));
                }
            }
            if(!this.selectionContains(i))newLblImgs.add(this.lblImgs.get(i));
        }
        
        List<JPanel> newPnlImgs=new ArrayList();
        for(int i=0;i<this.pnlImgs.size();i++){
            if(i==to+1){
                for(int j=0;j<this.selected.size();j++){
                    newPnlImgs.add(this.pnlImgs.get(this.selected.get(j)));
                }
            }
            if(!this.selectionContains(i))newPnlImgs.add(this.pnlImgs.get(i));
        }
        
        this.paths=newPaths;
        this.images=newImages;
        this.lblImgs=newLblImgs;
        this.pnlImgs=newPnlImgs;
        
        this.pnlList.removeAll();
        for(JPanel p:this.pnlImgs){
            this.pnlList.add(p);
        }
        
        List<Integer> newSelected=new ArrayList();
        int start=to+1;
        for(int i=0;i<this.selected.size();i++){
            newSelected.add(start++);
        }
        this.selected=newSelected;
        for(int i=0;i<this.selected.size();i++){
            if(i==0)this.select(this.selected.get(i));
            else this.addSelection(this.selected.get(i));
        }
        this.addThumbsListener();
    }
}
