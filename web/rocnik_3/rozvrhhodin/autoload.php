<?php
spl_autoload_register(function ($class) {
  $path = $class . '.php';
  if (file_exists($path)) {
    require_once $path;
  }
});
