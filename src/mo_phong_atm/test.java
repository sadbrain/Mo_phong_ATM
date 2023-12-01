package mo_phong_atm;

import java.io.IOException;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class test {
    public static void main(String[] args) {
        // Đường dẫn tới tệp tin
        String filePath = "example.txt";
        try {
            // Tạo một đối tượng FileWriter với đường dẫn của tệp tin
            FileWriter fileWriter = new FileWriter(filePath);

            // Viết nội dung vào tệp tin
            fileWriter.write("Hello, this is a sample text written to a file. \n");
            fileWriter.write("Hello, this is a sample text written to a file 2.");
            
            // Đóng FileWriter để giải phóng tài nguyên
            fileWriter.close();

            System.out.println("File has been written successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            // Kiểm tra xem Desktop được hỗ trợ trên hệ thống không
            if (Desktop.isDesktopSupported()) {
                // Lấy đối tượng Desktop
                Desktop desktop = Desktop.getDesktop();

                // Mở tệp tin
                File file = new File(filePath);
                desktop.open(file);

                System.out.println("File has been opened successfully.");

            } else {
                System.out.println("Desktop is not supported on this platform.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}