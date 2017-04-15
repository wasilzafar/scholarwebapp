$(function() {
	$( "#menu" ).menu({
	    select: function(event, ui ) {
	    	showOptions = { mode: 'show' };
	    	hideOptions = { mode: 'hide' };
	    	if(ui.item.text()=='Monthly Reports'){
	    		$('#qrcontent').hide('fold');
	    		$('#reportTable').hide('fold');
	    		$('#mrcontent').effect( 'slide', showOptions, 500, function(){} );
	    	}else if(ui.item.text()=='Quarterly Reports'){
	    		$('#mrcontent').hide('fold');
	    		$('#reportTable').hide('fold');
	    		$('#qrcontent').effect( 'slide', showOptions, 500, function(){} );
	    	}else if(ui.item.text()=='My Monthly Reports'){
	    		$('#mrcontent').hide('fold');
	    		$('#qrcontent').hide('fold');
	    		$('#reportTable').hide('fold');
	    		$.get('http://localhost:8080/PHDWebApp/scholarmonthlyreports', appendTable);
	    		$('#reportTable').effect( 'slide', showOptions, 500, function(){} );
	    	}else if(ui.item.text()=='My Quarterly Reports'){
	      		$('#mrcontent').hide('fold');
	    		$('#qrcontent').hide('fold');
	    		$('#reportTable').hide('fold');
	    		$.get('http://localhost:8080/PHDWebApp/scholarquarterlyreports', appendTable);
	    		$('#reportTable').effect( 'slide', showOptions, 500, function(){} );
	    	}
	    }
	});
	$("#slider").slider({
	    range: "min",
	    value: 1,
	    step: 1,
	    min: 0,
	    max: 100,
	    slide: function (event, ui) {
	        $("#progressMade").val(ui.value);
	    }
	});
	});

function appendTable(returnData) {
	$('#reportTable').html(returnData);
	$("table").tablesorter();
	}
$(document).ready(function() {
	$('#qrcontent').hide();
	$('#mrcontent').hide();
	$(function() {
		$( ".calender" ).datepicker({
			dateFormat: "dd-mm-yy",
			changeMonth: true,
			changeYear: true
		});
	});
	});



$(document).ready(function() {
	var dialogOpts = {
			autoOpen: false
			};
	var dialogProgOpts = {
	show: true,
	hide: true,
	autoOpen: false
	};
	$('#submitSuccess').dialog(dialogOpts);
	$('#submitProgress').dialog(dialogProgOpts);
	$('#submitFailure').dialog(dialogOpts);
	
	function displayPanel(){
		var h = window.innerHeight;
		var w = window.innerWidth;
	$("#showpanel").css("top",h/3);
	$("#showpanel").css("left",w/3);
	$("#showpanel").css("width",w/3);
	$("#showpanel").css("height",h/4);
	$("#hideBg").css("width",w);
	$("#hideBg").css("height",h);
	$("#hideBg").css("margin",0);
	$("#hideBg").css("padding",0);
	$("#hideBg").css("opacity",0.5);
	$("#hideBg").css("background-color","white");
	$("#hideBg").css("z-index",100);
	$("#showpanel").css("z-index",200);
	$("#hideBg").show();
	$("#showpanel").show();
	}
	
	$("#showpanel").click(function(){
		$("#hideBg").hide();
		$("#showpanel").hide();
	}
	);
	
});




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






//=================Ajax Form Submission======================

function getDoc(frame) {
    var doc = null;

    // IE8 cascading access check
    try {
        if (frame.contentWindow) {
            doc = frame.contentWindow.document;
        }
    } catch(err) {
    }

    if (doc) { // successful getting content
        return doc;
    }

    try { // simply checking may throw in ie8 under ssl or mismatched protocol
        doc = frame.contentDocument ? frame.contentDocument : frame.document;
    } catch(err) {
        // last attempt
        doc = frame.document;
    }
    return doc;
}
$(document).ready(function() { 
	$(".monthlyFormToSubmit,.quarterlyFormToSubmit").submit(function(){

   var formObj = $(this);
   var formURL = formObj.attr("action");

   if(window.FormData !== undefined)  // for HTML5 browsers
   {

       var formData = new FormData(this);
       $.ajax({
           url: formURL,
           type: 'POST',
           async:false,
           data:  formData,
           contentType:false,
           cache: false,
           processData:false,
           success: function(result)
           {
        	   if(!$("#submitSuccess").dialog("isOpen")) {
        		   $("#submitSuccess").dialog("open");
        		   } else {
        		   $("#submitSuccess").dialog("close");
        		   }
           },
           error: function(error)
           {
        	   if(!$("#submitFailure").dialog("isOpen")) {
        		   $("#submitFailure").dialog("open");
        		   } else {
        		   $("#submitFailure").dialog("close");
        		   }
           }

      });
       return false;
  }
  else  //for olden browsers
   {
       //generate a random id
       var  iframeId = 'unique' + (new Date().getTime());

       //create an empty iframe
       var iframe = $('<iframe src="javascript:false;" name="'+iframeId+'" />');

       //hide it
       iframe.hide();

       //set form target to iframe
       formObj.attr('target',iframeId);

       //Add iframe to body
       iframe.appendTo('body');
       iframe.load(function()
       {
           var doc = getDoc(iframe[0]);
           var docRoot = doc.body ? doc.body : doc.documentElement;
           var data = docRoot.innerHTML;
           //data is returned from server.

       });

   }

});
});
//$(".monthlyFormToSubmit").submit();

//=======================================

