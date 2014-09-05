var myVar=setInterval(function(){callCheck()},10000);

function callCheck() {

	$.ajax({
		url : 'welcome/checkEnemy',
		success : function(data) {
			if (data == "enemyFound") {
				document.location.href = "welcome/gameboard";
			}
		}
	});
}

