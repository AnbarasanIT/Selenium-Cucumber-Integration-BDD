@smoke @smokeCore

Feature: Verify if customer is able to perform various types of basic core functionalities across dotcom

#Test Cases Covered Core Features
#******************
# <--- Please put the test case id's from ALM --->

#-----------------------------------------------------------------------------------------------------------
@Smoke-Core-1
Scenario: To verify whether Store Results are displayed providing  Filter option as 24 hours 

Given "Smoke-Core-1" Customer starts the scenario:
| InputFileName | SheetName    | RowId                |
| QC-Smoke	    | Core         | Smoke-Core-1 		  |

Then "Smoke-Core-1" Customer clicks on an object and validates the expected results:
| InputFileName 			| SheetName 	| RowId        |
| PharmacyLinkVerification  |StoreLocator   | StoreLocator |

Then "Smoke-Core-1" Customer searches for a store:
| InputFileName | SheetName   | RowId     		    |
| StoreLocator  |MakeMyStore  | StoreSearchSmoke    |

Then "Smoke-Core-1" Customer finished testing the scenario:
| InputFileName | SheetName    | RowId                |
| QC-Smoke	    | Core         | Smoke-Core-1 		  |
#-----------------------------------------------------------------------------------------------------------
@Smoke-Core-2
Scenario: To verify whether Search Results are displayed by entering Keywords in search Text box

Given "Smoke-Core-2" Customer starts the scenario:
| InputFileName | SheetName    | RowId                |
| QC-Smoke	    | Core         | Smoke-Core-2 		  |

Then "Smoke-Core-2" Customer verifies Search text box by entering keyword in homepage:

Then "Smoke-Core-2" Customer finished testing the scenario:
| InputFileName | SheetName    | RowId                |
| QC-Smoke	    | Core         | Smoke-Core-2 		  |
#-----------------------------------------------------------------------------------------------------------
@Smoke-Core-3
Scenario: To verify navigation of various links present in the home page

Given "Smoke-Core-3" Customer starts the scenario:
| InputFileName | SheetName    | RowId                |
| QC-Smoke	    | Core         | Smoke-Core-3 		  |

Given "Smoke-Core-3" Customer login to the walgreens.com:
| InputFileName  | SheetName   | RowId               |
| CommonData 	 | login       | SmokeCore     		 |

Then "Smoke-Core-3" Customer clicks on an object and validates the expected results:
| InputFileName 			 | SheetName 	| RowId             | 
| MarketingLinkVerification  | Smoke   	| Transfer_registered |
| MarketingLinkVerification  | Smoke   	| New_registered      |


#And "Smoke-Core-3" Customer navigates to refill prescriptions page through refill faster overlay from pharmacy page:

Then "Smoke-Core-3" Customer logout from walgreens:
| Source  |
| Global  |

Then "Smoke-Core-3" Customer clicks on an object and validates the expected results:
| InputFileName 			 | SheetName 	| RowId            |TargetOverlay		|
| MarketingLinkVerification  | Smoke   		| Transfer_unknown |-					|
| MarketingLinkVerification  | Smoke   		| New_unknown      |-					|
#| MarketingLinkVerification  | Smoke   		| Photo    		   |-					|
| MarketingLinkVerification  | Smoke   		| Shop    		   |-					|
#| MarketingLinkVerification  | Smoke   		| Contactus    	   |-					|
| MarketingLinkVerification  | Smoke   		| Refill_unkown    |Yes					|


#Then "Smoke-Core-3" Customer clicks Take care clinic and Health information links using mouse hover under pharmacy menu and validates the respective pages from homepage:

Then "Smoke-Core-3" Customer finished testing the scenario:
| InputFileName | SheetName    | RowId                |
| QC-Smoke	    | Core         | Smoke-Core-3 		  |
#-----------------------------------------------------------------------------------------------------------