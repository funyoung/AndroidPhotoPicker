package com.zhy.picker;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhy.bean.ImageFloder;
import com.zhy.imageloader.R;
import com.zhy.utils.BasePopupWindowForListView;
import com.zhy.utils.CommonAdapter;
import com.zhy.utils.RecyclerClickListener;
import com.zhy.utils.ViewHolder;

import java.util.List;

public class ListImageDirPopupWindow extends BasePopupWindowForListView<ImageFloder>
{
	private RecyclerView mListDir;

	public ListImageDirPopupWindow(int width, int height,
								   List<ImageFloder> datas, View convertView)
	{
		super(convertView, width, height, true, datas);
	}

	@Override
	public void initViews()
	{
		mListDir = (RecyclerView) findViewById(R.id.id_list_dir);

		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
		mListDir.setLayoutManager(linearLayoutManager);
		CommonAdapter adapter = new CommonAdapter<ImageFloder>(context, mDatas,
				R.layout.list_dir_item);

		adapter.setOnItemClickListener(new RecyclerClickListener() {
			@Override
			public void onElementClick(ViewHolder holder, int position) {
				if (mImageDirSelected != null) {
					mImageDirSelected.selected(mDatas.get(position));
				}
			}
		});

		mListDir.setAdapter(adapter);
	}

	public interface OnImageDirSelected
	{
		void selected(ImageFloder floder);
	}

	private OnImageDirSelected mImageDirSelected;

	public void setOnImageDirSelected(OnImageDirSelected mImageDirSelected)
	{
		this.mImageDirSelected = mImageDirSelected;
	}

	@Override
	public void initEvents() {
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void beforeInitWeNeedSomeParams(Object... params) {
		// TODO Auto-generated method stub
	}
}
