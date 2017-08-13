Ext.define('BillingApp.model.Bill', {
    extend: 'Ext.data.Model',
    
    fields: [
        { name: 'billId', type: 'int' },
        { name: 'amount', type: 'int' },
        { name: 'customerId', type: 'auto' }
    ]
});
