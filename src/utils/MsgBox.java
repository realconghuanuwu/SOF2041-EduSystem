/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author huanl
 */
public class MsgBox {
    public static void alert(Component parent, String msg) {
        JOptionPane.showMessageDialog(parent, msg,"Hệ Thống Quản Lý Đào Tạo", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static boolean confirm(Component parent, String msg) {
        int result = JOptionPane.showConfirmDialog(parent, msg,"Hệ Thống Quản Lý Đào Tạo",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }
    
    public static String prompt(Component parent, String msg) {
        return JOptionPane.showInputDialog(parent,msg,"Hệ Thống Quản Lý Đào Tạo",JOptionPane.INFORMATION_MESSAGE);
    }
     
     public static int prompts(Component parent, String msg,  JScrollPane scrollPane) {
         //OptionPane.OK_OPTION có giá trị -1, JOptionPane.CANCEL_OPTION có giá trị 0
         return JOptionPane.showOptionDialog(parent, scrollPane, msg, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
     }
}
