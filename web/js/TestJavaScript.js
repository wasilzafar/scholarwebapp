    $(document).ready(function() {
	//Counter
	counter = 0;

	//Make element draggable
	$(".drag").draggable({
		containment : 'body',
		helper : generateDomElement,
		stop : function(ev, ui) {
			objName = "#clonediv" + counter;
			$(objName).removeClass("drag");
			$(objName).draggable({
                	containment: 'body',
                    stop:function(ev, ui) {
                    	var pos=$(ui.helper).offset();
                    	console.log($(this).attr("id"));
						console.log(pos.left)
                        console.log(pos.top)
                    }
                });
			$(objName).resizable(); 
		}
	});

	//Make element droppable
	$("#frame").droppable({
	accept: ".drag",
		drop : function(ev, ui) {
			//if (ui.helper.attr('id').search(/drag[0-9]/) != -1){
			counter++;
			var element = $(ui.helper).clone();
			element.addClass("dragClass");
			$(this).append(element);
			$(".dragClass").attr("id", "clonediv" + counter);
			$("#clonediv" + counter).removeClass("dragClass");
		},
    out: function (event, ui) {
        $(ui.draggable).fadeOut(500, function () {
            $(this).remove();
        });
    }

	//}

	});
});

function generateDomElement() {
	var di = document.createElement('div');
	$(di).append('<input type="text" class="tool"></input>');
	$(di).css('width', '155px');
	$(di).css('height', '25px');
	$(di).css('background-color', 'red');
	$(di).css('cursor', 'move');
	return di;
}