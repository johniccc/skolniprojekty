<!DOCTYPE html>
<html lang="cs">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<?php include 'partials/headLinks.php' ?>
	<link rel="stylesheet" href="resources/css/general.css">
	<link rel="stylesheet" href="resources/css/cart.css">
	<script src="resources/js/general.js" defer></script>
	<script src="resources/js/cart.js" defer></script>
	<title>Košík</title>
</head>

<body>
	<?php include 'partials/popup.php' ?>
	<main id="cart">
		<section id="details">
			<form id="userInputs" action="cart.php/?action=buy" method="POST">
				<div class="top">
					<a class="backBtn" href="index.php">
						<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 256 256">
							<rect width="256" height="256" fill="none" />
							<circle cx="128" cy="128" r="96" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="24" />
							<polyline points="168 160 136 128 168 96" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="24" />
							<polyline points="112 160 80 128 112 96" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="24" />
						</svg>
					</a>
					<h2>Podrobnosti objednávky</h2>
				</div>
				<div class="category">
					<h3>Kontaktní údaje</h3>
					<div class="inputs">
						<div class="input-field">
							<label for="name">Jméno a příjmení</label>
							<div class="feedback">
								<input class="input" placeholder="Jméno a příjmení" type="text" name="name" id="name" value="<?php echo isset($_SESSION['form_data']['name']) ? $_SESSION['form_data']['name'] : ''; ?>">
								<?php if (isset($errors['name'])) { ?>
									<span class="error"><?php echo $errors['name']; ?></span>
								<?php } ?>
							</div>
						</div>
						<div class="input-field">
							<label for="email">Email</label>
							<div class="feedback">
								<input class="input" placeholder="Email" type="email" name="email" id="email" value="<?php echo isset($_SESSION['form_data']['email']) ? $_SESSION['form_data']['email'] : ''; ?>">
								<?php if (isset($errors['email'])) { ?>
									<span class="error"><?php echo $errors['email']; ?></span>
								<?php } ?>
							</div>
						</div>
						<div class="input-field">
							<label for="phone">Telefonní číslo</label>
							<div class="feedback">
								<input class="input" placeholder="Telefonní číslo" type="tel" name="phone" id="phone" value="<?php echo isset($_SESSION['form_data']['phone']) ? $_SESSION['form_data']['phone'] : ''; ?>">
								<?php if (isset($errors['phone'])) { ?>
									<span class="error"><?php echo $errors['phone']; ?></span>
								<?php } ?>
							</div>
						</div>
					</div>
				</div>
				<div class="category">
					<h3>Doručovací údaje</h3>
					<div class="inputs">
						<div class="input-field">
							<label for="address">Ulice a číslo popisné</label>
							<div class="feedback">
								<input class="input" placeholder="Ulice a číslo popisné" type="text" name="address" id="address" value="<?php echo isset($_SESSION['form_data']['address']) ? $_SESSION['form_data']['address'] : ''; ?>">
								<?php if (isset($errors['address'])) { ?>
									<span class="error"><?php echo $errors['address']; ?></span>
								<?php } ?>
							</div>
						</div>
						<div class="input-field">
							<label for="city">Město / obec</label>
							<div class="feedback">
								<input class="input" placeholder="Město / obec" type="text" name="city" id="city" value="<?php echo isset($_SESSION['form_data']['city']) ? $_SESSION['form_data']['city'] : ''; ?>">
								<?php if (isset($errors['city'])) { ?>
									<span class="error"><?php echo $errors['city']; ?></span>
								<?php } ?>
							</div>
						</div>
						<div class="input-field">
							<label for="postcode">PSČ</label>
							<div class="feedback">
								<input class="input" placeholder="PSČ" type="text" name="postcode" id="postcode" value="<?php echo isset($_SESSION['form_data']['postcode']) ? $_SESSION['form_data']['postcode'] : ''; ?>">
								<?php if (isset($errors['postcode'])) { ?>
									<span class="error"><?php echo $errors['postcode']; ?></span>
								<?php } ?>
							</div>
						</div>
					</div>
				</div>
				<div class="category">
					<h3>Platba</h3>
					<div class="inputs">
						<div class="input-field">
							<label for="card-number">Číslo karty</label>
							<div class="feedback">
								<input class="input" placeholder="Číslo karty" type="text" name="card-number" id="card-number" value="<?php echo isset($_SESSION['form_data']['card-number']) ? $_SESSION['form_data']['card-number'] : ''; ?>">
								<?php if (isset($errors['card-number'])) { ?>
									<span class="error"><?php echo $errors['card-number']; ?></span>
								<?php } ?>
							</div>
						</div>
						<div class="input-field">
							<label for="card-expiry">Datum platnosti</label>
							<div class="input card-expiry feedback" id="card-expiry">
								<input type="number" name="card-month" id="card-month" max="99" tab-index="1" placeholder="MM" value="<?php echo isset($_SESSION['form_data']['card-month']) ? $_SESSION['form_data']['card-month'] : ''; ?>" />
								<span>/</span>
								<input type="number" name="card-year" id="card-year" max="99" tab-index="2" placeholder="YY" value="<?php echo isset($_SESSION['form_data']['card-year']) ? $_SESSION['form_data']['card-year'] : ''; ?>" />
								<?php if (isset($errors['card-month'])) { ?>
									<span class="error"><?php echo $errors['card-month']; ?></span>
								<?php } else if (isset($errors['card-year'])) { ?>
									<span class="error"><?php echo $errors['card-year']; ?></span>
								<?php } ?>
							</div>
						</div>
						<div class="input-field">
							<label for="cvv">CVV</label>
							<div class="feedback">
								<input class="input" placeholder="CVV" max="999" type="number" name="cvv" id="cvv" value="<?php echo isset($_SESSION['form_data']['cvv']) ? $_SESSION['form_data']['cvv'] : ''; ?>">
								<?php if (isset($errors['cvv'])) { ?>
									<span class="error"><?php echo $errors['cvv']; ?></span>
								<?php } ?>
							</div>
						</div>
					</div>
				</div>
				<div class="category">
					<h3>Preference</h3>
					<div class="inputs">
						<div class="input-field full-width">
							<label for="notes">Poznámka</label>
							<textarea class="input" rows="10" placeholder="Pište text sem..." name="notes" id="notes"></textarea>
						</div>
						<div class="input-field checkbox">
							<input class="input" type="checkbox" name="save-order" id="save-order">
							<label for="save-order">Uložit košík pro příští objednávku</label>
						</div>
					</div>
				</div>
			</form>
		</section>
		<section id="summary">
			<div class="box">
				<h2>Vaše objednávka</h2>
				<div class="items">
					<?php if (empty($cartItems)) { ?>
						<span class="empty">Váš košík je prázdný</span>
					<?php } ?>
					<?php foreach ($cartItems as $index => $item) { ?>
						<div class="item">
							<div class="top">
								<h3>Vlastní pizza</h3>
								<form action="cart.php/?action=removeOne" method="POST">
									<input type="hidden" name="item" value="<?php echo $index ?>">
									<button type="submit" class="closeBtn">
										<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 256 256">
											<rect width="256" height="256" fill="none" />
											<line x1="200" y1="56" x2="56" y2="200" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="24" />
											<line x1="200" y1="200" x2="56" y2="56" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="24" />
										</svg>
									</button>
								</form>
							</div>
							<div class="ingredients">
								<div class="base">
									<p>Základ:</p>
									<?php if (isset($item[0])) {
										foreach ($item[0] as $ingredient) { ?>
											<p><?php echo $ingredient ?></p>
									<?php }
									} ?>
								</div>
								<div class="cheese">
									<p>Sýry:</p>
									<?php if (isset($item[1])) {
										foreach ($item[1] as $ingredient) { ?>
											<p><?php echo $ingredient ?></p>
									<?php }
									} ?>
								</div>
								<div class="meat">
									<p>Maso:</p>
									<?php if (isset($item[2])) {
										foreach ($item[2] as $ingredient) { ?>
											<p><?php echo $ingredient ?></p>
									<?php }
									} ?>
								</div>
								<div class="other">
									<p>Ostatní:</p>
									<?php if (isset($item[3])) {
										foreach ($item[3] as $ingredient) { ?>
											<p><?php echo $ingredient ?></p>
									<?php }
									} ?>
								</div>
							</div>
						</div>
					<?php } ?>
				</div>
				<div class="bottom">
					<!-- <div class="price">
						<p class="withoutTax">Cena bez DPH: <span>0</span> Kč</p>
						<p class="withTax">Cena s DPH: <span>0</span> Kč</p>
					</div> -->
					<div class="buttons">
						<form action="cart.php/?action=removeAll" method="POST">
							<button type="submit" class="button cancel"><span>Vymazat položky</span></button>
						</form>
						<button type="submit" form="userInputs" class="button confirm"><span>Objednat</span></button>
					</div>
				</div>
			</div>
			</div>
		</section>
	</main>
</body>

</html>