package org.example.util;
import java.io.PrintWriter;
public class FileUtil {
    public static void saveToFile(String content) {
        try (PrintWriter out = new PrintWriter("chat_history.txt")) {
            out.println(content);
        } catch (Exception e) { e.printStackTrace(); }
    }
}