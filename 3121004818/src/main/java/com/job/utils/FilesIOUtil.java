package com.job.utils;

import org.apache.commons.io.FileUtils;

import java.io.*;

/**
 * @author czh
 * @version 1.0.0
 * 2023/9/12 13:41
 */

//读取文件工具类
public class FilesIOUtil {

    private FilesIOUtil() {
    }

    //读取文件
    public static String readFile(String name) throws IOException {
        /*
         * name：文件名
         * */
        File file = new File(name);
        if (!file.exists()) {
            //抛出异常
            throw new FileNotFoundException();

        }
        return FileUtils.readFileToString(file);
    }

    //写入文件
    public static void writeFile(String name, String str) throws IOException {
        /*
        name:文件名
        str：写入的字符串
         */
        File file = new File(name);
        if (!file.exists())
            //抛出异常
            throw new FileNotFoundException();
        FileUtils.writeStringToFile(file, str+"\n", true);
    }
}
