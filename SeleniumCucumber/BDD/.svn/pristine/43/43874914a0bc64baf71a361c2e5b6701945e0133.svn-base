@smoke @smokeCheckout

Feature: Verify if customer is able to perform regular SS/DL/CL/CREDIT CARD/BML and PAYPAL checkout

#Test Cases Covered
#******************
# <--- Please put the test case id's from ALM --->

#-----------------------------------------------------------------------------------------------------------
@Smoke-Checkout-1
Scenario:Logged in Customer adds a SS item and CL item to cart and performs checkout with credit card as payment option

Given  "Smoke-Checkout-1" Customer starts the scenario:
| InputFileName | SheetName 	| RowId            |
| QC-Smoke      | Checkout      | Smoke-Checkout-1 |

Given "Smoke-Checkout-1" Customer login to the walgreens.com:
| InputFileName | SheetName | RowId         	  |
| CommonData    | login     | SmokecheckoutCCuser |

And "Smoke-Checkout-1" Customer Clears the Shopping Cart

And "Smoke-Checkout-1" Customer searches for a product using keywords in the search textbox:
| InputFileName | SheetName | RowId    |
| CommonData    | search    | DC1      |

Then "Smoke-Checkout-1" Customer adds Daily Living items to the cart from search.

And "Smoke-Checkout-1" Customer searches for a product using keywords in the search textbox:
| InputFileName | SheetName | RowId |
| CommonData    | search    | cl1   |

Then "Smoke-Checkout-1" Customer adds Contact Lens items to the cart from search:
| Side  | Quantity  | Power | BC | Cylinder | Axis | Addition | Diameter |
| right | 1         | -     | -  | -        | -    | -        | -        |

Then "Smoke-Checkout-1" Customer Goes to the Shopping Cart

Then "Smoke-Checkout-1" Customer clicks on proceed to checkout button in the cart:
| ExpectedTargetPage |
| Doctor Information |

Then "Smoke-Checkout-1" Customer enters required information and selects the required option in Patient Info and Doctor Details Page:
| InputFileName |   SheetName  			  | RowId		       |Continue	| AgeErrorValidationType  |DoctorErrorValidationType |
| CACData       |	CLPatientDoctorInfo | CLSmokeData1   | 	Yes 	  | - 						          |   - 							       |

Then "Smoke-Checkout-1" Customer Selects required Option From Doctor Search Results Page:
| InputFileName | SheetName 		       | RowId          |ContinueToShipping  | NewSearch  | PrePopVerify  | PreviousRowID |ContinueShippingBTN |NewSearchAgain  | ErrorMsgType 	| Select  | BacktoPatientInfoLnk | ExpectedTargetPage   |
| CACData	      | CLPatientDoctorInfo  | CLSmokeData2   |   -	 			         | -		      |   -  		      |  -			      | -	  			         |  -     		    |  -			      |  Yes 	  | 	   -			         | 	ShippingInfo	      |

Then "Smoke-Checkout-1" Customer selects a shipping option from the shipping information page and clicks on continue:
| ShippingAddressType | SaveShippingAddress | VerifyYourCounty | FirstName  | LastName   | StreetAddress   | City      | State | ZipCode | EmailAddress           | PhoneNumber  |
| newshippingaddress  | -                   | -                | Test Order | Test Order | 104 Wilmot Road | Deerfield | 16    | 60015   | taxwaremay22@yahoo.com | 847122345127 |

Then "Smoke-Checkout-1" Customer selects a shipping speed from shipping method page and clicks on continue:
| RxShippingSpeed | DailyLivingShippingSpeed | ContactLensShippingSpeed |
| -               | -                        | standard                 |

Then "Smoke-Checkout-1" Customer selects a payment option and clicks on continue button on the payment Information page:
| PaymentOption    | BillingAddress | SameAsShippingAddress | SaveBillingAddress | CCInputFileName | CCSheetName | CCRowId |GuestPayment |
| CreditCard	   | -              | true                  | -                  | CACData         | CreditCard  | cc1     | -			 |

Then "Smoke-Checkout-1" Customer makes the selection from Review Order and Submit page:
| SubmitOrder | ChangeStoreOrPickUpTime | ChangeShippingInformation | ChangeShippingMethod | ChangeBillingInformation |
| yes         | -                       | -                         | -                    | -                        |

Then "Smoke-Checkout-1" Customer gets the Order IDs and verifies Order details in the Order Confirmation Page:
| InputFileName         | SheetName    | RowId           |
| CheckoutOrderDetails  | SmokeOrders  |Smoke-Checkout-1 |

Then "Smoke-Checkout-1" Customer logout from walgreens:
| Source  |
| Global  |

Then "Smoke-Checkout-1" Customer finished testing the scenario:
| InputFileName | SheetName 	| RowId            |
| QC-Smoke      | Checkout    | Smoke-Checkout-1 |

#-----------------------------------------------------------------------------------------------------------
@Smoke-Checkout-2
Scenario: Logged in User performs PayPal Checkout for a DC item

Given  "Smoke-Checkout-2" Customer starts the scenario:
| InputFileName | SheetName 	| RowId            |
| QC-Smoke      | Checkout    | Smoke-Checkout-2 |

#Given "Smoke-Checkout-2" Customer login to the paypal.com:
#| InputFileName       | SheetName 	  | RowId       |
#| CACData		      | Paypal	      | paypal1	    |

Given "Smoke-Checkout-2" Customer login to the walgreens.com:
| InputFileName | SheetName | RowId    			      |
| CommonData    | login     | SmokecheckoutPaypaluser |

And "Smoke-Checkout-2" Customer Clears the Shopping Cart

And "Smoke-Checkout-2" Customer searches for a product using keywords in the search textbox:
| InputFileName | SheetName | RowId    |
| CommonData    | search    | DC1      |

Then "Smoke-Checkout-2" Customer adds Daily Living items to the cart from search.

Then "Smoke-Checkout-2" Customer Goes to the Shopping Cart

Then "Smoke-Checkout-2" Customer clicks on proceed to checkout button in the cart:
| ExpectedTargetPage       |
| Shipping Information	 |

Then "Smoke-Checkout-2" Customer selects a shipping option from the shipping information page and clicks on continue:
| ShippingAddressType | SaveShippingAddress | VerifyYourCounty | FirstName     | LastName    | StreetAddress   | City      | State | ZipCode  |ZipExtn | EmailAddress          | PhoneNumber  |OffersbyEmail |
| newshippingaddress  | -                   | -                | Testuser      | Checkout    | 1204 WILMOT RD  | DEERFIELD | 16    | 60015    | 5121   |taxwaremay22@yahoo.com | 847122345127 |  No	        |

Then "Smoke-Checkout-2" Customer selects a shipping speed from shipping method page and clicks on continue:
| RxShippingSpeed | DailyLivingShippingSpeed | ContactLensShippingSpeed |
| -      	        | standard		         |     -			        |

Then "Smoke-Checkout-2" Customer selects a payment option and clicks on continue button on the payment Information page:
| PaymentOption    | BillingAddress | SameAsShippingAddress | SaveBillingAddress | PaypalInputFileName | PaypalSheetName | PaypalRowId 	|GuestPayment|
| PayPal	         | -              | -               	    | -                  | CACData        	   | Paypal		  	   | sandbox1     | -			     |

Then "Smoke-Checkout-2" Customer makes the selection from Review Order and Submit page:
| SubmitOrder | ChangeStoreOrPickUpTime | ChangeShippingInformation | ChangeShippingMethod | ChangeBillingInformation |
| yes         | -                       | -                         | -                    | -                        |

Then "Smoke-Checkout-2" Customer gets the Order IDs and verifies Order details in the Order Confirmation Page:
| InputFileName         | SheetName    | RowId           |
| CheckoutOrderDetails  | SmokeOrders  |Smoke-Checkout-2 |

Then "Smoke-Checkout-2" Customer logout from walgreens:
| Source  |
| Global  |

Then "Smoke-Checkout-2" Customer finished testing the scenario:
| InputFileName | SheetName 	| RowId            |
| QC-Smoke      | Checkout      | Smoke-Checkout-2 |

#-----------------------------------------------------------------------------------------------------
@Smoke-Checkout-3
Scenario: Logged in customer Performs BML checkout for a SS item

Given  "Smoke-Checkout-3" Customer starts the scenario:
| InputFileName | SheetName   | RowId            |
| QC-Smoke      | Checkout    | Smoke-Checkout-3 |

Given "Smoke-Checkout-3" Customer login to the walgreens.com:
| InputFileName | SheetName | RowId   			  	 |
| CommonData    | login     | SmokecheckoutBMLuser   |

And "Smoke-Checkout-3" Customer Clears the Shopping Cart

And "Smoke-Checkout-3" Customer searches for a product using keywords in the search textbox:
| InputFileName | SheetName | RowId    |
| CommonData    | search    | DC3      |

Then "Smoke-Checkout-3" Customer adds Daily Living items to the cart from search.

Then "Smoke-Checkout-3" Customer Goes to the Shopping Cart

Then "Smoke-Checkout-3" Customer clicks on proceed to checkout button in the cart:
| ExpectedTargetPage     |
| Shipping Information	 |

Then "Smoke-Checkout-3" Customer selects a shipping option from the shipping information page and clicks on continue:
| ShippingAddressType | SaveShippingAddress | VerifyYourCounty | FirstName     | LastName   | StreetAddress  	| City      		    | State | ZipCode |ZipExtn | EmailAddress        | PhoneNumber  |OffersbyEmail |
| newshippingaddress  | -                   | -                | karen         | merchantk  | 9690 DEERECO RD	| TIMONIUM              | 23    | 21093   |   -	   |karen@merchant.com   | 4439211900   |  No	       |

Then "Smoke-Checkout-3" Customer selects a shipping speed from shipping method page and clicks on continue:
| RxShippingSpeed | DailyLivingShippingSpeed | ContactLensShippingSpeed |
| -      	      | standard		         |     -			        |

Then "Smoke-Checkout-3" Customer selects a payment option and clicks on continue button on the payment Information page:
| PaymentOption  | BillingAddress      | SameAsShippingAddress | SaveBillingAddress | CustomerInputFileName | CustomerSheetName    | CustomerRowId  |GuestPayment |
| BillMeLater    | NewBillingAddress   | -                     | Yes                | CACData               | CustomerBilling      | BML2           | -			      |

Then "Smoke-Checkout-3" Customer makes the selection from Review Order and Submit page:
| SubmitOrder | ChangeStoreOrPickUpTime | ChangeShippingInformation | ChangeShippingMethod | ChangeBillingInformation |
| yes         | -                       | -                         | -                    | -                        |

Then "Smoke-Checkout-3" Customer gets the Order IDs and verifies Order details in the Order Confirmation Page:
| InputFileName         | SheetName    | RowId           |
| CheckoutOrderDetails  | SmokeOrders  |Smoke-Checkout-3 |

Then "Smoke-Checkout-3" Customer logout from walgreens:
|Source  |
| Global |

Then "Smoke-Checkout-3" Customer finished testing the scenario:
| InputFileName | SheetName 	| RowId            |
| QC-Smoke      | Checkout    | Smoke-Checkout-3 |

#------------------------------------------------------------------------------------------------------------------------------------------------------------
@Smoke-Checkout-4
Scenario: Guest User performs Express Checkout for a DC Item 

Given  "Smoke-Checkout-4" Customer starts the scenario:
| InputFileName | SheetName 	| RowId            |
| QC-Smoke      | Checkout      | Smoke-Checkout-4 |

And "Smoke-Checkout-4" Customer Clears the Shopping Cart

Then "Smoke-Checkout-4" Customer clicks on an object and validates the expected results:
| InputFileName	   	   | SheetName        | RowId 	 	              |
| CheckoutOrderDetails | PageNavigations  | Tier1NavigationBeauty   |
| CheckoutOrderDetails | PageNavigations  | Tier2NavigationHaircare |
| CheckoutOrderDetails | PageNavigations  | Tier3NavigationShampoo  |

Then "Smoke-Checkout-4" Customer adds Daily Living items to the cart from search.

Then "Smoke-Checkout-4" Customer Goes to the Shopping Cart

Then "Smoke-Checkout-4" Customer clicks on proceed to checkout button in the cart:
| ExpectedTargetPage   |
| SigninorRegister		 |

Then "Smoke-Checkout-4" Customer selects either Login to walgreens or Register a new account:
| UserisonSigninPage | Signin | Register |GuestCheckout|
| -                  | -      | -        |  Yes	       |

Then "Smoke-Checkout-4" Customer selects a shipping option from the shipping information page and clicks on continue:
| ShippingAddressType | SaveShippingAddress | VerifyYourCounty | FirstName    | LastName    | StreetAddress  | City      | State | ZipCode |ZipExtn | EmailAddress             | PhoneNumber  |OffersbyEmail|
| newshippingaddress  | -                   | -                | Test Order   | Test Order  | 104 WILMOT RD  | DEERFIELD | 16    | 60015   |   5121 | pobreakout_001@yahoo.com | 8471234567   |  Yes	       |

Then "Smoke-Checkout-4" Customer selects a shipping speed from shipping method page and clicks on continue:
| RxShippingSpeed | DailyLivingShippingSpeed | ContactLensShippingSpeed |
| -      	        | standard		             |     -			              |

Then "Smoke-Checkout-4" Customer selects a payment option and clicks on continue button on the payment Information page:
| PaymentOption | BillingAddress | SameAsShippingAddress | SaveBillingAddress | CCInputFileName | CCSheetName | CCRowId |GuestPayment|
| -		          | -              | true                  | -                  | CACData         | CreditCard  | cc1     | CreditCard |

# Customer submits the order
Then "Smoke-Checkout-4" Customer makes the selection from Review Order and Submit page:
| SubmitOrder | ChangeStoreOrPickUpTime | ChangeShippingInformation | ChangeShippingMethod | ChangeBillingInformation |
| yes         | -                       | -                         | -                    | -                        |

Then "Smoke-Checkout-4" Customer gets the Order IDs and verifies Order details in the Order Confirmation Page:
| InputFileName         | SheetName    | RowId           |
| CheckoutOrderDetails  | SmokeOrders  |Smoke-Checkout-4 |

Then "Smoke-Checkout-4" Customer finished testing the scenario:
| InputFileName | SheetName 	| RowId            |
| QC-Smoke      | Checkout    | Smoke-Checkout-4 |

#------------------------------------------------------------------------------------------------------------------------------------------------

# <--- Smoke test case Shopping cart Plus Create TC-05 --->
# <--- THE PRODUCT ADDED HAS TO BE A LOYALTY PRODUCT ---> prod360963
#-----------------------------------------------------------------------------------------------------------
@Smoke-Checkout-5
Scenario: To verify whether confirmation modal is displayed when user creates the walgreens account with loyalty via Join now button in shopping cart page

Given "Smoke-Checkout-5" Customer starts the scenario:
| InputFileName | SheetName    | RowId            |
| QC-Smoke	    | Checkout     | Smoke-Checkout-5 |

And "Smoke-Checkout-5" Customer Clears the Shopping Cart

And "Smoke-Checkout-5" Customer searches for a product using keywords in the search textbox:
| InputFileName | SheetName | RowId    	   	      |
| CommonData    | search    | loyaltybonuspoints1 |

Then "Smoke-Checkout-5" Customer adds Daily Living items to the cart from search.

Then "Smoke-Checkout-5" Customer Goes to the Shopping Cart

And "Smoke-Checkout-5" Customer clicks on an object and validates the expected results:
| InputFileName | SheetName | RowId      	      |
| HomepageLinks | CartPage  | CartPageJoinSave    |
| HomepageLinks | CartPage  | SignInPageRegister  |

Then "Smoke-Checkout-5" Customer enters the information in the fields on Join Balance Rewards page and clicks on Submit:
| BalanceRewardsOrPhoneNumber | BalanceRewardsOption | ZipCode | Phone  | DOB-Month | DOB-Day | DOB-Year | GuestUser | InputFileName | SheetName   | RowId 			  |saveUserNameAndPasswordInDataSheet|
| -                           | CreateNew		     | 60015   | Random | JAN       | Random  | Random   | Yes       |CommonData     | personalInfo| LoyaltyLightUser2|Yes								|

Then "Smoke-Checkout-5" Customer verifies the loyalty registeration by observing the Congratulations message.
|MembershipType |
|  New			|

Then "Smoke-Checkout-5" Customer close the Currently Opened Overlay to go back to the Main Page:
|InputFileName | SheetName  | RowId        | 
|CommonData    | TargetPage | Shopping Cart|

Then "Smoke-Checkout-5" Customer logout from walgreens:
| Source |
| Global |

Then "Smoke-Checkout-5" Customer finished testing the scenario:
| InputFileName | SheetName    | RowId                |
| QC-Smoke	    | Checkout     | Smoke-Checkout-5     |
#-----------------------------------------------------------------------------------------------------------