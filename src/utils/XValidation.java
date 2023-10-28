/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author huanl
 */
public class XValidation {
    
    public static boolean isValidNumber(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean isValidDateFormat(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // Không cho phép chuyển đổi ngày không hợp lệ (ví dụ: 31/02/2023)
        
        try {
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    
    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Định dạng số điện thoại ở Việt Nam: 10 hoặc 11 chữ số, bắt đầu bằng 0
        String regex = "0\\d{9,10}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }
    
    public static boolean isValidAlphaWhitespace(String input) {
        // Kiểm tra nếu chuỗi rỗng
        if (input == null || input.isEmpty()) {
            return false;
        }
        
        // Kiểm tra tất cả các ký tự trong chuỗi
        for (char c : input.toCharArray()) {
            if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                return false;
            }
        }
        
        return true;
    }
    
    public static boolean isBirthdayBeforeTodayMinusYears(String ngaySinh, int years) {
        // Định dạng ngày tháng
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Chuyển đổi chuỗi ngày sinh thành đối tượng LocalDate
        LocalDate birthday = LocalDate.parse(ngaySinh, formatter);

        // Lấy ngày hiện tại
        LocalDate today = LocalDate.now();

        // Tính ngày trước khi đủ tuổi
        LocalDate minDate = today.minusYears(years);

        // Kiểm tra ngày sinh có nằm trước ngày tính toán không
        return birthday.isBefore(minDate);
    }
    
    public static boolean isStartDateAfterTodayPlusDays(String ngayKhaiGiang, int days) {
        // Định dạng ngày tháng
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Chuyển đổi chuỗi ngày khai giảng thành đối tượng LocalDate
        LocalDate startDate = LocalDate.parse(ngayKhaiGiang, formatter);

        // Lấy ngày hiện tại
        LocalDate today = LocalDate.now();

        // Tính ngày sau khi khai giảng
        LocalDate minDate = today.plusDays(days);

        // Kiểm tra ngày khai giảng có nằm sau ngày tính toán không
        return startDate.isAfter(minDate);
    }

}
