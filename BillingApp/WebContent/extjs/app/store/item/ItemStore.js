Ext.define('BillingApp.store.item.ItemStore', {
    extend: 'Ext.data.Store',
    model: 'BillingApp.model.Item',
    pageSize : 20,
    autoLoad: true,
    remoteSort : true,
    remoteFilter: true,
    proxy: {
        type: 'ajax',
 		actionMethods : {
 			read: Service.POST
 		},       
        url: appPath + Service.GET_ALL_ITEMS,
        reader: {
            type: 'json',
            rootProperty: 'items',
            successProperty: 'success',
            totalProperty : 'total'
        }
    }
});
