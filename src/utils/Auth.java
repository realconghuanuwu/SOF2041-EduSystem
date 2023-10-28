/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import Entity.NhanVien;

/**
 *
 * @author huanl
 */
public class Auth {
    public static NhanVien user = null; //duy tri user dang nhap vao he thong
    public static void clear() {
        Auth user = null;
    }
    
    public static boolean isLogin() {
        return Auth.user != null;
    }
    
    public static boolean isManager() {
        return Auth.isLogin()&&user.isVaiTro();
    }
}
