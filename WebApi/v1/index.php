<?php

require_once '../includes/DBOperation.php';

$response = array();

if(isset($_GET['op']))
{
    switch($_GET['op'])
    {
        case 'addUsers':
            if(!empty($_POST['FULLNAME'])&& !empty($_POST['EMAIL']) && !empty($_POST['PASSWORD']))
            {
                $db = new DBOperation();
                if($db->addUserAcc($_POST['FULLNAME'],$_POST['EMAIL'], $_POST['PASSWORD'])=='c')
                {
                    $response['error'] = false;
                    $response['message'] = 'Sign Up successful';
                }
                else if($db->addUserAcc($_POST['FULLNAME'], $_POST['EMAIL'], $_POST['PASSWORD'])=='b')
                {
                    $response['error'] = true;
                    $response['message'] = 'The Email is already taken';
                }
                else{
                    $response['error'] = true;
                    $response['message'] = 'Could not sign up';
                }
            }
            else{
                $response['error'] = true;
                $response['message'] = 'Required parameter missing';
            }
           
        break;

        case 'getUsers':
            $db = new DBOperation();
            $users = $db->getUserAcc();
            if(count($users)<=0)
            {
                $response['error'] = true;
                $response['message'] = 'Nothing found database';
            }
            else
            {
                $response['error'] = false;
                $response['users'] = $users;    
            }
        break;
        
        case 'loginuser':
            if(!empty($_POST['EMAIL']) && !empty($_POST['PASSWORD']))
            {
                $db = new DBOperation();
                if($db->usersLogin($_POST['EMAIL'], $_POST['PASSWORD']))
                {
                    $response['error'] = false;
                    $response['message'] = 'Logged in Succesfully';
                }else{
                    $response['error'] = true;
                    $response['message'] = 'Email or Password is incorrect';
                }
            }
            else{
                $response['error'] = true;
                $response['message'] = 'Required parameter missing';
            }
            

        break;

        default:
            $response['error'] = true; 
            $response['message'] = 'No Operation to perform';

    }
}
else
{
    $response['error'] = true; 
    $response['message'] = 'Invalid request';
}

echo json_encode($response);
?>