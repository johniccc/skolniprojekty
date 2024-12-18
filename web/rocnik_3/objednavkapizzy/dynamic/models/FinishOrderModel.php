<?php

class FinishOrderModel
{
  public function setErrors($errors)
  {
    $_SESSION['errors'] = $errors;
  }

  public function getErrors()
  {
    return isset($_SESSION['errors']) ? $_SESSION['errors'] : array();
  }

  public function clearErrors()
  {
    if (!isset($_SESSION['errors'])) {
      return false;
    }
    unset($_SESSION['errors']);
    return true;
  }

  public function setFormData($formData)
  {
    $_SESSION['form_data'] = $formData;
  }

  public function getFormData()
  {
    return isset($_SESSION['form_data']) ? $_SESSION['form_data'] : array();
  }

  public function clearFormData()
  {
    if (!isset($_SESSION['form_data'])) {
      return false;
    }
    unset($_SESSION['form_data']);
    return true;
  }
}
