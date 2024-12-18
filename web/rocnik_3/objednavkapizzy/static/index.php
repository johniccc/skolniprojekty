<?php

include 'autoloaders/controller_autoload.php';

session_start();

$requestUri = $_SERVER['REQUEST_URI'];
$request = isset($_GET['action']) ? $_GET['action'] : basename($requestUri);
$method = $_SERVER['REQUEST_METHOD'];

switch ($request) {
  case 'add':
    $orderController = new OrderController();
    if ($method == 'POST') {
      $orderController->addToCart();
    } else {
      $orderController->initPage();
    }
    break;
  case 'loadPrevious':
    $orderController = new OrderController();
    $orderController->loadPrevious();
    break;
  case 'removePrevious':
    $orderController = new OrderController();
    $orderController->removePrevious();
    break;
  default:
    $orderController = new OrderController();
    $orderController->initPage();
    break;
}
