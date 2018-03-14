jQuery(document).ready(function(){
//	runAllForms();
	$("#formLogin").validate({
		rules : {
			email : {
				required : true,
				email : true
			},
			password : {
				required : true
			}
		},

		messages : messagesI18n(),
		
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		},
		
		showErrors: function(errorMap, errorList) {
			habilitaDesabilitaBotao(this.numberOfInvalids(), '#botaoEntrar');
            this.defaultShowErrors();
        },
        
        invalidHandler: function() {
        	$('#botaoEntrar').attr('disabled', 'disabled');
        },
        
        submitHandler: function() {
        	rcEntrar();
        }
        
	});
	
	$('#togglingForm').bootstrapValidator({
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			firstName : {
				validators : {
					notEmpty : {
						message : 'The first name is required'
					}
				}
			}
		}
	}).find('button[data-toggle]').on('click', function() {
		var $target = $($(this).attr('data-toggle'));
		// Show or hide the additional fields
		// They will or will not be validated based on their visibilities
		$target.toggle();
		if (!$target.is(':visible')) {
			// Enable the submit buttons in case additional fields are not valid
			$('#togglingForm').data('bootstrapValidator').disableSubmitButtons(false);
		}
	});
	
	
	$("#formIncluirAlterarGrupo").bootstrapValidator({
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		
		fields : {
			nome : {
				validators : {
					callback : {
						
					},
					notEmpty : {
						message : 'Campo Obrigatório'
					}
				}
			},
			descricao : {
				validators : {
					notEmpty : {
						message : 'Campo Obrigatório'
					}
				}
			}
		}  
		
	}).on('success.form.bv', function(e) {
        e.preventDefault();
        rcSalvar();
    });
	
	$("#formIncluirAlterarUsuario").bootstrapValidator({
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		
		fields : {
			nome : {
				validators : {
					notEmpty : {
						message : 'Campo Obrigatório'
					}
				}
			},
			email : {
				validators : {
					notEmpty : {
						message : 'Campo Obrigatório'
					}
				}
			},
			login : {
				validators : {
					callback : {
						
					},
					notEmpty : {
						message : 'Campo Obrigatório'
					}
				}
			},
			'listaGruposUsuario_helper2' : {
				validators: {
					notEmpty : {
						message : 'Campo Obrigatório, selecione pelo menos 1 grupo'
					}
//			,
//                    callback: {
//                        message: '',
//                        callback: function(value, validator, $field) {
//                            var options = validator.getFieldElements('listaGruposUsuario_helper2').val();
//                            return (options != null && options.length > 0);
//                        }
//                    }
                }
            }
		}  
		
	}).on('success.form.bv', function(e) {
		e.preventDefault();
		preecheCampoHiddenUsuario();
		rcSalvar();
	});
	
	
	$("#formEsqueceuSenha").validate({
		rules : {
			email : {
				required : true,
				email : true
			}
		},

		messages : messagesI18n(),
		
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		},
		
		showErrors: function(errorMap, errorList) {
			
			habilitaDesabilitaBotao(this.numberOfInvalids(), '#botaoReset');
            this.defaultShowErrors();
        },
        
        invalidHandler: function() {
        	$('#botaoReset').attr('disabled', 'disabled');
        },
        
        submitHandler: function() {
        	rcResetSenha();
        }
	});
	
	
	
	$("#formAlterarSenha").validate({
		rules : {
			password : {
				required : true,
				minlength : 5
			},
			passwordConfirm : {
				required : true,
				minlength : 5,
				equalTo : '#password'
			}
		},

		messages : messagesI18n(),
		
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		},
		
		showErrors: function(errorMap, errorList) {
			habilitaDesabilitaBotao(this.numberOfInvalids(), '#botaoAlterarSenha');
            this.defaultShowErrors();
        },
        
        invalidHandler: function() {
        	$('#botaoAlterarSenha').attr('disabled', 'disabled');
        },
        
        submitHandler: function() {
        	rcAlterarSenha();
        }
	});
	
	$("#formIncluirAlterarEmissor").bootstrapValidator({
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		
		fields : {
			emissor : {
				validators : {
					notEmpty : {
						message : 'Campo Obrigatório'
					}
				}
			},
		
			unidadeNegocio : {
				validators : {
					notEmpty : {
						message : 'Campo Obrigatório'
					}
				}
			}
		}
		
	}).on('success.form.bv', function(e) {
		e.preventDefault();
		rcSalvar();
	});
	$("#formIncluirAlterarCliente").bootstrapValidator({
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		
		fields : {
			nome : {
				validators : {
					notEmpty : {
						message : 'Campo Obrigatório'
					}
				}
			},
		
			cpf : {
				validators : {
					notEmpty : {
						message : 'Campo Obrigatório'
					}
				}
			},
			
			statusCliente : {
				validators : {
					notEmpty : {
						message : 'Campo Obrigatório'
					}
				}
			}
		}
		
	}).on('success.form.bv', function(e) {
		e.preventDefault();
		rcSalvar();
	});
	
	$("#formIncluirAlterarPasta").bootstrapValidator({
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		
		fields : {
			
			emissor : {
				validators : {
					notEmpty : {
						message : 'Campo Obrigatório'
					}
				}
			},
		
			nome : {
				validators : {
					notEmpty : {
						message : 'Campo Obrigatório'
					}
				}
			},
			
			email: {
				validators : {
					notEmpty : {
						message : 'Campo Obrigatório'
					}
				}
			},
			
			loginFtp : {
				validators : {
					notEmpty : {
						message : 'Campo Obrigatório'
					}
				}
			},
			
			senhaFtp : {
				validators : {
					notEmpty : {
						message : 'Campo Obrigatório'
					}
				}
			},
			
			ipFtp : {
				validators : {
					notEmpty : {
						message : 'Campo Obrigatório'
					}
				}
			}
		}
		
	}).on('success.form.bv', function(e) {
		e.preventDefault();
		rcSalvar();
	});
	
	$("#formIncluirAlterarArquivo").bootstrapValidator({
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		}
	}).on('success.form.bv', function(e) {
		e.preventDefault();
		rcSalvar();
	}).on('error.form.bv', function(e) {
        alert(e);
        console.log(e);
    });
});

function habilitaDesabilitaBotao(erros, botao){
	if(erros == 0){
    	$(botao).removeAttr('disabled');
    }else{
    	$(botao).attr('disabled', 'disabled');
    }
}