package com.example.module_main.bean;

import com.zl.common_base.base.bean.BaseBean;

import lombok.Getter;
import lombok.Setter;

public class Template extends BaseBean {

    private String title;//标题
    private String describe;//表述

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Template(String title, String describe) {
        this.title = title;
        this.describe = describe;
    }
}
