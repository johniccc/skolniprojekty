<?php
if (isset($popupInfo)) { ?>
  <div class="popup active">
    <div class="box">
      <div class="top">
        <span class="title"><?php echo $popupInfo[1] ?></span>
        <button type="button" class="closeBtn">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 256 256">
            <rect width="256" height="256" fill="none" />
            <line x1="200" y1="56" x2="56" y2="200" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="24" />
            <line x1="200" y1="200" x2="56" y2="56" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="24" />
          </svg>
        </button>
      </div>
      <div class="center">
        <p class="message"><?php echo $popupInfo[2] ?></p>
      </div>
      <div class="bottom">
        <?php if ($popupInfo[0] === 'confirm') { ?>
          <form action="<?php echo $currentPage ?>/?action=removePrevious" method="POST">
            <button type="submit" class="button confirm">
              <span>Vymazat preferenci</span>
            </button>
          </form>
        <?php } ?>
        <?php
        switch ($popupInfo[0]) {
          case 'confirm': ?>
            <form action="<?php echo $currentPage ?>/?action=loadPrevious" method="POST">
              <button type="submit" class="button confirm">
                <span>Potvrdit</span>
              </button>
            </form>
          <?php
            break;
          case 'error': ?>
            <button type="button" class="button confirm">
              <span>OK</span>
            </button>
          <?php
            break;
          case 'success': ?>
            <button type="button" class="button confirm">
              <span>OK</span>
            </button>
            <a href="cart.php" class="button change">Přejít do košíku</a>
          <?php
            break;
          default: ?>
            <button type="button" class="button confirm">
              <span>Potvrdit</span>
            </button>
        <?php
            break;
        }
        ?>

      </div>
    </div>
  </div>
<?php } ?>