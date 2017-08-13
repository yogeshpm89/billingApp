package com.prounited.billingapp.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.prounited.billingapp.helpers.Criterion;
import com.prounited.billingapp.models.AdminSetting;
import com.prounited.billingapp.services.AdminService;
import com.prounited.billingapp.services.DBService;
import com.prounited.billingapp.vos.AdminSettingVO;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired DBService dbService;
	
	@Override
	public String updateSetting(String requestJson) {
		Gson gson = new Gson();
		Map<String, Object> settingMap = gson.fromJson(requestJson, HashMap.class);
		
		List<Object> adminSettings = new ArrayList<Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Set<Map.Entry<String, Object>> entrySet = settingMap.entrySet();
		Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
		while(iterator.hasNext()) {
			AdminSetting adminSetting = new AdminSetting();
			Map.Entry<String, Object> entry = iterator.next();
			adminSetting.setSettingKey(entry.getKey());
			adminSetting.setSettingValue(entry.getValue().toString());
			adminSettings.add(adminSetting);
		}
		Session session = dbService.openSession();
		try {
			adminSettings = dbService.bulkSaveOrUpdate(session, adminSettings);
			resultMap.put("success", true);
			
			List<AdminSetting> list = new ArrayList<AdminSetting>();
			for(Object setting: adminSettings) {
				list.add((AdminSetting) setting);
			}
			resultMap.put("items", AdminSettingVO.getAdminSettingVOs(list));
		} catch (Exception e) {
			resultMap.put("success", false);
			resultMap.put("error", e.getMessage());
		} finally {
			dbService.closeSession(session);
		}
		return gson.toJson(resultMap);
	}

	@Override
	public String getSettings() {

		Gson gson = new Gson();
		List<AdminSetting> adminSettings = new ArrayList<AdminSetting>();
		List<Criterion> criterions = new ArrayList<Criterion>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Session session = dbService.openSession();
		try {
			int count = dbService.getAllCount(session, AdminSetting.class, null);
			if (count > 0) {
				adminSettings = dbService.getAll(session, AdminSetting.class, null, criterions, 0, 0);
			}
			resultMap.put("success", true);
			resultMap.put("items", AdminSettingVO.getAdminSettingVOs(adminSettings));
			resultMap.put("total", count);
		} catch (Exception e) {
			resultMap.put("success", false);
			resultMap.put("error", e.getMessage());
		} finally {
			dbService.closeSession(session);
		}
		
		String resultString = gson.toJson(resultMap);
		return resultString;
	}

}
