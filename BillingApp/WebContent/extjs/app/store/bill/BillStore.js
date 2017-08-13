Ext.define('BillingApp.store.bill.BillStore', {
    extend: 'Ext.data.Store',
    model: 'BillingApp.model.Bill',
    pageSize : 20,
    autoLoad: false,
    remoteSort : true,
    remoteFilter: true,
    proxy: {
        type: 'ajax',
        //url: 'redjohn/app/data/categoryData.json',
 		actionMethods : {
 			read: Service.POST
 		},       
        url: appPath + Service.GET_ALL_BILLS,
        reader: {
            type: 'json',
            rootProperty: 'items',
            successProperty: 'success',
            totalProperty : 'total'
        }
    }
});