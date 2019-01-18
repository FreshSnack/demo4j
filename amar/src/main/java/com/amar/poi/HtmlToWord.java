package com.amar.poi;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @Author: mxding
 * @Date: 2019-01-17 14:47
 */
public class HtmlToWord {

    public static void main(String[] args) throws Exception {

        File htmlFile = new File(HtmlToWord.class.getResource("/test.html").getPath());
        FileInputStream fileInputStream = new FileInputStream(htmlFile);

        String outputPath = "F:\\idea\\workspace\\demo4j\\amar\\out";

        File wordFile = new File(outputPath + "\\html.doc");

        FileOutputStream fileOutputStream = new FileOutputStream(wordFile);
        POIFSFileSystem poifs = new POIFSFileSystem();
        poifs.createDocument(fileInputStream, "WordDocument");
        poifs.writeFilesystem(fileOutputStream);
        poifs.close();
        fileOutputStream.close();
        fileInputStream.close();
    }
}
