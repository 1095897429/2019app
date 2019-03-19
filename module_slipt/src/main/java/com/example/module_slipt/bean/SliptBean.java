package com.example.module_slipt.bean;

import com.zl.common_base.base.bean.BaseBean;

import java.util.List;

public class SliptBean extends BaseBean {


        private int is_lock;
        private int less_time;//距离下一场拆红包的时间 ：秒
        private int remain_time;//距离本场红包结束的时间 ：秒
        private int packet_num;//限时拆红包第几场
        private int is_new_user;//是否为新用户：0 - 否；1-是
        private List<SliptTaskBean> daily_task;
        private List<SliptTaskBean> packet_task;
        private List<SliptWeafareBean> benefit_adv;
        private List<SliptMarquee> scroll_data;

    public int getIs_lock() {
        return is_lock;
    }

    public void setIs_lock(int is_lock) {
        this.is_lock = is_lock;
    }

    public int getLess_time() {
        return less_time;
    }

    public void setLess_time(int less_time) {
        this.less_time = less_time;
    }

    public int getRemain_time() {
        return remain_time;
    }

    public void setRemain_time(int remain_time) {
        this.remain_time = remain_time;
    }

    public int getPacket_num() {
        return packet_num;
    }

    public void setPacket_num(int packet_num) {
        this.packet_num = packet_num;
    }

    public int getIs_new_user() {
        return is_new_user;
    }

    public void setIs_new_user(int is_new_user) {
        this.is_new_user = is_new_user;
    }

    public List<SliptTaskBean> getDaily_task() {
        return daily_task;
    }

    public void setDaily_task(List<SliptTaskBean> daily_task) {
        this.daily_task = daily_task;
    }

    public List<SliptTaskBean> getPacket_task() {
        return packet_task;
    }

    public void setPacket_task(List<SliptTaskBean> packet_task) {
        this.packet_task = packet_task;
    }

    public List<SliptWeafareBean> getBenefit_adv() {
        return benefit_adv;
    }

    public void setBenefit_adv(List<SliptWeafareBean> benefit_adv) {
        this.benefit_adv = benefit_adv;
    }

    public List<SliptMarquee> getScroll_data() {
        return scroll_data;
    }

    public void setScroll_data(List<SliptMarquee> scroll_data) {
        this.scroll_data = scroll_data;
    }
}
