<?php
    require("password.php");

    $con = mysqli_connect("localhost", "id12912043_alz", "alzapp", "id12912043_alz");
    
    $username = isset($_POST['username']) ? $_POST['username']:'';
    $password = isset($_POST['password']) ? $_POST['password']:'';
    
    $statement = mysqli_prepare($con, "SELECT * FROM User WHERE username = ?");
    mysqli_stmt_bind_param($statement, "s", $username);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $colusername,$colpassword,$firstname,$lastname,$dob,$email,$gender,$age);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        if (password_verify($password, $colpassword)) {
            $response["success"] = true;  
            $response['username'] = $colusername;
            $response['password'] = $colpassword;
            $response['firstname']=  $firstname;
            $response['lastname'] = $lastname;
            $response['dob'] = $dob;
            $response['email'] = $email;
            $response['gender'] = $gender;
            $response['age'] = $age;
        }
    }

    echo json_encode($response);
?>