package com.amar.util;

import java.io.File;
import java.io.InputStream;

/**
 * @author dingmx
 * @date 2019/1/17 20:28
 */
public class AmarUtils {

    public static String getWSPath() {
        return System.getProperty("user.dir") + "/";
    }

    public static String getTmpPath() {
        String tmpPath = getWSPath() + "out/tmp/";
        File tmpFile = new File(tmpPath);
        if(!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        return tmpPath;
    }

    public static String getResourcePath() {
        return AmarUtils.class.getResource("/").getPath();
    }

    public static InputStream getResStream(String fileName) {
        return AmarUtils.class.getResourceAsStream("/" + fileName);
    }

    public static void main(String[] args) {
        System.out.println(AmarUtils.getWSPath());
        System.out.println(AmarUtils.getTmpPath());
        System.out.println(AmarUtils.getResourcePath());
    }
}
