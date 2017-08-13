Ext.define('BillingApp.model.Customer', {
    extend: 'Ext.data.Model',
    
    fields: [
        { name: 'customerId', type: 'int' },
        { name: 'firstName', type: 'auto' },
        { name: 'lastName', type: 'auto' },
        { name: 'birthDate', type: 'date', dateFormat: 'd/m/Y'},
        { name: 'email', type: 'auto' },
        { name: 'address', type: 'auto' },
        { name: 'phone', type: 'auto' }
    ]
});
