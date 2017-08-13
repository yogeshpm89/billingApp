Ext.define('BillingApp.store.customer.CustomerStore', {
    extend: 'Ext.data.Store',
    storeId : 'customerStoreId',
    model: 'BillingApp.model.Customer',
    pageSize : 20,
    autoLoad: true,
    remoteSort : true,
    remoteFilter: true,
    proxy: {
        type: 'ajax',
        //url: 'redjohn/app/data/categoryData.json',
 		actionMethods : {
 			read: Service.POST
 		},       
        url: appPath + Service.GET_ALL_CUSTOMERS,
        reader: {
            type: 'json',
            rootProperty: 'items',
            successProperty: 'success',
            totalProperty : 'total'
        }
    }
});
