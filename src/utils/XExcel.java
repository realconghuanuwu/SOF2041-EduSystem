/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author huanl
 */
public class XExcel {
    public static void openFile(String file){
        try{
            File path = new File(file);
            Desktop.getDesktop().open(path);
        }catch(IOException ioe){
            System.out.println(ioe);
        }
    }
    
    public static void exportToExcel(JTable tbl, String sheetName) {
        try{
           JFileChooser jFileChooser = new JFileChooser();
           jFileChooser.showSaveDialog(null);
           File saveFile = jFileChooser.getSelectedFile();
           
           if(saveFile != null){
               saveFile = new File(saveFile.toString()+".xlsx");
               Workbook wb = new XSSFWorkbook();
               Sheet sheet = wb.createSheet(sheetName);
               
               Row rowCol = sheet.createRow(0);
               for(int i=0;i<tbl.getColumnCount();i++){
                   Cell cell = rowCol.createCell(i);
                   cell.setCellValue(tbl.getColumnName(i));
               }
               
               for(int j=0;j<tbl.getRowCount();j++){
                   Row row = sheet.createRow(j+1);
                   for(int k=0;k<tbl.getColumnCount();k++){
                       Cell cell = row.createCell(k);
                       if(tbl.getValueAt(j, k)!=null){
                           cell.setCellValue(tbl.getValueAt(j, k).toString());
                       }
                   }
               }
               FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
               wb.write(out);
               wb.close();
               out.close();
               openFile(saveFile.toString());
           }else{
               JOptionPane.showMessageDialog(null,"Xuất file excel thất bại!");
           }
       }catch(FileNotFoundException e){
           System.out.println(e);
       }catch(IOException io){
           System.out.println(io);
       }
    }
    
    public static void print(JTable tbl) {
        MessageFormat header = new MessageFormat("");
        MessageFormat footer = new MessageFormat("");
        
        try {
            PrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
            set.add(OrientationRequested.LANDSCAPE);
            tbl.print(JTable.PrintMode.FIT_WIDTH,header,footer, true, set, true);
            MsgBox.alert(null, "In thành công!");
        } catch (java.awt.print.PrinterException e) {
            MsgBox.alert(null, "In thất bại!");
        }
    }
}
