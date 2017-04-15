$(function() {
	$("#menu")
			.menu(
					{
						select : function(event, ui) {
							$('#display').show();
							showOptions = {
								mode : 'show'
							};
							hideOptions = {
								mode : 'hide'
							};
							if (ui.item.text() == 'Institute Management') {
								$('#content').hide('fold');
								$
										.get(
												'http://localhost:8080/PHDWebApp/admininstitute',
												appendToContent);
								$('#content').effect( 'slide', showOptions, 500, function(){} );
							} else if (ui.item.text() == 'Faculty Management') {
								$('#content').hide('fold');
								$
										.get(
												'http://localhost:8080/PHDWebApp/adminfaculty',
												appendToContent);
								$('#content').effect( 'slide', showOptions, 500, function(){} );
							} else if (ui.item.text() == 'Department Management') {
								$('#content').hide('fold');
								$
										.get(
												'http://localhost:8080/PHDWebApp/admindepartment',
												appendToContent);
								$('#content').effect( 'slide', showOptions, 500, function(){} );
							} else if (ui.item.text() == 'Supervisor Management') {
								$('#content').hide('fold');
								$
										.get(
												'http://localhost:8080/PHDWebApp/adminsupervisor',
												appendToContent);
								$('#content').effect( 'slide', showOptions, 500, function(){} );
							} else if (ui.item.text() == 'Scholar Management') {
								$('#content').hide('fold');
								$
										.get(
												'http://localhost:8080/PHDWebApp/adminscholar',
												appendToContent);
								$('#content').effect( 'slide', showOptions, 500, function(){} );
							} else if (ui.item.text() == 'Monthly Reports') {
								$('#content').hide('fold');
								$
										.get(
												'http://localhost:8080/PHDWebApp/supervisormonthlyreport',
												appendToContent);
								$('#content').effect( 'slide', showOptions, 500, function(){} );
							} else if (ui.item.text() == 'Quarterly Reports') {
								$('#content').hide('fold');
								$
										.get(
												'http://localhost:8080/PHDWebApp/supervisorquarterlyreport',
												appendToContent);
								$('#content').effect( 'slide', showOptions, 500, function(){} );
							} else if (ui.item.text() == 'Colloquium') {
								$('#content').hide('fold');
								$
										.get(
												'http://localhost:8080/PHDWebApp/admincolloquium',
												appendToContent);
								$('#content').effect( 'slide', showOptions, 500, function(){} );
							} else if (ui.item.text() == 'Report Controller') {
								$('#content').hide('fold');
								$
										.get(
												'http://localhost:8080/PHDWebApp/reportcontroller',
												appendToContent);
								$('#content').effect( 'slide', showOptions, 500, function(){} );
							} else if (ui.item.text() == 'Others') {
								$('#content').hide('fold');
								//$.get('http://localhost:8080/PHDWebApp/',appendToContent);
								//$('#content').effect( 'slide', showOptions, 500, function(){} );
							}
						}
					});
});

function clickhandler(operation) {
	if (operation === 'newinstitute') {
		$('#content').hide('fold');
		$.get('http://localhost:8080/PHDWebApp/addinstitute', appendToContent);
		$('#content').effect( 'slide', showOptions, 500, function(){} );
	} else if (operation === 'newfaculty') {
		$('#content').hide('fold');
		$.get('http://localhost:8080/PHDWebApp/addfaculty', appendToContent);
		$('#content').effect( 'slide', showOptions, 500, function(){} );
	} else if (operation === 'newdepartment') {
		$('#content').hide('fold');
		$.get('http://localhost:8080/PHDWebApp/adddepartment', appendToContent);
		$('#content').effect( 'slide', showOptions, 500, function(){} );
	} else if (operation === 'newcolloquium') {
		$('#content').hide('fold');
		$.get('http://localhost:8080/PHDWebApp/newcolloquium', appendToContent);
		$('#content').effect( 'slide', showOptions, 500, function(){} );
	}else if (operation === 'newsupervisor') {
		$('#content').hide('fold');
		$.get('http://localhost:8080/PHDWebApp/registersupervisor',	appendToContent);
		$('#content').effect( 'slide', showOptions, 500, function(){} );
	} else if (operation === 'newscholar') {
		$('#content').hide('fold');
		$.get('http://localhost:8080/PHDWebApp/register',	appendToContent);
		$('#content').effect( 'slide', showOptions, 500, function(){} );
	} else if (operation === 'deleteinstitute') {
		var url = "http://localhost:8080/PHDWebApp/invoke?";
		url = url + "object=institute&";
		url = url + "operation=delete&";
		url = url + "input=" + getSelectedIDs();
		$.get(url,function(){});
		$('#content').effect( 'slide', showOptions, 500, function(){} );
	} else if (operation === 'deletefaculty') {
		var url = "http://localhost:8080/PHDWebApp/invoke?";
		url = url + "object=faculty&";
		url = url + "operation=delete&";
		url = url + "input=" + getSelectedIDs();
		$.get(url,function(){});
		$('#content').effect( 'slide', showOptions, 500, function(){} );
	} else if (operation === 'deletedepartment') {
		var url = "http://localhost:8080/PHDWebApp/invoke?";
		url = url + "object=department&";
		url = url + "operation=delete&";
		url = url + "input=" + getSelectedIDs();
		$.get(url,function(){});
		$('#content').effect( 'slide', showOptions, 500, function(){} );
	} else if (operation === 'deletesupervisor') {
		var url = "http://localhost:8080/PHDWebApp/invoke?";
		url = url + "object=supervisor&";
		url = url + "operation=delete&";
		url = url + "input=" + getSelectedIDs();
		$.get(url,function(){});
		$('#content').effect( 'slide', showOptions, 500, function(){} );
	} else if (operation === 'deletescholar') {
		var url = "http://localhost:8080/PHDWebApp/invoke?";
		url = url + "object=scholar&";
		url = url + "operation=delete&";
		url = url + "input=" + getSelectedIDs();
		$.get(url,function(){});
		$('#content').effect( 'slide', showOptions, 500, function(){} );
	} else if (operation === 'deletemonthlyreport') {
		var url = "http://localhost:8080/PHDWebApp/invoke?";
		url = url + "object=monthlyreport&";
		url = url + "operation=delete&";
		url = url + "input=" + getSelectedIDs();
		$.get(url,function(){});
		$('#content').effect( 'slide', showOptions, 500, function(){} );
	} else if (operation === 'deletequarterlyreport') {
		var url = "http://localhost:8080/PHDWebApp/invoke?";
		url = url + "object=quarterlyreport&";
		url = url + "operation=delete&";
		url = url + "input=" + getSelectedIDs();
		$.get(url,function(){});
		$('#content').effect( 'slide', showOptions, 500, function(){} );
	}
}

/*function viewhandler(operation) {
	var count = 0;
	$(".mainBody tr").each(function(){
			if($(this).find('td input[type="checkbox"]').prop('checked') === true){
				count = count + 1;
			}
		});
	if(count > 1){
	alert('Please select only one at a atime. No of selected items : '+ count);
	return;
	}
	if (operation === 'colloquium'){
		var url = "http://localhost:8080/PHDWebApp/viewhandler?";
		url = url + "object=colloquium&";
		url = url + "id=" + getSelectedIDs();
		$.get(url,displayPanel);
	}else if (operation === 'institute') {
		var url = "http://localhost:8080/PHDWebApp/viewhandler?";
		url = url + "object=institute&";
		url = url + "id=" + getSelectedIDs();
		$.get(url,function(){});
		$('#content').effect( 'slide', showOptions, 500, function(){} );
	}else if (operation === 'faculty') {
		var url = "http://localhost:8080/PHDWebApp/viewhandler?";
		url = url + "object=faculty&";
		url = url + "id=" + getSelectedIDs();
		$.get(url,function(){});
		$('#content').effect( 'slide', showOptions, 500, function(){} );
	}else if (operation === 'department') {
		var url = "http://localhost:8080/PHDWebApp/viewhandler?";
		url = url + "object=department&";
		url = url + "id=" + getSelectedIDs();
		$.get(url,function(){});
		$('#content').effect( 'slide', showOptions, 500, function(){} );
	}else if (operation === 'scholar') {
		var url = "http://localhost:8080/PHDWebApp/viewhandler?";
		url = url + "object=scholar&";
		url = url + "id=" + getSelectedIDs();
		$.get(url,function(){});
		$('#content').effect( 'slide', showOptions, 500, function(){} );
	}else if (operation === 'supervisor') {
		var url = "http://localhost:8080/PHDWebApp/viewhandler?";
		url = url + "object=supervisor&";
		url = url + "id=" + getSelectedIDs();
		$.get(url,function(){});
		$('#content').effect( 'slide', showOptions, 500, function(){} );
	}
}*/

function appendToContent(returnData) {
	$('#content').html(returnData);
	//var arr = $('#content script');
	   // eval(arr.text());//run script inside div
	//$('table').tablesorter();
}

function checkAll(){
	if($('#main thead tr:first th input[type="checkbox"]').prop('checked') == true){
			$('.mainBody tr').each(function(){
			$(this).find('td input[type="checkbox"]').prop('checked', true);
			});
		}else{
			$('.mainBody tr').each(function(){
			$(this).find('td input[type="checkbox"]').prop('checked', false);
			});
		}
}

function getSelectedIDs() {
	var idStr = "";
	$(".mainBody tr").each(function(){
		if($(this).find('td input[type="checkbox"]').prop('checked') === true){
			idStr = idStr + escape($(this).find('td#id').html())+'@';
		}
	});
	return idStr;
}

function reportHandler(obj, opr) {
	var url = "http://localhost:8080/PHDWebApp/invoke?";
	url = url + "object=" + obj + "&";
	url = url + "operation=" + opr + "&";
	url = url + "input=" + getSelectedIDs();
	$.get(url,
			function(){});
	alert('URL invoked : '+url);
}
function viewSelected(obj){
	var count = 0;
	$(".mainBody tr").each(function(){
			if($(this).find('td input[type="checkbox"]').prop('checked') === true){
				count = count + 1;
			}
		});
	if(count > 1){
	alert('Please select only one at a atime. No of selected items : '+ count);
	return;
	}
	var url = 'http://localhost:8080/PHDWebApp/viewreport?type='+obj+'&id=';
	if(($('.mainBody tr td input[type="checkbox"]').prop('checked')).length > 1) return;
	$(".mainBody tr").each(function(){
		if($(this).find('td input[type="checkbox"]').prop('checked') === true){
			url = url + escape($(this).find('td#id').html());
			window.open(url);
			return;
		}
	});
}

function displayPanel(returnData){
	var h = window.innerHeight;
	var w = window.innerWidth;
$("#showpanel").css("top",h/3);
$("#showpanel").css("left",w/4);
$("#showpanel").css("width",w/2);
$("#showpanel").css("height",h/3);
$("#showpanel").css("background-color","white");
$("#hideBg").css("width",w);
$("#hideBg").css("height",h);
$("#hideBg").css("margin",0);
$("#hideBg").css("padding",0);
$("#hideBg").css("opacity",0.5);
$("#hideBg").css("background-color","white");
$("#hideBg").css("z-index",100);
$("#showpanel").css("z-index",200);
$("#hideBg").show();
$("#showpanel").html(returnData);
$("#showpanel").show();
}




function closeDisplayPanel(){
	$("#hideBg").hide();
	$("#showpanel").hide();
}


var elem = $( '#showpanel' )[0];


/*$( document ).on( 'click', function ( e ) {
    if ( $( e.target ).closest( elem ).length === 0 ) {
    	closeDisplayPanel();
    }
});*/
                
$( document ).on( 'keydown', function ( e ) {
    if ( e.keyCode === 27 ) {
    	closeDisplayPanel();
    }
});









