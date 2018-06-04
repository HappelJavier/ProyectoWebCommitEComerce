	
	

    $(document).ready( function(){      
    	$('.barras').each(function() {
    		
    	     var dataWidth = $(this).val();
    	     $(this).css("width", dataWidth + "%");
    	    if (dataWidth <=20) { $(this).css("background-color", "darkred"); }
    	    	else if (dataWidth >20 && dataWidth <=40){ $(this).css("background-color", "red"); }
    	    	else if (dataWidth >40 && dataWidth <=60){ $(this).css("background-color", "orange"); }
    			else if (dataWidth >60 && dataWidth<=75) { $(this).css("background-color", "yellow"); }
    			else if (dataWidth >75 && dataWidth<=100) { $(this).css("background-color", "green"); }	
    	  });
    	
    	$("#upload_widget_opener").click(function () {
	    		cloudinary.openUploadWidget({
	    	
	    		upload_preset : 'zsxcsaup', 
	    		cloud_name: 'dcamfoks1',
	    		theme: 'white',
	    		multiple: true,
	    		max_image_width: 750,
	    		max_image_height: 500,
	    		max_files: 5,
	    		folder: 'mi_carpeta',
	    		sources: [ 'local', 'url', 'facebook'], 
	    	}, function(error, result) {
	    		console.log(error, result)
	    		var url = result[0].secure_url;
	            
	            console.log (url);
	            
	            $("form").find(".foto").val(url);
	    	})
    	})
    	
    	moment.locale('es');
    	$('.proc-comentario').click(function(){
    		var a = moment().format('MMMM Do YYYY, h:mm:ss a');
    		$($(this).siblings(".fecha")[0]).attr('value', a)
    	})
    	
    	$( ".fecha-com" ).each(function( index ) {
    		var b = moment($(this).text(), "MMMM Do YYYY, h:mm:ss a").fromNow();
        	$(this).text(b);
    	});
    	
    	
    	
        $('.boton-subcomentar').click(function(){
        	$($(this).siblings(".form-subcom")[0]).toggleClass('form-subcomentar-visible')
        })
        
        $('.boton-modificar-com').click(function() {
    		
    		
    		$($(this).parent().children(".form-mod")[0]).toggleClass('div_visible');
    		 
         	$($(this).parent().children('.comentario-modificable')[0]).toggleClass('div_oculto');
    		
    		$($(this).parent().children('.form-eliminar-com')[0]).toggleClass('div_oculto');
    	  		  
    	});
    	
    	
    	
    	var client = filestack.init('AOWJCzBZJTmiP4481B25Ez'); // aca va la clave
        $('#botonSubirImagen').click( function(){ // este es el manejador de evento a lo que quieran que haya que hacerle click
            client.pick({accept: 'image/*'}).then(function(result) {
                $('#inputUrlImagen').val(result.filesUploaded[0].url); // esto guarda en un input la url del archivo recien subido. pueden 
                                                                  //ponerlo hidden despues para que el usuario no lo vea
            });
        });
    });
