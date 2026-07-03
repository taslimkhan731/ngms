<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="./customJS/MasterSetup/roomrent.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-3 admin-col-right">
				<div class="card">
					<div class="card-header">
						<i class="fa fa-plus pr-2"></i>Room Rent Details
					</div>
					<div class="card-body c-body">
						<form action="#" class="city_resource" id="new_city_form"
							method="post" accept-charset="utf-8">
							<div class="form-group">
								<input type="hidden" name="rentId" id="rentId" value="123"
									class="form-control">
							</div>

							<div class="form-group">
								<label>Rent Amount: <small class="small">*</small></label> <input type="number"
									class="form-control" name="rent_amount" id="rent_amount"
									min="1">
							</div>

							<div class="form-action">
								<div class="row">
									<div class="col-xs-6 col-sm-6 pt-1 col-btn-left">
										<button type="reset" class="btn btn-reset btn-block"
											onClick="resetFormCreateRent()">Reset</button>
									</div>
									<div class="col-xs-6 col-sm-6 pt-1 col-btn-right">
										<button type="submit" class="btn btn-add btn-block"
											id="addRent">Add</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>

			<div class="col-xs-12 col-sm-12 col-md-9 admin-col-left">
				<div class="card">
					<div class="card-header">Room Rent Details</div>
					<div class="card-body">
						<table class="table tbl" id="UserRentDetails" width="100%">
							<thead class="t-head">
								<tr>
									<th class="text-nowrap">Room Rent Id</th>
									<th class="text-nowrap">Room Rent Amount</th>
									<th class="text-nowrap">Action</th>
								</tr>
							</thead>
							<tbody class="text-style">
							</tbody>
						</table>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>
</html>