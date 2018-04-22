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
		"url": "/com.example/mongoGetAllClients",
		"type": 'GET'
	})
	.withOption('createdRow', function(row, data, dataIndex){
		// recompile so we can bind angular directive to the DT
		$compile(angular.element(row).contents())($scope);
	});
	//call to account.jsp
	vm.dtColumns = [
	    DTColumnBuilder.newColumn(null).withTitle('').notSortable().renderWith(function(data, type, full, meta){
	    	
	    	vm.selected[full.id] = false;
	    	
	    	numbersId[meta.row] = data.cliNumber;
	    	
	    	return '<input type="checkbox" ng-model="vm.selected[' + meta.row + ']"/>';
	    }),
	    DTColumnBuilder.newColumn('cliNumber').withTitle('_id').notVisible(),
	    DTColumnBuilder.newColumn('cliName').withTitle('Name'),
	    DTColumnBuilder.newColumn('cliDatebirth').withTitle('Date Birth'),
	    DTColumnBuilder.newColumn('cliRegister').withTitle('Register'),
	    DTColumnBuilder.newColumn('cliCountry').withTitle('Country'),
	 // ----------------- Add column with button to access account ---------------
        DTColumnBuilder.newColumn(null).withTitle('').notSortable()
          .renderWith(function (data, type, full, meta) {
        	  $scope.url = window.location.href + "account?accRegister=" + data.cliRegister;
               return '<a class="btn btn-info" ng-model="vm.buttonSearch" href="'+$scope.url+'">Access</a>';
        })
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
		getRestMongo: function(service, name, dateBirth, register, country, checked){
			
			var Indata = {'name': name, 'dateBirth': dateBirth,
							'register': register, 'country': country, 'checked': checked };
			
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