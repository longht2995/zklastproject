package vn.greenglobal.front.service;

import java.util.ArrayList;
import java.util.List;

import vn.toancauxanh.gg.model.QVideo;
import vn.toancauxanh.gg.model.Video;
import vn.toancauxanh.service.BaseObject;

public class HomeService extends BaseObject<HomeService>{
	public List<Video> getTarget(){
		String tukhoa = "";
		String page = "";
		List<Video> list = new ArrayList<Video>();
		list = find(Video.class).where(QVideo.video.trangThai.eq(core().TT_KHONG_AP_DUNG))
				.orderBy(QVideo.video.ngaySua.desc())
				.fetch();
		return list;
	}
}
