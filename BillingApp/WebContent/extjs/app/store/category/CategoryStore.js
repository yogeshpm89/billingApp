Ext.define('BillingApp.store.category.CategoryStore', {
    extend: 'Ext.data.Store',
     storeId:'categoryStore',
    model: 'BillingApp.model.Category',
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
        url: appPath + Service.GET_ALL_CATEGORIES,
        reader: {
            type: 'json',
            rootProperty: 'items',
            successProperty: 'success',
            totalProperty : 'total'
        }
    }
});
