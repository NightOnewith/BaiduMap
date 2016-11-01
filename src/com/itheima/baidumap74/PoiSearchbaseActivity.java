package com.itheima.baidumap74;

import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

public abstract class PoiSearchbaseActivity extends BaseActivity implements
		OnGetPoiSearchResultListener {

	protected PoiSearch poiSearch;
	protected PoiOverlay poiOverlay;

	// ��Ϊ��������Ҳ��Ҫ���ʵ���������ڸ����ʼ���������Ļ����׾Ͳ���Ҫ��ʵ����
	@Override
	public final void init() {
		poiSearch = PoiSearch.newInstance();
		poiSearch.setOnGetPoiSearchResultListener(this);
		poiOverlay = new PoiOverlay(baiduMap) {
			@Override
			public boolean onPoiClick(int index) {
				return PoiSearchbaseActivity.this.onPoiClick(index);
			}
		};
		baiduMap.setOnMarkerClickListener(poiOverlay);

		poiSearchInit();
	}

	//�������������Ϊ����������Ը���
	public boolean onPoiClick(int index) {
		PoiInfo poiInfo = poiOverlay.getPoiResult().getAllPoi().get(index);
		showToast(poiInfo.name + "," + poiInfo.address);
		return true;
	}

	// poi�����ĳ�ʼ������д�����������
	public abstract void poiSearchInit();

	// ��Ϊ���������Ĵ�������һ�������Է��ڸ���
	// ��ȡ��Ȥ����Ϣ
	@Override
	public void onGetPoiResult(PoiResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			showToast("û�����������");
			return;
		}

		poiOverlay.setData(result);// ���������ø�������
		poiOverlay.addToMap();// �����и�������ӵ�BaiduMap
		poiOverlay.zoomToSpan();// �������һ����Ļ����ʾ����
	}

}
