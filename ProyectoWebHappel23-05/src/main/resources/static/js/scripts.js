	
	

    $(document).ready( function(){        
    	
    	
    	
        $('.boton-subcomentar').click(function(){
        	$($(this).siblings(".form-subcom")[0]).toggleClass('form-subcomentar-visible')
        })
        
        $('.boton-modificar-com').click(function() {
    		
    		 debugger
    		$($(this).parent().children().children().children(".form-mod")[0]).toggleClass('div_visible');
    		 debugger
         	$($(this).parent().children().children().children('.comentario-modificable')[0]).toggleClass('div_oculto');
  		  
    		});
    	
    	
    	
    	var client = filestack.init('AOWJCzBZJTmiP4481B25Ez'); // aca va la clave
        $('#botonSubirImagen').click( function(){ // este es el manejador de evento a lo que quieran que haya que hacerle click
            client.pick({accept: 'image/*'}).then(function(result) {
                $('#inputUrlImagen').val(result.filesUploaded[0].url); // esto guarda en un input la url del archivo recien subido. pueden 
                                                                  //ponerlo hidden despues para que el usuario no lo vea
            });
        });
    });
