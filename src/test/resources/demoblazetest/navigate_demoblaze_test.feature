Feature: Checks for DEMO ONLINE SHOP, product navigation

  Scenario Outline: User can navigate through product categories
   Given User is on the demoblaze online shop page
   When User navigates through "<category>"
   Then the page shows the results of "<category>"
   
  Examples:
    | category   |
    | Phones     |
    | Laptops    |
    | Monitors   |
