<?php 



$user_name = $_POST["login"];
$user_pass = $_POST["pass"];
//echo $user_name;
//echo $user_pass;
require_once (__DIR__.'/db_connect.php');
$db=$db = new DB_CONNECT();
$con=$db->connect();
$mysql_qry = "select * from client where  login='".$user_name."' and pass='".$user_pass."';";
//echo $mysql_qry;
$result = mysqli_query($con ,$mysql_qry);
//echo"exec req";

if(mysqli_num_rows($result) > 0) {
 //$response['user'] = array();
 $row = mysqli_fetch_array($result);
	$user = array();
$user['id']=$row[0];
$user['nom']=$row[1];
$user['prenom']=$row[2];
$response['user']=$user;


$response["success"] = 1;
	echo json_encode($response);
}
else {
 $response["success"] = 0;
    $response["message"] = "No user found";
 
    // echo no users JSON
    echo json_encode($response);
}



?>
