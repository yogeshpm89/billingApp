Ext.define('BillingApp.view.customer.CustomerForm', {
	extend : 'Ext.form.Panel',
	alias : 'widget.customerForm',
	hideSearchButton : true,
	initComponent : function() {
		var me = this;
		var hideSearchButton = me.hideSearchButton;
		Ext.apply(this, {
			width: '100%',
			// Fields will be arranged vertically, stretched to full width
			layout : {
				type : 'table',
				columns : 3
			},
			defaults : {
				anchor : '100%',
				padding : '0px 0px 5px 5px'
			},
			// The fields
			defaultType : 'textfield',
			items : [ {
				fieldLabel : 'First Name <span style="color:red;">*</span>',
				name : 'firstName',
				allowBlank : false,
				hideTrigger : true,
				maxLength : 30
			}, {
				fieldLabel : 'Last Name',
				name : 'lastName',
				allowBlank : true,
				maxLength : 30
			}, {
				xtype : 'datefield',
				fieldLabel : 'Birth Date',
				name : 'birthDate',
				format : 'd/m/Y',
				allowBlank : true
			}, {
				fieldLabel : 'Address',
				name : 'address',
				allowBlank : true,
				maxLength : 100
			}, {
				fieldLabel : 'Email',
				name : 'email',
				allowBlank : true,
				 vtype: 'email',
				 value: 'bz@bz.com',
				 maxLength : 255
			}, {
				xtype: 'numberfield',
				fieldLabel : 'Phone/Mobile',
				name : 'phone',
				allowBlank : true,
				minLength: 10,
				maxLength: 10,
				minLengthText: 'Phone number should contains 10 digits'
			} ],
			dockedItems: [
			              {
			            	  xtype: 'label',
			            	  dock: 'top',
			            	  padding : '0px 0px 5px 5px',
			            	  html: '<i style="color:red">1. All fields marked with <span style="color:red;">*</span> are madatory. <br>2. Search customers on First Name, Last Name and Phone/Mobile.</i>'
			              }
			 ],
			// Reset and Submit buttons
			buttons : [ {
				text : 'Search',
				hidden: hideSearchButton,
				itemId : 'searchCustomerButton'
			}, {
				text : 'Reset',
				itemId : 'ResetCustomerButton',
				handler : function() {
					this.up('form').getForm().reset();
				}
			}, {
				text : 'Add',
				formBind : true, // only enabled once the form is valid
				disabled : true,
				itemId : 'AddCustomerButton'
			} ]
		});
		this.callParent();
	}

})