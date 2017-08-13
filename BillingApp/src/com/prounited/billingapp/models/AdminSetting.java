package com.prounited.billingapp.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="admin_settings")
public class AdminSetting implements java.io.Serializable {

	@Id
	@Column(name = "setting_key", nullable = false)
	private String settingKey;
	
	@Column(name = "setting_value", nullable = false)
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
	
}
