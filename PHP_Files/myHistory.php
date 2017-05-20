<?php
 
/*
 * Following code will list all the products
 */
 
// array for JSON response


 
    $id = $_GET['id'];
//echo $id;
$response = array();
 
// include db connect class
require_once (__DIR__.'/db_connect.php');

// connecting to db
$db = new DB_CONNECT();
$con=$db->connect();

// get all products from products table
$result = mysqli_query($con,"select d.id,t.designation,tp.designation,d.prix ,d.date from demande d, typepartaille tt, type tp, taille t where d.idClient=".$id." and d.idTypeParTaille=tt.id and tt.idtype=tp.id and tt.idtaille=t.id");// or die(mysqli_error());

// echo "requete "; Full texts 	
//echo "Num rows ".mysqli_num_rows($result);

// check for empty result
if (mysqli_num_rows($result) > 0) {
//echo "connection b1";
    // looping through all results
    // products node
    $response["pizza"] = array();
 
    while ($row = mysqli_fetch_array($result)) {
//echo "while";
        // temp user array

        $pizza = array();
/*$product["id"] = $row["id"];
$product["login"] = $row["login"];
$product["pass"] = $row["pass"];*/
/*echo $row[0];
echo $row[1];
echo $row[2];
echo $row[3];
echo $row[4];*/
        $pizza["pid"] = $row[0];
        $pizza["taille"] = $row[1];
        $pizza["type"] = $row[2];
        $pizza["prix"] = $row[3];
        $pizza["date"] = $row[4];
 
        // push single product into final response array
        array_push($response["pizza"], $pizza);
    }
    // success
    $response["success"] = 1;
  
    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "No pizzas found";
 
    // echo no users JSON
    echo json_encode($response);
}


?>
