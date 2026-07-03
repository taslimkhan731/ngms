<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./customCSS/bookingForm.css" rel="stylesheet" media="screen">
<script src="./customJS/application/extention.js"></script>
</head>
<div class="row">
	<div class="col-md-12 table-responsive">
		<table
			class="table table-condensed table-striped table-bordered display nowrap w-100"
			id="guestDataTable">
			<thead>
				<tr class="text-center text-nowrap">
					<th scope="col">Sl. No.</th>
					<th scope="col">Booking Id</th>
					<th scope="col">Guest House</th>
					<th scope="col">From Dt</th>
					<th scope="col">To Dt</th>
					<th scope="col">Purpose</th>
					<th scope="col">Conveyance</th>
					<th scope="col">Status</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody class="text-center">
			</tbody>
		</table>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModalView" tabindex="-1"
	data-backdrop="static" data-keyboard="false" role="dialog"
	aria-labelledby="myModalView" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document"
		style="overflow-y: scroll; max-height: 100%; overflow-y: initial !important">
		<!-- Modal content-->
		<form action="#">
			<input type="hidden" id="saltKey" name="saltKey" value="${saltKey}">
			<input type="hidden" id="fromDate" name="fromDate">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">
						Extention Application For Booking Id <span id="bookingId"
							style="color: #b30f5a; text-decoration: underline;"></span>
					</h5>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group row">
								<label for="frmDt" class="col-sm-4 text-font-var">From
									Date</label> <input type="text" class="form-control col-sm-8"
									id="frmDt" readonly="readonly">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group row">
								<label for="toDt" class="col-sm-4 text-font-var">To Date<small
									class="text-red">*</small></label> <input type="text"
									class="form-control col-sm-8" id="toDt" name="toDt"
									placeholder="DD-MM-YYYY">
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer card-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-success" id="extBtn">Submit</button>
				</div>
			</div>
		</form>
	</div>
</div>
</html>