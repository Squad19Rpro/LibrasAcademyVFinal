/* function loading(){
	$('#preloader').css('display','none');
} */
/* Preloader */
function loading() {
	setTimeout(function() {
		$('#preloader').css('display', 'none');
	}, 500)
}; //1000 milissegundos = 1 segundo

/* Botão para voltar ao topo */
$(document).ready(function() {
	// Add smooth scrolling to all links
	$("a").on('click', function(event) {
		// Make sure this.hash has a value before overriding default behavior
		if (this.hash !== "") {
			// Prevent default anchor click behavior
			event.preventDefault();
			// Store hash
			var hash = this.hash;
			// Using jQuery's animate() method to add smooth page scroll
			// The optional number (800) specifies the number of milliseconds it takes to scroll to the specified area
			$('html, body').animate({
				scrollTop: $(hash).offset().top
			}, 800, function() {
				// Add hash (#) to URL when done scrolling (default click behavior)
				window.location.hash = hash;
			});
		} // End if
	});
	//Nav BG and TopBTN function
	(function scrollFn() {
		if ($(document).scrollTop() >= 1) {
			$("nav").css({ "background-color": "#37517ed4" });
			$(".up-scroll").addClass("display");
			setTimeout(function() {
				$(".up-scroll").addClass("active");
			}, 100);
		};
		$(document).scroll(function() {
			if ($(this).scrollTop() >= 1) {
				$("nav").css({ "background-color": "#37517ed4" });
				$(".up-scroll").addClass("display");
				setTimeout(function() {
					$(".up-scroll").addClass("active");
				}, 10);
			} else {
				$("nav").css({ "background-color": " #37517e" });
				$(".up-scroll").removeClass("active");
			};
		});
	})();
});
/* pesquisa cursos */
document.addEventListener('DOMContentLoaded', function() {
	const cards = document.querySelectorAll('.card-curso');
	const input = document.querySelector('#input-busca');

	function removeAccents(str) {
		return str.normalize("NFD").replace(/[\u0300-\u036f]/g, "");
	}

	input.addEventListener('input', function() {
		const searchTerm = removeAccents(input.value.toLowerCase());
		cards.forEach(card => {
			const cardText = removeAccents(card.textContent.toLowerCase());
			if (cardText.includes(searchTerm)) {
				card.style.display = 'block';
			} else {
				card.style.display = 'none';
			}
		});
	});
});

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

document.addEventListener("DOMContentLoaded", function () {
	document.getElementById("formCadSite").addEventListener("submit", function (event) {
		// Prevenir o envio imediato do formulário
		event.preventDefault();
		Swal.fire({
						title: 'Parabéns!',
						text: 'Você agora faz parte do time do Libras Academy!',
						icon: 'success',
						showConfirmButton: false,
						timer: 1500
					})
		// Aguarde 2 segundos antes de enviar o formulário
		setTimeout(function () {					
			// Envie o formulário após o atraso
			document.getElementById("formCadSite").submit();
		}, 1700);
	});
});