# David - Crypto Wizards Accomplishment Tracking

Each team member should have their own version of this document.

## Background

It's a great habit to record accomplishments and progress throughout your SDE
career. It's useful to reflect on what you've worked on in the past and comes in
handy during performance reviews and promotion cycles.

## Instructions

**Save a copy of this document in your “private” folder.**

Using the below template, keep track of what you’ve worked on each week during
the unit. 1-3 bullets under each section for each week should suffice. This
should only take 5 - 10 minutes of reflection each week.

As you track your work, think about how it relates to the SDE fundamental skills
laid out in the syllabus and how you are practicing them.

* Converts a design into code and delivers it using best practices
* Writes secure, testable, maintainable code
* Understands when to use (or not) a broad range of data structures and
  algorithms
* Creates unit tests that thoroughly test functionality
* Troubleshoots by debugging and reviewing errors, logfiles, and metrics
* Contributes to planning and design
* Escalates when projects hit roadblocks and risks

The important work samples don’t only include the things you authored, but
should include things like key CRs you reviewed that you are proud of as well!

_You will submit your completed Accomplishment Tracking Document to your
instructors by the end of the unit._

## Week 1

**Goals:**
* Create overall project design
* Implement login/authentication backend
* Create lambda function to handle login

**Activity:**
* Brainstormed with team to decide on project
* Decided on basic design and API for app

**Important Docs, Commits, or Code Reviews**:
* Implemented backend functionality for registration, login, and authentication
* Created lambda using proxy integration

**Things learned:**
* Learned about JSON web tokens, bcrypt for password hashing, and lambda proxy integration

## Week 2

**Goals:**
* Research React
* Help Shannon finish front end implementation of registration and login
* Connect front end and back end for login
* Implement service to pull data from CoinGecko

**Activity:**
* Researched React

**Important Docs, Commits, or Code Reviews**:
* Fixed implementation of public/private routes
* Implemented service to pull CoinGecko data
* Changed design of app to pull from CoinGecko on front end

**Things learned:**
* Learned the basics of React Router v6
* Learned about CORS

## Week 3

**Goals:**
* Finish implementing front end

**Activity:**
* Further researched React

**Important Docs, Commits, or Code Reviews**:
* Switched from using lambda proxy integration to body pass through
* Implemented create/update portfolio frontend
* Created GSI for indexing transactions by assetI

**Things learned:**
* Learned about GSIs
* Learned how to handle errors through AWS API gateway

## Week 4

**Goals:**
* Fix bugs and polish front end

**Activity:**
* Finished reflection and accomplishment tracking
* Prepared for presentation

**Important Docs, Commits, or Code Reviews**:
* Implemented conditional rendering based on newUser status
* Improved formatting for portfolio list and transaction history

**Things learned:**
* Learned how sessionStorage stores everything as a string while investigating a bug
