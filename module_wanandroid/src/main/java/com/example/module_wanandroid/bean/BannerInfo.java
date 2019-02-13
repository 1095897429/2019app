package com.example.module_wanandroid.bean;

import com.zl.common_base.base.bean.BaseBean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BannerInfo extends BaseBean {

    /**
     * desc :
     * id : 18
     * imagePath : http://www.wanandroid.com/blogimgs/00f83f1d-3c50-439f-b705-54a49fc3d90d.jpg
     * isVisible : 1
     * order : 0
     * title : 公众号文章列表强势上线
     * type : 0
     * url : http://www.wanandroid.com/wxarticle/list/409/1
     */

    private int id;
    private String title;
    private String desc;
    private String imagePath;
    private int isVisible;
    private int order;
    private int type;
    private String url;

}
