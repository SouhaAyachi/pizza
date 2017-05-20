<?php
 
/*
 * Following code will list all the products
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once (__DIR__.'/db_connect.php');
require_once (__DIR__.'/db_config.php');
 
// connecting to db
$db = new DB_CONNECT();
$con=$db->connect();

// get all products from products table
$result = mysqli_query($con,"select * from products");// or die(mysqli_error());

// echo "requete ";
//echo "Num rows ".mysqli_num_rows($result);

// check for empty result
if (mysqli_num_rows($result) > 0) {
//echo "connection b1";
    // looping through all results
    // products node
    $response["products"] = array();
 
    while ($row = mysqli_fetch_array($result)) {
        // temp user array

        $product = array();
/*$product["id"] = $row["id"];
$product["login"] = $row["login"];
$product["pass"] = $row["pass"];*/

        $product["pid"] = $row["pid"];
        $product["name"] = $row["name"];
        $product["price"] = $row["price"];
        $product["created_at"] = $row["created_at"];
        $product["updated_at"] = $row["updated_at"];
 
        // push single product into final response array
        array_push($response["products"], $product);
    }
    // success
    $response["success"] = 1;
  
    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "No products found";
 
    // echo no users JSON
    echo json_encode($response);
}
?>
