<?php
    $con = mysqli_connect("localhost", "id12912043_alz", "priyatham", "id12912043_alz");
    
    
    
    $username = isset($_POST['username']) ? $_POST['username'] : '';
    $password = isset($_POST['password']) ? $_POST['password'] : '';
    $firstname = isset($_POST['firstname']) ? $_POST['firstname'] : '';
    $lastname = isset($_POST['lastname']) ? $_POST['lastname'] : '';
    $dob = isset($_POST['dob']) ? $_POST['dob'] : '';
    $email = isset($_POST['email']) ? $_POST['email'] : '';
    
$statement = mysqli_prepare($con, "INSERT INTO User (username,password,firstname,lastname,dob,email) VALUES (?, ?, ?, ? ,? ,?)");
    mysqli_stmt_bind_param($statement, "ssssss",$username,$password, $firstname,$lastname,$dob,$email);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>