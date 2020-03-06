@Search
Feature: Search for a bus
  Search for a bus on particular route

  Scenario Outline: Basic Bus Search 
    Given user is on redbus homepage
    When user inputs <from> and <to> destination
    #And user inputs "onward" and "return" date
    #And click on find buses
    #Then list of options are displayed
    
    Examples:
    |from		|to					|
    |"Pune"	|"Mumbai"		|
    |"Delhi"|"Haridwar"	|
    
    