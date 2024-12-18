<?php

include 'autoloaders/controller_autoload.php';

session_start();

$requestUri = $_SERVER['REQUEST_URI'];
$request = isset($_GET['action']) ? $_GET['action'] : basename($requestUri);
$method = $_SERVER['REQUEST_METHOD'];

switch ($request) {
  case 'loadPrevious':
    $cartController = new CartController();
    $cartController->loadPrevious();
    break;
  case 'removePrevious':
    $cartController = new CartController();
    $cartController->removePrevious();
    break;
  case 'removeOne':
    $cartController = new CartController();
    $cartController->removeItem();
    break;
  case 'removeAll':
    $cartController = new CartController();
    $cartController->removeAllItems();
    break;
  case 'buy':
    $cartController = new CartController();
    $cartController->buyOrder();
    break;
  default:
    $cartController = new CartController();
    $cartController->initPage();
    break;
}
