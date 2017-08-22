function messagesI18n(){
        return {
    		email : {
				required : 'Este campo &eacute; requerido.',
				email : 'Por favor, forne&ccedil;a um endere&ccedil;o de email v&aacute;lido.'
			},
			
			password : {
				required : 'Este campo &eacute; requerido.',
				minlength: $.validator.format("Por favor, forne&ccedil;a ao menos {0} caracteres.")
			},
			
			passwordConfirm : {
				required:'Este campo &eacute; requerido.',
				minlength: $.validator.format("Por favor, forne&ccedil;a ao menos {0} caracteres."),
				equalTo: 'Por favor, forne&ccedil;a o mesmo valor novamente.'
			},
			
			nome : {
				required:'Este campo &eacute; requerido.'
			},
			
			sobreNome : {
				required:'Este campo &eacute; requerido.'
			},
			
			descricao : {
				required:'Este campo &eacute; requerido.'
			},
			
			contaBancaria : {
				required:'Este campo &eacute; requerido.'
			}, 
			
			categoriaMovimentacao : {
				required:'Este campo &eacute; requerido.'
			},
			
			valorMovimentacaoDespesa : {
				required:'Este campo &eacute; requerido.'
			},
			
			nomeCategoria : {
				required:'Este campo &eacute; requerido.'
			},
			
			nomeCategoriaEdit : {
				required:'Este campo &eacute; requerido.'
			}
        };
}

jQuery(document).ready(function(){
	
	$('#valorMovimentacaoDespesa').val("0,00");
	$('#valorMovimentacaoReceita').val("0,00");
	
	$("#campoDataDespesa").mask("99/99/9999");
	$("#campoDataReceita").mask("99/99/9999");
	
	$("#campoDataReceita").datepicker({
		dateFormat: 'dd/mm/yy',
		dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
		dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
		dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
		monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
		monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
		nextText: '>',
		prevText: '<',
		pattern: "dd/MM/yy"  
	});
	$("#campoDataDespesa").datepicker({
		dateFormat: 'dd/mm/yy',
		dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
		dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
		dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
		monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
		monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
		nextText: '>',
		prevText: '<',
		pattern: "dd/MM/yy"  
	});
});