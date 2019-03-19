package com.example.module_slipt.bean;

import com.zl.common_base.base.bean.BaseBean;

/***
 * 拆红包奖励实体
 */
public class RewardBean extends BaseBean {

    private String coinNum;//金币数量
    private String imgUrl;//图片路径

    public String getCoinNum() {
        return coinNum;
    }

    public void setCoinNum(String coinNum) {
        this.coinNum = coinNum;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
