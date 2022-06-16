

<table class="table" id="order">
  <thead>
    <tr>
    <th scope="col">Id</th> 
    <th scope="col">NumberOrder</th> 
      <th scope="col">Email</th>
      <th scope="col">Time</th>
      <th scope="col">Total</th>
      <th scope="col">Address</th>
      <th scope="col"></th>
    </tr>
    <?php
  $conn = mysqli_connect("localhost","root","","db_fastka");


  $sql = "SELECT * FROM `myorder` ";
  $result = mysqli_query($conn, $sql);
  while($row = mysqli_fetch_assoc($result)){
      $id = $row['id'];
      $numberOrder = $row['numberOrder'];
      $email = $row['email'];
      $date = $row['date'];
      $total = $row['total'];
      $address = $row['address'];

   
      echo'
    <tr>
    <td class="align-middle">'.$id.'</td>
        <td class="align-middle">'.$numberOrder.'</td>    
            <td class="align-middle">'.$email.'</td>
            <td class="align-middle">'.$date.'</td>
            <td class="align-middle">'.$total.'</td>
            <td class="align-middle">'.$address.'</td>
     
            <td class="align-middle">
            <form action="code.php" method="POST">
            <button name="removeItem" class="btn btn-sm btn-outline-danger">Xóa</button>
            <input type="hidden" name="itemId" value="'.$id. '">
        </form>
             
            </td>
          </tr>
<i class="fas fa-place-of-worship    "></i>

'  ;  }
?>
       
  </thead>
  <tbody>

  </tbody>
</table>