package com.prounited.billingapp.services;

import org.springframework.stereotype.Service;

@Service("adminService")
public interface AdminService {

	public String updateSetting(String requestJson);
	public String getSettings();

}
