package icu.chzqz.foj.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

public class CmdUtil {
    private static Map<String, String[]> compileMap,runMap,envMap;
    private static Map<String,String> fileName, fileOutName;
    static {
        compileMap = new HashMap<>();
        runMap = new HashMap<>();
        envMap = new HashMap<>();
        fileName = new HashMap<>();
        fileOutName = new HashMap<>();

        compileMap.put("C",new String[]{"gcc","temp.c","-o","temp"});
        compileMap.put("C++",new String[]{"g++","temp.cpp","-o","temp"});
        compileMap.put("Java",new String[]{"javac","Temp.java"});

        runMap.put("C",new String[]{"temp"});
        runMap.put("C++",new String[]{"temp"});
        runMap.put("Java",new String[]{"java","Temp"});

        envMap.put("C",new String[]{"PATH=/usr/bin:/bin"});
        envMap.put("C++",new String[]{"PATH=/usr/bin:/bin"});
        envMap.put("Java",new String[]{"PATH=/usr/bin:/bin"});

        fileName.put("C","temp.c");
        fileName.put("C++","temp.cpp");
        fileName.put("Java","Temp.java");

        fileOutName.put("C","temp");
        fileOutName.put("C++","temp");
        fileOutName.put("Java","Temp.class");
    }

    public static String[] getCompileArgs(String lang){
        return compileMap.getOrDefault(lang,null);
    }
    public static String[] getRunArgs(String lang){
        return runMap.getOrDefault(lang,null);
    }
    public static String[] getEnv(String lang){
        return envMap.getOrDefault(lang,null);
    }
    public static String getFileName(String lang){
        return fileName.getOrDefault(lang,null);
    }
    public static String getFileOutName(String lang){
        return fileOutName.getOrDefault(lang,null);
    }
}
