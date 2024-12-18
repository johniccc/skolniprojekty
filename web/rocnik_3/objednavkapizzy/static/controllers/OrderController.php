<?php

include 'autoloaders/model_autoload.php';

class OrderController implements Controller
{
  private $cartModel;
  private $orderModel;
  private $popupModel;
  public function __construct()
  {
    $this->cartModel = new CartModel();
    $this->orderModel = new OrderModel();
    $this->popupModel = new PopupModel();
  }
  public function initPage()
  {
    $userPreference = $this->orderModel->getUserPreference();
    if ($userPreference && $userPreference[0]) {
      $this->popupModel->setPopupInfo('confirm', 'Našli jsme objednávku', 'V minulosti jste si objednával. Přidat?');
    }
    $currentPage = 'index.php';
    $orderCount = $this->cartModel->getItemCount();
    $popupInfo = $this->popupModel->getPopupInfo();
    include 'views/OrderView.php';
    $this->popupModel->clearPopupInfo();
  }

  public function addToCart()
  {
    $ingredientsBase = isset($_POST['ingredientsBase']) ? $_POST['ingredientsBase'] : null;
    $ingredientsCheese = isset($_POST['ingredientsCheese']) ? $_POST['ingredientsCheese'] : null;
    $ingredientsMeat = isset($_POST['ingredientsMeat']) ? $_POST['ingredientsMeat'] : null;
    $ingredientsOther = isset($_POST['ingredientsOther']) ? $_POST['ingredientsOther'] : null;

    if ($ingredientsBase !== null) {
      if (count($ingredientsBase) != 1) {
        $this->popupModel->setPopupInfo('error', 'Chyba', 'Pizza musí mít jen jeden základ!');
        header('Location: ../index.php');
        exit;
      }
    } else {
      $this->popupModel->setPopupInfo('error', 'Chyba', 'Pizza musí mít základ!');
      header('Location: ../index.php');
      exit;
    }

    if (empty($ingredientsCheese) && empty($ingredientsMeat)) {
      $this->popupModel->setPopupInfo('error', 'Chyba', 'Pizza musí mít sýr nebo maso!');
      header('Location: ../index.php');
      exit;
    }

    $this->cartModel->addItemToCart($ingredientsBase, $ingredientsCheese, $ingredientsMeat, $ingredientsOther);
    $this->popupModel->setPopupInfo('success', 'Úspěch', 'Vaše pizza byla přidána do košíku.');
    header('Location: ../index.php');
  }

  public function loadPrevious()
  {
    $userPreference = $this->orderModel->getUserPreference();
    if ($userPreference && $userPreference[0]) {
      $this->cartModel->setCart($userPreference[1]);
      $this->orderModel->removeUserPreference();
      header('Location:../index.php');
    }
  }
  public function removePrevious()
  {
    $this->orderModel->removeUserPreference();
    header('Location:../index.php');
  }
}
