package com.Cinema_Management_System.Arrangement.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Description class
 * @Auther GaoYi
 * @Date 2020/10/28 3:57 下午
 */
@Data
@Accessors(chain = true)
public class UsedTime {
    private Date arrangementTime;
    private String movieTime;

    public UsedTime() {
    }

    public UsedTime(Date arrangementTime, String movieTime) {
        this.arrangementTime = arrangementTime;
        this.movieTime = movieTime;
    }

}
