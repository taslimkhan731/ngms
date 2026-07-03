<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="./customJS/MasterSetup/notice.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-3 admin-col-right">
				<div class="card">
					<div class="card-header">
						<i class="fa fa-plus pr-2"></i>Notice Master Details
					</div>
					<div class="card-body c-body">
						<form action="#" class="city_resource" id="new_city_form"
							method="post" accept-charset="utf-8">
							<input type="hidden" name="noticeId" id="noticeId" value=""
								class="form-control">
							<div class="form-row">
								<div class="form-group col-md-6">
									<label>City: <small class="small">*</small></label> <select name="city" id="city_name"
										class="form-control">
											<option value="">Select City</option>
									</select>
								</div>

								<div class="form-group col-md-6">
									<label>Guest House: <small class="small">*</small></label> <select name="gHouse" id="gh_name"
										class="form-control">
											<option value="">Select Guest House</option>
									</select>
								</div>
							</div>

							<div class="form-group">
								<label>Notice Subject: <small class="small">*</small></label> <input type="text"
									name="notice_subject" id="notice_subject" class="form-control"
									placeholder="Enter Notice Subject" autocomplete="off">
							</div>

							<div class="form-group">
								<label>Notice Description: <small class="small">*</small></label>
								<textarea id="notice_desc" name="notice_desc" rows="3"
									class="form-control"></textarea>
							</div>

							<div class="form-row">
								<div class="form-group col-md-6">
									<label>Start Date: <small class="small">*</small></label> <input type="text" name="startDt"
										id="startDt" class="form-control" placeholder="DD-MM-YYYY"
										autocomplete="off">
								</div>

								<div class="form-group col-md-6">
									<label>End Date: <small class="small">*</small></label><input type="text" name="endDt"
										id="endDt" class="form-control" placeholder="DD-MM-YYYY"
										autocomplete="off">
								</div>
							</div>

							<div class="form-action">
								<div class="row">
									<div class="col-xs-6 col-sm-6 col-btn-left pt-1">
										<button type="reset" class="btn btn-reset btn-block"
											onClick="resetFormCreateNotice()">Reset</button>
									</div>
									<div class="col-xs-6 col-sm-6 col-btn-right pt-1">
										<button type="submit" class="btn btn-add btn-block"
											id="addNotice">Add</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>

			<div class="col-xs-12 col-sm-12 col-md-9 admin-col-left">
				<div class="card">
					<div class="card-header">Notice Master Details</div>
					<div class="card-body">
						<table class="table tbl" id="UserNoticeDetails" width="100%">
							<thead class="t-head">
								<tr>
									<th class="text-nowrap">Id</th>
									<th class="text-nowrap">Notice Subject</th>
									<th class="text-nowrap">Notice Description</th>
									<th class="text-nowrap">Start Date</th>
									<th class="text-nowrap">End Date</th>
									<th class="text-nowrap">Status</th>
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