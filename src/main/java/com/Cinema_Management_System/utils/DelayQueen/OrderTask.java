package com.Cinema_Management_System.utils.DelayQueen;

import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description class
 * @Auther GaoYi
 * @Date 2020/10/31 4:13 下午
 */
public class OrderTask<T extends Callable<String>> implements Delayed {
    private final long time;

    /**
     * 问题对象
     */
    private final T task;
    private static final AtomicLong atomic = new AtomicLong(0);
    private final long orderId;


    public long getTime() {
        return time;
    }

    public T getTask() {
        return task;
    }

    public static AtomicLong getAtomic() {
        return atomic;
    }

    public long getOrderId() {
        return orderId;
    }

    public OrderTask(long time, T task, long orderId) {
        System.out.println("起始时间为" + orderId);
        System.out.println("设置终止时间为" + time);
        this.time = time;
        this.task = task;
        this.orderId = orderId;
    }

    @Override
    public long getDelay(TimeUnit unit) {
//        return unit.convert(this.time - System.nanoTime(), TimeUnit.NANOSECONDS);
        return this.time - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed other) {
        // TODO Auto-generated method stub
        if (other == this) // compare zero ONLY if same object
            return 0;
        if (other instanceof OrderTask) {
            OrderTask x = (OrderTask) other;
            long diff = time - x.time;
            if (diff < 0)
                return -1;
            else if (diff > 0)
                return 1;
            else if (orderId < x.orderId)
                return -1;
            else
                return 1;
        }
        long d = (getDelay(TimeUnit.NANOSECONDS) - other.getDelay(TimeUnit.NANOSECONDS));
        return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof OrderTask) {
            return ((OrderTask) object).orderId == orderId ? true : false;
        }
        return false;
    }
}
