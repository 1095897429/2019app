package com.example.module_slipt.ui.fragment;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_slipt.R;
import com.example.module_slipt.R2;
import com.example.module_slipt.adapter.SliptDayTaskAdapter;
import com.example.module_slipt.adapter.SliptLimitRedAdapter;
import com.example.module_slipt.adapter.SliptWeafareAdapter;
import com.example.module_slipt.bean.SliptTaskBean;
import com.example.module_slipt.bean.SliptMarquee;
import com.example.module_slipt.bean.SliptWeafareBean;
import com.example.module_slipt.constants.SliptConstants;
import com.example.module_slipt.dialog.RewardShowDialog;
import com.example.module_slipt.dialog.SliptTaskShowDialog;
import com.example.module_slipt.view.MarqueeView;
import com.zl.common_base.base.BaseFragment;
import com.zl.common_base.constants.ARouterConfig;
import com.zl.common_base.utils.DateUtils;
import com.zl.common_base.utils.PxUtils;
import com.zl.common_base.utils.SPUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;


@Route(path = ARouterConfig.SLIPT_FRAGMENT)
public class SliptFragment extends BaseFragment {


    @BindView(R2.id.timecount1)
    LinearLayout timecount1;
    @BindView(R2.id.hour1)
    TextView hour1;
    @BindView(R2.id.hour2)
    TextView hour2;
    @BindView(R2.id.min1)
    TextView min1;
    @BindView(R2.id.min2)
    TextView min2;
    @BindView(R2.id.se1)
    TextView se1;
    @BindView(R2.id.se2)
    TextView se2;

    @BindView(R2.id.timecount2)
    RelativeLayout timecount2;
    @BindView(R2.id.min1_next)
    TextView min1_next;
    @BindView(R2.id.min2_next)
    TextView min2_next;
    @BindView(R2.id.se1_next)
    TextView se1_next;
    @BindView(R2.id.se2_next)
    TextView se2_next;


    @BindView(R2.id.time_count_down)
    TextView time_count_down;//第几场倒计时

    @BindView(R2.id.time_count_down_next)
    TextView time_count_down_next;//下一场倒计时


    @BindView(R2.id.gone_view)
    ImageView gone_view;

    @BindView(R2.id.rl_hint_text)
    RelativeLayout rl_hint_text;

    @BindView(R2.id.text_weafare)
    TextView text_weafare;

    @BindView(R2.id.nestedScrollView)
    NestedScrollView nestedScrollView;


    @BindView(R2.id.marqueeView)
    MarqueeView marqueeView;
    List<SliptMarquee> mSliptMarquees;

    @BindView(R2.id.rv_horizontal)
    RecyclerView rv_horizontal;
    LinearLayoutManager layoutManager;
    SliptLimitRedAdapter mSliptLimitRedAdapter;
    List<SliptTaskBean> mLimitBeanList;
    SliptTaskBean mLimitBean;


    @BindView(R2.id.rv_vertical)
    RecyclerView rv_vertical;
    LinearLayoutManager verticalLayoutManager;
    SliptDayTaskAdapter mSliptDayTaskAdapter;
    List<SliptTaskBean> mSliptTaskBeanList;
    SliptTaskBean mSliptTaskBean;

    @BindView(R2.id.rv_weafare)
    RecyclerView rv_weafare;
    LinearLayoutManager weafareLayoutManager;
    SliptWeafareAdapter mSliptWeafareAdapter;
    List<SliptWeafareBean> mSliptWeafareBeanList;
    SliptWeafareBean mSliptWeafareBean;

    Timer timer = new Timer();
    int totalTime = 2460;//单位为秒

    @Override
    protected int getLayoutId() {
        return R.layout.slipt_fragment;
    }

    @Override
    protected void initView() {
        initStatusBar();
        initGuide1();
        bind();
        initTimerCount();
    }

    private void initStatusBar() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
//            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(null != timer){
            timer.cancel();
            timer = null;
        }
    }

    private void initTimerCount() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        totalTime--;

                        Map<String,String> myMap = formatDownTimeByMap(totalTime);
                        setTimeCount(myMap);

                        if(totalTime <=0 ){
                            Log.d("heihei","倒计时已跑完");
                            timer.cancel();
                        }
                    }
                });
            }
        },0,1000);// 参数：0，延时0秒后执行; 1000，每隔1秒执行1次task。
    }



    Map<String,String> mMap = new HashMap<>();
    int hour = 0;
    int min = 0;
    int se = 0;
    public  Map<String,String> formatDownTimeByMap(int second){
//        mMap.clear();
        if(second >= 3600){
            hour = second / 3600;//获取小时
            int temp = second % 3600;
            if(temp != 0){
                if(temp > 60){//👆的262秒
                    min = temp / 60;
                    if(temp % 60 != 0){
                        se = temp % 60;
                    }
                }else{
                    se = temp;
                }
            }
        }else{
            min = second / 60;
            if(second % 60 != 0){
                se = second % 60;
            }
        }

        if(hour == 0){//如果hour为空则不显示
            mMap.put("hour1","0");
            mMap.put("hour2","0");
        }else if(hour < 10){
            mMap.put("hour1","0");
            mMap.put("hour2",hour + "'");
        }else{
            mMap.put("hour1", String.valueOf(hour).substring(0,1) + "");
            mMap.put("hour2", String.valueOf(hour).substring(1,2) + "");
        }

        if(min == 0){
            mMap.put("min1","0");
            mMap.put("min2","0");
        }else if(min < 10){
            mMap.put("min1","0");
            mMap.put("min2", min + "");
        }else{
            mMap.put("min1", String.valueOf(min).substring(0,1) + "");
            mMap.put("min2", String.valueOf(min).substring(1,2) + "");
        }


        if(se < 10){
            mMap.put("se1", "0");
            mMap.put("se2", se + "");
        }else{
            mMap.put("se1", String.valueOf(se).substring(0,1) + "");
            mMap.put("se2", String.valueOf(se).substring(1,2) + "");
        }


        return mMap;
    }


    //设置倒计时
    private void setTimeCount(Map<String,String> map) {

        if(timecount1.getVisibility() == View.VISIBLE){
            hour1.setText(map.get("hour1"));
            hour2.setText(map.get("hour2"));
            min1.setText(map.get("min1"));
            min2.setText(map.get("min2"));
            se1.setText(map.get("se1"));
            se2.setText(map.get("se2"));
        }

        if(timecount2.getVisibility() == View.VISIBLE){
            min1_next.setText(map.get("min1"));
            min2_next.setText(map.get("min2"));
            se1_next.setText(map.get("se1"));
            se2_next.setText(map.get("se2"));
        }

    }

    private void initGuide1() {
        //1.判读是否是第一次进入，默认为true，如果不是，隐藏给予的图片
        boolean isFristGuide = (boolean) SPUtils.get(getActivity(),SliptConstants.FRIST_GUIDE_IN_SLIPT,true);
        if(isFristGuide){
            gone_view.setVisibility(View.VISIBLE);
            //引导1 -- 提示左滑
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.slipt_first_guide_new,null);
            final GuideView guideview ;
            guideview = GuideView.Builder
                    .newInstance(getActivity())
                    .setTargetView(gone_view)  //设置目标view
                    .setCustomGuideView(view)    //设置图片
                    .setOffset(PxUtils.dp2px(getActivity(),94 / 2 + 10 + 30),
                            PxUtils.dp2px(getActivity(),-142 / 2)) //偏移量 +10是margin 30是自定义
                    .setDirction(GuideView.Direction.RIGHT)   //引导布局方向
                    .setShape(GuideView.MyShape.RECTANGULAR)   //矩形
                    .setRadius(4)//圆角度数
                    .setBgColor(getResources().getColor(R.color.shadow))   //背景颜色
                    .build();
            guideview.setOnclickListener(new GuideView.OnClickCallback() {
                @Override
                public void onClickedGuideView() {
                    guideview.hide();
                    initGuide2();
                    SPUtils.put(getActivity(),SliptConstants.FRIST_GUIDE_IN_SLIPT,false);
                }
            });
            guideview.show();
        }
    }


    private void initGuide2() {
        //引导1 -- 提示左滑
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.slipt_second_guide_new,null);
        final GuideView guideview ;
        guideview = GuideView.Builder
                .newInstance(getActivity())
                .setTargetView(gone_view)  //设置目标view
                .setCustomGuideView(view)    //设置图片
                .setOffset(PxUtils.dp2px(getActivity(),0),
                        PxUtils.dp2px(getActivity(),142 / 2 - 20)) //偏移量
                .setDirction(GuideView.Direction.RIGHT_BOTTOM)   //引导布局方向
                .setShape(GuideView.MyShape.RECTANGULAR)   //矩形
                .setRadius(4)//圆角度数
                .setBgColor(getResources().getColor(R.color.shadow))   //背景颜色
                .build();
        guideview.setOnclickListener(new GuideView.OnClickCallback() {
            @Override
            public void onClickedGuideView() {
                guideview.hide();
                gone_view.setVisibility(View.GONE);
            }
        });
        guideview.show();
    }

    private void initMarquee() {
        marqueeView.removeAllViews();
        mSliptMarquees = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            SliptMarquee sliptMarquee = new SliptMarquee();
            String desc = "186****1823 抢到了1元阅读赚钱" + i + " " + DateUtils.getTimeStateNew("1552974997");
            sliptMarquee.setDesc(desc);
            mSliptMarquees.add(sliptMarquee);
        }
        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View textView) {
            }
        });
        marqueeView.startWithList(mSliptMarquees);
    }




    private void bind() {
        initMarquee();
        initHorizontalLayoutManager();
        initDayTaskLayoutManager();
        initWeafareLayoutManager();
        initEvent();
    }




    /** -------------------------------------------------------------------------------- */

    //初始化水平管理器
    private void initHorizontalLayoutManager() {
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //设置布局管理器
        rv_horizontal.setLayoutManager(layoutManager);
        //初始化适配器
        initHorizotalAdapter();
        //一行代码开启动画 默认CUSTOM动画
        mSliptLimitRedAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
    }


    private void initHorizotalAdapter() {
        mLimitBeanList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            mLimitBean = new SliptTaskBean();

            if(i == 2){
                mLimitBean.setDone_status(1);
            }else if( i == 5){
                mLimitBean.setDone_status(2);
            }else
                mLimitBean.setDone_status(0);

            mLimitBean.setNum(10);
            mLimitBean.setDone_num(1);
            mLimitBean.setTitle("完成逛逛");
            mLimitBeanList.add(mLimitBean);
        }
        mSliptLimitRedAdapter = new SliptLimitRedAdapter(mLimitBeanList);
        rv_horizontal.setAdapter(mSliptLimitRedAdapter);
    }



    //初始化日常任务管理器
    private void initDayTaskLayoutManager() {
        verticalLayoutManager = new LinearLayoutManager(getActivity());
        verticalLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //设置布局管理器
        rv_vertical.setLayoutManager(verticalLayoutManager);
        //初始化适配器
        initDayTaskAdapter();
        //一行代码开启动画 默认CUSTOM动画
        mSliptDayTaskAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
    }


    private void initDayTaskAdapter() {
        mSliptTaskBeanList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            mSliptTaskBean = new SliptTaskBean();
            mSliptTaskBean.setTitle("观看新手视频" + i);
            mSliptTaskBeanList.add(mSliptTaskBean);
        }
        mSliptDayTaskAdapter = new SliptDayTaskAdapter(mSliptTaskBeanList);
        rv_vertical.setAdapter(mSliptDayTaskAdapter);
    }

    //初始化福利社管理器
    private void initWeafareLayoutManager() {
        weafareLayoutManager = new LinearLayoutManager(getActivity());
        weafareLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //设置布局管理器
        rv_weafare.setLayoutManager(weafareLayoutManager);
        //初始化适配器
        initWeafareAdapter();
        //一行代码开启动画 默认CUSTOM动画
        mSliptWeafareAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
    }

    private void initWeafareAdapter() {
        mSliptWeafareBeanList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            mSliptWeafareBean = new SliptWeafareBean();
            mSliptWeafareBean.setTitle("观看新手视频" + i);
            mSliptWeafareBeanList.add(mSliptWeafareBean);
        }
        mSliptWeafareAdapter = new SliptWeafareAdapter(mSliptWeafareBeanList);
        rv_weafare.setAdapter(mSliptWeafareAdapter);
    }


    private void initEvent() {
        //福利社item点击事件
        mSliptWeafareAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.d("heihei:", mSliptWeafareBeanList.get(position).getTitle());


            }
        });

        //限时红包item点击事件
        mSliptLimitRedAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.d("heihei",mLimitBeanList.get(position).getDone_status() + "");

                SliptTaskBean bean = mLimitBeanList.get(position);
                //判断红包🧧状态 0-去完成；1-领取奖励；2-已完成
                if(bean.getDone_status() == 0){
                    Log.d("heihei","我去做任务了,别拦我!");
                    SliptTaskShowDialog sliptTaskShowDialog = new SliptTaskShowDialog(getActivity())
                            .builder();
                    sliptTaskShowDialog.show();
                }else if(bean.getDone_status() == 1){
                    Log.d("heihei","当前状态为可领取，弹出框提示！，如果没有网络，啥都不做");
                    RewardShowDialog rewardShowDialog = new RewardShowDialog(getActivity())
                            .builder()
                            .setCanceledOnTouchOutside(false);
                    rewardShowDialog.show();
                }else if(bean.getDone_status() == 2){
                    Log.d("heihei","当前状态为任务已领取完，弹出view让用户点击福利社,并，且定位到福利社开启可领取状态！");


                    int location[] = new int[2];
                    text_weafare.getLocationOnScreen(location);
                    int y = location[1];
                    Log.d("heihei",y + " ");

                    nestedScrollView.scrollTo(0,y - PxUtils.dp2px(getActivity(),10));//margin的10距离

                    rl_hint_text.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            rl_hint_text.setVisibility(View.GONE);
                        }
                    },3000);
                }


            }
        });

        //日常任务子项点击事件 1.阅读新闻
        mSliptDayTaskAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Log.d("heihei:", mSliptTaskBeanList.get(position).getTitle());
                SliptTaskBean bean = mSliptTaskBeanList.get(position);
                int doneStatus = bean.getDone_status();
                String type = bean.getType();
                //判断日常任务状态 0-去完成；1-领取奖励；2-已完成
                if(doneStatus == 0){
                    doingTask(type);
                }else if(doneStatus == 1){

                }else if(doneStatus == 2){

                }

            }
        });
    }

    //日常任务 根据类型去跳转
    private void doingTask(String type) {


    }

}
