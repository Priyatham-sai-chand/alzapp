<?php
    $con = mysqli_connect("localhost", "id12912043_alz", "alzapp", "id12912043_alz");
    $username = isset($_POST['username']) ? $_POST['username'] : '';
    $jumble = isset($_POST['jumble']) ? $_POST['jumble'] : '';
    function usernameExists() {
        global $con, $username;
        $statement = mysqli_prepare($con, "SELECT * FROM game_times WHERE username = ?"); 
        mysqli_stmt_bind_param($statement, "s", $username);
        mysqli_stmt_execute($statement);
        mysqli_stmt_store_result($statement);
        $count = mysqli_stmt_num_rows($statement);
        mysqli_stmt_close($statement); 
        if ($count > 0){
            return true; 
        }else {
            return false; 
        }
    }
    
    function newAddition() {
        global $con, $username,$jumble;
        $statement = mysqli_prepare($con, "INSERT INTO game_times (username,jumble) VALUES (?, ?)"); 
        mysqli_stmt_bind_param($statement, "si", $username,$jumble);
        mysqli_stmt_execute($statement);
        mysqli_stmt_store_result($statement);
        mysqli_stmt_close($statement); 
        
    }
    function oldAddition(){
        global $con, $username,$jumble;
        $statement = mysqli_prepare($con, "UPDATE game_times SET jumble = ? WHERE username = ?"); 
        mysqli_stmt_bind_param($statement, "is",$jumble,$username);
        mysqli_stmt_execute($statement);
        mysqli_stmt_store_result($statement);
        mysqli_stmt_close($statement);
        
        
    }

$response = array();
$response["success"] = false;

if(usernameExists()){
    oldAddition();
    $response["success"] = true; 
}
else{
    newAddition();
    $response["success"] = true; 
}
    
   echo json_encode($response);
    
    
?>