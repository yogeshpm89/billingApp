/**
 * 
 */
package com.prounited.billingapp.services.impl;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prounited.billingapp.constants.Constants;
import com.prounited.billingapp.helpers.Criterion;
import com.prounited.billingapp.helpers.DBSqlQuery;
import com.prounited.billingapp.models.Bill;
import com.prounited.billingapp.models.Category;
import com.prounited.billingapp.services.CategoryService;
import com.prounited.billingapp.services.DBService;
import com.prounited.billingapp.vos.CategoryVO;

/**
 * @author yogeshpm89
 *
 */

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired DBService dbService;
	/* (non-Javadoc)
	 * @see com.prounited.billingapp.services.CategoryService#addCategory(java.lang.String)
	 */
	@Override
	public String addCategory(String categoryJsonString) {
		Gson gson = new Gson();
		Map<String, Object> categoryMap = gson.fromJson(categoryJsonString, HashMap.class);
		Category category = new Category();
		
		category.setCategoryName((String) categoryMap.get(Constants.CATEGORY_NAME));
		category.setCategoryDesc((String) categoryMap.get(Constants.CATEGORY_DESC));
		category.setCreateDate(new Date());
		category.setIsActive("Y");
		
		category.setCreateUser(Constants.SYSTEM_USER);
		category.setUpdateDate(new Date());
		category.setUpdateUser(Constants.SYSTEM_USER);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Session session = dbService.openSession();
		try {
			category = (Category) dbService.saveOrUpdate(session, category, Category.class);
			if (category != null) {
				resultMap.put("success", true);
			} else {
				resultMap.put("success", false);
			}
		} catch (Exception e) {
			resultMap.put("success", false);
			resultMap.put("error", e.getMessage());
		} finally {
			dbService.closeSession(session);
		}
		
		String resultString = gson.toJson(resultMap);
		return resultString;
	}
	
	@Override
	public String editCategory(String categoryJsonString) {
		Gson gson = new Gson();
		Session session = dbService.openSession();
		Map<String, Object> categoryMap = gson.fromJson(categoryJsonString, HashMap.class);
		Long categoryId = Long.parseLong((String) categoryMap.get(Constants.CATEGORY_ID));
		Category category = (Category) session.get(Category.class, categoryId);
		category.setCategoryName((String) categoryMap.get(Constants.CATEGORY_NAME));
		category.setCategoryDesc((String) categoryMap.get(Constants.CATEGORY_DESC));
		category.setIsActive("Y");
		category.setUpdateDate(new Date());
		category.setUpdateUser(Constants.SYSTEM_USER);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			category = (Category) dbService.saveOrUpdate(session, category, Category.class);
			if (category != null) {
				resultMap.put("success", true);
			} else {
				resultMap.put("success", false);
			}
		} catch (Exception e) {
			resultMap.put("success", false);
			resultMap.put("error", e.getMessage());
		} finally {
			dbService.closeSession(session);
		}
		
		String resultString = gson.toJson(resultMap);
		return resultString;
	}

	/* (non-Javadoc)
	 * @see com.prounited.billingapp.services.CategoryService#deleteCategory(java.lang.String)
	 */
	@Override
	public String deleteCategory(String categoryJsonString) {
		Gson gson = new Gson();
		Map<String, String> requestMap = new HashMap<String, String>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Type stringStringMap = new TypeToken<Map<String, String>>() {
		}.getType();

		requestMap = gson.fromJson(categoryJsonString, stringStringMap);
		List<Criterion> criterions = new ArrayList<Criterion>();

		if (requestMap.containsKey(Constants.CATEGORY_ID)) {
			String categoryId = requestMap.get(Constants.CATEGORY_ID);
			long categoryIdL = -1l;
			if (categoryId != null) {
				categoryIdL = Long.parseLong(categoryId);
			}

			if (categoryIdL > 0) {
				criterions.add(new Criterion("categoryId", categoryIdL, "long"));
			}
		}

		String result;
		Session session = dbService.openSession();
		try {
			if (!criterions.isEmpty()) {
				result = (String) dbService.executeUpdateQuery(session, DBSqlQuery.DELETE_ITEMS_FOR_CATEGORY, criterions);
				if (result == "1") {
					result = (String) dbService.executeUpdateQuery(session, DBSqlQuery.DELETE_CATEGORY, criterions);
					if (result == "1") {
						resultMap.put("success", true);
					} else {
						resultMap.put("success", false);
					}
				} else {
					resultMap.put("success", false);
				}
				
				
			}
		} catch (Exception e) {
			resultMap.put("success", false);
			resultMap.put("error", e.getMessage());
		} finally {
			dbService.closeSession(session);
		}
		String resultString = gson.toJson(resultMap);
		return resultString;
	}

	@Override
	public String getAllCategories(String sort, String filter, String query, int page, int limit) {
		Gson gson = new Gson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Category> categories = new ArrayList<Category>();
		
		List<Map<String, Object>> filterMapList = new ArrayList<Map<String, Object>>();
		Map<String, Object> filterMap = new HashMap<String, Object>();
		
		List<Map<String, String>> sortMapList = new ArrayList<Map<String, String>>();
		Map<String, String> sortMap = new HashMap<String, String>();
		
		Type listStringObjectMap = new TypeToken<List<Map<String, Object>>>(){}.getType();
		Type listStringMap = new TypeToken<List<Map<String, String>>>(){}.getType();

		filterMapList = gson.fromJson(filter, listStringObjectMap);
		if (filterMapList == null || filterMapList.isEmpty()) {
			filterMap = new HashMap<String, Object>();
		} else {
			filterMap = filterMapList.get(0);
		}
		sortMapList = gson.fromJson(sort, listStringMap);
		
		if (sortMapList != null && !sortMapList.isEmpty()) {
			sortMap = sortMapList.get(0);
		} else {
			sortMap.put("direction", Constants.SORT_DESC);
			sortMap.put("property", Constants.UPDATE_DATE);
		}
		
		List<Criterion> criterions = new ArrayList<Criterion>();
		if (query != null && !"".equalsIgnoreCase(query)) {
			Criterion criterion = new Criterion();
			criterion.setKey("categoryName");
			criterion.setType("string");
			criterion.setValue(query);
			criterions.add(criterion);
			filterMap.put("categoryName", query);
		}
		
		filterMap.put("isActive", "Y");
		
		Session session = dbService.openSession();
		try {
			int count = dbService.getAllCount(session, Category.class, filterMap);
			if (count > 0) {
				categories = dbService.getAll(session, Category.class, sortMap, filterMap, page, limit);
			}
			
			resultMap.put("success", true);
			resultMap.put("items", CategoryVO.getCategoryVOs(categories));
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

	@Override
	public List<Category> getAllCategories() {
		List<Category> categories = null;
		Map<String, String> sortMap = new HashMap<String, String>();
		sortMap.put("direction", Constants.SORT_DESC);
		sortMap.put("property", Constants.CREATE_DATE);
		
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("isActive", "Y");
		
		Session session = dbService.openSession();
		try {
			int count = dbService.getAllCount(session, Category.class, filterMap);
			if (count > 0) {
				categories = dbService.getAll(session, Category.class, sortMap, filterMap, 1, count);
			}
		} catch (Exception e) {
		} finally {
			dbService.closeSession(session);
		}
		return categories;
	}

}
