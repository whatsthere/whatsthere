package com.app.whatsthere.customcomponents;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ListAdapter;

/**
 * Created by maratibragimov on 1/21/15.
 */
public class ImagesGridView extends GridView {

    int mHorizontalSpacing = 1;
    int mNumColumns = 3;
    int mVerticalSpacing = 3;

    public ImagesGridView(Context context) {
        super(context);
    }

    public ImagesGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImagesGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public int getColumnWidth() {
        // This method will be called from onMeasure() too.
        // It's better to use getMeasuredWidth(), as it is safe in this case.
        final int totalHorizontalSpacing = mNumColumns > 0 ? (mNumColumns - 1) * mHorizontalSpacing : 0;
        return (getMeasuredWidth() - getPaddingLeft() - getPaddingRight() - totalHorizontalSpacing) / mNumColumns;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Sets the padding for this view.
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final int measuredWidth = getMeasuredWidth();
        final int childWidth = getColumnWidth();
        int childHeight = 0;

        // If there's an adapter, use it to calculate the height of this view.
        final ListAdapter adapter = getAdapter();
        final int count;

        // There shouldn't be any inherent size (due to padding) if there are no child views.
        if (adapter == null || (count = adapter.getCount()) == 0) {
            setMeasuredDimension(0, 0);
            return;
        }

        // Get the first child from the adapter.
        final View child = adapter.getView(0, null, this);
        if (child != null) {
            // Set a default LayoutParams on the child, if it doesn't have one on its own.
            AbsListView.LayoutParams params = (AbsListView.LayoutParams) child.getLayoutParams();
            if (params == null) {
                params = new AbsListView.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT,
                        AbsListView.LayoutParams.WRAP_CONTENT);
                child.setLayoutParams(params);
            }

            // Measure the exact width of the child, and the height based on the width.
            // Note: the child takes care of calculating its height.
            int childWidthSpec = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
            int childHeightSpec = MeasureSpec.makeMeasureSpec(0,  MeasureSpec.UNSPECIFIED);
            child.measure(childWidthSpec, childHeightSpec);
            childHeight = child.getMeasuredHeight();
        }

        // Number of rows required to 'mTotal' items.
        final int rows = (int) Math.ceil((double) adapter.getCount() / mNumColumns);
        final int childrenHeight = childHeight * rows;
        final int totalVerticalSpacing = rows > 0 ? (rows - 1) * mVerticalSpacing : 0;

        // Total height of this view.
        final int measuredHeight = childrenHeight + getPaddingTop() + getPaddingBottom() + totalVerticalSpacing;
        setMeasuredDimension(measuredWidth, measuredHeight);
    }
}
