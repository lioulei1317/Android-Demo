package com.example.t20160505b_bunder01;

import java.io.Serializable;

public class Bunder002 implements Serializable {
	private String name;
	private String passwd;
	private String sex;

	public Bunder002(String name, String passwd, String sex) {
		this.name = name;
		this.passwd = passwd;
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
