package top.zzk0.io;

import java.io.File;
import java.nio.file.*;

public class WatchServiceTest {

    public static void main(String[] args) throws Exception {
        File file = new File(".");
        System.out.println(file.getAbsolutePath()); // 说好的 Path 方便呢, 绝对路径都获取不了
        Path path = Paths.get(".");
        WatchService service = FileSystems.getDefault().newWatchService();
        path.register(service, StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE);
        while (true) {
            WatchKey key = service.take();
            for (WatchEvent<?> event : key.pollEvents()) {
                System.out.println(event.context() + " " + event.kind());
            }
            boolean valid = key.reset();
            if (!valid) break;
        }
    }
}
