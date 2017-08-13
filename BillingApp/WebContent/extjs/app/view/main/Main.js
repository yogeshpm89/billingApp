/**
 * This class is the main view for the application. It is specified in app.js as the
 * "autoCreateViewport" property. That setting automatically applies the "viewport"
 * plugin to promote that instance of this class to the body element.
 *
 * TODO - Replace this content of this view to suite the needs of your application.
 */
Ext.define('BillingApp.view.main.Main', {
    extend: 'Ext.container.Container',
    requires: [
        'BillingApp.view.main.MainController',
        'BillingApp.view.main.MainModel'
    ],

    xtype: 'app-main',
    
    controller: 'main',
    viewModel: {
        type: 'main'
    },

    layout: {
        type: 'hbox'
    },
    height: 'auto',
    items: [
	    	{
	        	xtype: 'leftNavTree'
	    	},
	    	{
		        xtype: 'mainPanel'
	        }
	]
});
