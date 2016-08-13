package sxd.learn.android.recyclerview;

import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by lab on 2016/8/9.
 */
public class DefaultItemTouchHelper extends ItemTouchHelper {

    private DefaultItemTouchHelpCallback itemTouchHelpCallback;
    /**
     * Creates an ItemTouchHelper that will work with the given Callback.
     * <p/>
     * You can attach ItemTouchHelper to a RecyclerView via
     * {@link #attachToRecyclerView(RecyclerView)}. Upon attaching, it will add an item decoration,
     * an onItemTouchListener and a Child attach / detach listener to the RecyclerView.
     *
     * @param callback The Callback which controls the behavior of this touch helper.
     */
    private DefaultItemTouchHelper(Callback callback) {
        super(callback);

        this.itemTouchHelpCallback = (DefaultItemTouchHelpCallback) callback;
    }

    public DefaultItemTouchHelper(DefaultItemTouchHelpCallback.OnItemTouchCallbackListener onItemTouchCallbackListener) {
        this(new DefaultItemTouchHelpCallback(onItemTouchCallbackListener));
    }

    /**
     * 设置是否可以被拖拽
     *
     * @param canDrag 是true，否false
     */
    public void setCanDrag(boolean canDrag) {
        itemTouchHelpCallback.setCanDrag(canDrag);
    }

    /**
     * 设置是否可以被滑动
     *
     * @param canSwipe 是true，否false
     */
    public void setCanSwipe(boolean canSwipe) {
        itemTouchHelpCallback.setCanSwipe(canSwipe);
    }
}
