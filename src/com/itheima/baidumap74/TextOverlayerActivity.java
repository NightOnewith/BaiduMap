package com.itheima.baidumap74;

import com.baidu.mapapi.map.TextOptions;

public class TextOverlayerActivity extends BaseActivity {

	@Override
	public void init() {
		TextOptions options = new TextOptions();
		options.position(hmPos); // 位置
		options.text("黑马程序员"); // 文字内容
		options.fontSize(40); // 文字大小
		options.fontColor(0xFF000000);// 文字颜色
		options.bgColor(0x55FF0000);// 背景颜色
		options.rotate(30);// 旋转
		baiduMap.addOverlay(options);
	}

}
