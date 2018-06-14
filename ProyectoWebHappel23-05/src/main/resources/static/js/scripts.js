$(document).ready( function(){
	console.log($().jquery); 
	console.log($j().jquery); 
	
	$( '.comentario-body' ).on( 'click', '.btn-com-com', function() {
	
		var fecha = moment().format('MMMM Do YYYY, h:mm:ss a');
		$j(this).siblings(".text-com").data("fecha", fecha);
		var id = $j("#comentario-nuevo").data("id"); 
		var comentarionuevo = $j("#comentario-nuevo").val();
		$j.ajax({
			url:"/procesar-comentario-ajax",
			data: "id=" +id+ "&comentarionuevo=" +comentarionuevo+ "&fecha="+fecha
			
		}).done(function( data){
			debugger
			if (data[0]== 'false'){
				window.location.href='/login';
			}else{
				var comentarionuevo = data [0];
				var fecha = data[1];
				var imgurl = data[2];
				var nick = data[3];
				var idcom = data[4];
				var cmid = data[5];
				var idpro = data[6];
				var cid = "c"+idcom;
				var fecha2 = moment(fecha, "MMMM Do YYYY, h:mm:ss a").fromNow();
				$(".comentario-body").append("<div class='col-12 comentario' id="+idcom+">" +
								"<div class='row com-box bg-com'>" +
										"<div class='col-1'>" +
											"<img class='img-fluid' src="+imgurl+"></img>" +
										"</div>"+
										"<div class='col-11 com-text'>"+
										"<div class='row'>"+
												"<div class='col-11'>"+
													"<label class='text-capitalize' >"+nick+"</label>"+
													"<label class='fecha-com' >"+fecha2+"</label>"+
												"</div>"+
												"<div class='col-1 flex-end'>"+
													"<label class='boton-modificar-com mx-auto'><i class='fa fa-edit'></i></label>"+
													"<label class='boton-subcomentar mx-auto'><i class='fa fa-reply'></i></label>"+
												"</div>"+
										"</div>"+	
										"<h6 id="+cmid+" class='comentario-modificable'>"+comentarionuevo+"</h6>"+
										
										"<div class='form-subcomentar-oculto form-subcom'>"+
											"<div>"+
													"<textarea class='text-com coment-area subcomentario-nuevo' data-idcom="+idcom+" 'data-id="+idpro+" 'data-fecha=3' rows='4' cols='50'></textarea>"+
											"</div>"+
											"<div class='col-4'>"+
													"<label class='btn btn-primary btn-xs proc-comentario btn-subcom-com'>Comentar</label>"+
											"</div>"+
										"</div>"+
										
										"<div class='form-modificar-oculto form-mod'>"+
											"<textarea class='text-com coment-area' id="+cid+" text="+comentarionuevo+" rows='4' cols='50'></textarea>"+
										"</div>"+
										"<label class='btn btn-primary btn-xs div_oculto btn-mod-com' data-id="+idpro+" data-idcom="+idcom+">Modificar</label>"+
										"<label class='btn btn-primary btn-xs form-eliminar-com div_oculto btn-del-com' data-id="+idpro+" data-idcom="+idcom+">Borrar Comentario</label>"+
	
										"<div class='form-subcomentar-oculto form-subcom'>"+
											"<div>"+
												"<textarea class='text-com coment-area subcomentario-nuevo' data-idcom="+idcom+" data-id="+idpro+" data-fecha=3 rows='4' cols='50'></textarea>"+
											"</div>"+
											"<div class='col-4'>"+
												"<label class='btn btn-primary btn-xs proc-comentario btn-subcom-com' >Comentar</label>"+
											"</div>"+
										"</div>"+
										
									"</div>"+
								"</div>");
			}
			
		})
	})
	
	$( '.comentario-body' ).on( 'click', '.btn-subcom-com', function() {
		var fecha = moment().format('MMMM Do YYYY, h:mm:ss a');
		$j(this).siblings(".text-com").data("fecha", fecha);
		var id = $j(this).parent().parent().find(".subcomentario-nuevo").data("id"); 
		var idcom = $j(this).parent().parent().find(".subcomentario-nuevo").data("idcom"); 
		var comentarionuevo =  $j(this).parent().parent().find(".subcomentario-nuevo").val();
		$j.ajax({
			url:"/procesar-subcomentario-ajax",
			data: "id=" +id+ "&idcom=" +idcom+"&comentarionuevo=" +comentarionuevo+ "&fecha="+fecha 
			
		}).done(function( data){
			if (data[0]== 'false'){
				window.location.href='/login';
			}else{
				var comentarionuevo = data [0];
				var fecha = data[1];
				var imgurl = data[2];
				var nick = data[3];
				var idcom = data[4];
				var cmid = data[5];
				var idpro = data[6];
				var cid = "c"+idcom;
				var idcompadre = data[7];
				var fecha2 = moment(fecha, "MMMM Do YYYY, h:mm:ss a").fromNow();
		    	
				$j("#"+idcompadre+".comentario").children(".subcom-box").append("<div class='bg-subcom subcom-unibox' id="+idcom+">" +
						"<div class='row'>" +
							"<div class='col-1'>" +
								"<img class='img-fluid' src="+imgurl+"></img>" +
									"</div>"+
								"<div class='col-10'>"+
									"<label class='text-uppercase'>"+nick+"&nbsp;</label>"+
									"<label class='fecha-com'>"+fecha2+"</label>"+
									"<h6 id="+cmid+" class='comentario-modificable'>"+comentarionuevo+"</h6>"+
									"<div class='form-modificar-oculto form-mod'>"+
										"<textarea class='text-com coment-area' id="+cid+" rows='4' cols='50'></textarea>"+
										"<label class='btn btn-primary btn-xs btn-mod-com' data-id="+idpro+" data-idcom="+idcom+" >Modificar</label>"+
									"</div>"+
									"<label class='btn btn-primary btn-xs form-eliminar-com div_oculto btn-del-com' data-id="+idpro+">Borrar Comentario</label>"+
									"<button class='boton-modificar-com'>Modificar Comentario</button>"+
									"<button class='boton-subcomentar'>Responder <i class='fa fa-reply'></i></button>"+
									"<form class='col-12 form-subcomentar-oculto form-subcom' action='/procesar-subcomentario'>"+
										"<input name='id' type='hidden' value="+idpro+"></input>"+
										"<input name='idcom' type='hidden' value="+idcom+"></input>"+
										"<input class='fecha' name='fecha' type='hidden' value="+fecha+"></input>"+
										"<textarea class='coment-area' name='comentarionuevo' type='text' placeholder='Ingrese su comentario...'></textarea>"+
										"<input class='proc-comentario' type='submit'></input>"+
									"</form>"+
								"</div>"+
								"<div class='col-11'>" +
									"<label  class='fecha-com'>"+fecha2+"</label>" +
									"<h6 id="+idpro+" class='comentario-modificable'>"+comentarionuevo+"</h6>" +
									"<div class='form-modificar-oculto form-mod'>" +
									"<textarea class='text-com coment-area' text="+comentarionuevo+" id="+cid+" rows='4' cols='50'></textarea>" +
									"</div>" +
									"<label class='btn btn-primary btn-xs div_oculto btn-mod-com' data-id="+idpro+" data-idcom="+idcom+">Modificar</label>" +
									"<label class='btn btn-primary btn-xs form-eliminar-com div_oculto btn-del-com' data-id="+idpro+", data-idcom="+idcom+">Borra Comentario</label>" +
									"<button class='boton-modificar-com'>Modificar Comentario</button>" +
									"</div>" +
							"</div>");
			}
		})
	})
	$( '.comentario-body' ).on( 'click', '.btn-del-com', function() {
		var idcom = $j(this).data("idcom");
		var id = $j(this).data("id");
		$j.ajax({
			url:"/eliminar-comentario-ajax",
			data: "idcom=" +idcom + "&id=" +id
		}).done(function( data){
			var id = data;
			$j("div#"+ id).remove();

		})
	}) 
	$( '.comentario-body' ).on( 'click', '.btn-mod-com', function() {
		var idcom = $j(this).data("idcom");
		var id = $j(this).data("id");
		var comentarionuevo = $j("#c"+idcom+".text-com").val();
		$j.ajax({
			url:"/modificar-comentario-ajax",
			data: "idcom=" +idcom + "&id=" +id +"&comentarionuevo=" +comentarionuevo
		}).done(function( data){
			if(data[0]== 'false'){
				window.location.href='/login';
			}else{
				var comentarionuevo= data[0] 
				var id = data[1];
				$j("#cm"+id).text(comentarionuevo);
				$j($j("#"+id).find(".form-mod")).toggleClass('div_visible');
		  
				$j($j("#"+id).find('.form-eliminar-com')).toggleClass('div_oculto');
			}
			
		})
	})
    	      
	$( '.comentario-body' ).on( 'click', '.boton-subcomentar', function() {
    	$j($j(this).closest(".com-text").find(".form-subcom")[0]).toggleClass('form-subcomentar-visible')
    	if ($j(this).closest(".com-text").find(".form-mod").hasClass('div_visible')){
    		$j($j(this).closest(".com-text").find(".form-mod")[0]).toggleClass('div_visible'); 
    		$j($j(this).closest(".com-text").find('.form-eliminar-com')[0]).toggleClass('div_oculto');
    		$j($j(this).closest(".com-text").find('.btn-mod-com')[0]).toggleClass('div_oculto');
    	}
    })
    
   $( '.comentario-body' ).on( 'click', '.boton-modificar-com', function() {
		$j($j(this).closest(".com-text").find(".form-mod")[0]).toggleClass('div_visible'); 
		$j($j(this).closest(".com-text").find('.form-eliminar-com')[0]).toggleClass('div_oculto');
		$j($j(this).closest(".com-text").find('.btn-mod-com')[0]).toggleClass('div_oculto');
    	if ($j(this).closest(".com-text").find(".form-subcom").hasClass('form-subcomentar-visible')){
			$j($j(this).closest(".com-text").find(".form-subcom")[0]).toggleClass('form-subcomentar-visible')
		}
	});
	
	$j('.card-producto').click(function(){
		debugger
		var id=$(this).closest('.card-group').attr('id');
		window.location.href='/detalle/'+id;
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
            $j(".b-subir").toggleClass('div_oculto');
            $j(".b-enviar").toggleClass('div_visible');
    	})
	})
	
	moment.locale('es');
	
	
	$j( ".fecha-com" ).each(function( index ) {
		var b = moment($j(this).text(), "MMMM Do YYYY, h:mm:ss a").fromNow();
    	$j(this).text(b);
	});
	
	
	$('.carousel').carousel({
		  interval: 3000 
	});
	
	
	
	var client = filestack.init('AOWJCzBZJTmiP4481B25Ez'); 
    $j('#botonSubirImagen').click( function(){ 
        client.pick({accept: 'image/*'}).then(function(result) {
            $j('#inputUrlImagen').val(result.filesUploaded[0].url); 
        });
    });
});
