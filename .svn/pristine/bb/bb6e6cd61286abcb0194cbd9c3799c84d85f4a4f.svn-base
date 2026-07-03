jQuery(document).ready(function () {
$('.modal').on('show.bs.modal', centerModal);
$(window).on("resize", function () {
    $('.modal:visible').each(centerModal);
});
});


function centerModal() {
    $(this).css('display', 'block');
    var $dialog = $(this).find(".modal-dialog");
	
    var offset = ($(window).height() - $dialog.height()) / 2;
	if(offset <0) offset = 20;

    // Center modal vertically in window
    $dialog.css("margin-top", offset);
	
        if($(this).hasClass('in') == false){
            $(this).show(); /* Need this to get modal dimensions */
        };
        var contentHeight = $(window).height() - 60;
		var contentWidth = '100%' ;

        $(this).find('.modal-content').css({
            'max-height': function () {
                return contentHeight;
            },
			 'max-width': function () {
                return contentWidth;
            }
        });
				
        $(this).find('.modal-body').css({
            'max-height': function () {
                return (contentHeight);
            }
        });
		
        if($(this).hasClass('in') == false){
            $(this).hide(); /* Hide modal */
        };
    };
    
    
    function show_nicpopup(pval)
    {
    $('#popcontentdiv').html(pval);
    $('#nic-popup').modal('show');
    }

    function close_nicpopup()
    {
    	$('#nic-popup').modal('hide');
    }
   