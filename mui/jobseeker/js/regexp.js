function checkUsername(username){
	var reg=/^[a-zA-Z0-9_]{3,15}$/;
	return reg.test(username);
}

function checkPassword(password){
	var reg=/^[a-zA-Z0-9_]{6,20}$/;
	return reg.test(password);
}

function checkPhone(phone){
	var reg=/^[0-9]{11}$/;
	return reg.test(phone);
}
