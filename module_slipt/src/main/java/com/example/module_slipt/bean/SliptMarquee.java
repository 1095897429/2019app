package com.example.module_slipt.bean;


import com.zl.common_base.base.bean.BaseBean;

public class SliptMarquee extends BaseBean {
    private String mobile;
    private String award_time;
    private String desc;


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAward_time() {
        return award_time;
    }

    public void setAward_time(String award_time) {
        this.award_time = award_time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
