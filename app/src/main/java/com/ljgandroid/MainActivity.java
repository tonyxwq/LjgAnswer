package com.ljgandroid;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ljgandroid.adapter.QuickAdapter;
import com.ljgandroid.bean.UserBean;
import com.ljgandroid.dagger.DaggerPlantMainA;
import com.ljgandroid.dagger.PlantMainA;
import com.ljgandroid.tools.PreferenceUtils;
import com.ljgandroid.tools.ToastUtils;
import com.ljganswer.R;
import com.ljgandroid.dagger.CoomModule;
import com.ljgandroid.presenter.LoginPresenter;
import com.ljgandroid.view.ILoginView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static android.widget.LinearLayout.VERTICAL;

public class MainActivity extends BaseActivity implements ILoginView, View.OnClickListener
{
    @Inject
    LoginPresenter loginPresenter;

    @BindView(R.id.btn_login)
    Button mButton;

    @BindView(R.id.btn_add)
    Button mButton_Add;

    @BindView(R.id.spins)
    Spinner mSpinner;

    @BindView(R.id.tv_user_name)
    TextView mTextViewName;

    @BindView(R.id.tv_user_phone)
    TextView mTextViewPhone;

    @BindView(R.id.tv_user_id)
    TextView mTextViewId;

    @BindView(R.id.et_input)
    EditText mEditText;

    @BindView(R.id.recycler_id_main)
    RecyclerView recyclerView;

    List<String> data = new ArrayList<>();

    String[] list = new String[15];

    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView()
    {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget()
    {
        super.initWidget();
        PlantMainA plantMainA = DaggerPlantMainA.builder().coomModule(new CoomModule(this)).build();
        plantMainA.inject(this);
        plantMainA.inject(loginPresenter);
    }

    @Override
    protected void initData()
    {
        super.initData();
        mButton.setOnClickListener(this);
        mButton_Add.setOnClickListener(this);
        mSpinner.setOnClickListener(this);
        for (int i = 0; i < 100; i++)
        {
            data.add(i + "");
        }
    }
    ArrayList<UserBean> userBeanList=new ArrayList<>();

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case  R.id.spins:
                try
                {
                    String userInfo=PreferenceUtils.getPrefString(this,"key","");
                    JSONObject jsonObject=new JSONObject(userInfo);
                    Log.d("mmp",jsonObject.toString());

                }catch (Exception e)
                {
                    e.printStackTrace();
                }
                //适配器
                arr_adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
                //设置样式
                arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //加载适配器
                if(arr_adapter!=null)
                {
                    mSpinner.setAdapter(arr_adapter);
                }
                break;

            case R.id.btn_add:
                String name=mTextViewName.getText().toString();
                if(TextUtils.isEmpty(name))
                {
                    ToastUtils.show("姓名不能为空");
                    return;
                }
                String phone=mTextViewPhone.getText().toString();
                if(TextUtils.isEmpty(phone))
                {
                    ToastUtils.show("电话不能为空");
                    return;
                }
                try
                {
                    JSONObject  json = new JSONObject();
                    json.put("name", name);
                    json.put("phone", phone);
                    PreferenceUtils.setPrefString(this,"key",json.toString());
                    ToastUtils.show("添加成功");

                }catch (Exception e)
                {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_login:
                String str=mEditText.getText().toString().trim();
                if(TextUtils.isEmpty(str))
                {
                    ToastUtils.show("答案不能为空");
                    return;
                }
                String testId=mTextViewId.getText().toString().trim();
                if(TextUtils.isEmpty(testId))
                {
                    ToastUtils.show("test_id不能为空");
                    return;
                }
                try
                {
                    String userInfo=PreferenceUtils.getPrefString(this,"key","");
                    JSONObject jsonObject=new JSONObject(userInfo);
                    Log.d("mmp",jsonObject.toString());

                }catch (Exception e)
                {
                    e.printStackTrace();
                }


                loginPresenter.Login("张炜", "13684512598", list, "310412499578");

                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutManager);
                //设置为垂直布局，这也是默认的
                layoutManager.setOrientation(OrientationHelper.VERTICAL);
                //设置Adapter
                //recyclerView.setAdapter(new MainAdapter(data));
                QuickAdapter<String> baseAdapter = new QuickAdapter<String>(data)
                {
                    @Override
                    public int getLayoutId(int viewType)
                    {
                        return R.layout.main_layout_item_one;
                    }

                    @Override
                    public void convert(VH holder, String data, int position)
                    {
                        holder.setText(R.id.tv_main_id, data);
                        holder.getView(R.id.tv_main_id).setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                ToastUtils.show("click");
                            }
                        });
                    }
                };
                recyclerView.setAdapter(baseAdapter);
                //设置分隔线
                recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, VERTICAL));
                //设置增加或删除条目的动画
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                break;
        }
    }

    @Override
    public void showMessage(String msg)
    {
        // Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
