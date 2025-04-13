<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đặt hàng thành công</title>
    <style>
        .success-container {
            text-align: center;
            margin: 100px auto;
            border: 1px solid #ccc;
            border-radius: 10px;
            padding: 20px;
            width: 400px;
            height: 300px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .success-icon {
            font-size: 60px;
            color: #4CAF50;
        }

        .success-message {
            font-size: 22px;
            margin: 20px;
        }

        .ok-button {
            background-color: #4CAF50;
            color: white;
            padding: 12px 24px;
            font-size: 16px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }

        .ok-button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="success-container">
    <h1>Đặt hàng thành công!</h1>
    <div class="success-icon">✅</div>
    <div class="success-message">
        Cảm ơn <strong>${customerName}</strong> đã đặt hàng!
    </div>
    <button class="ok-button" onclick="window.location.href='shop'">Về trang chủ</button>
</div>
</body>
</html>
