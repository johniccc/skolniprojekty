document.addEventListener('DOMContentLoaded', () => {
	initToggleAccordion();
	initToggleCheckbox();
});

function initToggleAccordion() {
	const items = document.querySelectorAll('.item.accordion');

	items.forEach((item) => {
		const itemHeader = item.querySelector('.item.header');
		itemHeader.addEventListener('click', () => {
			item.classList.toggle('active');
			itemHeader.classList.toggle('selected');
			toggleAccordion(item);

			const selects = item.querySelectorAll('.item.select');
			selects.forEach((select) => {
				if (select.classList.contains('selected')) {
					itemHeader.classList.add('selected');
				}
			});
		});
	});
}

function initToggleCheckbox() {
	const items = Array.from(document.querySelectorAll('.item.select'));

	items.forEach((item) => {
		item.addEventListener('click', () => {
			item.classList.toggle('selected');
			const checkbox = item.querySelector('.checkbox');
			checkbox.checked = item.classList.contains('selected');

			const baseSelects = Array.from(
				document.querySelectorAll('.item.accordion#base .item.select')
			);
			if (baseSelects.includes(item)) {
				checkBaseCount(baseSelects, item);
			}

			addIngredientsToView();
		});
	});
}

function checkBaseCount(baseSelects, item) {
	if (
		item.classList.contains('selected') &&
		item.querySelector('input[type="checkbox"]').checked
	) {
		baseSelects.forEach((select) => {
			select.classList.remove('selected');
			const checkbox = select.querySelector('input[type="checkbox"]');
			checkbox.checked = false;
		});
		item.classList.add('selected');
		const checkbox = item.querySelector('input[type="checkbox"]');
		checkbox.checked = true;
	}
}

function addIngredientsToView() {
	/* Remove disselected */
	const items = document.querySelectorAll('.item.select');
	items.forEach((item) => {
		const ingredient = item.querySelector('input[type="checkbox"]').value;
		const img = document.querySelector(
			'#pizza-view .pizza img[pizza-ing="' + ingredient + '"]'
		);

		if (!item.classList.contains('selected'))
			if (img != null && img.classList.contains('selected'))
				img.classList.remove('selected');
	});

	/* Add additional selected */
	const selected = document.querySelectorAll('.item.select.selected');
	selected.forEach((item) => {
		const ingredient = item.querySelector('input[type="checkbox"]').value;
		const img = document.querySelector(
			'#pizza-view .pizza img[pizza-ing="' + ingredient + '"]'
		);

		if (img) img.classList.add('selected');
	});
}
