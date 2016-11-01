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

	// 因为其他搜索也需要这个实例，所以在父类初始化，这样的话自雷就不需要在实例化
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

	//生成这个方法是为了让子类可以覆盖
	public boolean onPoiClick(int index) {
		PoiInfo poiInfo = poiOverlay.getPoiResult().getAllPoi().get(index);
		showToast(poiInfo.name + "," + poiInfo.address);
		return true;
	}

	// poi搜索的初始化代码写在这个方法中
	public abstract void poiSearchInit();

	// 因为其他搜索的处理结果都一样，所以放在父类
	// 获取兴趣点信息
	@Override
	public void onGetPoiResult(PoiResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			showToast("没有搜索到结果");
			return;
		}

		poiOverlay.setData(result);// 把数据设置给覆盖物
		poiOverlay.addToMap();// 把所有覆盖物添加到BaiduMap
		poiOverlay.zoomToSpan();// 将结果在一个屏幕上显示出来
	}

}
