package com.amar.poi;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: mxding
 * @Date: 2019-01-17 17:37
 */
public class PoiUtil {

    public static void htmlToWord(String htmlStr, String outputPath) throws IOException {
        //System.out.println(htmlStr);
        List<Map<String, String>> imgList = findImgList(htmlStr);
        if (imgList != null && imgList.size() > 0) {
            for (int i = 0; i < imgList.size(); i++) {
                Map<String, String> imgMap = imgList.get(i);
                htmlStr = htmlStr.replaceFirst(imgMap.get("outerHtml"), "{image" + i + "}");
            }
        }
        POIFSFileSystem poifs = new POIFSFileSystem();
        poifs.createDocument(new ByteArrayInputStream(htmlStr.getBytes()), "WordDocument");
        FileOutputStream out1 = new FileOutputStream(outputPath);
        poifs.writeFilesystem(out1);
        out1.close();
        poifs.close();

        FileInputStream inputStream = new FileInputStream(outputPath);
        HWPFDocument doc = new HWPFDocument(inputStream);
        inputStream.close();
        Range range = doc.getRange();
        range.replaceText("{image0}", "ppppp");
        OutputStream os = new FileOutputStream(outputPath);
        doc.write(os);
        /*XWPFDocument doc = new XWPFDocument(new FileInputStream(outputPath));
        FileOutputStream output = new FileOutputStream(outputPath);
        doc.write(output);
        output.close();
        doc.close();*/
    }

    private static List<Map<String, String>> findImgList(String htmlStr) {
        List<Map<String, String>> imgList = new ArrayList();
        Document document = Jsoup.parse(htmlStr);
        Elements imgElements = document.select("img");
        if (imgElements != null && imgElements.size() > 0) {
            for (Element imgElem : imgElements) {
                Map imgMap = new HashMap();
                imgMap.put("height", imgElem.attr("height"));
                imgMap.put("width", imgElem.attr("width"));
                imgMap.put("outerHtml", imgElem.outerHtml());
                imgMap.put("src", imgElem.attr("src"));
                imgList.add(imgMap);
            }
        }
        return imgList;
    }

    public static void main(String[] args) throws Exception {
        File htmlFile = new File(PoiUtil.class.getResource("/test.html").getPath());
        String htmlStr = FileUtils.readFileToString(htmlFile);
        String outputPath = "F:\\idea\\workspace\\demo4j\\amar\\output\\image.doc";
        PoiUtil.htmlToWord(htmlStr, outputPath);
    }
}
