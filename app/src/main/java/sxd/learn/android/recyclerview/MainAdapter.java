package sxd.learn.android.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Jackie
 * 2016/8/9
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{

    /**
     * Item是否被选中监听
     */
    private OnCheckedChangeListener mOnCheckedChangeListener;
    /**
     * Item点击监听
     */
    private OnItemClickListener mItemOnClickListener;
    /**
     * 数据
     */
    private List<UserInfo> userInfos = null;

    /**
     * Item拖拽滑动帮助
     */
    private ItemTouchHelper itemTouchHelper;

    public MainAdapter() {
    }

    public MainAdapter(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }

    public void notifyDataSetChanged(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
        super.notifyDataSetChanged();
    }

    public void setItemTouchHelper(ItemTouchHelper itemTouchHelper) {
        this.itemTouchHelper = itemTouchHelper;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mItemOnClickListener = onItemClickListener;
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener mOnCheckedChangeListener) {
        this.mOnCheckedChangeListener = mOnCheckedChangeListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData();
    }

    @Override
    public int getItemCount() {
        return userInfos == null ? 0 : userInfos.size();
    }

    public UserInfo getData(int position) {
        return userInfos.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnCheckedChangeListener {
        void onItemCheckedChange(CompoundButton view, int position, boolean checked);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnTouchListener {

        /**
         * 名字和性别
         */
        private TextView mTvName, mTvSex;
        /**
         * 触摸就可以拖拽
         */
        private ImageView mIvTouch;
        /**
         * 是否选中
         */
        private CheckBox mCbCheck;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTvName = (TextView) itemView.findViewById(R.id.tv_name);
            mTvSex = (TextView) itemView.findViewById(R.id.tv_sex);
            mIvTouch = (ImageView) itemView.findViewById(R.id.iv_touch);
            mCbCheck = (CheckBox) itemView.findViewById(R.id.cb_item_check);
            mCbCheck.setOnClickListener(this);
            mIvTouch.setOnTouchListener(this);
        }

        /**
         * 给这个Item设置数据
         */
        public void setData() {
            UserInfo userInfo = getData(getAdapterPosition());
            mTvName.setText(userInfo.getName());
            mTvSex.setText(userInfo.getSex());
            mCbCheck.setChecked(userInfo.isCheck());
        }

        @Override
        public void onClick(View view) {
            if (view == itemView && itemTouchHelper != null) {
                mItemOnClickListener.onItemClick(view, getAdapterPosition());
            } else if (view == mCbCheck && mOnCheckedChangeListener != null) {
                mOnCheckedChangeListener.onItemCheckedChange(mCbCheck, getAdapterPosition(), mCbCheck.isChecked());
            }
        }

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            if (view == mIvTouch)
                itemTouchHelper.startDrag(this);
            return false;
        }
    }

}
