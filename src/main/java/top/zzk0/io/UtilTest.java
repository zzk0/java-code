package top.zzk0.io;

/*
Path, Paths, Files 工具类

懒得写了, 路径相关的用 Paths, 文件相关的用 Files, 用到再看了
*/

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UtilTest {

    public static void main(String[] args) {
        Path path = Paths.get(".");
        System.out.println(path.getFileName());
    }
}
