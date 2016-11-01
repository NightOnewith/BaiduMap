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
			new ClassAndName(MapLayerActivity.class, "��ͼͼ��"),
			new ClassAndName(CircleOverlayActivity.class, "Բ�θ�����"),
			new ClassAndName(TextOverlayerActivity.class, "���ָ�����"),
			new ClassAndName(MarkerlayerActivity.class, "��־������"),
			new ClassAndName(SearchInBoundActivity.class, "�ڷ�Χ������"),
			new ClassAndName(SearchInCityActivity.class, "�ڳ���������"),
			new ClassAndName(SearchInNearbyActivity.class, "�ڸ�������"),
			new ClassAndName(DrivingSearchActivity.class, "�ݳ�·������"),
			new ClassAndName(TransitSearchActivity.class, "����·������"),
			new ClassAndName(WalkingSearchActivity.class, "����·������"),
			new ClassAndName(LocationActivity.class, "��λ")};

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
		// ���ڱ���Activity���ֽ���
		public Class<?> clazz;
		// ���ڱ���Activity�Ķ�Ӧ������
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
					Utils.showToast(DemoListActivity.this, "�������");
				} else if (SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR
						.equals(action)) {
					Utils.showToast(DemoListActivity.this, "key��֤ʧ��");
				}
			}
		};
		IntentFilter filter = new IntentFilter();
		// �����������
		filter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
		// �����ٶȵ�ͼSDK��key�Ƿ���ȷ
		filter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
		registerReceiver(receiver, filter);
	}

	@Override
	protected void onDestroy() {
		unregisterReceiver(receiver);
		super.onDestroy();
	}
}