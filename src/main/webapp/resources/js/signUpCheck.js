function validateSignUpForm() {
	var username = document.forms["signUpForm"]["username"].value;
	var email = document.forms["signUpForm"]["email"].value;
	var password1 = document.forms["signUpForm"]["password1"].value;
	var password2 = document.forms["signUpForm"]["password2"].value;
	var imageCheck = document.forms["signUpForm"]["imageCheck"].value;
	var regexpMailCheck = /\S+@\S+\.\S+/;
  	if( username == "" || username == null){
   		alert("uresen hagytad a nevet");
   		return false;
    }
    else if (!regexpMailCheck.test(email)){
    	alert("a mail cim nem megfelelo");
    	return false;
    }
    else if(password1 != password2){
    	alert("a ket jelszo nem egyezik");
    	return false;
    }
    else if(password1 == null || password1==""){
    	alert("uresen hagytad a jelszot");
    	return false;
    }
    else if(password2 == null || password2==""){
    	alert("uresen hagytad a jelszo ujrat");
    	return false;
    }
    else if(imageCheck != imageValue){
    	alert("rosszul adtad meg a kepen szereplo szamot")
    	return false;
    }
    
}