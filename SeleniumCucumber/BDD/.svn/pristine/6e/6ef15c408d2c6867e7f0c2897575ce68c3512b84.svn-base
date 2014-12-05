@smoke @smokeCSC

Feature: Verify if customer is able to perform various types of basic functionalities in CSC Application
#-----------------------------------------------------------------------------------------------------------
@Smoke-CSC-1
Scenario: To verify whether CSC User is able to Manual Match

Given "Smoke-CSC-1" Customer starts the scenario:
| InputFileName | SheetName    | RowId                |
| QC-Smoke	    | CSC          | Smoke-CSC-1		  |

And "Smoke-CSC-1" Customer clicks on an object and validates the expected results:
| InputFileName  		   | SheetName       | RowId               |
| PharmacyLinkVerification | UnAuthenticated | Pharmacy 		   |

And "Smoke-CSC-1" Customer performs Pharmacy Registration:
| InputFileName | SheetName		 | RowId 	 	| SaveUserPersonalInfo |
| Registration  | RxRegistration | ManualMatch  | Yes			       |

Given "Smoke-CSC-1" Customer login to the csc application:
| InputFileName | SheetName | RowId    |
| smokeCSC       | login    | cscuser1 |

Then "Smoke-CSC-1" Customer selects the CSC Window

Then "Smoke-CSC-1" Customer Manually Matches a Dotcom Registered User:
| InputFileName   | SheetName   | RowId               |
| CSCData         | login       | ManualMatch 		  |

Then "Smoke-CSC-1" Customer finished testing the scenario:
| InputFileName | SheetName    | RowId                |
| QC-Smoke	    | CSC          | Smoke-CSC-1		  |

#-----------------------------------------------------------------------------------------------------------
@Smoke-CSC-2
Scenario: To verify whether CSC User is able to Place a CL order.

Given "Smoke-CSC-2" Customer starts the scenario:
| InputFileName | SheetName    | RowId                |
| QC-Smoke	    | CSC          | Smoke-CSC-2		  |

Given "Smoke-CSC-2" Customer login to the csc application:
| InputFileName | SheetName | RowId    |
| smokeCSC       | login     | cscuser1 |

Then "Smoke-CSC-2" Customer selects the CSC Window

Then "Smoke-CSC-2" Customer Searches for a User:
| InputFileName | SheetName 		| RowId       |
| smokeCSC       | CustomerSearch    | SmokeUser1 |

And "Smoke-CSC-2" Customer adds the product to cart from Add product by ID page:
| InputFileName | SheetName 		| RowId   	 |ContinueToShipping |
| smokeCSC       | ProductSearch     | CLProduct1 |Yes				 |

Then "Smoke-CSC-2" Customer selects the shipping address:
| InputFileName | SheetName 		| RowId   		   |
| smokeCSC       | ShippingAddress   | ShippingAddress2 |

Then "Smoke-CSC-2" Customer selects the shipping method:
| ShippingMethod |ContinueToBilling |SelectPrescriber |
| standard       | No 				|Yes			  |

And "Smoke-CSC-2" Customer selects patient and prescriber information:
| InputFileName | SheetName 			| RowId   			   |
| smokeCSC       | PatientDoctorInfo     | PatientDoctorInfo2   |

Then "Smoke-CSC-2" Customer adds the payment information:
| InputFileName | SheetName 		| RowId   	   |
| smokeCSC       | PaymentInfo       | PaymentInfo1 |

Then "Smoke-CSC-2" Customer submits the order and get the order number:
| InputFileName | SheetName 		| RowId        |
| smokeCSC      | OrderDetails     | Smoke-CSC-2   |

And "Smoke-CSC-2" Customer either accpets or rejects the Contact Lens Order from CSC:
| InputFileName | SheetName 		| RowId   	  |OrderNumber |
| smokeCSC       | OrderDetails      | Smoke-CSC-2 |Yes		   |

Then "Smoke-CSC-2" Customer finished testing the scenario:
| InputFileName | SheetName    | RowId                |
| QC-Smoke	    | CSC          | Smoke-CSC-2		  |

#-----------------------------------------------------------------------------------------------------------
@Smoke-CSC-3
Scenario: To verify whether CSC User is able to register a new Loyalty user.

Given "Smoke-CSC-3" Customer starts the scenario:
| InputFileName | SheetName    | RowId                |
| QC-Smoke	    | CSC          | Smoke-CSC-3 		  |

Given "Smoke-CSC-3" Customer login to the csc application:
| InputFileName | SheetName | RowId    |
| smokeCSC      | login     | cscuser1 |

Then "Smoke-CSC-3" Customer selects the CSC Window

Then "Smoke-CSC-3" Customer creates user in CSC:
| InputFileName | SheetName    | RowId            |
| smokeCSC      | CreateUser   | CreateCustomer1  |

Then "Smoke-CSC-3" Customer finished testing the scenario:
| InputFileName | SheetName    | RowId                |
| QC-Smoke	    | CSC          | Smoke-CSC-3		  |

#------------------------------------------------------------------------------------------------------------

@Smoke-CSC-4
Scenario: To verify whether CSC User is able to register a new user.

Given "Smoke-CSC-4" Customer starts the scenario:
| InputFileName | SheetName    | RowId                |
| QC-Smoke	    | CSC          | Smoke-CSC-4 		  |

Given "Smoke-CSC-4" Customer login to the csc application:
| InputFileName | SheetName | RowId    |
| smokeCSC      | login     | cscuser1 |

Then "Smoke-CSC-4" Customer selects the CSC Window

Then "Smoke-CSC-4" Customer creates user in CSC:
| InputFileName | SheetName    | RowId            |ValidateUserInfo|
| smokeCSC      | CreateUser   | CreateCustomer2  |Yes             |

Then "Smoke-CSC-4" Customer finished testing the scenario:
| InputFileName | SheetName    | RowId                |
| QC-Smoke	    | CSC          | Smoke-CSC-4		  |

#------------------------------------------------------------------------------------------------------------
@Smoke-CSC-5
Scenario: To verify whether CSC User is able to Place a DC order, search the placed order & cancel order.

Given "Smoke-CSC-5" Customer starts the scenario:
| InputFileName | SheetName    | RowId                |
| QC-Smoke	    | CSC          | Smoke-CSC-5		  |

Given "Smoke-CSC-5" Customer login to the csc application:
| InputFileName | SheetName | RowId    |
| smokeCSC       | login    | cscuser1 |

Then "Smoke-CSC-5" Customer selects the CSC Window

Then "Smoke-CSC-5" Customer Searches for a User:
| InputFileName | SheetName 		| RowId       |
| smokeCSC       | CustomerSearch   | SmokeUser1  |

And "Smoke-CSC-5" Customer adds the product to cart from Add product by ID page:
| InputFileName | SheetName 		| RowId   	 |ContinueToShipping |
| smokeCSC       | ProductSearch    | DCProduct1 |Yes				 |

Then "Smoke-CSC-5" Customer selects the shipping address:
| InputFileName | SheetName 		| RowId   		   |
| smokeCSC       | ShippingAddress   | ShippingAddress2 |

Then "Smoke-CSC-5" Customer selects the shipping method:
| ShippingMethod |ContinueToBilling |SelectPrescriber |
| standard       | Yes 				|No			      |

Then "Smoke-CSC-5" Customer adds the payment information:
| InputFileName | SheetName 		| RowId   	   |
| smokeCSC       | PaymentInfo       | PaymentInfo1 |

Then "Smoke-CSC-5" Customer submits the order and get the order number:
| InputFileName | SheetName 		| RowId        |
| smokeCSC      | OrderDetails      | Smoke-CSC-5  |

#Searches Order By UserName
Then "Smoke-CSC-5" Customer Searches for an Order in CSC and selects the Order:
| InputFileName | SheetName    | RowId                |
| smokeCSC      | OrderSearch  | OrderSearch1		  |

#Searches Order by OrderID
Then "Smoke-CSC-5" Customer Searches for an Order in CSC and selects the Order:
| InputFileName | SheetName    | RowId          |CancelOrder|
| smokeCSC      | OrderDetails | Smoke-CSC-5	|Yes        |

Then "Smoke-CSC-5" Customer finished testing the scenario:
| InputFileName | SheetName    | RowId                |
| QC-Smoke	    | CSC          | Smoke-CSC-5		  |
#------------------------------------------------------------------------------------------------------------
