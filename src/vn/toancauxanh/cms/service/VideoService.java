package vn.toancauxanh.cms.service;

import org.apache.commons.collections.MapUtils;
import org.zkoss.util.resource.Labels;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.gg.model.QVideo;
import vn.toancauxanh.gg.model.Video;
import vn.toancauxanh.service.BasicService;

public class VideoService extends BasicService<Video>{
	
	public JPAQuery<Video> getTargetQuery(){
		String tukhoa = MapUtils.getString(argDeco(), Labels.getLabel("param.tukhoa"));
		String trangthaisoan = MapUtils.getString(argDeco(),  Labels.getLabel("param.trangthaisoan"),"");
		JPAQuery<Video> q = find(Video.class);
		if(tukhoa!=null) {
			q.where(QVideo.video.tieuDe.like("%"+tukhoa+"%")
					.or(QVideo.video.moTa.like("%"+tukhoa+"%")));
		}
		if(trangthaisoan.equals("true")) {
			q.where(QVideo.video.xuatBan.eq(true));
		}else if(trangthaisoan.equals("false")) {
			q.where(QVideo.video.xuatBan.eq(false));
		}
		q.orderBy(QVideo.video.ngaySua.desc());
		return q;
	}
	
}
