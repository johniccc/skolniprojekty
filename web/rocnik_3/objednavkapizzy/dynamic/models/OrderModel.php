<?php

include 'autoloaders/db_autoload.php';

class OrderModel
{
  private $db;

  public function __construct()
  {
    $this->db = DBConnect::getInstance();
  }

  public function getAllIngredients()
  {
    try {
      $stmt = $this->db->getConnection()->prepare("SELECT * FROM ingredients");
      $stmt->execute();
      return $stmt->fetchAll(PDO::FETCH_ASSOC);
    } catch (PDOException $e) {
      die('Connection failed: ' . $e->getMessage());
    }
  }

  public function getIngredientsByType($type)
  {
    try {
      $stmt = $this->db->getConnection()->prepare("SELECT * FROM ingredients WHERE type = ?");
      $stmt->execute([$type]);
      return $stmt->fetchAll(PDO::FETCH_ASSOC);
    } catch (PDOException $e) {
      die('Connection failed: ' . $e->getMessage());
    }
  }
  public function setUserPreference($preference, $cart)
  {
    $cookies_allowed = true;
    if ($cookies_allowed) {
      $cookie_name = 'savePreference';
      $cookie_preference = ($preference === 'on') ? true : false;
      $cookie_value = serialize([$cookie_preference, $cart]);
      $cookie_expire = time() + (60 * 60 * 24 * 365);
      setcookie($cookie_name, $cookie_value, $cookie_expire, '/', '', true, true);
      return true;
    }
    return false;
  }

  public function getUserPreference()
  {
    $cookie_name = 'savePreference';
    if (isset($_COOKIE[$cookie_name])) {
      $cookie_value = unserialize($_COOKIE[$cookie_name]);
      return $cookie_value;
    }
    return false;
  }

  public function removeUserPreference()
  {
    $cookie_name = 'savePreference';
    if (isset($_COOKIE[$cookie_name])) {
      setcookie($cookie_name, '', time() - 3600, '/');
      return true;
    }
    return false;
  }
}
