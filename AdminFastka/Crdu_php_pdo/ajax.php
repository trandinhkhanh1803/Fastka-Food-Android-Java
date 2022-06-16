<?php
$action = $_REQUEST['action'];

if (!empty($action)) {
    require_once 'connect/Player.php';
    $obj = new Player();
}

if ($action == 'adduser' && !empty($_POST)) {
    $title = $_POST['title'];
    $description = $_POST['description'];
    $picture = $_FILES['picture'];
    $price = $_POST['price'];
    $rating = $_POST['rating'];
    $timeDelivery = $_POST['timeDelivery'];
    $playerId = (!empty($_POST['userid'])) ? $_POST['userid'] : '';

    // file (photo) upload
    $imagename = '';
    if (!empty($picture['name'])) {
        $imagename = $obj->uploadPhoto($picture);
        $playerData = [
            'title' => $title,
            'description' => $description,
            'price' => $price,
            'rating' => $rating,
            'timeDelivery' => $timeDelivery,
            'picture' => $imagename,
        ];
    } else {
        $playerData = [
            'title' => $title,
            'description' => $description,
            'price' => $price,
            'rating' => $rating,
            'timeDelivery' => $timeDelivery,
        ];
    }

    if ($playerId) {
        $obj->update($playerData, $playerId);
    } else {
        $playerId = $obj->add($playerData);
    }

    if (!empty($playerId)) {
        $player = $obj->getRow('id', $playerId);
        echo json_encode($player);
        exit();
    }
}

if ($action == "getusers") {
    $page = (!empty($_GET['page'])) ? $_GET['page'] : 1;
    $limit = 4;
    $start = ($page - 1) * $limit;

    $players = $obj->getRows($start, $limit);
    if (!empty($players)) {
        $playerslist = $players;
    } else {
        $playerslist = [];
    }
    $total = $obj->getCount();
    $playerArr = ['count' => $total, 'players' => $playerslist];
    echo json_encode($playerArr);
    exit();
}

if ($action == "getuser") {
    $playerId = (!empty($_GET['id'])) ? $_GET['id'] : '';
    if (!empty($playerId)) {
        $player = $obj->getRow('id', $playerId);
        echo json_encode($player);
        exit();
    }
}

if ($action == "deleteuser") {
    $playerId = (!empty($_GET['id'])) ? $_GET['id'] : '';
    if (!empty($playerId)) {
        $isDeleted = $obj->deleteRow($playerId);
        if ($isDeleted) {
            $message = ['deleted' => 1];
        } else {
            $message = ['deleted' => 0];
        }
        echo json_encode($message);
        exit();
    }
}

if ($action == 'search') {
    $queryString = (!empty($_GET['searchQuery'])) ? trim($_GET['searchQuery']) : '';
    $results = $obj->searchPlayer($queryString);
    echo json_encode($results);
    exit();
}
