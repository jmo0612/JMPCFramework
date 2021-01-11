/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thowo.jmpcframework.component;

import com.thowo.jmjavaframework.JMColor;
import java.awt.Color;

/**
 *
 * @author jimi
 */
public class JMPCColor {
    public static Color get(JMColor color){
        Color ret=new Color(color.getRed(),color.getGreen(),color.getBlue(),color.getAlpha());
        switch(color.getIndex()){
            case -2:
                ret=Color.decode(color.getString());
                break;
            case JMColor.black:
                ret=Color.black;
                break;
            case JMColor.blue:
                ret=Color.blue;
                break;
            case JMColor.cyan:
                ret=Color.cyan;
                break;
            case JMColor.darkGray:
                ret=Color.darkGray;
                break;
            case JMColor.gray:
                ret=Color.gray;
                break;
            case JMColor.green:
                ret=Color.green;
                break;
            case JMColor.lightGray:
                ret=Color.lightGray;
                break;
            case JMColor.magenta:
                ret=Color.magenta;
                break;
            case JMColor.orange:
                ret=Color.orange;
                break;
            case JMColor.pink:
                ret=Color.pink;
                break;
            case JMColor.red:
                ret=Color.red;
                break;
            case JMColor.white:
                ret=Color.white;
                break;
            case JMColor.yellow:
                ret=Color.yellow;
                break;
            case JMColor.BLACK:
                ret=Color.BLACK;
                break;
            case JMColor.BLUE:
                ret=Color.BLUE;
                break;
            case JMColor.CYAN:
                ret=Color.CYAN;
                break;
            case JMColor.DARK_GRAY:
                ret=Color.DARK_GRAY;
                break;
            case JMColor.GRAY:
                ret=Color.GRAY;
                break;
            case JMColor.GREEN:
                ret=Color.GREEN;
                break;
            case JMColor.LIGHT_GRAY:
                ret=Color.LIGHT_GRAY;
                break;
            case JMColor.MAGENTA:
                ret=Color.MAGENTA;
                break;
            case JMColor.ORANGE:
                ret=Color.ORANGE;
                break;
            case JMColor.PINK:
                ret=Color.PINK;
                break;
            case JMColor.RED:
                ret=Color.RED;
                break;
            case JMColor.WHITE:
                ret=Color.WHITE;
                break;
            case JMColor.YELLOW:
                ret=Color.YELLOW;
                break;
            default:
                
        }
        return ret;
    }
}
