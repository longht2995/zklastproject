package vn.toancauxanh.cms.service;

import java.util.Date;

import org.apache.commons.collections.MapUtils;
import org.zkoss.util.resource.Labels;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.gg.model.QVanBan;
import vn.toancauxanh.gg.model.VanBan;
import vn.toancauxanh.service.BasicService;

public class VanBanService extends BasicService<VanBan>{
	public JPAQuery<VanBan> getTargetQuery(){
		String tukhoa = MapUtils.getString(argDeco(),Labels.getLabel("param.tukhoa"),"").trim();
		Long loaivanban = (Long) argDeco().get(Labels.getLabel("param.loaivanban"));
		Long linhvuc = (Long) argDeco().get(Labels.getLabel("param.linhvuc"));
		Long coquanbanhanh = (Long) argDeco().get(Labels.getLabel("param.coquanbanhanh"));
		Long capbanhanh = (Long) argDeco().get(Labels.getLabel("param.capbanhanh"));
		String trangthaixuatban = MapUtils.getString(argDeco(),Labels.getLabel("param.trangthai"),"").trim();
		Date from = (Date) argDeco().get(Labels.getLabel("param.tungay"));
		Date to = (Date) argDeco().get(Labels.getLabel("param.denngay"));
		
		JPAQuery<VanBan> q = find(VanBan.class)
				.where(QVanBan.vanBan.trangThai.ne(core().TT_DA_XOA));
		if(tukhoa!=null && !tukhoa.isEmpty()) {
			q.where(QVanBan.vanBan.soKyHieu.like("%"+tukhoa+"%")
				.or(QVanBan.vanBan.trichYeu.like("%"+tukhoa+"%")));
		}
		if(loaivanban!=null) {
			q.where(QVanBan.vanBan.loaiVanBan.id.eq(loaivanban));
		}
		if(linhvuc!=null) {
			q.where(QVanBan.vanBan.linhVucVanBan.id.eq(linhvuc));
		}
		if(coquanbanhanh!=null) {
			q.where(QVanBan.vanBan.coQuanBanHanh.id.eq(coquanbanhanh));
		}
		if(capbanhanh!=null) {
			q.where(QVanBan.vanBan.capBanHanh.id.eq(capbanhanh));
		}
		if(trangthaixuatban!=null && !trangthaixuatban.isEmpty()) {
			System.out.println(trangthaixuatban);
			q.where(QVanBan.vanBan.trangThai.eq(trangthaixuatban));
		}
		if(from!=null) {
			q.where(QVanBan.vanBan.ngayBanHanh.goe(from));
		}
		if(to!=null) {
			q.where(QVanBan.vanBan.ngayBanHanh.loe(to));
		}
		q.orderBy(QVanBan.vanBan.ngaySua.desc());
		return q;
	}
	
}
