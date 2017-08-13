Ext.define('BillingApp.model.Item', {
    extend: 'Ext.data.Model',
    
    fields: [
        { name: 'itemId', type: 'int' },
        { name: 'categoryId', type: 'int' },
         { name: 'categoryName', type: 'auto' },
        { name: 'itemName', type: 'auto' },
        { name: 'itemDesc', type: 'auto' },
        { name: 'itemCode', type: 'auto' },
        { name: 'itemQuantity', type: 'int' },
        { name: 'itemDiscount', type: 'auto' },
        { name: 'unitPrice', type: 'int' }
    ]
});
