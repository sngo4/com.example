<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Angular Spring Rest Mongo</title>

<!-- JQUERY -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.3.1.min.js" />"></script>
<script	src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<link href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css" rel="stylesheet" />

<!-- ANGULAR -->
<script src="resources/js/angular.min.js"></script>

<script	src="resources/js/angularDatatables/angular-datatables.directive.js"></script>
<script	src="resources/js/angularDatatables/angular-datatables.instances.js"></script>
<script src="resources/js/angularDatatables/angular-datatables.util.js"></script>
<script	src="resources/js/angularDatatables/angular-datatables.renderer.js"></script>
<script	src="resources/js/angularDatatables/angular-datatables.factory.js"></script>
<script	src="resources/js/angularDatatables/angular-datatables.options.js"></script>
<script src="resources/js/angularDatatables/angular-datatables.js"></script>
<script	src="resources/js/angularDatatables/vendor/datatables-select/js/dataTables.select.js"></script>
<script	src="resources/js/angularDatatables/dist/plugins/select/angular-datatables.select.min.js"></script>
<link rel="stylesheet" href="resources/js/angularDatatables/vendor/datatables-select/css/select.dataTables.css">

<script src="resources/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.19.1/moment-with-locales.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet"	href="resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.css">
<link rel="stylesheet"	href="resources/js/angularDatatables/vendor/bootstrap/dist/css/bootstrap.min.css">
<!-- CUSTOM -->
<script src="resources/js/clientManager.js"></script>

<style>
body  {
    background-image: url("<c:url value='/resources/image/minimalistic.jpg'/>");
}
</style>

</head>
<body ng-app="myApp" ng-controller="dashboardCtrl as vm">
	<div class="container">
		<h2>System Example</h2>

		<form class="form-horizontal" role="form">
			<div class="form-group">
				<label class="control-label col-sm-2" for="name">Name:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" ng-model="vm.name">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="dateBirth">Date
					of Birth:</label>
				<div class="col-sm-10">
					<div class="input-group date" id="pickerDateBirth">
						<input type="text" class="form-control" placeholder="dd/MM/yyyy"
							id="dataB" ng-model="vm.dateBirth"> <span
							class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="register">Register:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" ng-model="vm.register">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="country">Country:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" ng-model="vm.country">
				</div>
			</div>
			<div class="form-group"> 
				<button type="button" class="btn btn-info btn-lg"
					ng-model="vm.buttonReload" ng-click="vm.callReload()">
					<span class="glyphicon glyphicon-refresh"></span> Reload
				</button>
				<button type="button" class="btn btn-success btn-lg"
					ng-model="vm.buttonInsert" ng-click="vm.callInsert()">
					<span class="glyphicon glyphicon-plus-sign"></span> Insert
				</button>
				<button type="button" class="btn btn-warning  btn-lg"
					ng-model="vm.buttonDelete" ng-click="vm.callDelete()">
					<span class="glyphicon glyphicon-minus-sign"></span> Delete
				</button>
			</div>
			<div>
				<table width="100%" datatable="" class="row-border hover"
					dt-options="vm.dtOptions" dt-column-defs="vm.dtColumnDefs"
					dt-columns="vm.dtColumns" dt-instance="vm.dtInstance"></table>
			</div>
		</form>


	</div>

</body>
</html>