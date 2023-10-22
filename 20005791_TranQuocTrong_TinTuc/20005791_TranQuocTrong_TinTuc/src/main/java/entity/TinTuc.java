package entity;

public class TinTuc {
	private int maTT;
	private String tieuDe;
	private String noiDung;
	private String lienKet;
	private DanhMuc danhMuc;
	public TinTuc(int maTT, String tieuDe, String noiDung, String lienKet, DanhMuc danhMuc) {
		super();
		this.maTT = maTT;
		this.tieuDe = tieuDe;
		this.noiDung = noiDung;
		this.lienKet = lienKet;
		this.danhMuc = danhMuc;
	}
	public TinTuc(String tieuDe, String noiDung, String lienKet, DanhMuc danhMuc) {
		super();
		this.tieuDe = tieuDe;
		this.noiDung = noiDung;
		this.lienKet = lienKet;
		this.danhMuc = danhMuc;
	}
	
	
	
	public TinTuc(int maTT, String tieuDe, String noiDung, String lienKet) {
		super();
		this.maTT = maTT;
		this.tieuDe = tieuDe;
		this.noiDung = noiDung;
		this.lienKet = lienKet;
	}
	public TinTuc() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getMaTT() {
		return maTT;
	}
	public void setMaTT(int maTT) {
		this.maTT = maTT;
	}
	public String getTieuDe() {
		return tieuDe;
	}
	public void setTieuDe(String tieuDe) {
		this.tieuDe = tieuDe;
	}
	public String getNoiDung() {
		return noiDung;
	}
	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}
	public String getLienKet() {
		return lienKet;
	}
	public void setLienKet(String lienKet) {
		this.lienKet = lienKet;
	}
	public DanhMuc getDanhMuc() {
		return danhMuc;
	}
	public void setDanhMuc(DanhMuc danhMuc) {
		this.danhMuc = danhMuc;
	}
	@Override
	public String toString() {
		return "TinTuc [maTT=" + maTT + ", tieuDe=" + tieuDe + ", noiDung=" + noiDung + ", lienKet=" + lienKet
				+ ", danhMuc=" + danhMuc + "]";
	}
	
	
}
