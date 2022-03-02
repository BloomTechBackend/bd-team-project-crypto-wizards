# Crypto Wizards Integration Test Plan

## Purpose

This captures the test plan for your project, including:

* testing of endpoints through automated integration tests
* testing of front-end through manual browser-based testing

## Product Background

The crypto portfolio tracking service provides a custom view of the client's portfolio to meet their needs. 
It is designed to connect with a 3rd party price quoting service, displaying realtime data converted to USD 
to see an accurate asset value. This will keep track of all assets in a single location with historical data 
of transactions to view growth and regression.

[Design Document](./design_document.md)

### Use Cases

U1. As a customer, I want register myself if I am accessing the website for the first time. 

U2. As a customer, I want to create a new portfolio from a list of assets when I log into the site.

U3. As a customer, I want to view a portfolio holding all my assets with realtime value converted to USD.

U4. As a customer, I want to update my list of assets in my portfolio.

U5. As a customer, I want to view the history of my transactions for a particular asset / for all my assets.

# Back-end Unit Test Plan

## Use Case: As a customer, I want to **register** myself if I am accessing the website for the first time.

### Manual Test Case: 
handleRequest_provideCorrectFormatUsernameAndPassword_registersUserSuccessfully

**Acceptance criteria:**

1. The user must be registered in the database.

**Endpoint(s) tested:**

1. RegisterActivityProvider

**GIVEN (Preconditions):**

1. A valid username and password are provided.
2. The user is a new user.

**WHEN (Action(s)):**

1. We call the RegisterResponse.

**THEN (Verification steps):**

1. The User's profile is create in the database.

**Is there any clean-up needed for this test?**

1. Since we are Mocking the data, there is no clean up required for this test.

### Manual Test Case: 
handleRequest_provideInCorrectFormatUsername_throwsIllegalArgumentException

**Acceptance criteria:**

1. IllegalArgumentException must be thrown.

**Endpoint(s) tested:**

1. RegisterActivityProvider

**GIVEN (Preconditions):**

1. An invalid format for username is provided.

**WHEN (Action(s)):**

1. We call the RegisterResponse.

**THEN (Verification steps):**

1. IllegalArgumentException is thrown.

**Is there any clean-up needed for this test?**

1. Since we are Mocking the data, there is no clean up required for this test.

### Manual Test Case: 
handleRequest_provideInCorrectFormatPassword_throwsIllegalArgumentException

**Acceptance criteria:**

1. IllegalArgumentException must be thrown.

**Endpoint(s) tested:**

1. RegisterActivityProvider

**GIVEN (Preconditions):**

1. An invalid format for password is provided.

**WHEN (Action(s)):**

1. We call the RegisterResponse.

**THEN (Verification steps):**

1. IllegalArgumentException is thrown.

**Is there any clean-up needed for this test?**

1. Since we are Mocking the data, there is no clean up required for this test.

## Use Case: As a customer, I want to **create** a new portfolio from a list of assets when I log into the site.

### Manual Test Case: 
handleRequest_withValidUsername_createsPortfolio

**Acceptance criteria:**

1. The portfolio must be created in the database.

**Endpoint(s) tested:**

1. CreatePortfolioActivityProvider

**GIVEN (Preconditions):**

1. A valid username and password are provided.
2. The user is a new user and does not have an existing portfolio.

**WHEN (Action(s)):**

1. We call the CreatePortfolioResponse.

**THEN (Verification steps):**

1. The User's Portfolio is created in the database. 
2. The input is not modified

**Is there any clean-up needed for this test?**

1. Since we are Mocking the data, there is no clean up required for this test.

### Manual Test Case: 
handleRequest_withInValidUsername_throwsLoginException

**Acceptance criteria:**

1. LoginException must be thrown.

**Endpoint(s) tested:**

1. CreatePortfolioActivityProvider

**GIVEN (Preconditions):**

1. An invalid username is provided.

**WHEN (Action(s)):**

1. We call the CreatePortfolioResponse.

**THEN (Verification steps):**

1. LoginException is thrown.

**Is there any clean-up needed for this test?**

1. Since we are Mocking the data, there is no clean up required for this test.

### Manual Test Case: 
handleRequest_withUnavailableAsset_throwsAssetNotAvailableException

**Acceptance criteria:**

1. AssetNotAvailableException must be thrown.

**Endpoint(s) tested:**

1. CreatePortfolioActivityProvider

**GIVEN (Preconditions):**

1. A valid username and password are provided.
2. The user is a new user and does not have an existing portfolio.
3. The user provides an Asset that is not in the list of asset supported by the Application.

**WHEN (Action(s)):**

1. We call the CreatePortfolioResponse.

**THEN (Verification steps):**

1. AssetNotAvailableException is thrown.

**Is there any clean-up needed for this test?**

1. Since we are Mocking the data, there is no clean up required for this test.

## Use Case: As a customer, I want to **view** a portfolio holding all my assets with realtime value converted to USD.

### Manual Test Case: 
handleRequest_withProperUsername_getsTheCorrectPortfolio

**Acceptance criteria:**

1. The customer is able to retrieve the portfolio.

**Endpoint(s) tested:**

1. GetPortfolioActivityProvider

**GIVEN (Preconditions):**

1. A valid username and password are provided.
2. The user has an existing portfolio.

**WHEN (Action(s)):**

1. We call the GetPortfolioResponse.

**THEN (Verification steps):**

1. The User's portfolio is displayed.

**Is there any clean-up needed for this test?**

1. Since we are Mocking the data, there is no clean up required for this test.

### Manual Test Case: 
handleRequest_withUnavailableAsset_throwsAssetNotAvailableException

**Acceptance criteria:**

1. AssetNotAvailableException must be thrown

**Endpoint(s) tested:**

1. GetPortfolioActivityProvider

**GIVEN (Preconditions):**

1. A valid username and password are provided.
2. The user add a non-Existent asset to the portfolio

**WHEN (Action(s)):**

1. We call the GetPortfolioResponse.

**THEN (Verification steps):**

1. AssetNotAvailableException is thrown.

**Is there any clean-up needed for this test?**

1. Since we are Mocking the data, there is no clean up required for this test.

## Use Case: As a customer, I want to **update** my list of assets in my portfolio.

### Manual Test Case: 
handleRequest_withProperUsername_updatesPortfolio

**Acceptance criteria:**

1. The portfolio must be updated in the database.

**Endpoint(s) tested:**

1. UpdatePortfolioActivityProvider

**GIVEN (Preconditions):**

1. A valid username and password are provided.
2. User enters a valid Asset to be updated or enters a valid new asset to add to the portfolio.

**WHEN (Action(s)):**

1. We call the UpdatePortfolioResponse.

**THEN (Verification steps):**

1. The User's Portfolio is updated in the database.
2. The input is not modified

**Is there any clean-up needed for this test?**

1. Since we are Mocking the data, there is no clean up required for this test.


### Manual Test Case: 
handleRequest_withUnavailableAsset_throwsAssetNotAvailableException

**Acceptance criteria:**

1. AssetNotAvailableException must be thrown.

**Endpoint(s) tested:**

1. UpdatePortfolioActivityProvider

**GIVEN (Preconditions):**

1. A valid username and password are provided.
2. The user enters an invalid asset Id to be added to the portfolio.

**WHEN (Action(s)):**

1. We call the UpdatePortfolioResponse.

**THEN (Verification steps):**

1. AssetNotAvailableException is thrown.

**Is there any clean-up needed for this test?**

1. Since we are Mocking the data, there is no clean up required for this test.

## Use Case: As a customer, I want to view the history of my **transactions** for a particular asset / for all my assets.

### Manual Test Case: 
handleRequest_withAssetFlagAll_getsAllTheTransactionsForTheUser

**Acceptance criteria:**

1. All the transactions for the User's portfolio are displayed .

**Endpoint(s) tested:**

1. GetTransactionsActivityProvider

**GIVEN (Preconditions):**

1. A valid username and password are provided.
2. The user has a portfolio and has a transaction history

**WHEN (Action(s)):**

1. We call the GetTransactionsResponse.

**THEN (Verification steps):**

1. The User portfolio's transaction history is retrieved from the database.

**Is there any clean-up needed for this test?**

1. Since we are Mocking the data, there is no clean up required for this test.

### Manual Test Case: 
handleRequest_withAssetFlagAsAssetId_getsAllTheTransactionsForParticularAssertIdForTheUser

**Acceptance criteria:**

1. All the transactions of a User for the provided asset Id are displayed.

**Endpoint(s) tested:**

1. GetTransactionsActivityProvider

**GIVEN (Preconditions):**

1. A valid username and password are provided.
2. The user has a portfolio and has a transaction history for the specified asset

**WHEN (Action(s)):**

1. We call the GetTransactionsResponse.

**THEN (Verification steps):**

1. All the transactions of a User for the provided asset Id are retrieved from the database.

**Is there any clean-up needed for this test?**

1. Since we are Mocking the data, there is no clean up required for this test.

### Manual Test Case: 
handleRequest_withNullTransactions_throwsTransactionsNotFoundException

**Acceptance criteria:**

1. TransactionsNotFoundException is thrown.

**Endpoint(s) tested:**

1. GetTransactionsActivityProvider

**GIVEN (Preconditions):**

1. A valid username and password are provided.
2. The user enters an invalid asset Id which returns null transactions. 

**WHEN (Action(s)):**

1. We call the GetTransactionsResponse.

**THEN (Verification steps):**

1. TransactionsNotFoundException is thrown.

**Is there any clean-up needed for this test?**

1. Since we are Mocking the data, there is no clean up required for this test.

# Manual Front-end Test Plan

## Use Case: As a customer, I want to **register** myself if I am accessing the website for the first time.

### Manual Test Case:
Test a user profile is accurately created in database.

GIVEN (Preconditions):
1. Valid username and password are provided.
2. User is a new user.

WHEN (Action(s)):
1. Entered password not meeting 6 character requirement.
2. Entered existing username.

THEN (Verification steps):
1. User profile is created in database.

## Use Case: As a customer, I want to **create** a new portfolio from a list of assets when I log into the site.

### Manual Test Case:
Test a user portfolio is accurately created in database.

GIVEN (Preconditions):
1. User is a new user.

WHEN (Action(s)):
1. Asset without quantity.
2. Asset already added to list.

THEN (Verification steps):
1. User portfolio is created in database.
2. Programmatically navigate to portfolio page.
3. Back to portfolio event handlers programmatically navigates to empty portfolio page.
4. Logout event handler programmatically navigates to login page.

## Use Case: As a customer, I want to **view** a portfolio holding all my assets with realtime value converted to USD.

### Manual Test Case:
Test a user portfolio is accurately displayed in page.

GIVEN (Preconditions):
1. User is an existing user.
2. User created a portfolio.

WHEN (Action(s)):
1. Existing user status.
2. New user status.

THEN (Verification steps):
1. User portfolio chart displays accurately.
2. Portfolio chart has correct formatting when various asset values are selected.
3. Removing all assets displays zero dollar and stores empty portfolio in database.
4. Update event handler programmatically navigates to update page.
5. Transaction history event handler programmatically navigates to transactions page.
6. Logout event handler programmatically navigates to login page.

## Use Case: As a customer, I want to **update** my list of assets in my portfolio.

### Manual Test Case:
Test a user portfolio is accurately updated and displayed correctly on portfolio page.

GIVEN (Preconditions):
1. User has existing portfolio.

WHEN (Action(s)):
1. Add new asset.
2. Update existing asset.
3. Remove asset.

THEN (Verification steps):
1. User portfolio is updated in database.
2. Add asset event handler updates portfolio list with new asset and quantity.
3. Update asset event handler updates existing asset with new quantity.
4. Update asset event handler removes asset when updating quantity to zero.
5. Update portfolio event handler programmatically navigates to portfolio page with updated asset information.
6. Back to portfolio event handlers programmatically navigates to portfolio page.
7. Logout event handler programmatically navigates to login page.

## Use Case: As a customer, I want to view the history of my **transactions** for a particular asset / for all my assets.

### Manual Test Case:
Test a user's transaction history is queried and displayed on transaction history page.

GIVEN (Preconditions):
1. User has existing portfolio.

WHEN (Action(s)):
1. Query all transactions.
2. Query selected assets transactions. 

THEN (Verification steps):
1. Dropdown list display's all assets in portfolio.
2. Transaction history event handler queries all transactions when asset isn't selected.
3. Transaction history event handler queries all transactions of a selected asset.
5. Back to portfolio event handlers programmatically navigates to portfolio page.
6. Logout event handler programmatically navigates to login page.

