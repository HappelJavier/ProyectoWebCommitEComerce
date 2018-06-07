$(document).ready( function(){
	console.log($().jquery); 
	console.log($j().jquery); 
	$('.boton').click(function(){
		
		var id = $(this).data("id");
		
		$.ajax({
			url:"/eliminar-ajax" + id
		}).done(function( respuesta){
			var id = respuesta;
			$("label[data-id=" + id + "]").closest('div').remove();
			$(loquesea).append(contenido);
		})
	})       
    	       	       
	$(function () {
	    var goToCartIcon = function($addTocartBtn){
	      var $cartIcon = $(".my-cart-icon");
	      var $image = $('<img width="30px" height="30px" src="' + $addTocartBtn.data("image") + '"/>').css({"position": "fixed", "z-index": "999"});
	      $addTocartBtn.prepend($image);
	      var position = $cartIcon.position();
	      $image.animate({
	        top: position.top,
	        left: position.left
	      }, 500 , "linear", function() {
	        $image.remove();
	      });
	    }
	
	    $('.my-cart-btn').myCart({
	      currencySymbol: '$',
	      classCartIcon: 'my-cart-icon',
	      classCartBadge: 'my-cart-badge',
	      classProductQuantity: 'my-product-quantity',
	      classProductRemove: 'my-product-remove',
	      classCheckoutCart: 'my-cart-checkout',
	      affixCartIcon: true,
	      showCheckoutModal: true,
	      numberOfDecimals: 2,
	      cartItems: [
	        
	      ],
	      clickOnAddToCart: function($addTocart){
	        goToCartIcon($addTocart);
	      },
	      afterAddOnCart: function(products, totalPrice, totalQuantity) {
	        console.log("afterAddOnCart", products, totalPrice, totalQuantity);
	      },
	      clickOnCartIcon: function($cartIcon, products, totalPrice, totalQuantity) {
	        console.log("cart icon clicked", $cartIcon, products, totalPrice, totalQuantity);
	      },
	      checkoutCart: function(products, totalPrice, totalQuantity) {
	        var checkoutString = "Total Price: " + totalPrice + "\nTotal Quantity: " + totalQuantity;
	        checkoutString += "\n\n id \t name \t summary \t price \t quantity \t image path";
	        $.each(products, function(){
	          checkoutString += ("\n " + this.id + " \t " + this.name + " \t " + this.summary + " \t " + this.price + " \t " + this.quantity + " \t " + this.image);
	        });
	        alert(checkoutString)
	        console.log("checking out", products, totalPrice, totalQuantity);
	      },
	    });
	  });
	
    	    	
	$j('.barras').each(function() {
		
		var dataWidth = $j(this).val();
	    $j(this).css("width", dataWidth + "%");
	    if (dataWidth <=20) { $j(this).css("background-color", "#cc0000"); }
	    	else if (dataWidth >20 && dataWidth <=40){ $j(this).css("background-color", "#ff1a1a"); }
	    	else if (dataWidth >40 && dataWidth <=60){ $j(this).css("background-color", "orange"); }
			else if (dataWidth >60 && dataWidth<=75) { $j(this).css("background-color", "yellow"); }
			else if (dataWidth >75 && dataWidth<=100) { $j(this).css("background-color", "green"); }	
	  });
	
	$j("#upload_widget_opener").click(function () {
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
            
            $j("form").find(".foto").val(url);
    	})
	})
	
	moment.locale('es');
	$j('.proc-comentario').click(function(){
		var a = moment().format('MMMM Do YYYY, h:mm:ss a');
		$j($j(this).siblings(".fecha")[0]).attr('value', a)
	})
	
	$j( ".fecha-com" ).each(function( index ) {
		var b = moment($j(this).text(), "MMMM Do YYYY, h:mm:ss a").fromNow();
    	$j(this).text(b);
	});
	
	
	
    $j('.boton-subcomentar').click(function(){
    	$j($j(this).siblings(".form-subcom")[0]).toggleClass('form-subcomentar-visible')
    })
    
    $j('.boton-modificar-com').click(function() {
		
		
		$j($j(this).parent().children(".form-mod")[0]).toggleClass('div_visible');
		 
     	$j($j(this).parent().children('.comentario-modificable')[0]).toggleClass('div_oculto');
		
		$j($j(this).parent().children('.form-eliminar-com')[0]).toggleClass('div_oculto');
	  		  
	});
	
	
	
	var client = filestack.init('AOWJCzBZJTmiP4481B25Ez'); // aca va la clave
    $j('#botonSubirImagen').click( function(){ // este es el manejador de evento a lo que quieran que haya que hacerle click
        client.pick({accept: 'image/*'}).then(function(result) {
            $j('#inputUrlImagen').val(result.filesUploaded[0].url); // esto guarda en un input la url del archivo recien subido. pueden 
                                                              //ponerlo hidden despues para que el usuario no lo vea
        });
    });
});
