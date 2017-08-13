package com.prounited.billingapp.vos;

import java.util.ArrayList;
import java.util.List;

import com.prounited.billingapp.models.AdminSetting;
import com.prounited.billingapp.models.Bill;
import com.prounited.billingapp.models.Customer;

public class AdminSettingVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String settingKey;
	private String settingValue;

	public String getSettingKey() {
		return settingKey;
	}

	public void setSettingKey(String settingKey) {
		this.settingKey = settingKey;
	}

	public String getSettingValue() {
		return settingValue;
	}

	public void setSettingValue(String settingValue) {
		this.settingValue = settingValue;
	}

	@Override
	public String toString() {
		return "AdminSetting [settingKey="
				+ settingKey + ", settingValue=" + settingValue + "]";
	}
	
	public static AdminSettingVO getAdminSettingVO(AdminSetting adminSetting) {
		AdminSettingVO adminSettingVO = new AdminSettingVO();

		adminSettingVO.setSettingKey(adminSetting.getSettingKey());
		adminSettingVO.setSettingValue(adminSetting.getSettingValue());
		return adminSettingVO;
	}
	
	public static List<AdminSettingVO> getAdminSettingVOs(List<AdminSetting> adminSettings) {
		List<AdminSettingVO> adminSettingVOs = new ArrayList<AdminSettingVO>();
		for (AdminSetting adminSetting: adminSettings) {
			adminSettingVOs.add(AdminSettingVO.getAdminSettingVO(adminSetting));
		}
		return adminSettingVOs;
	}
}
