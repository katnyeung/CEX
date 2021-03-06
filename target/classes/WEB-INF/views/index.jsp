<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="/resources/js/jquery.cookie.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		var url = window.location.href;
		if (url && window.location.href.indexOf('?') > 0) {
			var tags = url.toLowerCase().slice(window.location.href.indexOf('?') + 1).split(",");
			if (tags) {
				$("#searchTags").val(tags.join(" "));
				search(tags);
			}
		}

		$("#urlResultPattern").hide();
		$("#contentResultPattern").hide();

		$("#searchButton").click(function() {
			search($("#searchTags").val().split(" "));
		});
	});

	function search(tagArray) {
		result = $.ajax({
			url : "/search",
			type : "POST",
			data : JSON.stringify(tagArray),
			contentType : "text/plain; charset=UTF-8",
			success : function(data) {

				window.history.pushState(null, null, "/?" + tagArray.join(","));

				$("#searchResult").html('');

				$.each(jQuery.parseJSON(data), function(index, value) {
					var $urlPost = $('#urlResultPattern').clone();
					$urlPost.attr('rel', value.itemId);

					$urlPost.find('.title').text(value.title);
					$urlPost.find('.url').attr("href", "/redirect/" + value.itemId + "/?" + value.content);
					$urlPost.find('.remark').text(value.remark);
					
					$urlPost.find('.buyoutPrice').text(value.buyoutPrice);
					$urlPost.find('.createDate').text(value.createdate);
					$urlPost.find('.up').attr("rel", value.itemId);
					$urlPost.find('.down').attr("rel", (value.itemId * -1));

					$urlPost.find('.up').click(function() {
						rate($(this).attr('rel'));
					});
					$urlPost.find('.down').click(function() {
						rate($(this).attr('rel'));
					});

					$urlPost.find('.rating').text(value.rating);

					$("#searchResult").append($urlPost.show());

				});

			},
		});

	}

	function rate(id) {

		result = $.ajax({
			url : "/item/rate",
			type : "POST",
			data : id,
			contentType : "text/plain; charset=UTF-8",
			success : function(data) {
				if (data.redirect) {
					// data.redirect contains the string URL to redirect to
					window.location.href = data.redirect;
				} else {
					$('div[rel="' + id + '"]').find('.rate').text(data);
				}
			}
		});

	}
</script>
</head>
<body>
	<c:out value="${tags}"></c:out>

	<a href="/item/create">Create Item</a>

	<div>

		#<input type="text" id="searchTags" />
		<button id="searchButton">Search</button>

	</div>

	<div id="searchResult"></div>

	<div id="urlResultPattern">
		<table cellpadding="10" cellspacing="10">
			<tr>
				<td>
					<div class="up">Up</div>
					<div class="rating">.</div>
					<div class="down">Down</div>
				</td>
				<td>
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td><a href="#" class="url"><span class="title"></span></a></td>
						</tr>
						<tr>
							<td>Remark : <span class="remark"></span></td>
						</tr>
						<tr>
							<td>Buyout Price : <span class="buyoutPrice"></span></td>
						</tr>
						<tr>
							<td>Create Date : <span class="createDate"></span></td>
						</tr>
						<tr>
							<td><button>I WANT</button></td>
						</tr>

					</table>

				</td>
			</tr>
		</table>


	</div>


</body>
</html>