package test;

import javax.annotation.Resource;
import javax.sql.DataSource;

import dao.DanhMucDAO;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataSource dataSource = null;
		DanhMucDAO danhMucDAO = new DanhMucDAO(dataSource);
	}

}
