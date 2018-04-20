var app = angular.module('myApp', [ 'datatables', 'datatables.select' ]);
 
app.controller("dashboardCtrl", function($scope, $compile, callRestMongo,
											DTOptionsBuilder, DTColumnBuilder, DTColumnDefBuilder) {
 
	var numbersId = [];
	
	var vm = this;
	vm.listClients = [];
	vm.dtInstance = [];
	vm.selected = {};
	
	// Angular DataTables Options
	vm.dtOptions = DTOptionsBuilder.newOptions().withOption('ajax', {
		"contentType": "application/json; charset=utf-8",
		dataType: "json",
		"url": "/com.example/getAccountInfo",
		"type": 'GET'
	})
	.withOption('createdRow', function(row, data, dataIndex){
		// recompile so we can bind angular directive to the DT
		$compile(angular.element(row).contents())($scope);
	});

	vm.dtColumns = [
	    DTColumnBuilder.newColumn('_id').withTitle('_id').notVisible(),            
	    DTColumnBuilder.newColumn('accRegister').withTitle('Register'),
	    DTColumnBuilder.newColumn('accName').withTitle('Name'),
	    DTColumnBuilder.newColumn('accDate').withTitle('Date'),
	    DTColumnBuilder.newColumn('accTemperatureC').withTitle('Temperature(C)'),
	    DTColumnBuilder.newColumn('accTemperatureF').withTitle('Temperature(F)'),
	    DTColumnBuilder.newColumn('accSound').withTitle('Sound (DB)'),
	    DTColumnBuilder.newColumn('accImageUrl').withTitle('Camera'),
	];
	
//	// JQUERY DATE TIME PICKER
//	$(document).ready(function(){
//		$("#pickerDateBirth").datetimepicker({
//			 format: 'DD/MM/YYYY'
//		 });
//		
//		$("#pickerDateBirth").on("dp.change", function(){
//			vm.dateBirth = $("#dataB").val();
//		});
//	});
 
});
 
app.factory('callRestMongo', function($http){
	return{
		getRestMongo: function(service, register, name, date, tempC, tempF, sound, imageUrl){
			
			var Indata = {'accRegister': register, 'accName': name, 'accDate': date,
							'accTemperatureC': tempC, 'accTemperatureF': tempF,
							'accSound': sound, 'accImageUrl': imageUrl };
			
			console.log(Indata);
			return $http({
					url: '/com.example/account' + service,
					method: 'POST',
					params: Indata
			})
			.then(
				function(response){
					console.log(response.data);
					return response.data;
				}
			);
		}
	}
});