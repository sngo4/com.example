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
		"url": "/com.example/getAllAccountInfo",
		"type": 'GET'
	})
	.withOption('createdRow', function(row, data, dataIndex){
		// recompile so we can bind angular directive to the DT
		$compile(angular.element(row).contents())($scope);
	});
	//call to account.jsp
	vm.dtColumns = [DTColumnBuilder.newColumn('accId').withTitle('_id').notVisible(),
	        	    DTColumnBuilder.newColumn('accRegister').withTitle('Register'),
	        	    DTColumnBuilder.newColumn('accName').withTitle('Name'),
	        	    DTColumnBuilder.newColumn('accDate').withTitle('Date'),
	        	    DTColumnBuilder.newColumn('accTemperatureC').withTitle('Temperature(C)'),
	        	    DTColumnBuilder.newColumn('accTemperatureF').withTitle('Temperature(F)'),
	        	    DTColumnBuilder.newColumn('accSound').withTitle('Sound (DB)'),
	        	    DTColumnBuilder.newColumn('accImageUrl').withTitle('Camera')
	];
	vm.callReload = function(){
		vm.dtInstance.reloadData();
	}
	
	vm.callInsert = function(){
		callRestMongo.getRestMongo("insertClient", vm.name, vm.dateBirth, vm.register, vm.country, '')
			.then(function(data){
				vm.dtInstance.reloadData();
				vm.name = '';
				vm.dateBirth = '';
				vm.register = '';
				vm.country = '';
			});
	}
	
	vm.callDelete = function(){
		var checked = [];
		
		for( var prop in vm.selected ){
			if(Boolean(vm.selected[prop]) ){
				checked.push(numbersId[prop]);
				vm.selected[prop] = false;
			}
		}
	
		callRestMongo.getRestMongo("deleteClientByNumbers", vm.name, vm.dateBirth, vm.register, vm.country, checked.join(",") )
			.then(function(data){
				vm.dtInstance.reloadData();
			}
		);
	}
	
	// JQUERY DATE TIME PICKER
	$(document).ready(function(){
		$("#pickerDateBirth").datetimepicker({
			 format: 'DD/MM/YYYY'
		 });
		
		$("#pickerDateBirth").on("dp.change", function(){
			vm.dateBirth = $("#dataB").val();
		});
	});
 
});
 
app.factory('callRestMongo', function($http){
	return{
		getRestMongo: function(service, register, name, date, tempC, tempF, sound, imageUrl){
			
			var Indata = {'accRegister': register, 'accName': name, 'accDate': date,
							'accTemperatureC': tempC, 'accTemperatureF': tempF,
							'accSound': sound, 'accImageUrl': imageUrl };
			
			console.log(Indata);
			return $http({
					url: '/com.example/' + service,
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

// POPUP START
var app2 = angular.module('MyPopupApp', ['ngMaterial', 'ngMessages', 'material.svgAssetsCache']);

app2.controller('AppCtrl', function($scope, $mdDialog) {
  $scope.openFromLeft = function() {
    $mdDialog.show(
      $mdDialog.alert()
        .clickOutsideToClose(true)
        .title('Opening from the left')
        .textContent('Closing to the right!')
        .ariaLabel('Left to right demo')
        .ok('Nice!')
        // You can specify either sting with query selector
        .openFrom('#left')
        // or an element
        .closeTo(angular.element(document.querySelector('#right')))
    );
  };

  $scope.openOffscreen = function() {
    $mdDialog.show(
      $mdDialog.alert()
        .clickOutsideToClose(true)
        .title('Opening from offscreen')
        .textContent('Closing to offscreen')
        .ariaLabel('Offscreen Demo')
        .ok('Amazing!')
        // Or you can specify the rect to do the transition from
        .openFrom({
          top: -50,
          width: 30,
          height: 80
        })
        .closeTo({
          left: 1500
        })
    );
  };
});
// POPUP END