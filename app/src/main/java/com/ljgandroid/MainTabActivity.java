package com.ljgandroid;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.ljgandroid.fragment.FiveFragment;
import com.ljgandroid.fragment.FourFragment;
import com.ljgandroid.fragment.OneFragment;
import com.ljgandroid.fragment.ThreeFragment;
import com.ljgandroid.fragment.TwoFragment;
import com.ljgandroid.widget.BadgeView;
import com.ljgandroid.widget.BottomTabView;
import com.ljganswer.R;

import butterknife.BindView;
import q.rorbin.badgeview.QBadgeView;

public class MainTabActivity extends BaseActivity implements View.OnClickListener, BottomTabView.clickTouchListener
{
    private FragmentManager mFragmentManager;
    private Fragment mOneFragment;
    private Fragment mTwoFragment;
    private Fragment mThreeFragment;
    private Fragment mFourFragment;
    private Fragment mFiveFragment;

    @BindView(R.id.content)
    FrameLayout frameLayout;

    @BindView(R.id.radio_button_home)
    RadioButton mRadioButtonHome;

    @BindView(R.id.radio_button_discovery)
    RadioButton mRadioButtonDis;

    @BindView(R.id.radio_button_attention)
    RadioButton mRadioButtonAtt;

    @BindView(R.id.radio_button_profile)
    RadioButton mRadioButtonPro;

    @BindView(R.id.main_tab_person)
    RadioButton mRadioButtonPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView()
    {
        return R.layout.activity_main_tab;
    }

    @Override
    protected void initWidget()
    {
        super.initWidget();
        mFragmentManager = getSupportFragmentManager();
        BottomTabView.setClickTouchListener(this);

        /*QBadgeView qBadgeView=new QBadgeView(this);
        qBadgeView.setBadgeNumber(11);
        qBadgeView.setBadgeGravity(Gravity.END | Gravity.TOP);
        qBadgeView.bindTarget(mRadioButtonHome).setBadgeNumber(12);*/
    }

    @Override
    public void Click(int index)
    {
        switch (index)
        {
            case 0:
                setTabSelected(0);
                break;
            case 1:
                setTabSelected(1);
                break;
            case 2:
                setTabSelected(2);
                break;
            case 3:
                setTabSelected(3);
                break;
            case 4:
                setTabSelected(4);
                break;
        }
    }


    @Override
    protected void initData()
    {
        super.initData();
        setTabSelected(0);
    }

    @Override
    public void onClick(View v)
    {
    }

    private void setObjectAnimator(RadioButton radioButton)
    {
        AnimatorSet animatorSetsuofang = new AnimatorSet();//组合动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(radioButton, "scaleX", 0.9f,1f );
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(radioButton, "scaleY", 0.9f,1f );
        animatorSetsuofang.setDuration(400);
        animatorSetsuofang.setInterpolator(new DecelerateInterpolator());
        animatorSetsuofang.play(scaleX).with(scaleY);//两个动画同时开始
        animatorSetsuofang.start();
    }

    @Override
    public void onAttachFragment(Fragment fragment)
    {
        // TODO Auto-generated method stub
        super.onAttachFragment(fragment);

        if (mOneFragment == null && fragment instanceof OneFragment)
        {
            mOneFragment = fragment;

        } else if (mTwoFragment == null && fragment instanceof TwoFragment)
        {
            mTwoFragment = fragment;

        } else if (mThreeFragment == null && fragment instanceof ThreeFragment)
        {
            mThreeFragment = fragment;

        } else if (mFourFragment == null && fragment instanceof FourFragment)
        {
            mFourFragment = fragment;

        } else if (mFiveFragment == null && fragment instanceof FiveFragment)
        {
            mFiveFragment = fragment;
        }
    }

    /**
     * 根据传入的index参数显示对应的tab页
     *
     * @param index tab下标
     */
    private void setTabSelected(int index)
    {
        // 每次选中先清楚上次选中状态
        //clearSelected();
        // 开启一个事务
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        // 隐藏所有fragment，防止多个fragment显示在页面的情况
        hideFragments(transaction);

        switch (index)
        {
            case 0:
                mRadioButtonHome.setChecked(true);
                mRadioButtonDis.setChecked(false);
                mRadioButtonAtt.setChecked(false);
                mRadioButtonPro.setChecked(false);
                if (mOneFragment == null)
                {
                    mOneFragment = OneFragment.newInstance("", "");
                    transaction.add(R.id.content, mOneFragment);
                } else
                {
                    transaction.show(mOneFragment);
                }
                break;
            case 1:
                mRadioButtonHome.setChecked(false);
                mRadioButtonDis.setChecked(true);
                mRadioButtonAtt.setChecked(false);
                mRadioButtonPro.setChecked(false);
                if (mTwoFragment == null)
                {
                    mTwoFragment = TwoFragment.newInstance("", "");
                    transaction.add(R.id.content, mTwoFragment);
                } else
                {
                    transaction.show(mTwoFragment);
                }
                break;
            case 2:
                mRadioButtonHome.setChecked(false);
                mRadioButtonDis.setChecked(false);
                mRadioButtonPro.setChecked(false);
                mRadioButtonAtt.setChecked(true);
                if (mThreeFragment == null)
                {
                    mThreeFragment = ThreeFragment.newInstance("", "");
                    transaction.add(R.id.content, mThreeFragment);
                } else
                {
                    transaction.show(mThreeFragment);
                }
                break;
            case 3:
                mRadioButtonHome.setChecked(false);
                mRadioButtonDis.setChecked(false);
                mRadioButtonAtt.setChecked(false);
                mRadioButtonPro.setChecked(true);
                if (mFourFragment == null)
                {
                    mFourFragment = FourFragment.newInstance("", "");
                    transaction.add(R.id.content, mFourFragment);
                } else
                {
                    transaction.show(mFourFragment);
                }
                break;

            case 4:
                mRadioButtonHome.setChecked(false);
                mRadioButtonDis.setChecked(false);
                mRadioButtonAtt.setChecked(false);
                mRadioButtonPro.setChecked(false);
                mRadioButtonPerson.setChecked(true);
                if (mFiveFragment == null)
                {
                    mFiveFragment = FiveFragment.newInstance("", "");
                    transaction.add(R.id.content, mFiveFragment);
                } else
                {
                    transaction.show(mFiveFragment);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 隐藏所有fragment
     *
     * @param transaction fragment 事务
     */
    private void hideFragments(FragmentTransaction transaction)
    {
        mRadioButtonPerson.setChecked(false);
        mRadioButtonHome.setChecked(false);
        mRadioButtonDis.setChecked(false);
        mRadioButtonAtt.setChecked(false);
        mRadioButtonPro.setChecked(false);
        if (mOneFragment != null)
        {
            transaction.hide(mOneFragment);
        }
        if (mTwoFragment != null)
        {
            transaction.hide(mTwoFragment);
        }
        if (mThreeFragment != null)
        {
            transaction.hide(mThreeFragment);
        }
        if (mFourFragment != null)
        {
            transaction.hide(mFourFragment);
        }
        if (mFiveFragment != null)
        {
            transaction.hide(mFiveFragment);
        }
    }
}
