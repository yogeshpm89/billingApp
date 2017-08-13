Ext.define('BillingApp.model.Category', {
    extend: 'Ext.data.Model',
    
    fields: [
        { name: 'categoryId', type: 'int' },
        { name: 'categoryName', type: 'auto' },
        { name: 'categoryDesc', type: 'auto' }
    ]
});
