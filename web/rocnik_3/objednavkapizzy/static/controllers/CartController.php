<?php

include 'autoloaders/model_autoload.php';
include 'autoloaders/tools_autoload.php';

class CartController implements Controller
{
  private $cartModel;
  private $finishOrderModel;
  private $orderModel;
  private $popupModel;

  public function __construct()
  {
    $this->cartModel = new CartModel();
    $this->finishOrderModel = new FinishOrderModel();
    $this->orderModel = new OrderModel();
    $this->popupModel = new PopupModel();
  }

  public function initPage()
  {
    $userPreference = $this->orderModel->getUserPreference();
    if ($userPreference && $userPreference[0]) {
      $this->popupModel->setPopupInfo('confirm', 'Našli jsme objednávku', 'V minulosti jste si objednával. Přidat?');
    }
    $errors = $this->finishOrderModel->getErrors();
    $popupInfo = $this->popupModel->getPopupInfo();
    $currentPage = 'cart.php';
    $cartItems = $this->cartModel->getAllItems();
    include 'views/CartView.php';
    $this->popupModel->clearPopupInfo();
  }

  public function removeItem()
  {
    $index = isset($_POST['item']) ? $_POST['item'] : null;
    $removeItem = $this->cartModel->removeItem($index);

    if ($index === null || !$removeItem) $this->popupModel->setPopupInfo('error', 'nigga', 'fsdf2');
    $this->finishOrderModel->setFormData($_POST);
    header('Location: ../cart.php');
  }

  public function removeAllItems()
  {
    $removeAll = $this->cartModel->removeAllItems();
    if (!$removeAll) $this->popupModel->setPopupInfo('error', 'nigga', 'fsdf1');
    $this->finishOrderModel->setFormData($_POST);
    header('Location: ../cart.php');
  }

  public function buyOrder()
  {
    $cart = $this->cartModel->getAllItems();
    if (empty($cart)) {
      $this->popupModel->setPopupInfo('error', 'nigga', 'fsdf3');
      $this->finishOrderModel->setFormData($_POST);
      header('Location:../cart.php');
      exit;
    } else {
      $name = trim($_POST['name']);
      $email = trim($_POST['email']);
      $phone = $_POST['phone'];
      $address = trim($_POST['address']);
      $city = trim($_POST['city']);
      $postcode = $_POST['postcode'];
      $cardNumber = $_POST['card-number'];
      $cardExpiryMonth = $_POST['card-month'];
      $cardExpiryMonthSanitizied = ltrim($cardExpiryMonth, '0');
      $cardExpiryYear = $_POST['card-year'];
      $cardExpiryYearSanitizied = ltrim($cardExpiryYear, '0');
      $cvv = $_POST['cvv'];
      $notes = trim($_POST['notes']);
      $saveOrderPreference = isset($_POST['save-order']) ? $_POST['save-order'] : null;

      $mandatoryErrors = formatValidation::checkNull($name, $email, $address, $city, $postcode, $cardNumber, $cardExpiryMonthSanitizied, $cardExpiryYearSanitizied, $cvv);
      $formatErrors = formatValidation::checkAll($name, $email, $phone, $postcode, $cardNumber, $cardExpiryMonthSanitizied, $cardExpiryYearSanitizied, $cvv);

      $errors = array_merge($formatErrors, $mandatoryErrors);
      if (!empty($errors)) {
        $this->finishOrderModel->setErrors($errors);
        $this->finishOrderModel->setFormData($_POST);
        header('Location: ../cart.php');
      } else {
        echo $this->orderModel->setUserPreference($saveOrderPreference, $cart);
        $this->finishOrderModel->clearErrors();
        $this->finishOrderModel->clearFormData();
        $this->cartModel->removeAllItems();
        header('Location:../confirm.php');
      }
    }
  }
  public function loadPrevious()
  {
    $userPreference = $this->orderModel->getUserPreference();
    $this->cartModel->setCart($userPreference[1]);
    header('Location:../cart.php');
  }

  public function removePrevious()
  {
    $this->orderModel->removeUserPreference();
    header('Location:../cart.php');
  }
}
