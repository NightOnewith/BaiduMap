package com.itheima.baidumap74;

import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;

/**
 * ���ܱ�����
 */
public class SearchInNearbyActivity extends PoiSearchbaseActivity {

	@Override
	public void poiSearchInit() {
		poiSearch.searchNearby(getSearchParams());
	}

	private PoiNearbySearchOption getSearchParams() {
		PoiNearbySearchOption params = new PoiNearbySearchOption();
		params.location(hmPos); // ָ�����ĸ�λ������
		params.radius(1000); // ָ��������Χ
		params.keyword("��������"); // ָ������������
		return params;
	}

	/** ��ȡ��Ȥ��������Ϣ */
	@Override
	public void onGetPoiDetailResult(PoiDetailResult result) {

	}

}
