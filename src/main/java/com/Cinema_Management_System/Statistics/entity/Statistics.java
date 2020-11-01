package com.Cinema_Management_System.Statistics.entity;

import java.util.Date;

/**
 * @Description 管理员信息表
 * @Auther GaoYi
 * @Date 2020/10/19 1:01 下午
 * * @param day        时间
 * * @param money      总金额
 */
public class Statistics {
        private Date day;
        private float money;

        public Statistics() {
        }

        public Statistics(Date day, float money) {
                this.day = day;
                this.money = money;
        }

        public Date getDay() {
                return day;
        }

        public void setDay(Date day) {
                this.day = day;
        }

        public float getMoney() {
                return money;
        }

        public void setMoney(float money) {
                this.money = money;
        }

        @Override
        public String toString() {
                return "Statistics{" +
                        "day=" + day +
                        ", money=" + money +
                        '}';
        }
}
