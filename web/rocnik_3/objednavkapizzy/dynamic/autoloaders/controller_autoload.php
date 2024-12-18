<?php
spl_autoload_register(function ($class) {
  $path = 'controllers/' . $class . '.php';
  if (file_exists($path)) {
    require_once $path;
  }
});
