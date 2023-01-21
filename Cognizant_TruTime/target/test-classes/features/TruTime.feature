Feature: TruTime


	Scenario: Login using correct Email ID and Password

		Given User is on the Login page
		When User enters the Email ID
		And User clicks on Next button
		And User enters the Password
		And User clicks on Next button
		And User selects the required login options
		And User clicks No for Remember sign in
		Then User is redirected to Home page
  
	Scenario: Searching and Selecting TruTime

		Given User is on Home Page
		When User clicks on Search bar
		And User enters TruTime
		And User clicks on Search button
		And User clicks on Apps & Tools
		And User clicks on TruTime
		Then User is redirected to TruTime webpage

	Scenario: Verification of displayed dates

		Given User is on TruTime Page
		Then The dates for the current week are correctly displayed
		And Today's date is selected