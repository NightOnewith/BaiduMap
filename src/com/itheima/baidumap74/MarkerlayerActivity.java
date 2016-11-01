package com.itheima.baidumap74;

import android.view.View;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMarkerDragListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapViewLayoutParams;
import com.baidu.mapapi.map.MapViewLayoutParams.ELayoutMode;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;

public class MarkerlayerActivity extends BaseActivity {

	private View pop;
	private TextView tv_title;

	@Override
	public void init() {
		initMarker();
		baiduMap.setOnMarkerClickListener(mOnMarkerClickListener);// 给标志添加监听
		baiduMap.setOnMarkerDragListener(mOnMarkerDragListener);// 给标题添加拖动监听
	}

	/**
	 * 标志拖动监听器
	 */
	OnMarkerDragListener mOnMarkerDragListener = new OnMarkerDragListener() {

		/** 标志开始拖动 */
		@Override
		public void onMarkerDragStart(Marker marker) {
			mapView.updateViewLayout(pop,
					createLayoutParams(marker.getPosition()));
		}

		/** 标志拖动结束 */
		@Override
		public void onMarkerDragEnd(Marker marker) {
			mapView.updateViewLayout(pop,
					createLayoutParams(marker.getPosition()));
		}

		/** 标志正在拖动 */
		@Override
		public void onMarkerDrag(Marker marker) {
			mapView.updateViewLayout(pop,
					createLayoutParams(marker.getPosition()));
		}
	};

	/**
	 * 给标志添加监听
	 */
	OnMarkerClickListener mOnMarkerClickListener = new OnMarkerClickListener() {

		@Override
		public boolean onMarkerClick(Marker marker) {
			// 显示一个泡泡
			if (pop == null) {
				pop = View
						.inflate(MarkerlayerActivity.this, R.layout.pop, null);
				tv_title = (TextView) pop.findViewById(R.id.tv_title);
				mapView.addView(pop, createLayoutParams(marker.getPosition()));
			}  else {
				mapView.updateViewLayout(pop,
						createLayoutParams(marker.getPosition()));
			}
			tv_title.setText(marker.getTitle());
			return true;
		}
	};

	/** 初始化标志 */
	private void initMarker() {
		MarkerOptions options = new MarkerOptions();
		BitmapDescriptor icon = BitmapDescriptorFactory
				.fromResource(R.drawable.icon_eat);
		options.position(hmPos);// 位置
		options.title("黑马"); // title
		options.icon(icon); // 图标
		options.draggable(true); // 设置图标可以拖动

		baiduMap.addOverlay(options);

		// 添加一个向北的标志
		options = new MarkerOptions().icon(icon).title("向北");
		options.position(new LatLng(hmPos.latitude + 0.001, hmPos.longitude));
		options.draggable(true);
		baiduMap.addOverlay(options);

		// 添加一个向东的标志
		options = new MarkerOptions().icon(icon).title("向东");
		options.position(new LatLng(hmPos.latitude, hmPos.longitude + 0.001));
		options.draggable(true);
		baiduMap.addOverlay(options);

		// 添加一个向西南的标志
		options = new MarkerOptions();
		options.icon(icon);
		options.title("向西南");
		options.position(new LatLng(hmPos.latitude - 0.001,
				hmPos.longitude - 0.001));
		options.draggable(true);
		baiduMap.addOverlay(options);
	}

	/**
	 * 创建一个标题布局参数
	 */
	private MapViewLayoutParams createLayoutParams(LatLng position) {
		MapViewLayoutParams.Builder buidler = new MapViewLayoutParams.Builder();
		buidler.layoutMode(ELayoutMode.mapMode); // 指定坐标类型为经纬度
		buidler.position(position); // 设置标志的位置
		buidler.yOffset(-50); // 设置View往上偏移
		MapViewLayoutParams params = buidler.build();
		return params;
	}

}
