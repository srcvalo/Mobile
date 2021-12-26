<?php

class DBOperation
{
    private $con;

    function __construct()
    {
        require_once dirname(__FILE__).'/DBConnect.php';
        $db = new DBConnect;
        $this->con = $db->connect();
    }

    //adding acc to database
    public function addUserAcc($FULLNAME, $EMAIL, $PASSWORD)
    {
        $check = 'a';
        $stmt = $this->con->prepare("INSERT INTO users(FULLNAME, EMAIL, PASSWORD) Values(?, ?, ?)");
        $stmt->bind_param("sss", $FULLNAME, $EMAIL, $PASSWORD);
        $chkEmail="SELECT * FROM users WHERE  email='$EMAIL'";
        $res=mysqli_query($this->con,$chkEmail);
        $row = mysqli_fetch_assoc($res);
        
        if($EMAIL==$row['EMAIL'])
        {
            $check = 'b';
        }
        else if ($stmt->execute())
        {
            $check = 'c';
        }
        return $check;
    }

    public function getUserAcc()
    {
       $stmt = $this->con->prepare("SELECT id,EMAIL, PASSWORD  FROM users");
        $stmt->execute();
        $stmt->bind_result($id,$EMAIL, $PASSWORD);
        $users = array();

        while($stmt->fetch())
        {
            $temp = array();
            $temp ['id'] = $id;
            $temp['EMAIL'] = $EMAIL;
            $temp['PASSWORD'] = $PASSWORD;
            array_push($users, $temp); 
        }  
        return $users;
    }
    public function usersLogin($EMAIL, $PASSWORD)
    {
        $stmt = "SELECT * FROM users WHERE EMAIL = '$EMAIL' AND PASSWORD = '$PASSWORD'";
        $result = mysqli_query($this->con,$stmt); 

        $row = mysqli_fetch_array($result, MYSQLI_ASSOC);  
        $count = mysqli_num_rows($result); 
        if($count == 1){  
            return true;  
        }   
        
        return false; 
    }


}


?>