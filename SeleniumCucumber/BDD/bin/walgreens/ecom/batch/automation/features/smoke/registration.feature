@smoke @smokeRegistration

Feature: Verify if customer is able to perform various types of loyalty and non-loyalty registration

#Test Cases Covered
#******************
# <--- Smoke test case Light registration Plus Create TC-01 --->
#-----------------------------------------------------------------------------------------------------------
@Smoke-Registration-1
Scenario: To verify whether confirmation page is displayed when user creates the walgreens account with loyalty via register link at the header

Given "Smoke-Registration-1" Customer starts the scenario:
| InputFileName | SheetName    | RowId                |
| QC-Smoke	    | Registration | Smoke-Registration-1 |

And "Smoke-Registration-1" Customer clicks on an object and validates the expected results:
| InputFileName  | SheetName | RowId      	    |
| HomepageLinks  | Homepage  | Register Light 	|
| HomepageLinks  | Homepage  |Regn Basic Access |

Then "Smoke-Registration-1" Customer enters the information in the fields on Join Balance Rewards page and clicks on Submit:
| BalanceRewardsOrPhoneNumber | BalanceRewardsOption | ZipCode | Phone | DOB-Month | DOB-Day | DOB-Year | GuestUser | InputFileName | SheetName   | RowId 			 |saveUserNameAndPasswordInDataSheet|
| -                           | CreateNew		     | 60015   | Random| Random    | Random  | Random   | Yes       |CommonData     | personalInfo| LoyaltyLightUser|Yes								|

Then "Smoke-Registration-1" Customer logout from walgreens:
| Source |
| Global |

Then "Smoke-Registration-1" Customer finished testing the scenario:
| InputFileName | SheetName    | RowId                |
| QC-Smoke	    | Registration | Smoke-Registration-1 |
#-----------------------------------------------------------------------------------------------------------

# <--- Smoke test case Light to Rx registration plus Attach TC-02 --->
#-----------------------------------------------------------------------------------------------------------
@Smoke-Registration-2
Scenario: To verify whether confirmation page is displayed when user creates the Rx account with attach loyalty via complete your registration link via pharmacy widget.

Given "Smoke-Registration-2" Customer starts the scenario:
| InputFileName | SheetName    | RowId                |
| QC-Smoke	    | Registration | Smoke-Registration-2 |

And "Smoke-Registration-2" Customer clicks on an object and validates the expected results:
| InputFileName  | SheetName | RowId      	  |
| HomepageLinks  | Homepage  | Global Register|

Then "Smoke-Registration-2" Customer enters the information in the fields on Join Balance Rewards page and clicks on Submit:
| BalanceRewardsOrPhoneNumber | BalanceRewardsOption | ZipCode | Phone | DOB-Month | DOB-Day | DOB-Year | GuestUser | InputFileName | SheetName   | RowId 			  |saveUserNameAndPasswordInDataSheet|
| -                           | MaybeLater		     | -       | -     | -         | -       | -        | Yes       |CommonData     | personalInfo| LoyaltyLightUser3 |Yes								 |

Then "Smoke-Registration-2" Customer logout from walgreens:
| Source |
| Global |

Given "Smoke-Registration-2" Customer login to the walgreens.com:
| InputFileName | SheetName    | RowId             |
| CommonData  	| personalInfo | LoyaltyLightUser3 |

And "Smoke-Registration-2" Customer clicks on an object and validates the expected results:
| InputFileName            | SheetName | RowId      	        |
| PharmacyLinkVerification | LightReg  | Transfer Prescription  |
| PharmacyLinkVerification | LightReg  | Complete Regn          |

And "Smoke-Registration-2" Customer performs Pharmacy Registration:
| InputFileName | SheetName		 | RowId 	 	 |
| Registration  | RxRegistration | CompleteRegn1 |

Then "Smoke-Registration-2" Customer logout from walgreens:
| Source |
| Global |

Then "Smoke-Registration-2" Customer finished testing the scenario:
| InputFileName | SheetName    | RowId                |
| QC-Smoke	    | Registration | Smoke-Registration-2 |
#-----------------------------------------------------------------------------------------------------------

# <--- Smoke test case Rx Plus May be later TC-03 --->
#-----------------------------------------------------------------------------------------------------------
@Smoke-Registration-3
Scenario: To verify whether confirmation page is displayed when Rx user creates the loyalty account via Activate now button in your account home page

Given "Smoke-Registration-3" Customer starts the scenario:
| InputFileName | SheetName    | RowId                |
| QC-Smoke	    | Registration | Smoke-Registration-3 |

And "Smoke-Registration-3" Customer clicks on an object and validates the expected results:
| InputFileName  		   | SheetName | RowId      	        |
| PharmacyLinkVerification | Anonymous | Transfer Prescriptions |

And "Smoke-Registration-3" Customer performs Pharmacy Registration:
| InputFileName | SheetName		 | RowId 	 		      | SaveUserNameFlag |
| Registration  | RxRegistration | MatchedUnauthenticated | Yes				 |

And "Smoke-Registration-3" Customer clicks on an object and validates the expected results:
| InputFileName  		   | SheetName 	     | RowId      	      	|
| PharmacyLinkVerification | UnAuthenticated | UnauthRefillRx	    |
| PharmacyLinkVerification | UnAuthenticated | CompleteRegistration |

Then "Smoke-Registration-3" Customer checks whether the user is unmatched user or matched user in Verify your Identity page:
| InputFileName | SheetName		 | RowId 	 		      | FetchActivationFlag | UserType |
| Registration  | RxRegistration | MatchedUnauthenticated | Yes 				| MATCHED  |

Given "Smoke-Registration-3" Customer login to the csc application:
| InputFileName | SheetName | RowId    |
| CSCData       | login     | cscuser1 |

Then "Smoke-Registration-3" Customer selects the CSC Window

And "Smoke-Registration-3" Customer Authenticates an user:
| InputFileName | SheetName		 | RowId 	 		      |
| Registration  | RxRegistration | MatchedUnauthenticated |

Then "Smoke-Registration-3" Customer selects the Default Window

And "Smoke-Registration-3" Customer clicks on an object and validates the expected results:
| InputFileName  		   | SheetName 	     | RowId      	      	|
| PharmacyLinkVerification | UnAuthenticated | UnauthRefillRx	    |
| PharmacyLinkVerification | UnAuthenticated | CompleteRegistration |
| HomepageLinks            | RxPage          | Manage Prescriptions |

Then "Smoke-Registration-3" Customer logout from walgreens:
| Source |
| Global |

Then "Smoke-Registration-3" Customer finished testing the scenario:
| InputFileName | SheetName    | RowId                |
| QC-Smoke	    | Registration | Smoke-Registration-3 |
#-----------------------------------------------------------------------------------------------------------

# <--- Smoke test case Logged in Plus Create - Rx user TC-04 --->
#-----------------------------------------------------------------------------------------------------------
@Smoke-Registration-4
Scenario: To verify whether confirmation page is displayed when Rx user creates the loyalty account via Activate now button in your account home page

Given "Smoke-Registration-4" Customer starts the scenario:
| InputFileName | SheetName    | RowId                |
| QC-Smoke	    | Registration | Smoke-Registration-4 |

And "Smoke-Registration-4" Customer performs Pharmacy Registration:
| InputFileName | SheetName		 | RowId 	 |
| Registration  | RxRegistration | RxLoyalty |

And "Smoke-Registration-4" Customer clicks on an object and validates the expected results:
| InputFileName  | SheetName | RowId      	                |
| HomepageLinks  | Homepage  | Your Account                 |
| HomepageLinks  | Homepage  | Your Account BR Activate Now |
| HomepageLinks  | Homepage  | Create Loyalty 			    |
| HomepageLinks  | Homepage  | Create Loyalty Submit	    |

Then "Smoke-Registration-4" Customer logout from walgreens:
| Source |
| Global |

Then "Smoke-Registration-4" Customer finished testing the scenario:
| InputFileName | SheetName    | RowId                |
| QC-Smoke	    | Registration | Smoke-Registration-4 |
#-----------------------------------------------------------------------------------------------------------

# <--- Smoke test case Logo plus Attach TC-06 --->
#-----------------------------------------------------------------------------------------------------------
@Smoke-Registration-6
Scenario: To verify whether confirmation modal is displayed when user creates the walgreens account with attach loyalty via Activate now button in logo

Given "Smoke-Registration-6" Customer starts the scenario:
| InputFileName | SheetName    | RowId                |
| QC-Smoke	    | Registration | Smoke-Registration-6 |

Then "Smoke-Registration-6" Customer registers the loyalty account in store:
| InputFileName | SheetName 	  | RowId 		|
| Registration  | LoyaltyStoreReg | StoreReg1   |

Then "Smoke-Registration-6" Customer selects the Default Window

And "Smoke-Registration-6" Customer attaches loyalty through balance rewards in home page:
| InputFileName | SheetName   | RowId    	 |
| CommonData    | loyaltyinfo | ActivateUser |

And "Smoke-Registration-6" Customer clicks on an object and validates the expected results:
| InputFileName | SheetName | RowId      		    |
| HomepageLinks | CartPage  | ActivateRegister		|
| HomepageLinks | Homepage  | AttachRegnBasicAccess |

Then "Smoke-Registration-6" Customer enters the information in the fields on Join Balance Rewards page and clicks on Submit:
| BalanceRewardsOrPhoneNumber | BalanceRewardsOption | ZipCode     |ZipcodeExt	| Phone 	 | DOB-Month | DOB-Day  	| DOB-Year 	| GuestUser | InputFileName | SheetName   | RowId 			  | Submit |saveUserNameAndPasswordInDataSheet|
| FromSheet                   | ActivateNow		     | FromSheet   |6061		| -		     | FromSheet | FromSheet    | FromSheet |  Yes      | CommonData    | personalInfo| SmokeAttach1	  | Yes    |Yes								  |

Then "Smoke-Registration-6" Customer finds the info in We Found Page and clicks Submit:
|DiscrepencyOverly |Update  | NotNow |
|    Yes		   |  -     |  -     |

Then "Smoke-Registration-6" Customer verifies the loyalty registeration by observing the Congratulations message.
| MembershipType |
| Activation     |

Then "Smoke-Registration-6" Customer logout from walgreens:
| Source |
| Global |

Then "Smoke-Registration-6" Customer finished testing the scenario:
| InputFileName | SheetName    | RowId                |
| QC-Smoke	    | Registration | Smoke-Registration-6 |
#-----------------------------------------------------------------------------------------------------------
