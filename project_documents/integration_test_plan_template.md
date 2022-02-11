# [team name] Integration Test Plan

## Instructions

*Create a copy of this template for your team and save it to the same folder.*

*Fill out the test plan following the instructions provided, replacing/removing
the text in italics (including this section!) as you go. You will review this
document with the team you’re paired with for the unit.*

## Purpose

This captures the test plan for your project, including:

* testing of endpoints through automated integration tests
* testing of front-end through manual browser-based testing

Use the endpoint test plan to create your automated integration tests. Use the
front-end test plan to ensure that your front-end is working. Manual front-end
tests are more expensive, so aim to have fewer of them, and you may want to run
these at key milestones in your project (and certainly before presentation
day!).

## Product Background

*Provide a quick summary of your product, including a link back to your design
document.*

### Use Cases

*List your use cases here. These should be the same list from your design doc
(though might be updated based on what you’ve learned since then!). You will
refer back to these use cases in the test plans below.*

# Automated Integration Test Plan

*Organize your tests by use cases from your design document. Provide the entire
“Use Case:...” section below for each Use Case you will implement in the
project. If you have more than one test case for a given use case, repeat the
“Test Case” section below for each test case in that use case.*

*The goal should be that any member of your team could take this list of
integration tests and add these automated tests to the integration test
package.*

## Use Case:* [use case name]*

### **Test case name: *[test method name, following ATA conventions]***

**Acceptance criteria:**

1. *(List what must be true to verify the use case has been implemented
   correctly)*

**Endpoint(s) tested:**

1. *(List only the endpoints actually tested (the “WHEN” part of your test,
   which might have multiple steps in an integration test))*

**GIVEN (Preconditions):**

1. *(List the conditions that must be true for the test case to take place.)*

**WHEN (Action(s)):**

1. *(List the steps that we’re actually testing to verify that they work
   correctly. Often only one, but some integration tests might contain multiple
   WHEN steps for complex situations)*

**THEN (Verification steps):**

1. *(List the steps to verify/assert that the expected behavior actually
   happens, include any relevant invariants here as well.)*

**Is there any clean-up needed for this test?**

1. *(Is there anything we need to do after this test finishes, to clean up and
   leave our service like we found it?)* 

# Manual Front-end Test Plan

*List the key manual test cases that you will perform to verify the full
end-to-end functionality of your project. Your integration tests will verify
that your service is working properly, but you will also need to ensure that
your web pages integrate with them properly. Use your judgment about which cases
to cover in your manual tests, as they’re much more costly to execute. But you
should cover the key operations that your customers will use the application
for.*

*The goal should be that any one of your team members could take this list of
Preconditions, Actions and Verification Steps and run all of these manual tests,
then report any bugs that are observed.*

## Use Case:* [use case name]*

### **Manual Test Case: *[description of case being tested]***

**GIVEN (Preconditions):**

1. *(List the conditions that must be true for the test case to take place.
These may include seed data and/or manual steps to perform during the test—be
clear which is which)*

**WHEN (Action(s)):**

1. *(List the steps that we’re actually testing to verify that they work
correctly)*

**THEN (Verification steps):**

1. *(List the steps to verify that the expected behavior actually happens,
include any relevant invariants here as well. These can be steps to view other
pages and/or inspect data directly in the database. Try to make the steps to
execute the test as straightforward/simple as possible to save time.)*
