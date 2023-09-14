package com.job.utils;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author czh
 * @version 1.0.0
 * 2023/9/12 14:06
 */
public class FilesIOUtilTest extends TestCase {

    @Test
    public void testReadFile() throws IOException {
        //成功打开文件
        FilesIOUtil.readFile("E:\\AllJavaTools\\softwarejob\\txt\\orig.txt");
    }

    @Test
    public void testWriteFile() throws IOException {
        //成功写入文件
        FilesIOUtil.writeFile("E:\\AllJavaTools\\softwarejob\\txt\\123.txt", "dfdfd");
    }

    @Test
    public void testReadFileFail() throws IOException {
        //打开失败
        try {
            String result = FilesIOUtil.readFile("E:\\AllJavaTools\\softwarejob\\txt\\orig123.txt");
            fail();
        } catch (FileNotFoundException e) {
            System.out.println("文件打开失败");
        }
    }

    @Test
    public void testWriteFileFail() throws IOException {
        //写入失败
        try {
            FilesIOUtil.writeFile("E:\\AllJavaTools\\softwarejob\\txt\\123456.txt", "hsdfjds");
            fail();
        } catch (FileNotFoundException e) {
            System.out.println("文件打开失败");
        }
    }
}