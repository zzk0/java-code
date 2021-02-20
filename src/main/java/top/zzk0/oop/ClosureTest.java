package top.zzk0.oop;

/*
非静态内部类可以被当成一个闭包.
闭包: 是一种能被调用的对象, 保存了创建它的作用域信息

可以用内部类来调用外部类的方法, 从而实现回调
回调: 允许外部使用内部类对象来调用外部类的方法(貌似我这个例子不太符合也...)

输出:
开始播放视频, 不过要先下载才行, 先做点别的, 下好了通知您
开始下载
吃薯片
吃薯片
吃薯片
吃薯片
下载完毕
看视频...
吃薯片
吃薯片
看完视频...
吃薯片
吃薯片
吃薯片
吃薯片
薯片吃完了...溜了溜了
*/

public class ClosureTest {

    public static void main(String[] args) {
        User user = new User("aaa");
        VideoPlayer player = new VideoPlayer(user);
        player.start();
    }

}

class VideoPlayer {
    User user;
    public VideoPlayer(User user) {
        this.user = user;
    }

    void start() {
        System.out.println("开始播放视频, 不过要先下载才行, 先做点别的, 下好了通知您");
        Downloader downloader = new Downloader();
        downloader.start();
        user.eat();
    }

    private void notifyUser() {
        user.watch();
    }

    private class Downloader extends Thread {
        @Override
        public void run() {
            download();
        }

        void download() {
            System.out.println("开始下载");
            try {
                Thread.sleep(5000);
                System.out.println("下载完毕");
                notifyUser();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Downloader getDownloader() {
        return new Downloader();
    }
}

class User {
    String name;
    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    void eat() {
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                System.out.println("吃薯片");
            }
            System.out.println("薯片吃完了...溜了溜了");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    void watch() {
        try {
            System.out.println("看视频...");
            Thread.sleep(2000);
            System.out.println("看完视频...");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}