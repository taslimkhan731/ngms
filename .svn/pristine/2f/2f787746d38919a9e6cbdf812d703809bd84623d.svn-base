<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"
	src="./customJS/MasterSetup/notice.js"></script>
</head>
<body>
	<div class="container-fluid entry-header">
		<h3 class="heading-bg-color">
			<i class="fa fa-home fa-lg pr-2"></i>Notice Master
		</h3>
	</div>
	<div class="container-fluid vh100">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-3">
				<div class="card">
					<div class="card-header">
						<i class="fa fa-plus pr-2"></i>Notice Master Details
					</div>
					<div class="card-body">
						<form action="#" class="city_resource" id="new_city_form"
							method="post" accept-charset="utf-8">
								<input type="hidden" name="noticeId" id="noticeId"
									value="" class="form-control">
							<div class="form-row">
								<div class="form-group col-md-6">
									<label>City:</label> 
									<select name="city_name" id="city_name" class="form-control">
									
									</select>
								</div>
								
								<div class="form-group col-md-6">
									<label>Guest House:</label> 
									<select name="gh_name" id="gh_name" class="form-control">
									
									</select>
								</div>
							</div>

							<div class="form-group">
								<label>Notice Subject:
								</label> <input type="text" name="notice_subject" id="notice_subject"
									class="form-control" placeholder="Enter Notice Subject" autocomplete="off">
							</div>

							<div class="form-group">
								<label>Notice Description:</label>
								<textarea id="notice_desc" name="notice_desc" rows="3"
									class="form-control"></textarea>
							</div>
							
							<div class="form-row">
								<div class="form-group col-md-6">
									<label>Start Date:
									</label> <input type="text" name="startDt" id="startDt"
										class="form-control" placeholder="DD-MM-YYYY" autocomplete="off">
								</div>
								
								<div class="form-group col-md-6">
									<label>End Date:
									</label> <input type="text" name="endDt" id="endDt"
										class="form-control" placeholder="DD-MM-YYYY" autocomplete="off">
								</div>
							</div>

							<div class="form-action">
								<div class="row">
									<div class="col-xs-6 col-sm-6">
										<button type="reset" class="btn btn-warning btn-block"
											onClick="resetFormCreateNotice()">Reset</button>
									</div>
									<div class="col-xs-6 col-sm-6">
										<button type="button" class="btn btn-primary btn-block"
											id="addNotice">Add</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>

			<div class="col-xs-12 col-sm-12 col-md-9">
				<div class="card">
					<div class="card-header">Notice Master Details</div>
					<div class="card-body">
						<table class="table table-condensed table-bordered display nowrap"
							id="UserNoticeDetails" width="100%">
							<thead class="text-center">
								<tr>
									<th>Id</th>
									<th>Notice Subject</th>
									<th>Notice Description</th>
									<th>Start Date</th>
									<th>End Date</th>
									<th>Status</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody class="text-center">
							</tbody>
						</table>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>
</html>