package com.itheima.baidumap74;

import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.Stroke;

public class CircleOverlayActivity extends BaseActivity {

	@Override
	public void init() {
		CircleOptions options = new CircleOptions(); // ����һ��Բ�εĸ��������
		options.center(czPos); // Բ��
		options.radius(1000); // �뾶
		options.stroke(new Stroke(20, 0x55ff0000)); // ������ȡ���ɫ
		options.fillColor(0x5500ff00); // Բ�������ɫ
		baiduMap.addOverlay(options); // ���һ��������
	}

}
