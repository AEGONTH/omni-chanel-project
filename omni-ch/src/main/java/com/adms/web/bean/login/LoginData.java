package com.adms.web.bean.login;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class LoginData implements Serializable {

	private static final long serialVersionUID = -7623462791068302403L;

	private List<String> onlineUsers;

	public List<String> getOnlineUsers() {
		return onlineUsers;
	}

	public void setOnlineUsers(List<String> onlineUsers) {
		this.onlineUsers = onlineUsers;
	}
}
