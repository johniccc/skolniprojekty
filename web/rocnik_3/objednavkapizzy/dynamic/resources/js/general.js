document.addEventListener('DOMContentLoaded', () => {
	initPopup();
});

function toggleAccordion(accordion) {
	const submenu = accordion.querySelector('.submenu');
	submenu.style.maxHeight = submenu.style.maxHeight
		? null
		: `${submenu.scrollHeight}px`;
}

function initPopup() {
	const popup = document.querySelector('.popup');
	if (popup !== null) {
		const popupBtn = popup.querySelector('button.confirm[type=button]');
		popupBtn.addEventListener('click', () => {
			popup.classList.remove('active');
		});
		popup.addEventListener('transitionend', () => {
			popup.remove();
		});
	}
}
