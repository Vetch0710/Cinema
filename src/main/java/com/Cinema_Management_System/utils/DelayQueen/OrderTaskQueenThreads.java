package com.Cinema_Management_System.utils.DelayQueen;

import org.apache.log4j.Logger;

import java.util.concurrent.Callable;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * [任务调度系统]
 * <br>
 * [后台守护线程不断的执行检测工作]
 * </p>
 *
 * @author wangguangdong
 * @version 1.0
 * @Date 2015年11月23日14:19:40
 */
public class OrderTaskQueenThreads {
    private ExecutorService executorService;

    private static final Logger LOG = Logger.getLogger(OrderTaskQueenThreads.class);

    public OrderTaskQueenThreads() {
    }

    public OrderTaskQueenThreads(ExecutorService executorService) {
        this.executorService = executorService;
        init();
    }

    private static class LazyHolder {
        private static OrderTaskQueenThreads OrderTaskQueenThreads = new OrderTaskQueenThreads();
    }

    public static OrderTaskQueenThreads getInstance() {
        return LazyHolder.OrderTaskQueenThreads;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    /**
     * 守护线程
     */
    private Thread daemonThread;

    /**
     * 初始化守护线程
     */
    public void init() {
        System.out.println("延时队列启动");
        daemonThread = new Thread(() -> execute());
        daemonThread.setDaemon(true);
        daemonThread.setName("Task Queue Daemon Thread");
        daemonThread.start();
    }

    private void execute() {
        System.out.println("start:" + System.currentTimeMillis());
        while (true) {
            try {
                //从延迟队列中取值,如果没有对象过期则队列一直等待，
                OrderTask orderTask = delayQueue.take();
                if (orderTask != null) {
                    //修改问题的状态
                    System.out.println("获取到任务");
                    Callable<String> task = orderTask.getTask();
                    if (task == null) {
                        continue;
                    }
                    System.out.println("提交任务");
                    executorService.submit(task);
                    LOG.info("[at task:" + task + "]   [Time:" + System.currentTimeMillis() + "]");
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }

    /**
     * 创建一个最初为空的新 DelayQueue
     */
    private DelayQueue<OrderTask> delayQueue = new DelayQueue<>();

    /**
     * 添加任务，
     * time 延迟时间
     * task 任务
     * 用户为问题设置延迟时间
     */
    public void put(long time, Callable<String> task, long orderId) {
        //创建一个任务
        OrderTask k = new OrderTask(time, task, orderId);
        //将任务放在延迟的队列中
        System.out.println("任务加入队列");
        delayQueue.put(k);
    }

    /**
     * 结束订单
     *
     * @param task
     */
    public boolean endTask(OrderTask<Callable<String>> task) {
        System.out.println("任务已取消");
        return delayQueue.remove(task);
    }
}
