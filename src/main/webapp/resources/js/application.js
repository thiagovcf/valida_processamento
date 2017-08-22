function getNomeApp(){
	return window.location.pathname.split("/")[1];
}

function preecherAlterarFile(tipoRegra, valores){
	$("input[name=radioPeriodo][value='" + tipoRegra + "']").prop("checked",true);
	
	if (tipoRegra == "S") {
		$('#regrasSemana').val(valores);
		preencherTabelaCamposSemana();
		desabilitaTabelaDatas(); 
		habilitaTabelaSemana();
	} else if (tipoRegra == "M"){
		$('#regrasMes').val(valores);
		preencherTabelaCamposMes();
		desabilitaTabelaSemana(); 
		habilitaTabelaDatas();
	} else{
		desabilitaTabelaDatas(); 
		desabilitaTabelaSemana();
	}	
}

function alteraStatusBotaoSem(botao){
	if($("#"+botao.id).hasClass('btn btn-success ativo')){
		$("#"+botao.id).attr('class', 'btn btn-default');
		preencheCampoHiddenSemana();
		return;
	}
	if($("#"+botao.id).hasClass('btn btn-default')){
		$("#"+botao.id).attr('class', 'btn btn-success ativo');
		preencheCampoHiddenSemana();
		return;
	}
}

function preencheCampoHiddenSemana(){
	$('#regrasSemana').val("");
	$('div.botoesSemana').find( "button.ativo" ).each(function (index, value) { 
	  $('#regrasSemana').val($('#regrasSemana').val() + ($(this).text()) + ";"); 
	});
}


function preencherTabelaCamposSemana(){
	$.each($('#regrasSemana').val().split(";"), function(key, value) {
	   $('#bt'+value).attr('class', 'btn btn-success ativo');
	});
}

function alteraStatusBotaoMes(botao){
	if($("#"+botao.id).hasClass('btn btn-success ativo')){
		$("#"+botao.id).attr('class', 'btn btn-default');
		preencheCampoHiddenMes();
		return;
	}
	if($("#"+botao.id).hasClass('btn btn-default')){
		$("#"+botao.id).attr('class', 'btn btn-success ativo');
		preencheCampoHiddenMes();
		return;
	}
	
}

function preencheCampoHiddenMes(){
	$('#regrasMes').val("");
	$('div.botoesCalendario').find( "button.ativo" ).each(function (index, value) { 
	  $('#regrasMes').val($('#regrasMes').val() + ($(this).text()) + ";"); 
	});
}

function preencherTabelaCamposMes(){
	$.each($('#regrasMes').val().split(";"), function(key, value) {
	   $('#bt'+value).attr('class', 'btn btn-success ativo');
	});
}

function habilitaTabelaDatas(){
	$('#divTabelaDatas').show();
}

function desabilitaTabelaDatas(){
	$('#divTabelaDatas').hide();
}

function habilitaTabelaSemana(){
	$('#divTabelaSemana').show();
}

function desabilitaTabelaSemana(){
	$('#divTabelaSemana').hide();
}

function validacaoPersonalizada(form, campo, mensagem){
	$('#'+form).data('bootstrapValidator').updateStatus(campo, 'INVALID', 'callback')
	$('#'+form).data('bootstrapValidator').updateMessage(campo, 'callback', mensagem);	
}

function initBootstrapDualListbox(){
	$('#listaGruposUsuario').bootstrapDualListbox({
	    nonSelectedListLabel: 'Grupos Não Selecionado',
	    selectedListLabel: 'Grupos Selecionados',
	    preserveSelectionOnMove: 'all',
	    moveOnSelect: false,
	    infoText: 'Exibindo {0} grupo(s)',  
	    infoTextEmpty: 'Lista vazia',  
	    showFilterInputs: true,
	    filterPlaceHolder: 'Pesquisa',
		filterTextClear: 'Visualizar todos',
	    moveSelectedLabel: 'Mover selecionado',
	    moveAllLabel: 'Mover todos',
	    removeSelectedLabel: 'Remover selecionados',
	    removeAllLabel: 'Remover todos',
	});	
}

function preecheCampoHidden(campo, valor){
	$('#'+campo).val(valor);
}

function preecheCampoHiddenUsuario(){
	$('#hiddenGruposUsuario').val($('#listaGruposUsuario').val());
}

jQuery(document).ready(function(){
	
	atribuicoesUsuario();
	
	setInterval(function() {
		if(document.getElementById('alertMessage')){
			$('#alertMessage').fadeOut(2000);
			rcRemoverFlashMessage();
		}
	}, 3000);
	
	$('.progress-bar').progressbar({
		display_text : 'fill'
	});
	
	pageSetUp();
	 
});

function configuraTelaHistorico(){
	var updateOutput = function(e) {
		var list = e.length ? e : $(e.target), output = list.data('output');
		if (window.JSON) {
			output.val(window.JSON.stringify(list.nestable('serialize')));
			//, null, 2));
		} else {
			output.val('JSON browser support required for this demo.');
		}
	};

	$('#nestable').nestable({
		group : 1
	}).on('change', updateOutput);

	updateOutput($('#nestable').data('output', $('#nestable-output')));

	$('#nestable-menu').on('click', function(e) {
		var target = $(e.target), action = target.data('action');
		if (action === 'expand-all') {
			$('.dd').nestable('expandAll');
		}
		if (action === 'collapse-all') {
			$('.dd').nestable('collapseAll');
		}
	});		
	 
	$('#nestable').nestable('collapseAll');		
}

function configuraTelaListar(){
	$(document).ready(function() {
		
		pageSetUp();
		
			var responsiveHelper_dt_basic = undefined;
			var responsiveHelper_datatable_fixed_column = undefined;
			var responsiveHelper_datatable_col_reorder = undefined;
			var responsiveHelper_datatable_tabletools = undefined;
			
			var breakpointDefinition = {
				tablet : 1024,
				phone : 480
			};

			$('#dt_basic').dataTable({
				"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>t<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
				"autoWidth" : true,
		        "oLanguage": {
				    "sSearch": '<span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>'
				},
				"preDrawCallback" : function() {
					// Initialize the responsive datatables helper once.
					if (!responsiveHelper_dt_basic) {
						responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#dt_basic'), breakpointDefinition);
					}
				},
				"rowCallback" : function(nRow) {
					responsiveHelper_dt_basic.createExpandIcon(nRow);
				},
				"drawCallback" : function(oSettings) {
					responsiveHelper_dt_basic.respond();
				}
			});
	})
}

if($(location).attr('pathname') == "/incluirUsuario"){
	initBootstrapDualListbox();
}

function atribuicoesUsuario(){
	if($(location).attr('pathname').match("/alterarUsuario")){
		preecheCampoHiddenUsuario();
	}
	if($(location).attr('pathname').match("/visualizarUsuario")){
		preecheCampoHiddenUsuario();
		$(".bootstrap-duallistbox-container").find("*").prop("disabled", true);
	}
	
	$( "#listaGruposUsuario" ).change(function() {
		preecheCampoHiddenUsuario();
		
		grupos = $('#hiddenGruposUsuario').val();
		
		if(grupos != ""){
			habilitaDesabilitaBotao(0,'#botaoSalvar')
			$('#formIncluirAlterarUsuario').data('bootstrapValidator').updateStatus('listaGruposUsuario_helper2', 'VALID', 'notEmpty')
		}else{
			habilitaDesabilitaBotao(1,'#botaoSalvar')
			$('#formIncluirAlterarUsuario').data('bootstrapValidator').updateStatus('listaGruposUsuario_helper2', 'INVALID', 'notEmpty')
		}
	});
}




