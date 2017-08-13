Ext.define('BillingApp.model.BillItem', {
    extend: 'Ext.data.Model',
    
    fields: [
        { name: 'billItemId', type: 'int' },
        { name: 'categoryId', type: 'int' },
        { name: 'categoryName', type: 'auto' },
        { name: 'itemId', type: 'int' },
        { name: 'itemName', type: 'auto' },
        { name: 'itemQuantity', type: 'int' },
        { name: 'itemDiscount', type: 'auto' },
        { name: 'totalAmount', type: 'int' },
        { name: 'finalAmount', type: 'int' }
    ]
});
