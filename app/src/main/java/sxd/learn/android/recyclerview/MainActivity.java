package sxd.learn.android.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sxd.learn.android.util.Util;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    /**
     *数据源
     */
    private List<UserInfo> userInfoList;

    /**
     * 数据适配器
     */
    private MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initial();
    }

    private void initial(){
        recyclerView = (RecyclerView) findViewById(R.id.rv_main);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        mainAdapter = new MainAdapter(userInfoList);
        mainAdapter.setOnItemClickListener(onItemClickListener);
        mainAdapter.setOnCheckedChangeListener(onCheckedChangeListener);
        recyclerView.setAdapter(mainAdapter);

        //模拟数据
        userInfoList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            boolean isCheck = i% 2 == 0;
            userInfoList.add(new UserInfo("name " + i, isCheck ? "male" : "female", isCheck));
        }
        mainAdapter.notifyDataSetChanged(userInfoList);

        DefaultItemTouchHelper itemTouchHelper = new DefaultItemTouchHelper(onItemTouchCallbackListener);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        mainAdapter.setItemTouchHelper(itemTouchHelper);

        itemTouchHelper.setCanSwipe(true);
        itemTouchHelper.setCanDrag(true);
    }

    private DefaultItemTouchHelpCallback.OnItemTouchCallbackListener onItemTouchCallbackListener = new DefaultItemTouchHelpCallback.OnItemTouchCallbackListener() {
        @Override
        public void onSwiped(int adapterPosition) {
            // 滑动删除的时候，从数据源移除，并刷新这个Item。
            if (userInfoList != null) {
                userInfoList.remove(adapterPosition);
                mainAdapter.notifyItemRemoved(adapterPosition);
            }
        }

        @Override
        public boolean onMove(int srcPosition, int targetPosition) {
            if (userInfoList != null) {
                // 更换数据源中的数据Item的位置
                Collections.swap(userInfoList, srcPosition, targetPosition);
                // 更新UI中的Item的位置，主要是给用户看到交互效果
                mainAdapter.notifyItemMoved(srcPosition, targetPosition);
                return true;
            }
            return false;
        }
    };

    /**
     * RecyclerView的Item点击监听，接口位于Adapter中
     */
    private MainAdapter.OnItemClickListener onItemClickListener = new MainAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Util.showToast(MainActivity.this, "position " + position);
        }
    };

    private MainAdapter.OnCheckedChangeListener onCheckedChangeListener = new MainAdapter.OnCheckedChangeListener() {
        @Override
        public void onItemCheckedChange(CompoundButton view, int position, boolean checked) {
            Util.showToast(MainActivity.this, "position " + position);
        }
    };
}
