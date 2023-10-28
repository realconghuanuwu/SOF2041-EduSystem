/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author huanl
 */
public class MaHoa {
    public static String hashPassword(String password, String algorithm) {
        if(password != null) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
                byte[] hashedBytes = messageDigest.digest(password.getBytes());

                // Chuyển đổi mảng byte thành chuỗi hex
                StringBuilder hexString = new StringBuilder();
                for (byte hashedByte : hashedBytes) {
                    String hex = Integer.toHexString(0xff & hashedByte);
                    if (hex.length() == 1) {
                        hexString.append('0');
                    }
                    hexString.append(hex);
                }

                return hexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        } 
        return null;
    }
}
