Ext.define('BillingApp.view.bill.BillGrid', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.billGrid',
	width: '100%',
	height: 400,
    initComponent: function(){
    	// create the Data Store
        var store = Ext.create('BillingApp.store.bill.BillStore');
    	Ext.apply(this, {
    		border : true,
    		columnLines : true,
    		autoScroll: true,
            store: store,
            columns: [
		        { text: 'Bill Id',  dataIndex: 'billId',  flex: 1},
		        { text: 'Bill Number',  dataIndex: 'billNumber',  flex: 1},
		        { text: 'Customer Id', dataIndex: 'customerId', flex: 1},
 		        {
					text : 'Customer Details',
					dataIndex : 'customerVO',
					flex : 2,
					renderer: function(customerVO, metadata) {
						return customerVO.firstName + " " + customerVO.lastName + ", " +  customerVO.address;
					}
 		        },
 		       { text: 'Bill Date (dd/mm/yyyy)', dataIndex: 'createDate', flex: 1, formatter: 'date("d/m/Y")',},
 		       { text: 'Total (Rs)', dataIndex: 'amount', flex: 1},
 		      { text: 'Taxes (%)', dataIndex: 'taxPercentage', flex: 1},
 		      { text: 'Taxes (Rs)', dataIndex: 'taxAmount', flex: 1},
 		     { text: 'Discount (Rs)', dataIndex: 'discount', flex: 1},
 		      { text: 'Grand Total (Rs)', dataIndex: 'finalAmount', flex: 1},
 		     {
		            xtype:'actioncolumn',
		            width: 25,
		            items: [{
			                icon: 'resources/images/delete16.png',  // Use a URL in the icon config
			                tooltip: 'delete',
			                itemId: 'deleteBillImage',
			                handler: function(grid, rowIndex, colIndex) {
			                	var rec = grid.getStore().getAt(rowIndex);
			                    var billId = rec.get('billId');
			                	BillingControllerHelper.deleteBill(billId);
			                }
			            }]
		        },
 		      {
		            xtype:'actioncolumn',
		            width: 25,
		            items: [{
		                icon: 'resources/images/printer16.png',  // Use a URL in the icon config
		                tooltip: 'Print',
		                handler: function(grid, rowIndex, colIndex) {
		                	var store = grid.getStore();
		                	var record = store.getAt(rowIndex);
		                	var billItemVOs = record.get('billItemVOs');
		                	var html = "";
		                	
		                	var html = '<style>td{font-size:14px; padding: 0px 5px 0px 5px;}</style><table style="width:100%; border-collapse: collapse;" cellpadding="5" border="1">';
		                	html = html + '<tr><th></th><th style="width:300px;">Item</th><th style="width:120px;">Quantity</th><th>Total&nbsp;(Rs)</th><th>Discount&nbsp;(%)</th><th>Final&nbsp;(Rs)</th></tr>';
		                 	var total = 0;
		                 	/*Ext.Array.forEach(billItemVOs, function(record, index) {
		                 		html = html + '<tr>';
		                 		html = html + '<td>' + (index+1) + '</td>';
		                 		var itemVO = record.itemVO;
		                 		html = html + '<td>' + itemVO.itemName + '</td>';
		                 		html = html + '<td style="text-align:right">' + record.itemQuantity + '</td>';
		                 		html = html + '<td style="text-align:right">' + record.discount + '</td>';
		                 		html = html + '<td style="text-align:right">' + record.totalAmount + '</td>';
		                 		html = html + '<td style="text-align:right">' + record.finalAmount + '</td>';
		                 		html = html + '</tr>';
		                 		
		                 		total = total + record.finalAmount;
		                	});*/
		                 	
		                 	for (var i=0; i<10;i++) {
		                 		var rec =billItemVOs[i];// store.getAt(i);
		                 		html = html + '<tr>';
		                 		html = html + '<td>' + (i+1) + '</td>';
		                 		
		                 		if (Ext.isEmpty(rec)) {
		                 			html = html + '<td></td><td></td><td></td><td></td><td></td></tr>';
		                 		} else {
		                 			var itemVO = rec.itemVO;
		            	     		html = html + '<td>' + itemVO.itemName + '</td>';
		            	     		html = html + '<td style="text-align:right">' + rec.itemQuantity + '</td>';
		            	     		html = html + '<td style="text-align:right">' + rec.discount + '</td>';
		            	     		html = html + '<td style="text-align:right">' + rec.totalAmount + '</td>';
		            	     		html = html + '<td style="text-align:right">' + rec.finalAmount + '</td>';
		            	     		html = html + '</tr>';
		            	     		total = total + rec.finalAmount;
		            	     		//total1 = total1 + record.totalAmount;
		                 		}
		                 		
		                 	}
		                	var vat = Helper.settingsMap.get('VAT');
		                 	var vatValue = (total * vat)/100;
		                 	total = total + vatValue;
		                 	
		                 	html = html + '<tr><td></td><td></td><td style="text-align:right;"><b>Taxes (%)</b></td><td style="text-align:right">' +  record.get('taxPercentage') + 
		                 				'</td><td style="text-align:right;"><b>Taxes (Rs)</b></td><td style="text-align:right">' +  record.get('taxAmount') + '</td></tr>';
		                 	html = html + '<tr><td></td><td></td><td></td><td></td><td style="text-align:right;"><b>Total (Rs)</b></td><td style="text-align:right">' +  record.get('amount') + '</td></tr>'
		                 	html = html + '<tr><td></td><td></td><td style="text-align:right;"><b>Discount (Rs)</b></td><td style="text-align:right">' +  record.get('discount') + 
		                 				'<td style="text-align:right;"><b>Grand Total (Rs)</b></td><td style="text-align:right">' +  record.get('finalAmount') + '</td></tr>';
		                	html = html + '</table>';
		                	
		                	var billNumber = record.get('billNumber');
		                	var customerVO= record.get('customerVO');
		                	var customerName =  customerVO.firstName + " " + customerVO.lastName;
		                	var phone = customerVO.phone;
		                	var address = customerVO.address;
		                	var billHTML = BillingControllerHelper.getPrintHTML(customerName, phone, address, html, billNumber);
		                	BillingControllerHelper.printDiv(billHTML);
		                }
		            }]
		        }
		    ],
            dockedItems: [{
        		xtype: 'container',
        		layout: 'hbox',
        		items: [{
        			xtype: 'button',
        			itemId: 'excelExport',
        			text: 'Export',
        			grid: 'bills'
        		}]
        	},{	
		        xtype: 'pagingtoolbar',
		        store: store,
		        dock: 'bottom',
		        displayInfo: true
		    }],
    	});
    	this.callParent();
    }
});