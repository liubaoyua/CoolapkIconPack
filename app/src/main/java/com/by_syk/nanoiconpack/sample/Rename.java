package com.by_syk.nanoiconpack.sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by liubaoyua on 2017/9/6.
 */

public class Rename {

    public static void main(String[] args) throws Exception {
        File file = new File("/Users/liubaoyua/test/copy_for_originalwish.xml");

        String xmlFiles = inputStream2String(new FileInputStream(file));
        List<String> list = Arrays.asList(xmlFiles.split("   "));
        System.out.print(list);

        File dir = new File("/Users/liubaoyua/test/test2");
        List<String> result = new ArrayList<>();
        List<String> names = new ArrayList<>();
        for (File child : dir.listFiles()) {
            String name = child.getName().substring(0, child.getName().lastIndexOf("."));
            if (name.length() < 2) {
                continue;
            }
            List<String> lines = new ArrayList<>();
            for (String line : list) {
                if (line.contains(name)) {
                    lines.add(line);
                }
            }
            if (lines.size() > 0) {
                String line = lines.get(0);
                String newName = line.substring(line.indexOf("drawable=\"") + "drawable=\"".length(), line.indexOf("\" />"));
                child.renameTo(new File("/Users/liubaoyua/test/test2/", newName + ".png"));
                result.addAll(lines);
                names.add(newName);
            }
        }

        PrintStream stream = new PrintStream(new FileOutputStream("/Users/liubaoyua/test/test2/xml222"));
        for (String re : result) {
            stream.println(re);
        }
        stream.flush();

        stream = new PrintStream(new FileOutputStream("/Users/liubaoyua/test/test2/xml333"));
        for (String re : names) {
            stream.print("<item drawable=\"");
            stream.print(re);
            stream.println("\" />");
        }
        stream.flush();
    }


    static String inputStream2String(InputStream is) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

    public static void inputstreamtofile(InputStream ins, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        ins.close();
    }
}
