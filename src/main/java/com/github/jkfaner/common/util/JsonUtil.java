package com.github.jkfaner.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author: LiamLee
 * @since: 2022-09-27
 **/
public class JsonUtil {

    /**
     * 获取文件路径
     *
     * @param fileName 文件名
     * @return 路径
     */
    public static String getFilePath(String fileName) {
        return Objects.requireNonNull(JsonUtil.class.getClassLoader().getResource(fileName)).getPath();
    }

    /**
     * 读取json文件，返回json串
     *
     * @param fileName 路径
     * @return 结果
     */
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), StandardCharsets.UTF_8);
            int ch = 0;
            StringBuilder sb = new StringBuilder();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
