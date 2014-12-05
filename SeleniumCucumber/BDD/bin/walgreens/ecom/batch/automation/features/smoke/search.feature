@smoke @search

Feature:Searching the product

@Search-Search-1
Scenario: To verify whether confirmation page is displayed when user creates the walgreens account with loyalty via register link at the header

Given "Search-Search-1" Customer starts the scenario:
| InputFileName | SheetName    | RowId            |
| QC-Search	    | Search 	   | Search-Search-1   |


Then "Search-Search-1" Customer Search for a product using product ID:
| InputFileName | SheetName    | RowId            |
| CommonData    | search 	   | newproduct       |

Then "Search-Search-1" Customer finished testing the scenario:
| InputFileName | SheetName    | RowId                |
| QC-Search	    | Search | Search-Search-1 |