package com.prounited.billingapp.services;

import java.util.List;

public interface ExcelExportService {

	public <T> byte[] writeListToExcel(String fileName, List<T> list, Class<T> entityClass) throws Exception;
	public byte[] downloadExcel(String itemJsonString);
}
