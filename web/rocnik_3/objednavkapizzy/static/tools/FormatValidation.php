<?php

class FormatValidation
{
  public static function checkAll($name, $email, $phone, $postcode, $cardNumber, $cardExpiryMonth, $cardExpiryYear, $cvv)
  {
    $errors = [];

    $errors = array_merge($errors, self::validateName($name));
    $errors = array_merge($errors, self::validateEmail($email));
    $errors = array_merge($errors, self::validatePhone($phone));
    $errors = array_merge($errors, self::validatePostcode($postcode));
    $errors = array_merge($errors, self::validateCardNumber($cardNumber));
    $errors = array_merge($errors, self::validateCardExpiry($cardExpiryMonth, $cardExpiryYear));
    $errors = array_merge($errors, self::validateCVV($cvv));

    return $errors;
  }

  public static function checkNull($name, $email, $address, $city, $postcode, $cardNumber, $cardExpiryMonth, $cardExpiryYear, $cvv)
  {
    $errors = [];
    $requiredFields = [
      'name' => $name,
      'email' => $email,
      'address' => $address,
      'city' => $city,
      'postcode' => $postcode,
      'card-number' => $cardNumber,
      'card-month' => $cardExpiryMonth,
      'card-year' => $cardExpiryYear,
      'cvv' => $cvv,
    ];

    foreach ($requiredFields as $field => $value) {
      if (empty($value)) {
        $errors[$field] = "Vyplňte prosím pole.";
      }
    }

    return $errors;
  }

  public static function validateName($name)
  {
    $errors = [];
    if (!preg_match("/^[a-zA-ZáéíóúÁÉÍÓÚçÇàèìòùÀÈÌÒÙ\s'-]+$/", $name)) {
      $errors['name'] = "Neplatný formát jména.";
    }
    return $errors;
  }
  public static function validateEmail($email)
  {
    $errors = [];
    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
      $errors['email'] = "Neplatný formát emailové adresy.";
    }
    return $errors;
  }
  public static function validatePhone($phone)
  {
    $errors = [];
    if ($phone === '') {
      return $errors;
    } else if (!preg_match("/^\+420\s?[0-9]{3}\s?[0-9]{3}\s?[0-9]{3}$/", $phone)) {
      $errors['phone'] = "Neplatný formát telefonního čísla.";
    }
    return $errors;
  }

  public static function validatePostcode($postcode)
  {
    $errors = [];
    if (!preg_match("/^[0-9]{3}\s?[0-9]{2}$/", $postcode)) {
      $errors['postcode'] = "Neplatný formát PSČ.";
    }
    return $errors;
  }

  public static function validateCardNumber($cardNumber)
  {
    $errors = [];
    if (!preg_match("/^[0-9]{4}(\s?[0-9]{4}){3}$/", $cardNumber)) {
      $errors['card-number'] = "Neplatný formát čísla kreditní karty.";
    }
    return $errors;
  }
  public static function validateCardExpiry($cardExpiryMonth, $cardExpiryYear)
  {
    $errors = [];
    if (!is_numeric($cardExpiryMonth) || $cardExpiryMonth < 1 || $cardExpiryMonth > 12) {
      $errors['card-month'] = "Měsíc platnosti karty je neplatný. Zadejte měsíc mezi 1 a 12.";
    }
    if (!is_numeric($cardExpiryYear)) {
      $errors['card-year'] = "Rok platnosti karty je neplatný. Zadejte platný rok.";
    }

    $currentMonth = ltrim(date('m'), '0');
    $currentYear = ltrim(date('y'), '0');;

    if (
      ($cardExpiryYear < $currentYear) || ($cardExpiryYear == $currentYear && $cardExpiryMonth < $currentMonth)
    ) {
      $errors['card-year'] = "Datum platnosti karty je v minulosti.";
    }

    return $errors;
  }

  public static function validateCVV($cvv)
  {
    $errors = [];
    if (!preg_match("/^[0-9]{3,4}$/", $cvv)) {
      $errors['cvv'] = "Neplatný formát čísla CVV.";
    }
    return $errors;
  }
}
