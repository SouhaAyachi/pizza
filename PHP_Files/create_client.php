<?php
 
/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['nom']) && isset($_POST['prenom']) && isset($_POST['login']) && isset($_POST['pass']) && isset($_POST['age'])) {
 
    $nom = $_POST['nom'];
echo "nom ".$nom;
    $prenom = $_POST['prenom'];
echo "prenom ".$prenom;
    $login = $_POST['login'];
echo "login ".$login;
 	$pass = $_POST['pass'];
echo "pass ".$pass;
	$age = $_POST['age'];
echo "age ".$age;
 
    // include db connect class
    require_once (__DIR__.'/db_connect.php');
 
    // connecting to db
    $db = new DB_CONNECT();
$con=$db->connect();
//echo "cnx b1";
 
    // mysql inserting a new row
    $result = mysqli_query($con,"INSERT INTO client(nom, prenom, age,login,pass) VALUES('.".$nom."', '".$prenom."', '".$age."','".$login."','".$pass."')");
    //$result = mysqli_query($con,"INSERT INTO client(nom, prenom, age,login,pass) VALUES('bbbb', 'aaaa', '10','aaaa','aaaaaa')");// or die(mysqli_error());
 echo "\n cnx b2";
    // check if row inserted or not
    if ($result) {
 //echo "if";
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Client successfully created.";
 
        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
 
        // echoing JSON response
        echo json_encode($response);
    }
} 
else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>
