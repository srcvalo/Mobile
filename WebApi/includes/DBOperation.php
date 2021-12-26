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
    public function addUserAcc($fullname, $email, $password)
    {
        $check = 'a';
        $stmt = $this->con->prepare("INSERT INTO users(fullname, email, password) Values(?, ?, ?)");
        $stmt->bind_param("sss", $fullname, $email, $password);
        $chkEmail="SELECT * FROM users WHERE  email='$email'";
        $res=mysqli_query($this->con,$chkEmail);
        $row = mysqli_fetch_assoc($res);
        
        if($email==$row['email'])
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
       $stmt = $this->con->prepare("SELECT id,email, password  FROM users");
        $stmt->execute();
        $stmt->bind_result($id,$email, $password);
        $users = array();

        while($stmt->fetch())
        {
            $temp = array();
            $temp ['id'] = $id;
            $temp['email'] = $email;
            $temp['password'] = $password;
            array_push($users, $temp); 
        }  
        return $users;
    }
    public function usersLogin($email, $password)
    {
        $stmt = "SELECT * FROM users WHERE email = '$email' AND password = '$password'";
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