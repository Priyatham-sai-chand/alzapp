<?php
    require("password.php");

    $connect = mysqli_connect("localhost", "id12912043_alz", "alzapp" , "id12912043_alz");
    $username = isset($_POST['username']) ? $_POST['username'] : '';
    $password = isset($_POST['password']) ? $_POST['password'] : '';
    $firstname = isset($_POST['firstname']) ? $_POST['firstname'] : '';
    $lastname = isset($_POST['lastname']) ? $_POST['lastname'] : '';
    $dob = isset($_POST['dob']) ? $_POST['dob'] : '';
    $email = isset($_POST['email']) ? $_POST['email'] : '';
    $gender = isset($_POST['gender']) ? $_POST['gender'] : '';
    $age = isset($_POST['age']) ? $_POST['age'] : '';

     function registerUser() {
        global $connect,$username, $password,$firstname,$lastname,$dob,$email,$gender,$age;
        $passwordHash = password_hash($password, PASSWORD_DEFAULT);
        $statement = mysqli_prepare($connect, "INSERT INTO User (username,password,firstname,lastname,dob,email,gender,age) VALUES (?, ?, ?, ? ,? ,? ,? ,?)");
mysqli_stmt_bind_param($statement, "sssssssi",$username,$passwordHash, $firstname,$lastname,$dob,$email,$gender,$age);
        mysqli_stmt_execute($statement);
        mysqli_stmt_close($statement);     
    }

    function usernameAvailable() {
        global $connect, $username;
        $statement = mysqli_prepare($connect, "SELECT * FROM User WHERE username = ?"); 
        mysqli_stmt_bind_param($statement, "s", $username);
        mysqli_stmt_execute($statement);
        mysqli_stmt_store_result($statement);
        $count = mysqli_stmt_num_rows($statement);
        mysqli_stmt_close($statement); 
        if ($count < 1){
            return true; 
        }else {
            return false; 
        }
    }

    $response = array();
    $response["success"] = false;  

    if (usernameAvailable()){
        registerUser();
        $response["success"] = true;  
    }
    
    echo json_encode($response);
?>