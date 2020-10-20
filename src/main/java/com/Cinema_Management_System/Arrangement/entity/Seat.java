package com.Cinema_Management_System.Arrangement.entity;

import org.springframework.stereotype.Component;

/**
 * @Description class
 * @Auther GaoYi
 * @Date 2020/10/19 1:42 下午
 * * @param seatId          座位号
 * * @param arrangementId   场次编号
 */

@Component(value = "Seat")
public class Seat {
    private int seatId, arrangementId;

    public Seat() {
    }

    public Seat(int seatId, int arrangementId) {
        this.seatId = seatId;
        this.arrangementId = arrangementId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getArrangementId() {
        return arrangementId;
    }

    public void setArrangementId(int arrangementId) {
        this.arrangementId = arrangementId;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatId=" + seatId +
                ", arrangementId=" + arrangementId +
                '}';
    }
}
