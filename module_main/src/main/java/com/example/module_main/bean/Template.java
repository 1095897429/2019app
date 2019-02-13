package com.example.module_main.bean;

import com.zl.common_base.base.bean.BaseBean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Template extends BaseBean {

    private String title;//标题
    private String describe;//表述
    private String url;//跳转

}
