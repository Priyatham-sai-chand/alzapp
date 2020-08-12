<?php
    $con = mysqli_connect("localhost", "id12912043_alz", "priyatham", "id12912043_alz");
    
    $username = isset($_POST['username']) ? $_POST['username']:'';
    $password = isset($_POST['password']) ? $_POST['password']:'';
    
    
    $statement = mysqli_prepare($con, "SELECT * FROM User WHERE username = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $username, $password);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $username,$password,$firstname,$lastname,$dob,$email);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;  
        $response['username'] = $username;
        $response['password'] = $password;
        $response['firstname']=  $firstname;
        $response['lastname'] = $lastname;
        $response['dob'] = $dob;
        $response['email'] = $email;
    }
    
    echo json_encode($response);
?>