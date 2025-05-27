package com.duong.ptit_hn_ks2023b_lekhanhduong.connection;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseProperties {

    private static final String FILE_PATH = "D:/Learn/JavaWeb/PTIT_HN_KS2023B_LeKhanhDuong/src/main/resources/database.properties";

    public static Properties load() {
        Properties p = new Properties();
        try (InputStream i = new FileInputStream(FILE_PATH)) {
            p.load(i);
        } catch (Exception e) {
            System.err.println("Lỗi load cấu hình DB" + e.getMessage());
            return null;
        }

        return p;
    }
}
