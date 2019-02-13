package com.example.module_main.bean;

import com.zl.common_base.base.bean.BaseBean;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class AppInfo extends BaseBean {

    private int id;
    private int versionName;
    private String describe;
    private String path;

    @Override
    public String toString() {
        return "AppInfo{" +
                "id=" + id +
                ", versionName=" + versionName +
                ", describe='" + describe + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
