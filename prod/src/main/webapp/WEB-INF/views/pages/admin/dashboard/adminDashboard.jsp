<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="./customCSS/admin/sysadminDashboard.css" rel="stylesheet"
	media="screen">
<script src="./customJS/setting/adminDashboard.js"></script>
<title></title>
</head>
<body>
	<section class="container-fluid">
		<section class="row">
			<div class="col-md-9">
				<div class="row">
					<div class="col-md-3 colmd">
						<div class="mini-card">
							<div class="mini-card-header">Total Users</div>
							<div class="mini-card-divider"></div>
							<div class="rht-opt">
								<div class="opt-name" id="totalUser"></div>
							</div>
						</div>
					</div>
					<div class="col-md-3 colmd">
						<div class="mini-card">
							<div class="mini-card-header">Total Moderator</div>
							<div class="mini-card-divider"></div>
							<div class="rht-opt">
								<div class="opt-name" id = "moderator"></div>
							</div>
						</div>
					</div>
					<div class="col-md-3 colmd">
						<div class="mini-card">
							<div class="mini-card-header">Approver</div>
							<div class="mini-card-divider"></div>
							<div class="rht-opt">
								<div class="opt-name" id ="approver"></div>
							</div>
						</div>
					</div>
					<div class="col-md-3 colmd">
						<div class="mini-card">
							<div class="mini-card-header">Manager</div>
							<div class="mini-card-divider"></div>
							<div class="rht-opt">
								<div class="opt-name" id="manager"></div>
							</div>
						</div>
					</div>
					<div class="col-md-3 colmd">
						<div class="mini-card">
							<div class="mini-card-header">Total Receptionist</div>
							<div class="mini-card-divider"></div>
							<div class="rht-opt">
								<div class="opt-name" id = "receptionist"></div>
							</div>
						</div>
					</div>
					<div class="col-md-3 colmd">
						<div class="mini-card">
							<div class="mini-card-header">Guest Users</div>
							<div class="mini-card-divider"></div>
							<div class="rht-opt">
								<div class="opt-name" id = "guest"></div>
							</div>
						</div>
					</div>
					<div class="col-md-3 colmd">
						<div class="mini-card">
							<div class="mini-card-header">Total City</div>
							<div class="mini-card-divider"></div>
							<div class="rht-opt">
								<div class="opt-name" id= "city"></div>
							</div>
						</div>
					</div>
					<div class="col-md-3 colmd">
						<div class="mini-card">
							<div class="mini-card-header">Total GuestHouse</div>
							<div class="mini-card-divider"></div>
							<div class="rht-opt">
								<div class="opt-name" id ="guestHouse"></div>
							</div>
						</div>
					</div>	
				</div>
			</div>
		<div class="col-lg-3 col-xs-12 col-sm-3 col-md-3">
		<!-- style="background-color: white; padding: 0;" -->

		<div class="card">
			<div class="card-header" style="background-color: #3F150B;">
				<div class="card-title" style="color: wheat;">Notice</div>
			</div>
			<div class="card-body" style="height: 316px;">
				<div class="marquee">
					<div class="list">
						<div class="list-item">
							<div class="row ml-0 mr-0">
								<div class="col-2 list-item-icon"></div>
								<div class="col-10 list-item-content">
									<div class="list-item-header a">
										<marquee direction="up"
											style="height: 300px; font-size: 18px; padding-left: 24px;">
											Testing
										</marquee>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
		</section>
	</section>
</body>

</html>