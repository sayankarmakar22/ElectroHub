package com.sayan.ElectroHub.Helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LoggerHelper {

    public static void writeLogToFile(String msg) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/com/sayan/ElectroHub/Logs/log.txt",true));
        writer.newLine();
        writer.write(msg);
        writer.close();
    }
}
