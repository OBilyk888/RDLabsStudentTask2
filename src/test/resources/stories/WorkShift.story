Narrative:
As a user
I want to make sure that all functionality on Work Shifts page as expected

Lifecycle:
Before:
Given I am on the login page of application
And I login to application with username 'admin' and password 'admin123'
And I go to Work Shifts page

!-- https://jbehave.org/reference/latest/parameter-converters.html

Scenario: AC-1 Check that by default General and Twilight work shifts types are shown on work shifts page
Meta:
When I check that rows with values $General, $Twilight in WorkShift column are shown by default

Scenario: AC-2 Check that Work Shift field on Add work shift model requiired
Meta: @testCases
When I click on Add Work Shift button
And I Click on Save button
Then Check that Required error message is shown under Work Shift field

!-- TODO implement this scenario
Scenario: AC-3 Check that value in Hours Per Day field calculated propertly



