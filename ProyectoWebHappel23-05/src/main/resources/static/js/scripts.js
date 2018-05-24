	
	

    $(document).ready( function(){        
    	$('.form-modificar-oculto').each(function(i){
    	    var newID=$(this).attr('id') + i;
    	    $(this).attr('id',newID);
    	});
    	$('.boton-modificar-com').each(function(i){
    	    var newID=$(this).attr('id') + i;
    	    $(this).attr('id',newID);
    	});
        $('#boton-modificar0').click(function(){
        	$('#form-modificar0').toggleClass('form-modificar-visible')
        })
        
        $('.form-subcomentar-oculto').each(function(i){
    	    var newID=$(this).attr('id') + i;
    	    $(this).attr('id',newID);
    	});
    	$('.boton-subcomentar').each(function(i){
    	    var newID=$(this).attr('id') + i;
    	    $(this).attr('id',newID);
    	});
    	
        $('#boton-subcomentar0').click(function(){
        	$('#form-subcomentar0').toggleClass('form-subcomentar-visible')
        })
    	
    	
    	
    	var client = filestack.init('AOWJCzBZJTmiP4481B25Ez'); // aca va la clave
        $('#botonSubirImagen').click( function(){ // este es el manejador de evento a lo que quieran que haya que hacerle click
            client.pick({accept: 'image/*'}).then(function(result) {
                $('#inputUrlImagen').val(result.filesUploaded[0].url); // esto guarda en un input la url del archivo recien subido. pueden 
                                                                  //ponerlo hidden despues para que el usuario no lo vea
            });
        });
    });
