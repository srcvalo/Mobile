<?php
class DBConnect
{
    private $con;

    function __construct()
    {
        
    }

    function connect()
    {
        include_once dirname(__FILE__).'/Constants.php';

        $this->con = new mysqli(DB_HOST, DB_USERNAME, DB_PASSWORD, DB_NAME);

        if(mysqli_connect_errno())
        {
            echo "Failed to Connect to mySQL: ".mysqli_connect_error();
            return null;
        }
        return $this->con;
    }
}
?>
