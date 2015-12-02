package com.zhy.utils;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.bean.ImageFloder;
import com.zhy.imageloader.R;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ViewHolder extends RecyclerView.ViewHolder {
//	private final SparseArray<View> mViews;
//	private int mPosition;
//	private View mConvertView;
//
//	private ViewHolder(Context context, ViewGroup parent, int layoutId,
//			int position)
//	{
//		this.mPosition = position;
//		this.mViews = new SparseArray<View>();
//		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
//				false);
//		// setTag
//		mConvertView.setTag(this);
//	}
//
//	/**
//	 * 拿到一个ViewHolder对象
//	 *
//	 * @param context
//	 * @param convertView
//	 * @param parent
//	 * @param layoutId
//	 * @param position
//	 * @return
//	 */
//	public static ViewHolder get(Context context, View convertView,
//			ViewGroup parent, int layoutId, int position)
//	{
//		ViewHolder holder = null;
//		if (convertView == null)
//		{
//			holder = new ViewHolder(context, parent, layoutId, position);
//		} else
//		{
//			holder = (ViewHolder) convertView.getTag();
//			holder.mPosition = position;
//		}
//		return holder;
//	}
//
//	public View getConvertView()
//	{
//		return mConvertView;
//	}
//
//	/**
//	 * 通过控件的Id获取对于的控件，如果没有则加入views
//	 *
//	 * @param viewId
//	 * @return
//	 */
//	public <T extends View> T getView(int viewId)
//	{
//		View view = mViews.get(viewId);
//		if (view == null)
//		{
//			view = mConvertView.findViewById(viewId);
//			mViews.put(viewId, view);
//		}
//		return (T) view;
//	}
//
//	/**
//	 * 为TextView设置字符串
//	 *
//	 * @param viewId
//	 * @param text
//	 * @return
//	 */
//	public ViewHolder setText(int viewId, String text)
//	{
//		TextView view = getView(viewId);
//		view.setText(text);
//		return this;
//	}
//
//	/**
//	 * 为ImageView设置图片
//	 *
//	 * @param viewId
//	 * @param drawableId
//	 * @return
//	 */
//	public ViewHolder setImageResource(int viewId, int drawableId)
//	{
//		ImageView view = getView(viewId);
//		view.setImageResource(drawableId);
//
//		return this;
//	}
//
//	/**
//	 * 为ImageView设置图片
//	 *
//	 * @param viewId
//	 * @param drawableId
//	 * @return
//	 */
//	public ViewHolder setImageBitmap(int viewId, Bitmap bm)
//	{
//		ImageView view = getView(viewId);
//		view.setImageBitmap(bm);
//		return this;
//	}
//
//	/**
//	 * 为ImageView设置图片
//	 *
//	 * @param viewId
//	 * @param drawableId
//	 * @return
//	 */
//	public ViewHolder setImageByUrl(int viewId, String url)
//	{
//		ImageLoader.getInstance(3,Type.LIFO).loadImage(url, (ImageView) getView(viewId));
//		return this;
//	}
//
//	public int getPosition()
//	{
//		return mPosition;
//	}

	// folder item
	@Nullable
	@Bind(R.id.id_dir_item_name) TextView mFolderName;
	@Nullable
	@Bind(R.id.id_dir_item_image) ImageView mFolderAvatar;
	@Nullable
	@Bind(R.id.id_dir_item_count) TextView mCountLabel;

	// photo item
	@Nullable
	@Bind(R.id.id_item_image) ImageView mPhotoImage;

	@Nullable
	@Bind(R.id.id_item_select) ImageButton mPhotoCheckbox;

	private int SELECTED_FILTER_COLOR = Color.parseColor("#77000000");
	public ViewHolder(View itemView, final RecyclerClickListener recyclerListener) {
		super(itemView);

		ButterKnife.bind(this, itemView);
		bindListener(itemView, recyclerListener);
	}

	public void bindData(Object object, String extra) {
		if (object instanceof ImageFloder) {
			ImageFloder folder = (ImageFloder)object;
			mFolderName.setText(folder.getName());
			mCountLabel.setText(folder.getCount() + "张");
			ImageLoader.getInstance(3, ImageLoader.Type.LIFO).loadImage(folder.getFirstImagePath(), mFolderAvatar);
		} else if (object instanceof String && !TextUtils.isEmpty(extra)) {
			mPhotoImage.setImageResource(R.drawable.pictures_no);
			ImageLoader.getInstance(3, ImageLoader.Type.LIFO).loadImage(extra + File.separator + object, mPhotoImage);
		} else {
			// unknown object.
		}
	}

	private void bindListener(View itemView,  final RecyclerClickListener recyclerListener) {
		itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				recyclerListener.onElementClick(ViewHolder.this, getLayoutPosition());
			}
		});
	}

	public void onSelected(boolean selected) {
		if (null == mPhotoImage || null == mPhotoCheckbox) {
			return;
		}

		if (selected) {
			mPhotoImage.setColorFilter(SELECTED_FILTER_COLOR);
			mPhotoCheckbox.setImageResource(R.drawable.pictures_selected);
		} else {
			mPhotoImage.setColorFilter(null);
			mPhotoCheckbox.setImageResource(R.drawable.picture_unselected);
		}
	}
}
