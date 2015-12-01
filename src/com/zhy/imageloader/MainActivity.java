package com.zhy.imageloader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zhy.Constant;
import com.zhy.picker.PickerActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
	@Bind(R.id.id_display) TextView mDisplay;
	@OnClick(R.id.id_display)
	protected void onPickerStart(View view) {
		startActivity(new Intent(this, PickerActivity.class));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		ArrayList<String> selPhotoes = (ArrayList<String>)getIntent().getSerializableExtra(Constant.EXTRA_SELECTIONS);
		if (null == selPhotoes) {
			// do nothing so keep empty before update ui.
			mDisplay.setText("Empty selection");
		} else {
			mDisplay.setText("Selected " + selPhotoes.size() + " : " + selPhotoes);
		}
	}

//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//	}
}
