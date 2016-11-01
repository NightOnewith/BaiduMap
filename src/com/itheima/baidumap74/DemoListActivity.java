package com.itheima.baidumap74;

import com.baidu.mapapi.SDKInitializer;
import com.itheima.baidumap74.util.Utils;

import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DemoListActivity extends ListActivity {
	private BroadcastReceiver receiver;
	private ClassAndName[] datas = {
			new ClassAndName(HelloBaiduMapActivity.class, "HelloBaiduMap"),
			new ClassAndName(MapLayerActivity.class, "地图图层"),
			new ClassAndName(CircleOverlayActivity.class, "圆形覆盖物"),
			new ClassAndName(TextOverlayerActivity.class, "文字覆盖物"),
			new ClassAndName(MarkerlayerActivity.class, "标志覆盖物"),
			new ClassAndName(SearchInBoundActivity.class, "在范围内搜索"),
			new ClassAndName(SearchInCityActivity.class, "在城市内搜索"),
			new ClassAndName(SearchInNearbyActivity.class, "在附近搜索"),
			new ClassAndName(DrivingSearchActivity.class, "驾车路线搜索"),
			new ClassAndName(TransitSearchActivity.class, "换乘路线搜索"),
			new ClassAndName(WalkingSearchActivity.class, "步行路线搜索"),
			new ClassAndName(LocationActivity.class, "定位")};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		registerSDKCheckReceiver();
		ArrayAdapter<ClassAndName> adapter = new ArrayAdapter<DemoListActivity.ClassAndName>(
				this, android.R.layout.simple_list_item_1, datas);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		ClassAndName classAndName = (ClassAndName) l
				.getItemAtPosition(position);
		Intent intent = new Intent(this, classAndName.clazz);
		startActivity(intent);
	}

	class ClassAndName {
		// 用于保存Activity的字节码
		public Class<?> clazz;
		// 用于保存Activity的对应的名字
		public String name;

		public ClassAndName(Class<?> clazz, String name) {
			this.clazz = clazz;
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}

	}

	private void registerSDKCheckReceiver() {
		receiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				String action = intent.getAction();
				if (SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR
						.equals(action)) {
					Utils.showToast(DemoListActivity.this, "网络错误");
				} else if (SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR
						.equals(action)) {
					Utils.showToast(DemoListActivity.this, "key验证失败");
				}
			}
		};
		IntentFilter filter = new IntentFilter();
		// 监听网络错误
		filter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
		// 监听百度地图SDK的key是否正确
		filter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
		registerReceiver(receiver, filter);
	}

	@Override
	protected void onDestroy() {
		unregisterReceiver(receiver);
		super.onDestroy();
	}
}
