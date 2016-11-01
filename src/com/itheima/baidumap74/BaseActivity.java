package com.itheima.baidumap74;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.itheima.baidumap74.util.Utils;

public abstract class BaseActivity extends Activity {

	private static final String TAG = "BaseActivity";
	/**
	 * 黑马坐标
	 */
	protected LatLng hmPos = new LatLng(40.050513, 116.30361);
	/**
	 * 传智坐标
	 */
	protected LatLng czPos = new LatLng(40.065817, 116.349902);
	/**
	 * 天安门坐标
	 */
	protected LatLng tamPos = new LatLng(39.915112, 116.403963);

	protected MapView mapView;
	protected BaiduMap baiduMap;

	// 这里加final是为了不子类覆盖，原因是为了预防这里的一些类还没初始化的时候就被子类调用
	@Override
	protected final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 获取地图控件引用
		mapView = (MapView) findViewById(R.id.bmapView);

		baiduMap = mapView.getMap(); // 获取地图控制器

		// 1.隐藏比例尺和缩放按钮
		// mapView.showScaleControl(false); // 隐藏比例尺按钮， 默认是显示的
		// mapView.showZoomControls(false); // 隐藏缩放按钮， 默认是显示的

		// 2.获取最小（3），最大（20）缩放级别
		float minZoomLevel = baiduMap.getMinZoomLevel();
		float maxZoomLevel = baiduMap.getMaxZoomLevel();
		Log.i(TAG, "minZoomLevel= " + minZoomLevel + "maxZoomLevel= "
				+ maxZoomLevel);

		// 3.设置地图中心点为黑马
		MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory
				.newLatLng(hmPos);
		baiduMap.setMapStatus(mapStatusUpdate);

		// 4.设置地图开始的缩放大小为15
		// mapStatusUpdate = MapStatusUpdateFactory.zoomTo(15);
		// baiduMap.setMapStatus(mapStatusUpdate);

		// 6. 获取地图Ui控制器：隐藏指南针
		// UiSettings uiSettings = baiduMap.getUiSettings();
		// uiSettings.setCompassEnabled(false); // 不显示指南针

		init();
	}

	// 这个方法让子类实现
	public abstract void init();

	/**
	 * 在屏幕中间显示一个Toast
	 * 
	 * @param text
	 */
	public void showToast(CharSequence text) {
		Utils.showToast(this, text);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
		mapView.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
		mapView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		// 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
		mapView.onPause();
	}
}
