package com.example.module_slipt.bean;

import com.zl.common_base.base.bean.BaseBean;

/**
 * 日常任务 红包任务
 */
public class SliptTaskBean extends BaseBean {

    private int id;
    private int coin;//任务奖励
    private int num;//任务个数
    //任务种类:
    // cpa-试玩任务；
    // game-游戏任务；
    // gaoe-注册任务；
    // news-阅读任务；
    // invite-普通收徒；
    // relative-亲友收徒，
    // fir_login-首次登陆，
    // task_video-视频任务；
    // share -分享；
    // video-新手视频；
    // shake_coin-摇金币；
    // goods-逛逛；
    // exch_one-1元兑换
    private String type;//👆
    private String title;//标题描述
    private String icon;
    private String status;//任务状态：1-上线
    private String task_type;//任务类型：day_task-每日任务；packet_task-拆红包任务
    private int done_num;//已做任务数
    private int done_status;//目前该任务状态：0-去完成；1-领取奖励；2-已完成
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }
    public int getCoin() {
        return coin;
    }

    public void setNum(int num) {
        this.num = num;
    }
    public int getNum() {
        return num;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getIcon() {
        return icon;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    public void setTask_type(String task_type) {
        this.task_type = task_type;
    }
    public String getTask_type() {
        return task_type;
    }

    public void setDone_num(int done_num) {
        this.done_num = done_num;
    }
    public int getDone_num() {
        return done_num;
    }

    public void setDone_status(int done_status) {
        this.done_status = done_status;
    }
    public int getDone_status() {
        return done_status;
    }


}
