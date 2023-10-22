package entity;

public class DanhMuc {
	private int maDM;
	private String tenDM;
	private String nguoiQL;
	private String ghiChu;
	public DanhMuc(int maDM, String tenDM, String nguoiQL, String ghiChu) {
		super();
		this.maDM = maDM;
		this.tenDM = tenDM;
		this.nguoiQL = nguoiQL;
		this.ghiChu = ghiChu;
	}
	public DanhMuc(String tenDM, String nguoiQL, String ghiChu) {
		super();
		this.tenDM = tenDM;
		this.nguoiQL = nguoiQL;
		this.ghiChu = ghiChu;
	}
	public DanhMuc() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getMaDM() {
		return maDM;
	}
	public void setMaDM(int maDM) {
		this.maDM = maDM;
	}
	public String getTenDM() {
		return tenDM;
	}
	public void setTenDM(String tenDM) {
		this.tenDM = tenDM;
	}
	public String getNguoiQL() {
		return nguoiQL;
	}
	public void setNguoiQL(String nguoiQL) {
		this.nguoiQL = nguoiQL;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	@Override
	public String toString() {
		return "DanhMuc [maDM=" + maDM + ", tenDM=" + tenDM + ", nguoiQL=" + nguoiQL + ", ghiChu=" + ghiChu + "]";
	}
	
}
