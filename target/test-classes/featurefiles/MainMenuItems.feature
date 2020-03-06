@Menu
Feature: Main menu items of redbus app
  I want to validate all the top level menu items of redbus homepage

  Scenario Outline: Validate Bus tickets menu item
    Given user is on redbus homepage
    When user clicks on <menu>
    Then validate the url of the page <url>

    Examples: 
      | menu          | url                                                                                                          |
      | "BUS TICKETS" | "https://www.redbus.in/bus-tickets"                                                                         |
      | "PILGRIMAGES" | "https://www.redbus.in/pilgrimages/"                                                                         |
      | "BUS HIRE"    | "https://www.redbus.in/bushire/?utm_source=website&utm_medium=referral&utm_campaign=Homepage_bushire_banner" |
