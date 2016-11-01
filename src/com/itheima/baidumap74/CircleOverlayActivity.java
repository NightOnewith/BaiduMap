package com.itheima.baidumap74;

import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.Stroke;

public class CircleOverlayActivity extends BaseActivity {

	@Override
	public void init() {
		CircleOptions options = new CircleOptions(); // 创建一个圆形的覆盖物参数
		options.center(czPos); // 圆心
		options.radius(1000); // 半径
		options.stroke(new Stroke(20, 0x55ff0000)); // 线条宽度、颜色
		options.fillColor(0x5500ff00); // 圆的填充颜色
		baiduMap.addOverlay(options); // 添加一个覆盖物
	}

}
