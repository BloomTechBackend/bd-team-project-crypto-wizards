# Crypto Wizards Design Document

## Crypto Portfolio Tracker Design

## 1. Problem Statement

Crypto assets are digital tokens secured through a decentralized computer network. Owning several crypto assets can be difficult to track and view overall value and performance.

This design document describes a crypto portfolio tracking service that provides a custom view of the client's portfolio to meet their needs. It is designed to connect with a 3rd party price quoting service, displaying realtime data converted to USD to see an accurate asset value. This will keep track of all assets in a single location with historical data to view growth and regression.


## 2. Top Questions to Resolve in Review

1. How should daily historical data be calculated with a volatile market?   
2. How many and what assets should be accessible, eliminating stable coin and keeping a 
market cap order?  
3. How to manage API calls on the backend and design the frontend to utilize backend data? 

## 3. Use Cases

U1. As a customer, I want to create a new portfolio from a list of assets when I log 
into the site.

U2. As a customer, I want to view a portfolio holding all my assets with realtime value 
converted to USD.
    
U3. As a customer, I want to update my list of assets in my portfolio.

U4. As a customer, I want to remove assets from my portfolio.

U5. As a customer, I want to view historical data showing asset performance.

## 4. Project Scope

### 4.1. In Scope

* Creating, retrieving, and updating a porfolio.
* Retrieving the historical data of the portfolio.
* Retrieving the historical data showing asset performance.

### 4.2. Out of Scope

* Buying and selling of assets.

# 5. Proposed Architecture Overview

This initial iteration will provide creating, retrieving, and updating a user's portfolio, as well as viewing the historical data of the porfolio and the assets to gauge the overall performance over a period of time.

We will use API Gateway and Lambda to create five endpoints (CreatePotfolio, GetPortfolio, UpdatePortfolio, GetPorfolioHistory, GetAssetPotfolio) that will handle the creation, update, and retrieval of porfolio along with the retrieval of the historical data to satisfy our requirements.

We will store the assets available for the portfolio in a table in DynamoDB. The porfolios themselves will also be stored in DynamoDB. 

CryptoPortfolioTracker will also provide a web interface for users to manage their porfolios. A main page providing  a list view will let them create new porfolios and link off to pages to update assets, view assets and view historical Data.

# 6. API

## 6.1. Public Models

*Define the data models your service will expose in its responses via your
*`-Model`* package. These will be equivalent to the *`PlaylistModel`* and
*`SongModel`* from the Unit 3 project.*

## 6.2. *First Endpoint*

*Describe the behavior of the first endpoint you will build into your service
API. This should include what data it requires, what data it returns, and how it
will handle any known failure cases. You should also include a sequence diagram
showing how a user interaction goes from user to website to service to database,
and back. This first endpoint can serve as a template for subsequent endpoints.
(If there is a significant difference on a subsequent endpoint, review that with
your team before building it!)*

*(You should have a separate section for each of the endpoints you are expecting
to build...)*

## 6.3 *Second Endpoint*

*(repeat, but you can use shorthand here, indicating what is different, likely
primarily the data in/out and error conditions. If the sequence diagram is
nearly identical, you can say in a few words how it is the same/different from
the first endpoint)*

# 7. Tables

*Define the DynamoDB tables you will need for the data your service will use. It
may be helpful to first think of what objects your service will need, then
translate that to a table structure, like with the *`Playlist` POJO* versus the
`playlists` table in the Unit 3 project.*

# 8. Pages

*Include mock-ups of the web pages you expect to build. These can be as
sophisticated as mockups/wireframes using drawing software, or as simple as
hand-drawn pictures that represent the key customer-facing components of the
pages. It should be clear what the interactions will be on the page, especially
where customers enter and submit data. You may want to accompany the mockups
with some description of behaviors of the page (e.g. “When customer submits the
submit-dog-photo button, the customer is sent to the doggie detail page”)*
