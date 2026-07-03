<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript"
	src="./customJS/MasterSetup/designation.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-3 admin-col-right">
				<div class="card">
					<div class="card-header">
						<i class="fa fa-plus pr-2"></i>Designation Details
					</div>
					<div class="card-body c-body">
						<form action="#" class="city_resource" id="new_city_form"
							method="post" accept-charset="utf-8">
							<div class="form-group">
								<input type="hidden" name="desgId" id="desgId" value=""
									class="form-control">
							</div>
							<div class="form-group">
								<label>Category Type: <small class="small">*</small></label> <select class="form-control"
									name="category_type" id="category_type">
								</select>
							</div>

							<div class="form-group">
								<label>Designation Name: <small class="small">*</small></label> <input type="text"
									class="form-control" name="desg_name" id="desg_name">
							</div>

							<div class="form-group">
								<label>Priority: <small class="small">*</small></label> <input type="text" class="form-control"
									name="priority" id="priority">
							</div>

							<div class="form-action">
								<div class="row">
									<div class="col-xs-6 col-sm-6 pt-1 col-btn-left">
										<button type="reset" class="btn btn-reset btn-block"
											onClick="resetFormCreateDesignation()">Reset</button>
									</div>
									<div class="col-xs-6 col-sm-6 pt-1 col-btn-right">
										<button type="submit" class="btn btn-add btn-block"
											id="addDesignation">Add</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>

			<div class="col-xs-12 col-sm-12 col-md-9 admin-col-left">
				<div class="card">
					<div class="card-header">Designation Details</div>
					<div class="card-body">
						<table class="table tbl" id="UserDesignationDetails" width="100%">
							<thead class="t-head">
								<tr>
									<th class="text-nowrap">Designation Id</th>
									<th class="text-nowrap">Designation Name</th>
									<th class="text-nowrap">Category Type</th>
									<th class="text-nowrap">Priority</th>
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