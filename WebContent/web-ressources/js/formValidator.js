$.validate({
	lang : 'fr',
	form : '#addNewComputer, #editComputerForm',
	modules : 'toggleDisabled',
	onSuccess : function($form) {
		var introduced = $("#introduced").val();
		var discontinued = $("#discontinued").val();
		if (introduced > discontinued) {
			return false;
		}
		return true;
	}
});