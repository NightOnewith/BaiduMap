package com.itheima.baidumap74;

import com.baidu.mapapi.map.TextOptions;

public class TextOverlayerActivity extends BaseActivity {

	@Override
	public void init() {
		TextOptions options = new TextOptions();
		options.position(hmPos); // λ��
		options.text("�������Ա"); // ��������
		options.fontSize(40); // ���ִ�С
		options.fontColor(0xFF000000);// ������ɫ
		options.bgColor(0x55FF0000);// ������ɫ
		options.rotate(30);// ��ת
		baiduMap.addOverlay(options);
	}

}
