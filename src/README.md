# Taco App
## What does our taco app do?
- registers + creates users
- logs users in
  - creates a session for logged in user 
- creates taco
-add taco to order list of tacos
- takes ordering info

## what do we want the taco app to do? (future functionality)
- see most recent taco creations (not your own)
- see contents of current order/cart
- see status of order (placed, cooking, delivering, arrived)

## what is the data in our taco app?
- ingredients
- users
 - taco (made of ingredients)
-orders (made of taco & users)

## endpoints (the controllers)
- /design
  - POST: takes validated Taco object
    - saves Taco object to SQL table TACO
    - adds Taco object to the current order  
- /design/recent      
  - GET: returns a list of most recent taco designs
- /orders/current
  - GET: Takes orderId, maybe also userId as params & returns the current Order and 
- /orders/recent
  - POST: takes a valid Order object
    -save that order to our TACO_ORDERS table
- /register
  - POST: takes a valid User object and adds it to the USER table
  ? possibly start a session