<?php

class CartModel
{
  public function addItemToCart($ingredientsBase, $ingredientsCheese, $ingredientsMeat, $ingredientsOther)
  {
    if (!isset($_SESSION['order'])) $_SESSION['order'] = [];
    array_push($_SESSION['order'], array(
      $ingredientsBase,
      $ingredientsCheese,
      $ingredientsMeat,
      $ingredientsOther
    ));
  }
  public function setCart($cart)
  {
    $_SESSION['order'] = $cart;
  }
  public function getItem($index)
  {
    return (isset($_SESSION['order']) && isset($_SESSION['order'][$index])) ? $_SESSION['order'][$index] : null;
  }
  public function getAllItems()
  {
    return isset($_SESSION['order']) ? $_SESSION['order'] : array();
  }
  public function getItemCount()
  {
    return count($this->getAllItems());
  }
  public function removeItem($index)
  {
    if (isset($_SESSION['order']) && isset($_SESSION['order'][$index])) {
      unset($_SESSION['order'][$index]);
      $_SESSION['order'] = array_values($_SESSION['order']);
      return true;
    }
    return false;
  }
  public function removeAllItems()
  {
    if (!isset($_SESSION['order'])) {
      return false;
    }
    unset($_SESSION['order']);
    return true;
  }
}
