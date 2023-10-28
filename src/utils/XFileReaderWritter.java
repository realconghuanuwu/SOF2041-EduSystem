/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author huanl
 */
public class XFileReaderWritter {

    public static void writter(String content, String url) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(url));
            writer.write(content);
            writer.close();
            System.out.println("Đã xuất ra file thành công.");
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi xuất ra file: " + e.getMessage());
        }
    }

    public static String reader(String url) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(url));
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append("\n"); // Thêm dấu xuống dòng sau mỗi dòng đọc được
            }

            reader.close();

            String fileContent = content.toString();
            return fileContent;
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi đọc file: " + e.getMessage());
        }
        return null;
    }
    
    
    
    public static void printLog(String logMess) {
        String datenowFile = XDate.toString(new Date(), "dd-MM-yyyy") + ".txt";      
        String oldLog = reader("logs/" + datenowFile);
        if(oldLog == null) {
            writter(logMess, "logs/"+ datenowFile);
            return;
        }
        writter(oldLog + logMess, "logs/"+ datenowFile);
    }
}
