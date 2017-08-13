Ext.define('BillingApp.controller.BillingControllerHelper', {
	alternateClassName: ['BillingControllerHelper'],
	singleton: true,
	tryAgainMessage: '<br>There is an error on server, please try restarting server.',
	serverErrorMessage: 'Server error : ',
	failure: function(json, extraparams) {
    	 Ext.Msg.alert('Error', json);
    	 Ext.getBody().unmask();
     },
     
     getAppSettingsSuccess: function(json, extraParams) {
     	json = Ext.decode(json);
     	var settingArray = json.items;
     	Ext.Array.each(settingArray, function(setting, index) {
     		Helper.settingsMap.add(setting.settingKey, setting.settingValue);
		});
		Ext.getBody().unmask();
     },
     
     saveAppSettingsSuccess: function(json, extraParams) {
      		 json = Ext.decode(json);
	    	 if (json.success) {
	    		 	Ext.Msg.alert('Success', 'Settings updated successfully.');
	    		 	var settingArray = json.items;
			     	Ext.Array.each(settingArray, function(setting, index) {
			     		Helper.settingsMap.add(setting.settingKey, setting.settingValue);
					});
	    	 } else {
	    	 		 Ext.Msg.alert('Error', BillingControllerHelper.tryAgainMessage + json.error + BillingControllerHelper.tryAgainMessage);
	    	 }
	    	 Ext.getBody().unmask();
     },
     
	 addCategorySuccess: function(json, extraParams) {
    	 json = Ext.decode(json);
    	 if (json.success) {
    		 var form = extraParams.get('form');
    		 var isEdit = extraParams.get('isEdit');
    		 form.reset();
    		 if (!Ext.isEmpty(isEdit) && isEdit) {
    		 	extraParams.get('button').up('editCategoryWindow').destroy();
    		 	Ext.Msg.alert('Success', 'Category edited successfully.');
    		 } else {
    		 	Ext.Msg.alert('Success', 'Category added successfully.');
    		 }
    		 BillingControllerHelper.refreshGrid('category');
    	 } else {
    	 		 Ext.Msg.alert('Error', BillingControllerHelper.tryAgainMessage + json.error + BillingControllerHelper.tryAgainMessage);
    	 }
    	 Ext.getBody().unmask();
     },
     
    deleteCategory: function(categoryId) {
    	 var extraParams = new Ext.util.HashMap();
    	 var inputParams = new Ext.util.HashMap(); 
    	 inputParams.add('categoryId', categoryId);
    	 var requestParams = new Ext.util.HashMap(); 
    	 requestParams.add(Service.REQUEST_JSON, Ext.encode(inputParams.map));
    	 Service.sendRequest(Service.DELETE_CATEGORY, requestParams.map, Service.POST, BillingControllerHelper.deleteCategorySuccess, BillingControllerHelper.failure, extraParams);
     },
     
	deleteCategorySuccess: function(json, extraParams) {
    	 json = Ext.decode(json);
    	 if (json.success) {
    		 	Ext.Msg.alert('Success', 'Category deleted successfully.');
    		 	BillingControllerHelper.refreshGrid('category');
    	 } else {
    	 		 Ext.Msg.alert('Error', BillingControllerHelper.tryAgainMessage + json.error + BillingControllerHelper.tryAgainMessage);
    	 }
    	 Ext.getBody().unmask();
     },
     
     addItemSuccess: function(json, extraParams) {
    	 json = Ext.decode(json);
    	 if (json.success) {
    		 var form = extraParams.get('form');
    		 var isEdit = extraParams.get('isEdit');
    		 form.reset();
    		 if (!Ext.isEmpty(isEdit) && isEdit) {
    		 	extraParams.get('button').up('editItemWindow').destroy();
    		 	Ext.Msg.alert('Success', 'Item edited successfully.');
    		 } else {
    		 	Ext.Msg.alert('Success', 'Item added successfully.');
    		 }
    		 BillingControllerHelper.refreshGrid('item');
    	 } else {
    	 		 Ext.Msg.alert('Error', BillingControllerHelper.tryAgainMessage + json.error + BillingControllerHelper.tryAgainMessage);
    	 }
    	 Ext.getBody().unmask();
     },
     
	deleteItem: function(itemId) {
    	 var extraParams = new Ext.util.HashMap();
    	 var inputParams = new Ext.util.HashMap(); 
    	 inputParams.add('itemId', itemId);
    	 
    	 var requestParams = new Ext.util.HashMap(); 
    	 requestParams.add(Service.REQUEST_JSON, Ext.encode(inputParams.map));
    	 Service.sendRequest(Service.DELETE_ITEM, requestParams.map, Service.POST, BillingControllerHelper.deleteItemSuccess, BillingControllerHelper.failure, extraParams);
     },
     
     deleteItemSuccess: function(json, extraParams) {
    	 json = Ext.decode(json);
    	 if (json.success) {
    		 	Ext.Msg.alert('Success', 'Item deleted successfully.');
    		 	BillingControllerHelper.refreshGrid('item');
    	 } else {
    	 		 Ext.Msg.alert('Error', BillingControllerHelper.tryAgainMessage + json.error + BillingControllerHelper.tryAgainMessage);
    	 }
    	 Ext.getBody().unmask();
     },
     
	addCustomerSuccess: function(json, extraParams) {
    	 json = Ext.decode(json);
    	 if (json.success) {
    		 Ext.Msg.alert('Success', json.message);
    		 var form = extraParams.get('form');
    		 var isEdit = extraParams.get('isEdit');
    		 
    		 var customerId = json.customerId;
    		 var parentWindow = extraParams.get('parentWindow');
    		 if (!Ext.isEmpty(parentWindow) && parentWindow.getItemId() === 'createBillWindowItemId'){
    		 	var customerIdText = parentWindow.down('textfield[name=customerId]');
    		 	customerIdText.setValue(customerId);
    		 } else {
    		 	form.reset();
    		 	if (!Ext.isEmpty(isEdit) && isEdit) {
	    		 	extraParams.get('button').up('editCustomerWindow').destroy();
	    		 	Ext.Msg.alert('Success', 'Customer edited successfully.');
	    		 } else {
	    		 	Ext.Msg.alert('Success', 'Customer added successfully.');
	    		 }
    		 	BillingControllerHelper.refreshGrid('customer');
    		 }
    	 } else {
    	 		 Ext.Msg.alert('Error', BillingControllerHelper.tryAgainMessage + json.error + BillingControllerHelper.tryAgainMessage);
    	 }
    	 Ext.getBody().unmask();
     },
     
     deleteCustomer: function(customerId) {
	    	 var extraParams = new Ext.util.HashMap();
	    	 var inputParams = new Ext.util.HashMap(); 
	    	 inputParams.add('customerId', customerId);
	    	 
	    	 var requestParams = new Ext.util.HashMap(); 
	    	 requestParams.add(Service.REQUEST_JSON, Ext.encode(inputParams.map));
	    	 Service.sendRequest(Service.DELETE_CUSTOMER, requestParams.map, Service.POST, BillingControllerHelper.deleteCustomerSuccess, BillingControllerHelper.failure, extraParams);
	},
    	 
     deleteCustomerSuccess: function(json, extraParams) {
    	 json = Ext.decode(json);
    	 if (json.success) {
    		 	Ext.Msg.alert('Success', 'Customer deleted successfully.');
    		 	BillingControllerHelper.refreshGrid('customer');
    	 } else {
    	 		 Ext.Msg.alert('Error', BillingControllerHelper.tryAgainMessage + json.error + BillingControllerHelper.tryAgainMessage);
    	 }
    	 Ext.getBody().unmask();
     },
       
	refreshGrid: function(gridType) {
    	 	switch(gridType) {
    	 		case 'category':
    	 			var categoryGrid = Ext.ComponentQuery.query('categoryGrid')[0];
    				var categoryGridStore = categoryGrid.getStore();
    				categoryGridStore.load();
    	 			break;
    	 		case 'item':
    	 			var itemGrid = Ext.ComponentQuery.query('itemGrid')[0];
    				var itemGridStore = itemGrid.getStore();
    				itemGridStore.load();
    	 			break;
    	 		case 'customer':
    				var customerGrid = Ext.ComponentQuery.query('customerGrid')[0];
    				var customerGridStore = customerGrid.getStore();
    				customerGridStore.load();
    	 			break;
    	 		case 'bill':
    				var billGrid = Ext.ComponentQuery.query('billGrid')[0];
    				var billGridStore = billGrid.getStore();
    				billGridStore.load();
    	 			break;
    	 	}
     },
     
     
	nextBillIdSuccess: function(json, extraParams) {
     	json = Ext.decode(json);
     	if (json.success) {
     		var createBillWindow = extraParams.get('createBillWindow');
     		var billForm = createBillWindow.down('billForm');
     		var billIdField = billForm.down('textfield[name=billId]');
     		var billId = json.billId;
     		
     		billIdField.setValue(billId);
     	}
     	Ext.getBody().unmask();
	},
    
    addBillSuccess: function(json, extraParams) {
     	json = Ext.decode(json);
     	if (json.success) {
     		var button = extraParams.get('button');
     		var saveButton, saveAndPrintButton;
     		if (button.getItemId() == 'saveAndPrintBillButton') {
    	 		BillingApp.app.getController('BillingController').printBill(button);
    	 		var createBillPanel = button.up('createBillPanel');
    	 		saveButton = createBillPanel.down('button[itemId=saveBillButton]');
    	 		saveAndPrintButton = createBillPanel.down('button[itemId=saveAndPrintBillButton]');
    	 		saveAndPrintButton.setDisabled(true);
    	 	} else {
    	 		Ext.Msg.alert('Success', json.message);
    	 		saveButton = button;
    	 	}
    	 	saveButton.setDisabled(true);
     	} else {
     		Ext.Msg.alert('Error', BillingControllerHelper.tryAgainMessage + json.error + BillingControllerHelper.tryAgainMessage);
     	}
     	Ext.getBody().unmask();
	}, 	
	
	deleteBill: function(billId) {
			Ext.Msg.show({
			    title:'Confirm Delete Bill',
			    message: 'Do you want to delete bill: ' + billId,
			    buttons: Ext.Msg.YESNO,
			    icon: Ext.Msg.QUESTION,
			    fn: function(btn) {
			        if (btn === 'yes') {
			             var extraParams = new Ext.util.HashMap();
				    	 var inputParams = new Ext.util.HashMap(); 
				    	 inputParams.add('billId', billId);
				    	 
				    	 var requestParams = new Ext.util.HashMap(); 
				    	 requestParams.add(Service.REQUEST_JSON, Ext.encode(inputParams.map));
				    	 Service.sendRequest(Service.DELETE_BILL, requestParams.map, Service.POST, BillingControllerHelper.deleteBillSuccess, BillingControllerHelper.failure, extraParams);
			        }
			    }
			});
	},
	
	deleteBillSuccess: function(json, extraParams) {
    	 json = Ext.decode(json);
    	 if (json.success) {
    		 	Ext.Msg.alert('Success', 'Bill deleted successfully.');
    		 	BillingControllerHelper.refreshGrid('bill');
    	 } else {
    	 		 Ext.Msg.alert('Error', BillingControllerHelper.tryAgainMessage + json.error + BillingControllerHelper.tryAgainMessage);
    	 }
    	 Ext.getBody().unmask();
     },
     
     getItemDetailSuccess: function(json, extraParams) {
     		json = Ext.decode(json);
     		if (json.success) {
     			var billItemForm = extraParams.get('billItemForm');
     			
     			var itemDesc = billItemForm.down('textfield[name=itemDesc]');
     			var itemCode = billItemForm.down('textfield[name=itemCode]');
     			var billItemQuantityAvailable = billItemForm.down('textfield[name=billItemQuantityAvailable]');
     			var billItemQuantity = billItemForm.down('textfield[name=billItemQuantity]');
     			var unitPrice = billItemForm.down('textfield[name=unitPrice]');
     			var billItemDiscount = billItemForm.down('textfield[name=billItemDiscount]');
     			
     			var item = json.item;
     			itemDesc.setValue(item.itemDesc);
     			itemCode.setValue(item.itemCode);
     			billItemQuantityAvailable.setValue(item.itemQuantity);
     			billItemQuantity.setValue(1);
     			unitPrice.setValue(item.unitPrice);
     			billItemDiscount.setValue(0);
     		}
     		Ext.getBody().unmask();
     },
     
     
     getBillItemHTML: function(store, billDiscount) {
     	var html = '<style>td{font-size:14px; padding: 0px 5px 0px 5px;}</style>';
     	html = html + '<table style="width:100%; border-collapse: collapse;" cellpadding="2" border="1">';
     	html = html + '<tr><th></th><th style="width:300px;">Item</th><th style="width:120px;">Quantity</th><th>Total&nbsp;(Rs)</th><th>Discount&nbsp;(%)</th><th>Final&nbsp;(Rs)</th></tr>';
     	var total1 = 0;
     	var total = 0;
     	
     	for (var i=0; i<10;i++) {
     		var record = store.getAt(i);
     		html = html + '<tr>';
     		html = html + '<td>' + (i+1) + '</td>';
     		
     		if (Ext.isEmpty(record)) {
     			html = html + '<td></td><td></td><td></td><td></td><td></td></tr>';
     		} else {
	     		html = html + '<td>' + record.get('itemName') + '</td>';
	     		html = html + '<td style="text-align:right">' + record.get('itemQuantity') + '</td>';
	     		html = html + '<td style="text-align:right">' + record.get('totalAmount') + '</td>';
	     		html = html + '<td style="text-align:right">' + record.get('itemDiscount') + '</td>';
	     		html = html + '<td style="text-align:right">' + record.get('finalAmount') + '</td>';
	     		html = html + '</tr>';
	     		total = total + record.get('finalAmount');
	     		total1 = total1 + record.get('totalAmount');
     		}
     		
     	}
     	/*store.each(function(record,idx){
     		html = html + '<tr>';
     		html = html + '<td>' + (idx+1) + '</td>';
     		html = html + '<td>' + record.get('itemName') + '</td>';
     		html = html + '<td style="text-align:right">' + record.get('itemQuantity') + '</td>';
     		html = html + '<td style="text-align:right">' + record.get('itemDiscount') + '</td>';
     		html = html + '<td style="text-align:right">' + record.get('totalAmount') + '</td>';
     		html = html + '<td style="text-align:right">' + record.get('finalAmount') + '</td>';
     		html = html + '</tr>';
     		
     		total = total + record.get('finalAmount');
     	});*/
     	var vat = Helper.settingsMap.get('VAT');
     	var vatValue = (total * vat)/100;
     	vatValue = Math.ceil(parseFloat(Ext.Number.toFixed(vatValue, 2)));
     	total = total + vatValue;
     	total = Math.ceil(parseFloat(Ext.Number.toFixed(total, 2)));
     	
     	html = html + '<tr><td></td><td></td><td style="text-align:right;"><b>GST&nbsp;(%)</b></td><td style="text-align:right">' + vat + '</td><td style="text-align:right;"><b>GST (Rs)</b></td><td style="text-align:right">' +  vatValue + '</td></tr>';
     	html = html + '<tr><td></td><td></td><td style="text-align:right;"><b>Total&nbsp;(Rs)</b></td><td style="text-align:right">' +  total1 + '</td><td style="text-align:right;"><b>Final&nbsp;(Rs)</b></td><td style="text-align:right">' + Math.ceil(total) + '</td></tr>'
     	html = html + '<tr><td></td><td></td><td style="text-align:right;"><b>Discount&nbsp;(Rs)</b></td><td style="text-align:right">' + billDiscount + '</td><td style="text-align:right;"><b>Grand&nbsp;Total&nbsp;(Rs)</b></td><td style="text-align:right">' + Math.ceil((total - billDiscount)) + '</td></tr>';
    	html = html + '</table>';
     	return html;
     },
    
    
    getPrintHTML: function(customerName, phone, address, CreateNewBillItemListHTML, billNumber) {
     	return '<title>Print Bill</title><div id="printDiv">' +
 					'<table style="width:100%;">' +
 						'<tr><td colspan=4>' + 
 							'<p style="text-align:center; font-size:30px; font-weight:bold; ">' +
								'<span style="text-transform: uppercase;">' + Helper.settingsMap.get('SHOP_NAME') + '</span>' +
								'<span style="float:right; font-size:15px; font-weight:normal; vertical-align:top;">Bill No.:' + billNumber + '</span>' +
							'</p>' +
						'</tr>' +
 						'<tr><td colspan=4><p style="text-align:center; font-size:15px; font-weight:normal;">' + 
 							Helper.settingsMap.get('SHOP_ADDRESS') + '</p>' +
 						'</td></tr>' +
 						'<tr style="margin:0px; padding: 0px;">' + 
 							'<td>Customer name:</td><td style="text-align:left;">' + customerName + '</td>' +
 							'<td>Date:</td><td>' + new Date().toLocaleDateString() + '</td>' +
 						'</tr>' +
 						'<tr style="margin:0px; padding: 0px;">' +
 							'<td>Customer phone:</td><td style="text-align:left;">' + phone + '</td>' +
 							'<td>Address:</td><td>' + address + '</td>' +
 						'</tr>' +
 					'</table>' +
 					CreateNewBillItemListHTML + 
 					//'<ul>' +
 						//'<li>Exchange will be made within 2 days only</li>' + 
 						//'<li>Alteration and used items will not be returned</li>' + 
 						//'<li>No exchange</li>' + 
 					//'</ul>' +
 					'<span><i>Note: No exchange, No return</i></span>' + 
 					//'<table style="width:100%;"><tr><td>Shop Owner</td><td></td><td>Customer</td><td></td></tr>' +
 					//	'<tr><td>Name: </td><td></td><td>Name:</td><td></td></tr>' +
 					//	'<tr><td>Sign: </td><td></td><td>Sign:</td><td></td></tr>' +
 					//'</table>' +
 					'<p style="bottom: 0;left: 0;position:fixed;right: 0;text-align: right;">' +
 						Helper.settingsMap.get('SHOP_NAME') +
 					'</p>' +
 				'</div>';
     },
     
     printDiv: function(html) {
         //var printContents = document.getElementById(divName).innerHTML;
         //var originalContents = document.body.innerHTML;
     	 var iFrameId = "printerFrame";
          var printFrame = Ext.get(iFrameId);
   
          if (printFrame == null) {
              printFrame = Ext.getBody().appendChild({
                  id: iFrameId,
                  tag: 'iframe',
                  cls: 'x-hidden',
                  style: {
                      display: "none"
                  }
              });
          }
          
          var cw = printFrame.dom.contentWindow;
          
          // instantiate application stylesheets in the hidden iframe
          var stylesheets = "";
          for (var i = 0; i < document.styleSheets.length; i++) {
              stylesheets += Ext.String.format('<link rel="stylesheet" href="{0}" />', document.styleSheets[i].href);
          }
   
          // various style overrides
          stylesheets += ''.concat(
            "<style>", 
              ".x-panel-body {overflow: visible !important;}",
              // experimental - page break after embedded panels
              // .x-panel {page-break-after: always; margin-top: 10px}",
            "</style>"
           );
          
          // get the contents of the panel and remove hardcoded overflow properties
          var markup = html; 
          	//"<br>-----x-----x-----x-----x-----x-----x-----x-----x-----x-----x-----x-----x-----x-----x-----x-----x-----x-----x-----x-----<br>" 
          //	+ html; //pnl.getEl().dom.innerHTML;
          while (markup.indexOf('overflow: auto;') >= 0) {
              markup = markup.replace('overflow: auto;', '');
          }
   
          var str = Ext.String.format('<html><head>{0}</head><body>{1}</body></html>',stylesheets,markup);
   
   
   		var wnd = window.open("about:blank", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,location=no,top=0");
		wnd.document.write(str);
          // output to the iframe
          //cw.document.open();
          //cw.document.write(str);
         // cw.document.close();
   
          // remove style attrib that has hardcoded height property
          // cw.document.getElementsByTagName('DIV')[0].removeAttribute('style');
          // print the iframe
          //cw.print();
          // destroy the iframe
          Ext.fly(iFrameId).destroy();
          //document.body.innerHTML = html;
          //window.print();
          //document.body.innerHTML = originalContents;
    },
    
    
     updateBillTotal: function() {
    	 var billTotal = 0;
    	 
    	 var billItemGrid = Ext.ComponentQuery.query('billItemGrid')[0];
    	 var billItemGridStore = billItemGrid.getStore();
    	 billItemGridStore.each(function(rec) {
    		 billTotal = billTotal + rec.get('finalAmount');
    	 });
    	 
    	
    	 var vat = Ext.ComponentQuery.query('textfield[itemId=vat]')[0];
    	 vat = vat.getValue();

		 var vatTotal = (billTotal * vat)/100;
    	 vatTotal = Math.ceil(parseFloat(Ext.Number.toFixed(vatTotal, 2)));
    	 var vatTotalField = Ext.ComponentQuery.query('textfield[itemId=vatTotal]')[0];
    	 vatTotalField.setValue(vatTotal);
    	     	 
    	 billTotal = billTotal + (billTotal * vat)/100;
    	 var billTotalField = Ext.ComponentQuery.query('textfield[itemId=billTotal]')[0];
		 var billDiscountField = Ext.ComponentQuery.query('textfield[itemId=billDiscount]')[0];
		 //billTotal = Ext.Number.toFixed(billTotal, 2);
		 var billDiscount = billDiscountField.getValue();
		 if (Ext.isEmpty(billDiscount)) {
		 	billDiscount = 0; 
		 } else {
		 	billDiscount = parseInt(billDiscount );
		 }
		 billTotal = Math.ceil(parseFloat(Ext.Number.toFixed(billTotal, 2))) - billDiscount;
    	 billTotalField.setValue(Math.ceil(parseFloat(Ext.Number.toFixed(billTotal, 2))));
     },
     
     showBills: function(row) {
     	var customerGrid = Ext.ComponentQuery.query('customerGrid')[0];
     	var customerGridStore = customerGrid.getStore();
     	var record = customerGridStore.getAt(row);
     	
     	var customerId = record.get("customerId");
     	var firstName = record.get("firstName");
     	var lastName = record.get("lastName");
     	var address = record.get("address");
     	
     	var billsWindow = Ext.create('BillingApp.view.bill.BillsWindow', {
     		alwaysOnTop: true,
			modal: true,
		    title: 'Bills of Customer: ' + customerId + '  ' + firstName + ' ' + lastName + ', ' + address,
		    layout: 'fit',
     	});
     	
     	var billGrid = billsWindow.down('billGrid');
     	var billStore = billGrid.getStore();
     	var customerFilter = new Ext.util.Filter({
     		id: 'customerIdFilter',
		    property: 'customerId',
		    value   : '' + customerId
		});
		billStore.addFilter(customerFilter);
		billsWindow.show();
     },
     
     reArrangeBillNumbersSuccess:  function(json, extraParams) {
      		 json = Ext.decode(json);
	    	 if (json.success) {
	    		 	Ext.Msg.alert('Success', 'Bills updated successfully.');
	    	 } else {
	    	 		 Ext.Msg.alert('Error', BillingControllerHelper.tryAgainMessage + json.error + BillingControllerHelper.tryAgainMessage);
	    	 }
	    	 BillingControllerHelper.refreshGrid('bill');
	    	 Ext.ComponentQuery.query('billsWindow')[0].unmask();
     },
     
     excelDownloadSuccess: function(json, extraParams) {
     	BillingControllerHelper.onExportSuccess(json);
     },
     
     onExportSuccess: function(response){
    	//this.getView().unmask("Loading...");
     var disposition = response.getResponseHeader('Content-Disposition');
     var filename = disposition.slice(disposition.indexOf("=")+1,disposition.length);
     var type = response.getResponseHeader('Content-Type');
     var blob = new Blob([response.responseText], { type: type });
     if (typeof window.navigator.msSaveBlob !== 'undefined') {
        // IE workaround for "HTML7007: One or more blob URLs were revoked by closing the blob for which they were created These URLs will no longer resolve as the data backing the URL has been freed."
        window.navigator.msSaveBlob(blob, filename);
     } 
     else {
        var URL = window.URL || window.webkitURL;
        var downloadUrl = URL.createObjectURL(blob);
        if (filename) {
            // use HTML5 a[download] attribute to specify filename
            var a = document.createElement("a");
            // safari doesn't support this yet
            a.href = downloadUrl;
            a.download = filename;
            document.body.appendChild(a);
            a.click();
        } 
        setTimeout(function () { URL.revokeObjectURL(downloadUrl); }, 100); // cleanup
    }    
}
     
});