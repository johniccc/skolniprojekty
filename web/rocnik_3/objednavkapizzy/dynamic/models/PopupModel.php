<?php
class PopupModel
{
  public function getPopupInfo()
  {
    $popup_info = isset($_SESSION['popupInfo']) ? $_SESSION['popupInfo'] : null;
    return $popup_info;
  }

  public function setPopupInfo($type, $header, $popupMessage)
  {
    $_SESSION['popupInfo'] = [$type, $header, $popupMessage];
  }

  public function clearPopupInfo()
  {
    if (isset($_SESSION['popupInfo'])) {
      unset($_SESSION['popupInfo']);
      return true;
    }
    return false;
  }
}
