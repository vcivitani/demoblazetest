Feature: Checks for DEMO ONLINE SHOP, product navigation

Online shop URL: https://demoblaze.com

	Background:
   Given User is on the demoblaze online shop page

  Scenario: User add Sony vaio i5 product to cart
   When User navigates through "Laptops" 
   And adds "Sony vaio i5" to cart
   Then product "Sony vaio i5" is on the cart
   
  Scenario:  User removes Dell i7 8gb from cart having two products in cart
   When User navigates through "Laptops" 
   And adds "Sony vaio i5" to cart
   And User navigates through "Laptops"
   And adds "Dell i7 8gb" to cart
   Then product "Dell i7 8gb" can be removed from cart

   Scenario: User proceed with order
   When User navigates through "Laptops" 
   And adds "Sony vaio i5" to cart
   Then user can proceed with purchase 