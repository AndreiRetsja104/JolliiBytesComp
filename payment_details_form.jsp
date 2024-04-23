<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment Details Form</title>
    <link rel="stylesheet" href="css/PaymentDetailsForm.css" />
</head>
<body>
    <div class="container">
        <h2>Enter Payment Details</h2>
        <form action="PaymentServlet" method="post">
            <label for="card_number">Card Number:</label>
            <input type="text" id="card_number" name="card_number" required>
            
            <label for="card_holder">Card Holder Name:</label>
            <input type="text" id="card_holder" name="card_holder" required>
            
            <label for="expiration_date">Expiration Date (MM/YYYY):</label>
            <input type="text" id="expiration_date" name="expiration_date" placeholder="MM/YYYY" required>
            
            <label for="cvv">CVV:</label>
            <input type="text" id="cvv" name="cvv" required>
            
            <label for="total_price">Total Price (Euro):</label>
			<input type="text" id="total_price" name="total_price" value=" ${totalPrice}" readonly>
            <input type="submit" value="Submit Payment">
        </form>
    </div>
</body>
</html>