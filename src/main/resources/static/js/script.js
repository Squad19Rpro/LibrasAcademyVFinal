function toggleMenu() {
	let navigation = document.querySelector('.navigation');
	let toggle = document.querySelector('.toggle');
	navigation.classList.toggle('active');
	toggle.classList.toggle('active');
}

//o toggle vai adicionar e remover a classe a cada clique
function giro1() {
	document.getElementById("seta1").classList.toggle('giro');
}
function giro2() {
	document.getElementById("seta2").classList.toggle('giro');
}
function giro3() {
	document.getElementById("seta3").classList.toggle('giro');
}
function giro4() {
	document.getElementById("seta4").classList.toggle('giro');
}
function giro5() {
	document.getElementById("seta5").classList.toggle('giro');
}
function giro6() {
	document.getElementById("seta6").classList.toggle('giro');
}
function giro7() {
	document.getElementById("seta7").classList.toggle('giro');
}

/* Cadastro */
function mostrarSenha() {
	var senha = document.querySelector('#inputSenha')
	if (senha.type === "password") {
		senha.type = "text"
	} else if (senha.type === "text") {
		senha.type = "password";
	}
}

const formataCPF = event => {
	let input = event.target;
	input.value = cpf(input.value);
};

const cpf = value => {
	if (!value) return "";
	value = value.replace(/\D/g, '');
	value = value.replace(/(\d{3})(\d)/, "$1.$2");
	value = value.replace(/(\d{3})(\d)/, "$1.$2");
	value = value.replace(/(\d{3})(\d{2})$/, "$1-$2");
	return value;
};

const formataTEL = event => {
	let input = event.target;
	input.value = telefone(input.value);
};

const telefone = value => {
	if (!value) return "";
	value = value.replace(/\D/g, '');
	value = value.replace(/(\d{2})(\d)/, "($1) $2");
	value = value.replace(/(\d)(\d{4})$/, "$1-$2");
	return value;
};

document.addEventListener('DOMContentLoaded', function() {
	let cpfInput = document.getElementById('inputCpfEdit');
	cpfInput.readOnly = true;
});


function moeda(a, e, r, t) {
	let n = ""
		, h = j = 0
		, u = tamanho2 = 0
		, l = ajd2 = ""
		, o = window.Event ? t.which : t.keyCode;
	if (13 == o || 8 == o)
		return !0;
	if (n = String.fromCharCode(o),
		-1 == "0123456789".indexOf(n))
		return !1;
	for (u = a.value.length,
		h = 0; h < u && ("0" == a.value.charAt(h) || a.value.charAt(h) == r); h++)
		;
	for (l = ""; h < u; h++)
		-1 != "0123456789".indexOf(a.value.charAt(h)) && (l += a.value.charAt(h));
	if (l += n,
		0 == (u = l.length) && (a.value = ""),
		1 == u && (a.value = "0" + r + "0" + l),
		2 == u && (a.value = "0" + r + l),
		u > 2) {
		for (ajd2 = "",
			j = 0,
			h = u - 3; h >= 0; h--)
			3 == j && (ajd2 += e,
				j = 0),
				ajd2 += l.charAt(h),
				j++;
		for (a.value = "",
			tamanho2 = ajd2.length,
			h = tamanho2 - 1; h >= 0; h--)
			a.value += ajd2.charAt(h);
		a.value += r + l.substr(u - 2, u)
	}
	return !1
}

