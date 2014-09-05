var myVar=setInterval(function(){callCheckStillHere()},10000);

function callCheckStillHere() {

	$.ajax({
		url : 'welcome/checkEnemyStillHere',
		success : function(data) {
			if (data == "leftTheGame") {
				document.location.href = "welcome";
			}
		}
	});
}