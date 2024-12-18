<?php

class DBConnect {
    private static $instance = null;
    private $conn;

    private $DB_SERVER = 'localhost';
    private $DB_USERNAME = 'root';
    private $DB_PASSWORD = '';
    private $DB_NAME = 'pizza';

    private function __construct() {
        $connectionInfo = "mysql:host={$this->DB_SERVER};dbname={$this->DB_NAME}";
        try {
            $this->conn = new PDO($connectionInfo, $this->DB_USERNAME, $this->DB_PASSWORD);
            $this->conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            $this->conn->setAttribute(PDO::MYSQL_ATTR_MULTI_STATEMENTS, false);
        } catch (PDOException $e) {
            die('Connection failed: ' . $e->getMessage());
        }
    }
    private function __clone() {}

    public static function getInstance() {
        if (self::$instance === null) {
            self::$instance = new DBConnect();
        }
        return self::$instance;
    }

    public function getConnection() {
        return $this->conn;
    }
}