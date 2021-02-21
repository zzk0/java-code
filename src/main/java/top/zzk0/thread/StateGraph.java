package top.zzk0.thread;

/*
线程状态图:

新建: 新建 Thread 对象, start() 方法将会转移到就绪

就绪: 获取了处理器资源, 转移到运行; 也可以从阻塞转移过来

堵塞: 从运行态转移过来, sleep(), IO 阻塞, 同步锁, synchronized, suspend()

运行: 可以转移到就绪, yield 主动让出处理器资源, 或者线程调度

死亡: 方法执行完成 or stop or 异常 Exception Error

*/

public class StateGraph {
}
