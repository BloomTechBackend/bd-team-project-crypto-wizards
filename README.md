# Crypto Portfolio Tracker


Crypto assets are digital tokens secured through a decentralized computer network. Owning several crypto 
assets can be difficult to track and view overall value and performance.

The crypto portfolio tracking service provides a custom view of the client's portfolio to meet their needs. 
It is designed to connect with a 3rd party cryptocurrency data aggregator, displaying realtime data converted 
to USD for accurate asset value. 

This will keep track of all assets in a single location with historical data of transactions to view growth 
and regression.

We use API Gateway and Lambda to create seven endpoints (RegisterActivity, LoginActivity, VerifyActivity, 
CreatePortfolioActivity, GetPortfolioActivity, UpdatePortfolioActivity, GetTransactionActivity) that will 
handle the creation, update, and retrieval of portfolio to satisfy our requirements.

We store the user profile, portfolio, and transaction history in DynamoBD tables where we also designed
a GSI schema and attribute projection that optimizes queries for the transactions.

Crypto Portfolio Tracker also provides a web interface for users to manage their portfolio. After a user 
registers and creates a portfolio, the main portfolio page provides a list view of assets and displays a 
chart with asset information, portfolio percentages and total value. The user is also able to update their 
portfolio and view transaction history.
