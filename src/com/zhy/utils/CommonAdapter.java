package com.zhy.utils;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
	protected LayoutInflater mInflater;
	protected Context mContext;
	protected List<T> mDatas;
	protected final int mItemLayoutId;

	private RecyclerClickListener recyclerListener;

	public CommonAdapter(Context context, List<T> mDatas, int itemLayoutId)
	{
		this.mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
		this.mDatas = mDatas;
		this.mItemLayoutId = itemLayoutId;
	}

//	@Override
//	public int getCount()
//	{
//		return mDatas.size();
//	}
//
//	@Override
//	public T getItem(int position)
//	{
//		return mDatas.get(position);
//	}
//
//	@Override
//	public long getItemId(int position)
//	{
//		return position;
//	}
//
//	@Override
//	public View getView(int position, View convertView, ViewGroup parent)
//	{
//		final ViewHolder viewHolder = getViewHolder(position, convertView,
//				parent);
//		convert(viewHolder, getItem(position));
//		return viewHolder.getConvertView();
//
//	}
//	public abstract void convert(ViewHolder helper, T item);
//
//	private ViewHolder getViewHolder(int position, View convertView,
//			ViewGroup parent)
//	{
//		return ViewHolder.get(mContext, convertView, parent, mItemLayoutId,
//				position);
//	}

	protected ViewHolder mHolder;
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View rootView = LayoutInflater.from(mContext).inflate(mItemLayoutId, parent, false);
		mHolder = new ViewHolder(rootView, new RecyclerClickListener() {
			@Override
			public void onElementClick(ViewHolder holder, int position) {
				performClicked(holder, position);
			}
		});

		return mHolder;
	}

	protected void performClicked(ViewHolder holder, int position) {
		if (null != recyclerListener) {
			recyclerListener.onElementClick(holder, position);
		}
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		holder.bindData(mDatas.get(position), null);
	}

	@Override
	public int getItemCount() {
		return mDatas.size();
	}

	public void setOnItemClickListener(RecyclerClickListener recyclerListener) {
		this.recyclerListener = recyclerListener;
	}
}
