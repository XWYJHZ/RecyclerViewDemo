package sxd.learn.android.recyclerview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Jackie
 * 2016/8/9
 */
public class DefaultItemTouchHelpCallback extends ItemTouchHelper.Callback {

    /**
     * Item操作的回调
     */
    private OnItemTouchCallbackListener onItemTouchCallbackListener;

    /**
     * 是否可以拖拽
     */
    private boolean isCanDrag = false;

    /**
     * 是否可以滑动
     */
    private boolean isCanSwipe = false;

    public DefaultItemTouchHelpCallback(OnItemTouchCallbackListener onItemTouchCallbackListener){
        this.onItemTouchCallbackListener = onItemTouchCallbackListener;
    }

    public void setOnItemTouchCallbackListener(OnItemTouchCallbackListener onItemTouchCallbackListener){
        this.onItemTouchCallbackListener = onItemTouchCallbackListener;
    }

    public void setCanDrag(boolean isCanDrag){
        this.isCanDrag = isCanDrag;
    }

    public void setCanSwipe(boolean isCanSwipe){
        this.isCanSwipe = isCanSwipe;
    }

    /**
     * 当Item被长按时时候可以被拖拽
     * @return
     */
    public boolean isLongPressDragEnabled(){
        return isCanDrag;
    }

    /**
     * Item时候可以被滑动
     * @return
     */
    public boolean isItemViewSwipeEnabled(){
        return isCanSwipe;
    }

    /**
     * 当用户拖拽或者滑动Item的时候需要我们告诉系统滑动或者拖拽的方向
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int dragFlg = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
                    ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            int swipeFlag = 0;
            return makeMovementFlags(dragFlg, swipeFlag);
        }
        else if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            int orientation = linearLayoutManager.getOrientation();

            int dragFlag = 0;
            int swipeFlag = 0;

            if (orientation == LinearLayoutManager.HORIZONTAL) {
                swipeFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            } else if (orientation == LinearLayoutManager.VERTICAL ){
                dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                swipeFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            }
            return makeMovementFlags(dragFlag, swipeFlag);
        }
        return 0;
    }

    /**
     *当Item被拖拽的时候被回调
     * @param recyclerView
     * @param viewHolder 被拖拽的viewHolder
     * @param target    目的地的viewHolder
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
       if (onItemTouchCallbackListener != null){
           return onItemTouchCallbackListener.onMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
       }
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if(onItemTouchCallbackListener != null){
            onItemTouchCallbackListener.onSwiped(viewHolder.getAdapterPosition());
        }
    }

    public  interface OnItemTouchCallbackListener{
        /**
         * 当某个Item被滑动删除的时候
         *
         * @param adapterPosition item的position
         */
        void onSwiped(int adapterPosition);

        /**
         * 当两个Item位置互换的时候被回调
         *
         * @param srcPosition    拖拽的item的position
         * @param targetPosition 目的地的Item的position
         * @return 开发者处理了操作应该返回true，开发者没有处理就返回false
         */
        boolean onMove(int srcPosition, int targetPosition);
    }
}
