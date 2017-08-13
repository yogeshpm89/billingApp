Ext.define('BillingApp.controller.BillingController', {
    extend: 'Ext.app.Controller',
    id: 'billingController',
    config : {
		refs: [
         	{ref: 'mainPanel', selector: 'mainPanel'},
         	{ref: 'mainPanelWindow', selector: 'mainPanelWindow'},
         	{ref: 'categoryPanel', selector: 'categoryPanel'},
         	{ref: 'categoryWindow', selector: 'categoryWindow'},
         	{ref: 'categoryGrid', selector: 'categoryGrid'},
         	{ref: 'itemPanel', selector: 'itemPanel'},
         	{ref: 'itemWindow', selector: 'itemWindow'},
         	{ref: 'itemGrid', selector: 'itemGrid'},
         	{ref: 'customerPanel', selector: 'customerPanel'},
         	{ref: 'customerWindow', selector: 'customerWindow'},
         	{ref: 'customerGrid', selector: 'customerGrid'},
         	{ref: 'customerGridWindow', selector: 'customerGridWindow'},
         	{ref: 'createBillPanel', selector: 'createBillPanel'},
        	{ref: 'createBillWindow', selector: 'createBillWindow'},
        	{ref: 'createBillItemCombo', selector: 'combo[itemId=billItemId]'},
        	{ref: 'billTotal', selector: 'textfield[itemId=billTotal]'},
        	{ref: 'vat', selector: 'textfield[itemId=vat]'},
        	{ref: 'vatTotal', selector: 'textfield[itemId=vatTotal]'},
        	{ref: 'billItemGrid', selector: 'billItemGrid'},
        	{ref: 'addBillItemButton', selector: 'button[itemId=AddBillItemButton]'},
        	{ref: 'searchCustomerGrid', selector: 'customerGrid[itemId=searchCustomerGrid]'},
        	{ref: 'billsWindow', selector: 'billsWindow'},
        	{ref: 'billGrid', selector: 'billGrid'},
        	{ref: 'adminWindow', selector: 'adminWindow'},
        	{ref: 'adminForm', selector: 'adminForm'},
        	{ref: 'saveAdminButton', selector: 'button[itemId=saveAdminButton]'},
        	
        	{ref: 'billDiscount', selector: 'numberfield[itemId=billDiscount]'},
        	{ref: 'reArrangeBillNumbers', selector: 'button[itemId=reArrangeBillNumbers]'},
        	
        	{ref: 'leftNavTree', selector: 'leftNavTree'},
        	{ref: 'excelExport', selector: 'button[itemId=excelExport]'},
        ]
    },
    init: function() {
    	this.getAppSettings();
         this.control({
             'leftNav > button': {
                 click: this.updateMainPanel
             },
             'window': {
            	close: function(window, eOpts) {
            		window.destroy();
            	} 
             },
             'button[itemId=addCategoryButton]': {
            	 click: this.addCategory
             },
             'button[itemId=AddItemButton]': {
            	 click: this.addItem
             },
             'button[itemId=AddCustomerButton]': {
            	 click: this.addCustomer
             },
             'button[itemId=searchCustomerButton]': {
            	 click: this.seachCustomer
             },
             'customerGrid[itemId=searchCustomerGrid]': {
            	 rowclick: this.selectCustomer
             },
             'combo[name=categoryId]': {
            	 select: this.filterItemDetails
             },
             'combo[name=billItemId]': {
            	 select: this.getItemDetails
             },
             'button[itemId=AddBillItemButton]': {
            	 click: this.addBillItemToBillGrid
             },
             'button[itemId=saveBillButton]': {
            	 click: this.saveBill
             },
             'button[itemId=printBillButton]': {
            	 click: this.printBill
             },
             'button[itemId=saveAndPrintBillButton]': {
            	 click: this.saveAndPrintBill
             },
             'button[itemId=resetBillButton]': {
            	 click: this.resetBill
             },
             'button[itemId=cancelBillButton]': {
            	 click: this.cancelBill
             },
             'button[itemId=saveAdminButton]': {
            	 click: this.saveAppSettings
             },
             'numberfield[itemId=billDiscount]': {
             	change: this.updateGrandTotal
             },
             'billsWindow': {
             	beforedestroy: this.beforeBillsWindowDestroy
             },
             'button[itemId=reArrangeBillNumbers]': {
             	click: this.reArrangeBillNumbers
             },
             'leftNavTree': {
             	cellclick: this.leftNavTreeClick
             },
             'button[itemId=excelExport]': {
             	click: this.excelDownload
             }
         });
     },
     
     getAppSettings: function() {
    	 var extraParams = new Ext.util.HashMap();
    	 var inputParams = new Ext.util.HashMap();
    	 Service.sendRequest(Service.GET_APP_SETTINGS, inputParams.map, Service.POST, BillingControllerHelper.getAppSettingsSuccess, this.failure, extraParams);
     },
     
     closeAllWindows: function() {
    	 	var categoryWindow = this.getCategoryWindow();
    	 	if (!Ext.isEmpty(categoryWindow)) categoryWindow.destroy();
    	 	
    	 	var createBillWindow = this.getCreateBillWindow();
    	 	if (!Ext.isEmpty(createBillWindow)) createBillWindow.destroy();
    	 	
    	 	var itemWindow = this.getItemWindow();
    	 	if (!Ext.isEmpty(itemWindow)) itemWindow.destroy();
    	 	
    	 	var customerWindow = this.getCustomerWindow();
    	 	if (!Ext.isEmpty(customerWindow)) customerWindow.destroy();
    	 	
    	 	var billsWindow = this.getBillsWindow();
    	 	if (!Ext.isEmpty(billsWindow)) billsWindow.destroy();
    	 	
    	 	var adminWindow = this.getAdminWindow();
    	 	if (!Ext.isEmpty(adminWindow)) adminWindow.destroy();
    	 	
    	 	var leftNavButtons = Ext.ComponentQuery.query('leftNav > button');
    	 	for (var i=0; i<leftNavButtons.length; i++) {
    	 		leftNavButtons[i].setPressed(false);
    	 	}
     },
     
     leftNavTreeClick: function(tree, td, cellIndex, record, tr, rowIndex, e, eOpts) {
     	this.closeAllWindows();
     	console.log(record.get('id'));
     	switch(record.get('id')) {
		    case 'newBillButton':
		    	var createBillWindow = this.getCreateBillWindow();
     			if (Ext.isEmpty(createBillWindow)) {
     				createBillWindow = Ext.create('BillingApp.view.bill.CreateBillWindow');
     			}
     			 var extraParams = new Ext.util.HashMap();
	           	 extraParams.add('createBillWindow', createBillWindow);
	           	 var inputParams = new Ext.util.HashMap();
	           	 Service.sendRequest(Service.GET_NEXT_BILL_ID, inputParams.map, Service.POST, BillingControllerHelper.nextBillIdSuccess, this.failure, extraParams);
	           	var vat = createBillWindow.down('textfield[itemId=vat]');
	           	vat.setValue(Helper.settingsMap.get('VAT'));
     			createBillWindow.showAt(120,0);
		        break;
		    case 'categoryButton':
     			var categoryWindow = this.getCategoryWindow();
     			if (Ext.isEmpty(categoryWindow)) {
     				categoryWindow = Ext.create('BillingApp.view.category.CategoryWindow');
     			}
     			categoryWindow.showAt(120,0);
     			
     			var categoryGrid = this.getCategoryGrid();
     			var categoryGridStore = categoryGrid.getStore();
     			categoryGridStore.load();
		        break;
		    case 'itemButton':
		    	var itemWindow = this.getItemWindow();
     			if (Ext.isEmpty(itemWindow)) {
     				itemWindow = Ext.create('BillingApp.view.item.ItemWindow');
     			}
     			var itemGrid = this.getItemGrid();
     			var itemGridStore = itemGrid.getStore();
     			itemGridStore.load();
     			itemWindow.showAt(120,0);
		        break;
		    case 'customerButton':
		    	var customerWindow = this.getCustomerWindow();
     			if (Ext.isEmpty(customerWindow)) {
     				customerWindow = Ext.create('BillingApp.view.customer.CustomerWindow');
     			}
     			var customerGrid = this.getCustomerGrid();
     			var customerGridStore = customerGrid.getStore();
     			customerGridStore.load();
     			customerWindow.showAt(120,0);
		        break;
		    case 'billsButton':
		    	var billsWindow = this.getBillsWindow();
		    	if (Ext.isEmpty(customerWindow)) {
		    		billsWindow = Ext.create('BillingApp.view.bill.BillsWindow');
     			}
		    	var billGrid = this.getBillGrid();
		    	var billGridStore = billGrid.getStore();
		    	billGridStore.load();
		    	billsWindow.showAt(120,0);
		        break;    
			case 'adminButton':
				var adminWindow = this.getAdminWindow();
		    	if (Ext.isEmpty(adminWindow)) {
		    		adminWindow = Ext.create('BillingApp.view.admin.AdminWindow');
     			}
		    	
		    	var adminForm = adminWindow.down('adminForm');
		    	var shopName = adminForm.down('textfield[name=shopName]');
		    	var shopAddress = adminForm.down('textfield[name=shopAddress]');
		    	var vat = adminForm.down('textfield[name=vat]');
		    	shopName.setValue(Helper.settingsMap.get('SHOP_NAME'));
		    	shopAddress.setValue(Helper.settingsMap.get('SHOP_ADDRESS'));
		    	vat.setValue(Helper.settingsMap.get('VAT'));
		    	adminWindow.showAt(120,0);
				break; 
		}
     },
     
     updateMainPanel: function(button) {
     	var buttonId = button.getItemId();
     	this.closeAllWindows();
     	button.setPressed(true);
     	switch(buttonId) {
		    case 'newBillButton':
		    	var createBillWindow = this.getCreateBillWindow();
     			if (Ext.isEmpty(createBillWindow)) {
     				createBillWindow = Ext.create('BillingApp.view.bill.CreateBillWindow');
     			}
     			 var extraParams = new Ext.util.HashMap();
	           	 extraParams.add('createBillWindow', createBillWindow);
	           	 var inputParams = new Ext.util.HashMap();
	           	 Service.sendRequest(Service.GET_NEXT_BILL_ID, inputParams.map, Service.POST, BillingControllerHelper.nextBillIdSuccess, this.failure, extraParams);
	           	var vat = createBillWindow.down('textfield[itemId=vat]');
	           	vat.setValue(Helper.settingsMap.get('VAT'));
     			createBillWindow.showAt(120,0);
		        break;
		    case 'categoryButton':
     			var categoryWindow = this.getCategoryWindow();
     			if (Ext.isEmpty(categoryWindow)) {
     				categoryWindow = Ext.create('BillingApp.view.category.CategoryWindow');
     			}
     			categoryWindow.showAt(120,0);
     			
     			var categoryGrid = this.getCategoryGrid();
     			var categoryGridStore = categoryGrid.getStore();
     			categoryGridStore.load();
		        break;
		    case 'itemButton':
		    	var itemWindow = this.getItemWindow();
     			if (Ext.isEmpty(itemWindow)) {
     				itemWindow = Ext.create('BillingApp.view.item.ItemWindow');
     			}
     			var itemGrid = this.getItemGrid();
     			var itemGridStore = itemGrid.getStore();
     			itemGridStore.load();
     			itemWindow.showAt(120,0);
		        break;
		    case 'customerButton':
		    	var customerWindow = this.getCustomerWindow();
     			if (Ext.isEmpty(customerWindow)) {
     				customerWindow = Ext.create('BillingApp.view.customer.CustomerWindow');
     			}
     			var customerGrid = this.getCustomerGrid();
     			var customerGridStore = customerGrid.getStore();
     			customerGridStore.load();
     			customerWindow.showAt(120,0);
		        break;
		    case 'billsButton':
		    	var billsWindow = this.getBillsWindow();
		    	if (Ext.isEmpty(customerWindow)) {
		    		billsWindow = Ext.create('BillingApp.view.bill.BillsWindow');
     			}
		    	var billGrid = this.getBillGrid();
		    	var billGridStore = billGrid.getStore();
		    	billGridStore.load();
		    	billsWindow.showAt(120,0);
		        break;    
			case 'adminButton':
				var adminWindow = this.getAdminWindow();
		    	if (Ext.isEmpty(adminWindow)) {
		    		adminWindow = Ext.create('BillingApp.view.admin.AdminWindow');
     			}
		    	
		    	var adminForm = adminWindow.down('adminForm');
		    	var shopName = adminForm.down('textfield[name=shopName]');
		    	var shopAddress = adminForm.down('textfield[name=shopAddress]');
		    	var vat = adminForm.down('textfield[name=vat]');
		    	shopName.setValue(Helper.settingsMap.get('SHOP_NAME'));
		    	shopAddress.setValue(Helper.settingsMap.get('SHOP_ADDRESS'));
		    	vat.setValue(Helper.settingsMap.get('VAT'));
		    	adminWindow.showAt(120,0);
				break; 
		}
     },
     
     addCategory: function(button) {
    	 var form = button.up('form').getForm();
         if (form.isValid()) {
        	 var extraParams = new Ext.util.HashMap();
        	 extraParams.add('form', form);
        	 var inputParams = new Ext.util.HashMap();
        	 var formVals = form.getValues();
        	 var url;
        	 if (button.getText() == "Edit") {
        	 	url = Service.EDIT_CATEGORY;
        	 	formVals.categoryId = '' + form.getRecord().data.categoryId;
        	 	extraParams.add('isEdit', true);
        	 	extraParams.add('button', button);
        	 } else {
        	 	url = Service.ADD_CATEGORY
        	 }
        	 inputParams.add(Service.REQUEST_JSON, Ext.encode(formVals));
        	 Service.sendRequest(url, inputParams.map, Service.POST, BillingControllerHelper.addCategorySuccess, this.failure, extraParams);
         }
     },
     
     addItem: function(button) {
    	 var form = button.up('form').getForm();
         if (form.isValid()) {
        	 var extraParams = new Ext.util.HashMap();
        	 extraParams.add('form', form);
        	 var inputParams = new Ext.util.HashMap();
        	 var formVals = form.getValues();
        	 if (!Ext.isEmpty(formVals.categoryId)) formVals.categoryId = '' + formVals.categoryId;
        	 
        	 var url;
        	 if (button.getText() == "Edit") {
        	 	url = Service.EDIT_ITEM;
        	 	formVals.itemId = '' + form.getRecord().data.itemId;
        	 	extraParams.add('isEdit', true);
        	 	extraParams.add('button', button);
        	 } else {
        	 	url = Service.ADD_ITEM
        	 }
        	 inputParams.add(Service.REQUEST_JSON, Ext.encode(formVals));
        	 Service.sendRequest(url, inputParams.map, Service.POST, BillingControllerHelper.addItemSuccess, BillingControllerHelper.failure, extraParams);
         }
     },
     
     addCustomer: function(button) {
    	 var form = button.up('form').getForm();
    	 var parentWindow = button.up('window[itemId=customerWindowItemId]');
    	 if (Ext.isEmpty(parentWindow)) {
    		 parentWindow = button.up('window[itemId=createBillWindowItemId]');
    	 }
    	 
         if (form.isValid()) {
        	 var extraParams = new Ext.util.HashMap();
        	 extraParams.add('form', form);
        	 extraParams.add('parentWindow', parentWindow);
        	 var inputParams = new Ext.util.HashMap();
        	 
        	 var url;
        	 var formVals = form.getValues();
        	 if (button.getText() == "Edit") {
        	 	url = Service.EDIT_CUSTOMER;
        	 	formVals.customerId = '' + form.getRecord().data.customerId;
        	 	extraParams.add('isEdit', true);
        	 	extraParams.add('button', button);
        	 } else {
        	 	url = Service.ADD_CUSTOMER;
        	 }
        	 
        	 inputParams.add(Service.REQUEST_JSON, Ext.encode(formVals));
        	 Service.sendRequest(url, inputParams.map, Service.POST, BillingControllerHelper.addCustomerSuccess, BillingControllerHelper.failure, extraParams);
         }
     },
     
     seachCustomer: function(button) {
    	 var form = button.up('form').getForm();
    	 var searchObj = form.getValues();
    	 var fieldArray = form.getFields().getRange();
    	 var filterArray = [];
    	 Ext.Array.forEach(fieldArray, function(field, index) {
    		 if(!Ext.isEmpty(Ext.String.trim(field.getValue()))) {
    		 	if (field.getName() != "address" && field.getName() != "email" && field.getName() != "birthDate") {
		    		 var filter = new Ext.util.Filter({
		 			    property: field.getName(),
		 			    value   : field.getValue()
		 			});
		    		 filterArray.push(filter);
	    		 }
    		 }
    	 });
    	 
    	 if (Ext.isEmpty(filterArray)) {
    		 Ext.Msg.alert('Warning', 'Please enter search in above form.');
    	 } else {
    	 	var customerGridWindow = this.getCustomerGridWindow();
			if (Ext.isEmpty(customerGridWindow)) {
				customerGridWindow = Ext.create('BillingApp.view.customer.CustomerGridWindow');
			}
			var customerGrid = customerGridWindow.down('customerGrid');
			var customerGridStore = customerGrid.getStore();
			customerGridStore.addFilter(filterArray);
			customerGridStore.load();
			customerGridWindow.showAt(120,0);
    	 }
     },
     
     
     selectCustomer: function(grid, record, tr, rowIndex, e, eOpts ) {
    	 var createBillPanel = Ext.ComponentQuery.query('createBillPanel')[0]
    	 var customerForm = createBillPanel.down('customerForm');
    	 customerForm.getForm().loadRecord(record);
    	 
    	 var customerGridWindow = this.getCustomerGridWindow();
    	 customerGridWindow.destroy();
    	 
    	 var billForm = createBillPanel.down('billForm');
    	 var customerId = billForm.down('textfield[name=customerId]');
    	 customerId.setValue(record.get('customerId'));
     },
     
     filterItemDetails: function(combo, record, eOpts) {
    	 var categoryId = combo.getValue();
    	 
    	 var billItemForm = combo.up('billItemForm');
    	 if (!Ext.isEmpty(billItemForm)) {
	    	 var fields = billItemForm.query('[isFormField][name!="categoryId"]');
	    	 for (var i = 0, len = fields.length; i < len; i++) {
	    	     fields[i].reset();
	    	 }
	    	 
	    	 /*var billItemCombo = billItemForm.down('combo[name=billItemId]');
	    	 billItemCombo.clearValue();
	    	 var categoryFilter = new Ext.util.Filter({
	    		    property: 'categoryId',
	    		    value   : categoryId
	    		});
	    	 
	    	 var store = billItemCombo.getStore();
	    	 store.clearFilter();
	    	 store.setRemoteFilter(false);
	    	 store.addFilter(categoryFilter);*/
	    	 //store.setRemoteFilter(true);
	    }
     },
     
     getItemDetails: function(combo, record, eOpts) {
    	 var itemId = record.get('itemId');
    	 
    	 var extraParams = new Ext.util.HashMap();
    	 var billItemForm = combo.up('billItemForm');
    	 extraParams.add('billItemForm', billItemForm);
    	 var inputParams = new Ext.util.HashMap();
    	 inputParams.add('itemId', itemId);
    	 inputParams.add(Service.REQUEST_JSON, Ext.encode(inputParams.map));
    	 Service.sendRequest(Service.GET_ITEM_DETAIL, inputParams.map, Service.POST, BillingControllerHelper.getItemDetailSuccess, BillingControllerHelper.failure, extraParams);
     },
     
     
     addBillItemToBillGrid: function(button) {
     	var saveBillButton = Ext.ComponentQuery.query('button[itemId=saveBillButton]')[0];
     	if(saveBillButton.isDisabled()) {
     		Ext.Msg.alert('Message', 'Bill is already saved, you cannot add more items to bill, please click on "Create New Bill" to create bill');	
     		return;
     	}
    	 var billTotal = this.getBillTotal();
    	 billTotal = billTotal.getValue();
    	 billTotal = parseInt(billTotal);
    	 
    	 var billItemForm = button.up('form');
    	 
    	 var billItem = Ext.create('BillingApp.model.BillItem');
    	 var billItemFormValues = billItemForm.getValues();
    	 
    	 var billItemId = billItemFormValues.billItemId;
    	 var category = billItemForm.down('combo[name=categoryId]');
    	 
    	 var itemName = billItemForm.down('textfield[name=billItemId]');
    	 var itemDesc = billItemForm.down('textfield[name=itemDesc]');
    	 var itemCode = billItemForm.down('textfield[name=itemCode]');
    	 var billItemQuantityAvailable = billItemForm.down('textfield[name=billItemQuantityAvailable]');
    	 var billItemQuantity = billItemForm.down('textfield[name=billItemQuantity]');
    	 var unitPrice = billItemForm.down('textfield[name=unitPrice]');
    	 var billItemDiscount = billItemForm.down('textfield[name=billItemDiscount]');
    	 
    	 var discount = billItemDiscount.getValue();
    	 var billItemQuantity = billItemQuantity.getValue();
    	 
    	 var errorMessage = "";
    	 if(itemName.getValue().length > 30) {
    	 	errorMessage = errorMessage + "Item Name should not be greater than 30 characters<br>";
    	 }
    	 if(itemCode.getValue().length > 30) {
    	 	errorMessage = errorMessage + "Item Code  should not be greater than 30 characters<br>";
    	 }
    	 if(itemDesc.getValue().length > 30) {
    	 	errorMessage = errorMessage + "Item Description should not be greater than 30 characters<br>";
    	 }
    	 
    	 if (!Ext.isEmpty(errorMessage)) {
    	 	Ext.Msg.alert('Error', errorMessage);	
     		return;
    	 }
    	 /*if (parseInt(billItemQuantity) > parseInt(billItemQuantityAvailable.getValue())) {
    		 Ext.Msg.alert('Error', billItemQuantity + " Items are not available in Stock.");
    		 return;
    	 }*/
    	 var unitPrice = unitPrice.getValue();
    	 var totalAmout = parseInt(billItemQuantity)*parseInt(unitPrice);
    	 var finalAmount = totalAmout;
    	 if (discount>0) {
    		 finalAmount = finalAmount - ((totalAmout * discount)/100);
    	 }
    	 
    	 billItem.set({
    		 billItemId: null,
    		 categoryId: category.getValue(),
			 categoryName: category.getDisplayValue(),
    		 itemId: billItemId,
             itemName: itemName.getValue(),
             itemDesc: itemDesc.getValue(),
             itemCode: itemCode.getValue(),
             itemQuantity: billItemQuantity,
             itemDiscount: discount,
             totalAmount: totalAmout,
             finalAmount: finalAmount
    	 });
    	 
    	 var billItemGrid = this.getBillItemGrid();
    	 var billItemGridStore = billItemGrid.getStore();
    	 billItemGridStore.add(billItem);
		
		billTotal = 0;
		billItemGridStore.each(function(record) {
			billTotal = billTotal + record.get('finalAmount');
		});
		    	 
    	 var vat = this.getVat();
    	 vat = vat.getValue();
    	 
    	 var vatTotal = (billTotal * vat)/100;
    	 vatTotal = Math.ceil(parseFloat(Ext.Number.toFixed(vatTotal, 2)));
    	 this.getVatTotal().setValue(vatTotal);
    	 
    	 billTotal = billTotal + vatTotal;
		 billTotal =  Math.ceil(parseFloat(Ext.Number.toFixed(billTotal, 2)));
    	 this.getBillTotal().setValue(billTotal);
    	 
    	 button.up('createBillPanel').setBillTotal(billTotal);
     },
     
     saveAndPrintBill:function(button) {
     	var createBillPanel = button.up('createBillPanel');
     	var saveButton = createBillPanel.down('button[itemId=saveBillButton]');
     	if (saveButton.isDisabled()) {
     		BillingApp.app.getController('BillingController').printBill(button);
     	} else {
     		BillingApp.app.getController('BillingController').saveBill(button);
     	}
     },
     saveBill: function(button) {
    	 var createBillPanel = button.up('createBillPanel');
    	 var customerForm = createBillPanel.down('customerForm');
    	 var billForm = createBillPanel.down('billForm');
    	 var billItemForm = createBillPanel.down('billItemForm');
    	 var billItemGrid = createBillPanel.down('billItemGrid');
    	 
    	 var billMap = new Ext.util.HashMap();
    	 var billId = billForm.down('textfield[name=billId]').getValue();
    	 billMap.add('billId', billId);
    	 
    	 var customerId = billForm.down('textfield[name=customerId]');
    	 if (Ext.isEmpty(customerId) || Ext.isEmpty(customerId.getValue())) {
    		 Ext.Msg.alert('Warning', 'Please enter Customer details.');
    		 return;
    	 } else {
    		 billMap.add('customerId', customerId.getValue());
    	 }
    	 
    	 var billItemList = [];
    	 var billItemGridStore =  billItemGrid.getStore();
    	 if (billItemGridStore.getCount() == 0) {
    		 Ext.Msg.alert('Warning', 'Please enter Items.');
    		 return;
    	 } else {
	    	 billItemGridStore.each(function(record, index) {
	    		 var data = record.data;
	    		 data.discount = data.itemDiscount;
	    		 billItemList.push(data);
	    	 });
	    	 
	    	 billMap.add('billItems', billItemList);
    	 }
    	 
    	  var vat = createBillPanel.down('textfield[itemId=vat]');
    	  if (Ext.isEmpty(vat.getValue())) { 
    	  	vat.setValue(0); 
    	 }
    	 billMap.add('taxPercentage', vat.getValue());
    	 
    	 var vatTotal = createBillPanel.down('textfield[itemId=vatTotal]');
    	 billMap.add('taxAmount', vatTotal.getValue());
    	 
    	 var billDiscount = createBillPanel.down('textfield[itemId=billDiscount]');
    	 billMap.add('discount', billDiscount.getValue());
    	 
    	 var amount = createBillPanel.down('textfield[itemId=billTotal]');
    	 billMap.add('finalAmount', amount.getValue());
    	 
    	 var amount = createBillPanel.down('textfield[itemId=billTotal]');
    	 amount = parseInt(amount.getValue()) + parseInt(billDiscount.getValue());
    	 billMap.add('amount', amount);
    	 
    	 console.log(Ext.encode(billMap.map));
    	 var extraParams = new Ext.util.HashMap();
    	 extraParams.add('button', button);
       	 var inputParams = new Ext.util.HashMap();
       	 inputParams.add(Service.REQUEST_JSON, Ext.encode(billMap.map));
       	 Service.sendRequest(Service.ADD_BILL, inputParams.map, Service.POST, BillingControllerHelper.addBillSuccess, BillingControllerHelper.failure, extraParams);
     },
     
     resetBill: function(button) {
    	 var createBillPanel = button.up('createBillPanel');
    	 var customerForm = createBillPanel.down('customerForm');
    	 var billForm = createBillPanel.down('billForm');
    	 var billItemForm = createBillPanel.down('billItemForm');
    	 var billItemGrid = createBillPanel.down('billItemGrid');

    	 customerForm.getForm().reset();
    	 billForm.getForm().reset();
    	 billItemForm.getForm().reset();
    	 
    	 billItemGrid.getStore().removeAll();
    	 
    	 var vatTotal = createBillPanel.down('textfield[itemId=vatTotal]');
    	 vatTotal.setValue('');
    	 
    	 var billDiscount = createBillPanel.down('numberfield[itemId=billDiscount]');
    	 billDiscount.setValue(0);
    	 
    	 var billTotal = createBillPanel.down('textfield[itemId=billTotal]');
    	 billTotal.setValue('');
    	 
    	 var extraParams = new Ext.util.HashMap();
       	 extraParams.add('createBillWindow', createBillPanel.up('createBillWindow'));
       	 var inputParams = new Ext.util.HashMap();
       	 Service.sendRequest(Service.GET_NEXT_BILL_ID, inputParams.map, Service.POST, BillingControllerHelper.nextBillIdSuccess, this.failure, extraParams);
     },
     
     cancelBill: function(button) {
    	 var createBillWindow = this.getCreateBillWindow();
    	 createBillWindow.destroy();
     },
     
     printBill: function(button) {
    	 var billItemGrid = this.getBillItemGrid();
    	 var billDiscount = this.getBillDiscount().getValue();
    	 var billHTML = BillingControllerHelper.getBillItemHTML(billItemGrid.getStore(), billDiscount);
    	 var createBillPanel = button.up('createBillPanel');
    	 var customerForm = createBillPanel.down('customerForm');
    	 var firstName = customerForm.down('textfield[name=firstName]');
    	 var lastName = customerForm.down('textfield[name=lastName]');
    	 var phone =  customerForm.down('textfield[name=phone]');
    	 var address = customerForm.down('textfield[name=address]');
    	var customerName = firstName.getValue() + ' ' + lastName.getValue();
    	phone = phone.getValue();
    	address = address.getValue();
    	
    	var billForm = createBillPanel.down('billForm');
    	var billNumber = billForm.down('textfield[name=billId]');
    	billNumber = billNumber.getValue();
    	billHTML = BillingControllerHelper.getPrintHTML(customerName, phone, address, billHTML, billNumber);
    	BillingControllerHelper.printDiv(billHTML);
     },
     
     
     saveAppSettings: function(button) {
    	 	var adminForm = button.up('adminForm');
	    	var shopName = adminForm.down('textfield[name=shopName]');
	    	var shopAddress = adminForm.down('textfield[name=shopAddress]');
	    	var vat = adminForm.down('textfield[name=vat]');
	    	shopName = shopName.getValue();
	    	shopAddress = shopAddress.getValue();
	    	vat = vat.getValue();
	    	
	    	var settingMap = new Ext.util.HashMap();
	    	settingMap.add('SHOP_NAME', shopName);
	    	settingMap.add('SHOP_ADDRESS', shopAddress);
	    	settingMap.add('VAT', vat);
	    	var extraParams = new Ext.util.HashMap();
	       	var inputParams = new Ext.util.HashMap();
	       	inputParams.add(Service.REQUEST_JSON, Ext.encode(settingMap.map));
			Service.sendRequest(Service.SAVE_APP_SETTINGS, inputParams.map, Service.POST, BillingControllerHelper.saveAppSettingsSuccess, this.failure, extraParams);
     },
     
     updateGrandTotal: function(textField, newValue, oldValue, eOpts) {
     	var billTotal = this.getBillTotal();
     	var total = textField.up('createBillPanel').getBillTotal();
     	
     	if (newValue > total) {
     		Ext.Msg.alert('Error', 'Cannot give discount more than bill total.');		
     	} else {
     		billTotal.setValue(total - newValue);
     	}
     },
     
     beforeBillsWindowDestroy: function(window, eOpts ) {
     	var billGrid = window.down('billGrid');
     	var billStore = billGrid.getStore();
     	billStore.removeFilter('customerIdFilter');
     },
     
     
     reArrangeBillNumbers: function(button) {
     	Ext.Msg.show({
		    title:'Warning',
		    message: 'Re-Arranging Bill Numbers in sequence would take some time. Would you like to continue?',
		    buttons: Ext.Msg.YESNOCANCEL,
		    icon: Ext.Msg.QUESTION,
		    fn: function(btn) {
		        if (btn === 'yes') {
		            Ext.ComponentQuery.query('billsWindow')[0].mask('Loading...');
			     	var extraParams = new Ext.util.HashMap();
			       	var inputParams = new Ext.util.HashMap();
					Service.sendRequest(Service.REARRANGE_BILL_NUMBERS, inputParams.map, Service.POST, BillingControllerHelper.reArrangeBillNumbersSuccess, 
													function(json, extraparams) {
												    	 Ext.Msg.alert('Error', json);
												    	 Ext.ComponentQuery.query('billsWindow')[0].unmask();
												     }, extraParams);
		        } 
		    }
		});
     		
     },
     
     
     excelDownload: function(button) {
     				var url = appPath + Service.EXCEL_EXPORT ; //"http://localhost:8080/BillingApp/download/excelExport/download";
     				var body = Ext.getBody();
     				
     				var frame = body.createChild({
						tag:'iframe',
						cls:'x-hidden',
						id:'hiddenform-iframe',
						name:'iframe'
					});
					
					var form = body.createChild({
						tag:'form',
						cls:'x-hidden',
						id:'hiddenform-form',
						action: url,
						target:'iframe'
					});
					
					form.createChild({tag: 'input', type: 'hidden', name: 'grid', value: button.grid});
					form.dom.submit();
					form.destroy();
     }
}); 