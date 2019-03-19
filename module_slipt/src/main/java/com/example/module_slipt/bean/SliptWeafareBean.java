package com.example.module_slipt.bean;

import com.zl.common_base.base.bean.BaseBean;

/**
 * 福利社
 */
public class SliptWeafareBean extends BaseBean {

    private String title;
    private String link;
    private String pic;
    private String benefit_product;
    private String benefit_tags;
    private String benefit_description;
    private int benefit_nums;
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setLink(String link) {
        this.link = link;
    }
    public String getLink() {
        return link;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
    public String getPic() {
        return pic;
    }

    public void setBenefit_product(String benefit_product) {
        this.benefit_product = benefit_product;
    }
    public String getBenefit_product() {
        return benefit_product;
    }

    public void setBenefit_tags(String benefit_tags) {
        this.benefit_tags = benefit_tags;
    }
    public String getBenefit_tags() {
        return benefit_tags;
    }

    public void setBenefit_description(String benefit_description) {
        this.benefit_description = benefit_description;
    }
    public String getBenefit_description() {
        return benefit_description;
    }

    public void setBenefit_nums(int benefit_nums) {
        this.benefit_nums = benefit_nums;
    }
    public int getBenefit_nums() {
        return benefit_nums;
    }


}
