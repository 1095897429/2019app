package com.zl.common_base.mvp;

import android.content.Context;

/***
 * 所有View的父类,实现在BaseMvpActivity中
 * 1.也可以用契约类实现
 */
public interface IBaseView {
    /** 显示加载框 */
    void showLoading();

    /** 隐藏加载框 */
    void dismissLoading();

    /** 错误数据 */
    void onError(Object tag,String errorMsg);

    /** 空数据 */
    void onEmpty(Object tag);

    /** 上下文 */
    Context getContext();
}
