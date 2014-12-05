@smoke @smokePharmacy

Feature: Verify if customer is able to perform various pharmacy related functionalities including EXPRESS REFILL/REFILL/TRANSFER/NEW RX checkout

#Test Cases Covered
#******************
# <--- Please put the test case id's from ALM --->

#-----------------------------------------------------------------------------------------------------------
@Smoke-Pharmacy-1
Scenario:Pharmacy User places a New Prescription checkout with Pickup-Store pickup Option

Given  "Smoke-Pharmacy-1" Customer starts the scenario:
| InputFileName | SheetName 	| RowId            |
| QC-Smoke      | Pharmacy    	| Smoke-Pharmacy-1 |

Given "Smoke-Pharmacy-1" Customer login to the walgreens.com:
| InputFileName  | SheetName   | RowId     		        |
| CommonData 	   | login     | SmokeNewRxPickupUser   |

And "Smoke-Pharmacy-1" Customer Clears the Shopping Cart

When  "Smoke-Pharmacy-1" Customer adds a New Prescription to the cart: 
| FamilyMemberOption | isNinetyDayCheck | isGenericEquivalentCheck | DoctorFirstName | DoctorLastName | DoctorPhoneNumber | DrugNameAndStrength | DrugQuantity |
| -                  | -                | yes                      | Test Order      |  Test Order    | 8471234567        | Aspirin 10 Mg       | 12           |

Then "Smoke-Pharmacy-1" Customer selects the delivery option and clicks on proceed to checkout button:
|DeliveryOption|ExpectedTargetPage   |
|PickUpInStore |Pickup Location      |

Then "Smoke-Pharmacy-1" Customer changes rx store or selects new store in pickup options page:
| InputFileName | SheetName | RowId          |
| CACData       | RXpickup  | smokeRxStore1	 |

Then "Smoke-Pharmacy-1" Customer selects rx pick up date and time and click on continue

Then "Smoke-Pharmacy-1" Customer makes the selection from Review Order and Submit page:
| SubmitOrder | ChangeStoreOrPickUpTime | ChangeShippingInformation | ChangeShippingMethod | ChangeBillingInformation |
| yes         | -                       | -                         | -                    | -                        | 

Then "Smoke-Pharmacy-1" Customer gets the Order IDs and verifies Order details in the Order Confirmation Page:
| InputFileName         | SheetName    | RowId           |
| CheckoutOrderDetails  | SmokeOrders  |Smoke-Pharmacy-1 |

Then "Smoke-Pharmacy-1" Customer logout from walgreens:
| Source  |
| Global  |

Then "Smoke-Pharmacy-1" Customer finished testing the scenario:
| InputFileName | SheetName 	| RowId            |
| QC-Smoke      | Pharmacy    | Smoke-Pharmacy-1 |

#-----------------------------------------------------------------------------------------------------------
@Smoke-Pharmacy-2
Scenario:Pharmacy User places a New Prescription checkout with ShipToCustomer  Option

Given  "Smoke-Pharmacy-2" Customer starts the scenario:
| InputFileName | SheetName 	| RowId            |
| QC-Smoke      | Pharmacy    | Smoke-Pharmacy-2 |

Given "Smoke-Pharmacy-2" Customer login to the walgreens.com:
| InputFileName  | SheetName | RowId          	    |
| CommonData 	 | login     | SmokeNewRxShipUser   |

And "Smoke-Pharmacy-2" Customer Clears the Shopping Cart

When  "Smoke-Pharmacy-2" Customer adds a New Prescription to the cart: 
| FamilyMemberOption | isNinetyDayCheck | isGenericEquivalentCheck | DoctorFirstName | DoctorLastName | DoctorPhoneNumber | DrugNameAndStrength | DrugQuantity |
| -                  | -                | yes                      | Test Order      |  Test Order    | 8471234567        | Aspirin 10 Mg       | 12           |

Then "Smoke-Pharmacy-2" Customer selects the delivery option and clicks on proceed to checkout button:
|DeliveryOption|ExpectedTargetPage   |
|ShipToYou     |Shipping Information |

Then "Smoke-Pharmacy-2" Customer selects a shipping option from the shipping information page and clicks on continue:
| ShippingAddressType | SaveShippingAddress | VerifyYourCounty | FirstName | LastName  | StreetAddress  | City      | State | ZipCode | EmailAddress  | PhoneNumber  |
| homeaddress    	  | -                   | -                | Test Order| Test Order| 104 wilmot Road| Deerfield | -     | -       | -             | -            |

Then "Smoke-Pharmacy-2" Customer selects a shipping speed from shipping method page and clicks on continue:
| RxShippingSpeed | DailyLivingShippingSpeed | ContactLensShippingSpeed   |
| standard        |           -              |       -	 	              |

Then "Smoke-Pharmacy-2" Customer selects a payment option and clicks on continue button on the payment Information page:
| PaymentOption | BillingAddress | SameAsShippingAddress | SaveBillingAddress | CCInputFileName | CCSheetName | CCRowId |
| CreditCard    | -              | -                     | -                  | CACData         | CreditCard  | cc1     |


Then "Smoke-Pharmacy-2" Customer makes the selection from Review Order and Submit page:
| SubmitOrder | ChangeStoreOrPickUpTime | ChangeShippingInformation | ChangeShippingMethod | ChangeBillingInformation |
| yes         | -                       | -                         | -                    | -                        | 

Then "Smoke-Pharmacy-2" Customer gets the Order IDs and verifies Order details in the Order Confirmation Page:
| InputFileName         | SheetName    | RowId           |
| CheckoutOrderDetails  | SmokeOrders  |Smoke-Pharmacy-2 |

Then "Smoke-Pharmacy-2" Customer logout from walgreens:
| Source  |
| Global  |

Then "Smoke-Pharmacy-2" Customer finished testing the scenario:
| InputFileName | SheetName 	| RowId            |
| QC-Smoke      | Pharmacy    | Smoke-Pharmacy-2 |

#-----------------------------------------------------------------------------------------------------------
@Smoke-Pharmacy-3
Scenario:Pharmacy User places a Refill Prescription checkout with Pickup-Store pickup Option

Given  "Smoke-Pharmacy-3" Customer starts the scenario:
| InputFileName | SheetName 	| RowId            |
| QC-Smoke      | Pharmacy   	| Smoke-Pharmacy-3 |

Given "Smoke-Pharmacy-3" Customer login to the walgreens.com:
| InputFileName  | SheetName 	 | RowId     	          |
|  CommonData    | login		 | SmokeRefillPickupUser  |

And "Smoke-Pharmacy-3" Customer Clears the Shopping Cart

When  "Smoke-Pharmacy-3" Customer adds prescriptions to cart by type:
| InputFileName | SheetName | RowId     |
| CACData       | RefillRx  | smokeRx1  |

Then "Smoke-Pharmacy-3" Customer selects the delivery option and clicks on proceed to checkout button:
|DeliveryOption|ExpectedTargetPage   |
|PickUpInStore |Pickup Location      |

Then "Smoke-Pharmacy-3" Customer changes rx store or selects new store in pickup options page:
| InputFileName | SheetName | RowId          |
| CACData       | RXpickup  | smokeRxStore1	 |

Then "Smoke-Pharmacy-3" Customer selects rx pick up date and time and click on continue

Then "Smoke-Pharmacy-3" Customer makes the selection from Review Order and Submit page:
| SubmitOrder | ChangeStoreOrPickUpTime | ChangeShippingInformation | ChangeShippingMethod | ChangeBillingInformation |
| yes         | -                       | -                         | -                    | -                        | 

Then "Smoke-Pharmacy-3" Customer gets the Order IDs and verifies Order details in the Order Confirmation Page:
| InputFileName         | SheetName    | RowId           |
| CheckoutOrderDetails  | SmokeOrders  |Smoke-Pharmacy-3 |

Then "Smoke-Pharmacy-3" Customer logout from walgreens:
| Source  |
| Global  |

Then "Smoke-Pharmacy-3" Customer runs the Required Query from the DB:
| InputFileName | SheetName 	| RowId            |
| DataBase      | Query         | DBSelectQuery4   |

Then "Smoke-Pharmacy-3" Customer finished testing the scenario:
| InputFileName | SheetName 	| RowId            |
| QC-Smoke      | Pharmacy      | Smoke-Pharmacy-3 |

#-----------------------------------------------------------------------------------------------------------
@Smoke-Pharmacy-4
Scenario:Pharmacy User places a Refill Prescription checkout with ShipToCustomer  Option

Given  "Smoke-Pharmacy-4" Customer starts the scenario:
| InputFileName | SheetName 	| RowId            |
| QC-Smoke      | Pharmacy      | Smoke-Pharmacy-4 |

Given "Smoke-Pharmacy-4" Customer login to the walgreens.com:
| InputFileName  | SheetName	 | RowId     	        |
|  CommonData    | login		 | SmokeRefillShipUser  |

And "Smoke-Pharmacy-4" Customer Clears the Shopping Cart

When  "Smoke-Pharmacy-4" Customer adds prescriptions to cart by type:
| InputFileName | SheetName | RowId     |
| CACData       | RefillRx  | smokeRx1  |

Then "Smoke-Pharmacy-4" Customer selects the delivery option and clicks on proceed to checkout button:
|DeliveryOption|ExpectedTargetPage   |
|ShipToYou     |Shipping Information |

Then "Smoke-Pharmacy-4" Customer selects a shipping option from the shipping information page and clicks on continue:
| ShippingAddressType | SaveShippingAddress | VerifyYourCounty | FirstName | LastName  | StreetAddress  | City      | State | ZipCode | EmailAddress  | PhoneNumber  |
| homeaddress    	  | -                   | -                | Test Order| Test Order| 104 wilmot Road| Deerfield | -     | -       | -             | -            |

Then "Smoke-Pharmacy-4" Customer selects a shipping speed from shipping method page and clicks on continue:
| RxShippingSpeed | DailyLivingShippingSpeed | ContactLensShippingSpeed |
| standard        |           -              |       -	 	              |

Then "Smoke-Pharmacy-4" Customer selects a payment option and clicks on continue button on the payment Information page:
| PaymentOption | BillingAddress | SameAsShippingAddress | SaveBillingAddress | CCInputFileName | CCSheetName | CCRowId |
| NewCreditCard | -              | -                     | -                  | CACData         | CreditCard  | cc1     |


Then "Smoke-Pharmacy-4" Customer makes the selection from Review Order and Submit page:
| SubmitOrder | ChangeStoreOrPickUpTime | ChangeShippingInformation | ChangeShippingMethod | ChangeBillingInformation |
| yes         | -                       | -                         | -                    | -                        | 

Then "Smoke-Pharmacy-4" Customer gets the Order IDs and verifies Order details in the Order Confirmation Page:
| InputFileName         | SheetName    | RowId           |
| CheckoutOrderDetails  | SmokeOrders  |Smoke-Pharmacy-4 |

Then "Smoke-Pharmacy-4" Customer logout from walgreens:
| Source  |
| Global  |

Then "Smoke-Pharmacy-4" Customer finished testing the scenario:
| InputFileName | SheetName 	| RowId            |
| QC-Smoke      | Pharmacy    |Smoke-Pharmacy-4  |

#-----------------------------------------------------------------------------------------------------------
@Smoke-Pharmacy-5
Scenario:Authenticated Pharmacy User places a Transfer Prescription Order with PickupStore  Option

Given  "Smoke-Pharmacy-5" Customer starts the scenario:
| InputFileName | SheetName 	| RowId            |
| QC-Smoke      | Pharmacy      | Smoke-Pharmacy-5 |

Given "Smoke-Pharmacy-5" Customer login to the walgreens.com:
| InputFileName  | SheetName 	 | RowId             |
|  CommonData    | login		 | SmokeTransRxPickupUser  |

And "Smoke-Pharmacy-5" Customer Clears the Shopping Cart

When "Smoke-Pharmacy-5" Customer adds a Transfer Prescription to the cart:
| FamilyMemberOption | isNinetyDay  | PharmacyName | PharmacyPhone | PrescriptionName    | PrescriptionNumber |
| -                  | no           | Test Order   | 123-323-3234  | CVS Transfer Rx     | 123654             |

Then "Smoke-Pharmacy-5" Customer selects the delivery option and clicks on proceed to checkout button:
|DeliveryOption|ExpectedTargetPage   |
|PickUpInStore |Pickup Location      |

Then "Smoke-Pharmacy-5" Customer changes rx store or selects new store in pickup options page:
| InputFileName | SheetName | RowId          |
| CACData       | RXpickup  | smokeRxStore1	 |

Then "Smoke-Pharmacy-5" Customer selects rx pick up date and time and click on continue

Then "Smoke-Pharmacy-5" Customer makes the selection from Review Order and Submit page:
| SubmitOrder | ChangeStoreOrPickUpTime | ChangeShippingInformation | ChangeShippingMethod | ChangeBillingInformation |
| yes         | -                       | -                         | -                    | -                        | 

Then "Smoke-Pharmacy-5" Customer gets the Order IDs and verifies Order details in the Order Confirmation Page:
| InputFileName         | SheetName    | RowId           |
| CheckoutOrderDetails  | SmokeOrders  |Smoke-Pharmacy-5 |

Then "Smoke-Pharmacy-5" Customer logout from walgreens:
| Source  |
| Global  |

Then "Smoke-Pharmacy-5" Customer finished testing the scenario:
| InputFileName | SheetName 	| RowId            |
| QC-Smoke      | Pharmacy      |Smoke-Pharmacy-5  |

#-----------------------------------------------------------------------------------------------------------
@Smoke-Pharmacy-6
Scenario:Authenticated Pharmacy User places a Transfer Prescription Order with Ship to Customer  Option

Given  "Smoke-Pharmacy-6" Customer starts the scenario:
| InputFileName | SheetName 	| RowId            |
| QC-Smoke      | Pharmacy      | Smoke-Pharmacy-6 |

Given "Smoke-Pharmacy-6" Customer login to the walgreens.com:
| InputFileName  | SheetName | RowId        	     |
|  CommonData    | login	 | SmokeTransRxShipUser  |

And "Smoke-Pharmacy-6" Customer Clears the Shopping Cart

When "Smoke-Pharmacy-6" Customer adds a Transfer Prescription to the cart:
| FamilyMemberOption | isNinetyDay  | PharmacyName | PharmacyPhone | PrescriptionName    | PrescriptionNumber |
| -                  | no           | Test Order   | 123-323-3234  | CVS Transfer Rx     | 123654             |

Then "Smoke-Pharmacy-6" Customer selects the delivery option and clicks on proceed to checkout button:
|DeliveryOption|ExpectedTargetPage   |
|ShipToYou     |Shipping Information |

Then "Smoke-Pharmacy-6" Customer selects a shipping option from the shipping information page and clicks on continue:
| ShippingAddressType | SaveShippingAddress | VerifyYourCounty | FirstName | LastName  | StreetAddress  | City      | State | ZipCode | EmailAddress  | PhoneNumber  |
| homeaddress    	    | -                   | -                | Test Order| Test Order| 104 wilmot Road| Deerfield | -     | -       | -             | -            |

Then "Smoke-Pharmacy-6" Customer selects a shipping speed from shipping method page and clicks on continue:
| RxShippingSpeed | DailyLivingShippingSpeed | ContactLensShippingSpeed   |
| standard        |           -              |       -	 	              |

Then "Smoke-Pharmacy-6" Customer selects a payment option and clicks on continue button on the payment Information page:
| PaymentOption | BillingAddress | SameAsShippingAddress | SaveBillingAddress | CCInputFileName | CCSheetName | CCRowId |
| NewCreditCard | -              | -                     | -                  | CACData         | CreditCard  | cc1     |

Then "Smoke-Pharmacy-6" Customer makes the selection from Review Order and Submit page:
| SubmitOrder | ChangeStoreOrPickUpTime | ChangeShippingInformation | ChangeShippingMethod | ChangeBillingInformation |
| yes         | -                       | -                         | -                    | -                        | 

Then "Smoke-Pharmacy-6" Customer gets the Order IDs and verifies Order details in the Order Confirmation Page:
| InputFileName         | SheetName    | RowId           |
| CheckoutOrderDetails  | SmokeOrders  |Smoke-Pharmacy-6 |

Then "Smoke-Pharmacy-6" Customer logout from walgreens:
| Source  |
| Global  |

Then "Smoke-Pharmacy-6" Customer finished testing the scenario:
| InputFileName | SheetName 	| RowId            |
| QC-Smoke      | Pharmacy      |Smoke-Pharmacy-6  |

# ---> THIS COVERS BOTH SCEARIO 11 & 12 OF THE PHARMACY SMOKE TEST CASE LIST --->
#-----------------------------------------------------------------------------------------------------------
@Smoke-Pharmacy-7
Scenario:Authenticated Verify whether loyalty user has Balance rewards button enabled, any the link navgation from balance rewards settings to personal info and vice versa

Given  "Smoke-Pharmacy-7" Customer starts the scenario:
| InputFileName | SheetName 	| RowId            |
| QC-Smoke      | Pharmacy      | Smoke-Pharmacy-7 |

Given "Smoke-Pharmacy-7" Customer login to the walgreens.com:
| InputFileName | SheetName  | RowId          	  |
| CommonData    | login		 | SmokeLoyaltyUser   |

And "Smoke-Pharmacy-7" Customer clicks on an object and validates the expected results:
| InputFileName  | SheetName 		  | RowId      	    		 |
| HomepageLinks  | YourAccountSection | Balance Rewards          |
| HomepageLinks  | YourAccountSection | Rewards Settings         |
| HomepageLinks  | YourAccountSection | BR Account Personal Info |
| HomepageLinks  | YourAccountSection | BR Personal Info         |

Then "Smoke-Pharmacy-7" Customer logout from walgreens:
| Source  |
| Global  |

Then "Smoke-Pharmacy-7" Customer finished testing the scenario:
| InputFileName | SheetName 	| RowId            |
| QC-Smoke      | Pharmacy      |Smoke-Pharmacy-7  |

#-----------------------------------------------------------------------------------------------------------
@Smoke-Pharmacy-8
Scenario: Anonymous user Performs RX express Refill from Pharmacy HomePage. 

Given "Smoke-Pharmacy-8" Customer starts the scenario:
| InputFileName | SheetName        | RowId                |
| QC-Smoke	    | Pharmacy 		   | Smoke-Pharmacy-8 	  |

Then "Smoke-Pharmacy-8" Customer clicks on an object and validates the expected results:
| InputFileName	   	               | SheetName            | RowId 	        	   |
| PharmacyHomePageLinkVerification | Anonymous            | UseExpressRefill       |

Then "Smoke-Pharmacy-8" Customer performs Express Refill from InStore Express Refills Page:
| InputFileName	  | SheetName                 | RowId 	   |
| Pharmacy        | ExpressRefill             | production |

Then "Smoke-Pharmacy-8" Customer changes rx store or selects new store in pickup options page:
| InputFileName | SheetName | RowId            |ExpressRx  |
| CACData       | RXpickup  | SmokeExpressStore| Yes       |

Then "Smoke-Pharmacy-8" Customer clicks on an object and validates the expected results:
| InputFileName 				   | SheetName 			  | RowId            	   |
| PharmacyHomePageLinkVerification | AddAdult             | SubmitRx       		   |

Then "Smoke-Pharmacy-8" Customer gets the Order IDs and verifies Order details in the Order Confirmation Page:
| InputFileName         | SheetName    | RowId           |
| CheckoutOrderDetails  | SmokeOrders  |Smoke-Pharmacy-8 |

Then "Smoke-Pharmacy-8" Customer runs the Required Query from the DB:
| InputFileName | SheetName 	| RowId            |
| DataBase      | Query         | DBSelectQuery4   |

Then "Smoke-Pharmacy-8" Customer finished testing the scenario:
| InputFileName | SheetName        | RowId                |
| QC-Smoke	    | Pharmacy 		   | Smoke-Pharmacy-8 	  |
#-----------------------------------------------------------------------------------------------------------
@Smoke-Pharmacy-9
Scenario: Authenticated User Adds an adult to his family prescriptions account. 

Given "Smoke-Pharmacy-9" Customer starts the scenario:
| InputFileName | SheetName        | RowId                |
| QC-Smoke	    | Pharmacy 		   | Smoke-Pharmacy-9 	  |

Given "Smoke-Pharmacy-9" Customer login to the walgreens.com:
| InputFileName | SheetName  | RowId            |
|  CommonData   | login		 | Addadult_Admin   |

Then "Smoke-Pharmacy-9" Customer clicks on an object and validates the expected results:
| InputFileName	   	               | SheetName            | RowId 	        	   |
| PharmacyHomePageLinkVerification | AddAdult             | FamilyPrescription     |

Then "Smoke-Pharmacy-9" Customer adds an adult user from family prescriptions page:
| InputFileName | SheetName 			   | RowId            | UserType    	  |
| Pharmacy      | Smokedata 			   | AdminUser        | RemoveAdult       |

Then "Smoke-Pharmacy-9" Customer logout from walgreens:
| Source  |
| Global  |

Given "Smoke-Pharmacy-9" Customer login to the walgreens.com:
| InputFileName | SheetName  | RowId            |
|  CommonData   | login		 | Addadult_Admin   |

Then "Smoke-Pharmacy-9" Customer clicks on an object and validates the expected results:
| InputFileName	   	               | SheetName            | RowId 	        	   |
| PharmacyHomePageLinkVerification | AddAdult             | FamilyPrescription     |
| PharmacyHomePageLinkVerification | AddAdult             | AddAdult       		   |

Then "Smoke-Pharmacy-9" Customer adds an adult user from family prescriptions page:
| InputFileName | SheetName 			   | RowId            | UserType    |
| Pharmacy      | Smokedata 			   | AdminUser        | Admin       |

Then "Smoke-Pharmacy-9" Customer logout from walgreens:
| Source  |
| Global  |

Given "Smoke-Pharmacy-9" Customer login to the walgreens.com:
| InputFileName | SheetName  | RowId             |
|  CommonData   | login		 | Addadult_Member   |

Then "Smoke-Pharmacy-9" Customer adds an adult user from family prescriptions page:
| InputFileName | SheetName 			   | RowId             | UserType |
| Pharmacy      | Smokedata 			   | MemberUser        | Member   |

Then "Smoke-Pharmacy-9" Customer logout from walgreens:
| Source  |
| Global  |

Given "Smoke-Pharmacy-9" Customer login to the walgreens.com:
| InputFileName | SheetName  | RowId             |
|  CommonData   | login		 | Addadult_Admin    |

# Ater accepting invite 
Then "Smoke-Pharmacy-9" Customer clicks on an object and validates the expected results:
| InputFileName	   	               | SheetName            | RowId 	        	   							   |
| PharmacyHomePageLinkVerification | AddAdult             | FamilyPrescription_AfterAcceptInvite       		   |

Then "Smoke-Pharmacy-9" Customer logout from walgreens:
| Source  |
| Global  |

Then "Smoke-Pharmacy-9" Customer finished testing the scenario:
| InputFileName | SheetName 	                | RowId      	 	            |
| QC-Smoke 	    | Pharmacy       			    | Smoke-Pharmacy-9				|

#-----------------------------------------------------------------------------------------------------------
@Smoke-Pharmacy-10
Scenario: Anonymous user schedules an appointment via Walgreens Appointment scheduler ,reschedules and cancels the appointment

Given  "Smoke-Pharmacy-10" Customer starts the scenario:
| InputFileName | SheetName 	  | RowId         	  |
| QC-Smoke      | Pharmacy		  | Smoke-Pharmacy-10 |

Then "Smoke-Pharmacy-10" Customer Enter Details and verify schedule an Appointment page:
| InputFileName 			| SheetName 			| RowId     				|
| AppointmentScheduler  	| AnonymousUser 		| SchedulerOption3			|

Then "Smoke-Pharmacy-10" Customer Selects Appointment service and Verifies Error Message:
| InputFileName 			| SheetName 			| RowId     				|PageValidation	|
| AppointmentScheduler  	| AnonymousUser 		| SchedulerOption3			|No				|

Then "Smoke-Pharmacy-10" Customer Verifies The Special Messaging Overlay:
| InputFileName 			| SheetName 					| RowId     			|HealthPlan	|Assessment			| 
| AppointmentScheduler  	| AnonymousUser 				| SchedulerOption3 		|Plan 1		|Assessment Type 1	|
| AppointmentScheduler  	| SpecialMessagingOverlay 		| Services  			|-			|					|

Then "Smoke-Pharmacy-10" Customer selects Appointment Date and Time:
| InputFileName 		| SheetName 			| RowId             |
| AppointmentScheduler  |AnonymousUser		    | SchedulerOption3  |

Then "Smoke-Pharmacy-10" Customer fills in Patient Information:
| InputFileName 		| SheetName 			| RowId             |
| AppointmentScheduler  |AnonymousUser  		| SchedulerOption3  |

Then "Smoke-Pharmacy-10" Customer clicks on an object and validates the expected results:
| InputFileName 		    | SheetName 	| RowId	  		         |
| AppointmentScheduler   	| PageNav 		| RescheduleAppointment  |

Then "Smoke-Pharmacy-10" Customer Reschedules an appointment from appointment confirmation page:
| InputFileName 		| SheetName   			| RowId            |SchedulerOption	|
| AppointmentScheduler  |AnonymousUser			| SchedulerOption3 |Reschedule		|

Then "Smoke-Pharmacy-10" Customer verifies the Cancel appointment confirmation page via Appointment confirmation page:
| InputFileName 		 | SheetName 		 	| RowId            |
| AppointmentScheduler   |AnonymousUser  | SchedulerOption3 |

Then "Smoke-Pharmacy-10" Customer finished testing the scenario:
| InputFileName | SheetName    | RowId         	   |
| QC-Smoke 	    | Pharmacy     | Smoke-Pharmacy-10 |

#-----------------------------------------------------------------------------------------------------------
@Smoke-Pharmacy-11
Scenario: Pharmacy User places a Refill Prescription checkout with Pickup-Store pickup Option with out changing store

Given  "Smoke-Pharmacy-11" Customer starts the scenario:
| InputFileName | SheetName 	| RowId            |
| QC-Smoke      | Pharmacy    | Smoke-Pharmacy-11  |

Given "Smoke-Pharmacy-11" Customer login to the walgreens.com:
| InputFileName  | SheetName | RowId           				        |
|  CommonData    | login	 | SmokeRefillPickupUserNoStoreChange   |

And "Smoke-Pharmacy-11" Customer Clears the Shopping Cart

When  "Smoke-Pharmacy-11" Customer adds prescriptions to cart by type:
| InputFileName | SheetName | RowId     |
| CACData       | RefillRx  | smokeRx1  |

Then "Smoke-Pharmacy-11" Customer selects the delivery option and clicks on proceed to checkout button:
|DeliveryOption|ExpectedTargetPage   |
|PickUpInStore |Pickup Location      |

Then "Smoke-Pharmacy-11" Customer selects rx pick up date and time and click on continue

Then "Smoke-Pharmacy-11" Customer makes the selection from Review Order and Submit page:
| SubmitOrder | ChangeStoreOrPickUpTime | ChangeShippingInformation | ChangeShippingMethod | ChangeBillingInformation |
| yes         | -                       | -                         | -                    | -                        | 

Then "Smoke-Pharmacy-11" Customer gets the Order IDs and verifies Order details in the Order Confirmation Page:
| InputFileName         | SheetName    | RowId            |
| CheckoutOrderDetails  | SmokeOrders  |Smoke-Pharmacy-11 |

Then "Smoke-Pharmacy-11" Customer logout from walgreens:
| Source  |
| Global  |

Then "Smoke-Pharmacy-11" Customer finished testing the scenario:
| InputFileName | SheetName 	| RowId             |
| QC-Smoke      | Pharmacy      | Smoke-Pharmacy-11 |
#-----------------------------------------------------------------------------------------------------------
@Smoke-Pharmacy-12
Scenario: Customer places refill rx order by changing store and verifies the creation of new rx with the placed store number

Given  "Smoke-Pharmacy-12" Customer starts the scenario:
| InputFileName | SheetName 	| RowId             |
| QC-Smoke      | Pharmacy      | Smoke-Pharmacy-12 |

Given "Smoke-Pharmacy-12" Customer login to the walgreens.com:
| InputFileName  | SheetName 	 | RowId             |
|  CommonData    | login		 | SmokeNewRxUser	 |

And "Smoke-Pharmacy-12" Customer Clears the Shopping Cart

When  "Smoke-Pharmacy-12" Customer adds prescriptions to cart by type:
| InputFileName | SheetName | RowId  	   |
| CACData       | RefillRx  | smokeNewRx1  |

Then "Smoke-Pharmacy-12" Customer selects the delivery option and clicks on proceed to checkout button:
|DeliveryOption|ExpectedTargetPage   |
|PickUpInStore |Pickup Location      |

Then "Smoke-Pharmacy-12" Customer changes rx store or selects new store in pickup options page:
| InputFileName | SheetName | RowId      	     |
| CACData       | RXpickup  | smokeNewRxStore2	 |

Then "Smoke-Pharmacy-12" Customer selects rx pick up date and time and click on continue

Then "Smoke-Pharmacy-12" Customer makes the selection from Review Order and Submit page:
| SubmitOrder | ChangeStoreOrPickUpTime | ChangeShippingInformation | ChangeShippingMethod | ChangeBillingInformation |NewRefillRx|
| yes         | -                       | -                         | -                    | -                        | yes		  |

Then "Smoke-Pharmacy-12" Customer gets the Order IDs and verifies Order details in the Order Confirmation Page:
| InputFileName         | SheetName    | RowId            |
| CheckoutOrderDetails  | SmokeOrders  |Smoke-Pharmacy-12 |

Then "Smoke-Pharmacy-12" Customer logout from walgreens:
| Source  |
| Global  |

Then "Smoke-Pharmacy-12" Customer runs the Required Query from the DB:
| InputFileName | SheetName 	| RowId            |
| DataBase      | Query         | DBSelectQuery5   |
| DataBase      | Query         | DBSelectQuery6   |

Then "Smoke-Pharmacy-12" Customer finished testing the scenario:
| InputFileName | SheetName 	| RowId             |
| QC-Smoke      | Pharmacy      | Smoke-Pharmacy-12 |

#-----------------------------------------------------------------------------------------------------------