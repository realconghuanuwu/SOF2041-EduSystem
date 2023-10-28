/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author huanl
 */
public class DataFormat {
    public static String formatCurrency(double number) {
        // Tạo một đối tượng NumberFormat để định dạng số thành định dạng tiền tệ
        NumberFormat currencyFormat = new DecimalFormat("#,###");

        // Biến đổi số thành định dạng tiền tệ
        String formattedNumber = currencyFormat.format(number);

        return formattedNumber;
    }
    
    public static Object formatDiem(Object diem) {
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        return decimalFormat.format(diem);
    }
    
}
