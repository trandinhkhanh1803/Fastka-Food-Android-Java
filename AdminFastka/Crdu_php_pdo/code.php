<?php

$conn = mysqli_connect("localhost","root","","db_fastka");

if($_SERVER["REQUEST_METHOD"] == "POST") {
    if(isset($_POST['removeItem'])) {
        $itemId = $_POST["itemId"];
        $sql = "DELETE FROM `myorder` WHERE `id`='$itemId'";   
        $result = mysqli_query($conn, $sql);
        echo "<script>alert('Removed');
                window.history.back(1);
            </script>";
    }
}

?>