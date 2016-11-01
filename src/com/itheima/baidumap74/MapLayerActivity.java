package com.itheima.baidumap74;

import com.baidu.mapapi.map.BaiduMap;

import android.view.KeyEvent;

public class MapLayerActivity extends BaseActivity {

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		switch (keyCode) {
		case KeyEvent.KEYCODE_1:
			// 显示普通地图
			baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
			baiduMap.setTrafficEnabled(false);
			break;
		case KeyEvent.KEYCODE_2:
			// 显示卫星图
			baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
			baiduMap.setTrafficEnabled(false);
			break;
		case KeyEvent.KEYCODE_3:
			// 显示交通图
			baiduMap.setTrafficEnabled(true);
			break;

		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}
}
