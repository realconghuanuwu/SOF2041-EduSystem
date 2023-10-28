/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author huanl
 */
public class XImage {
    public static Image getAppIcon() {
//        URL url = XImage.class.getResource("src/Images/logo.jpg");
        String url = "src/Images/logo.jpg";
        return new ImageIcon(url).getImage();
    }
    
    public static void save(File src) {
        //tạo folder logos nếu chưa tồn tại
        File dst = new File("logos",src.getName());
        if(!dst.getParentFile().exists()) {
            dst.getParentFile().mkdirs();
        }
        //copy file vào folder logos
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    };
    
    public static ImageIcon read(String fileName) {
        File path = new File("logos",fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
    
    public static ImageIcon read(String url, String fileName) {
        File path = new File(url,fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
}
