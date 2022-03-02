# Crypto Wizards Reflection

## Instructions

1. *Create a new folder within this current folder, and name the new folder
   after your team name*
2. *If you prefer keeping this reflection private to your team, you may change
   the permissions on your folder so that only your teammates + unit instructors
   can view the contents*
3. *Create a copy of this reflection template, and put it in your newly created
   folder *

*Please answer at least one question from each section below. Answers here will
likely be inspiration for the “lessons learned” section of your project
presentation at the end of the unit.*

## Design

* How closely did you follow your design document after the review was complete?
Did your implementation need to change based on what you learned once you were
underway? 

We mostly stuck to it on the core components of the app, but we moved a number of things out of scope. We did change a number of things along the way. We moved the third party API calls to the front end rather than the back end. As a result, we didn't need some of our classes on the backend that were associated with that. We didn't realize we were supposed to create a GSI until Sprint 3, so we added the transaction history feature to use a GSI.

## Project

* How did you handle the ambiguity of defining your own project and user
  stories? What strategies did you use to decide on concrete work to do to
  satisfy your requirements?
* How did you deal with getting stuck on a problem? What strategies did you
  employ to get yourself unblocked?

For problems of how to implement certain features, I would look up videos/tutorials of people implementing similar features and adapt it to our situation. As far as getting stuck on bugs, I would try to go through a process of elimination to narrow down the possible causes. I would try to come up with very specific search terms related to the possible causes to see if other people had dealt with the same issue. I also of course googled a number of error messages.

* Did any of your commitments you made in your team charter help navigate a
  difficult decision, situation, or collaboration challenge? How?

## Scrum

* What did you find to be the most valuable part of daily stand-ups? Is there
  anything you would want to do differently at stand-up to make it more useful
  to you?

The daily meetings were good for finding out where there was any misunderstanding or lack of clarity about design decisions and how everything was supposed to fit together. It was also a good time to review what we had accomplished and discuss goals for the rest of the day.

* Did you over or underestimate the work you could complete during your sprints?
  What have you learned that will help you better estimate work next time?

I think we overestimated how long things would take on the back end and underestimated how long things would take on the front end. I also think we underestimated how much time would be required fixing bugs involved in getting the different parts of the system to work together. For example, we would have tested the back end and everything was working fine through postman. We had log statements in our front end indicating everything was fine. But then when we try to connect the front end to the back end we would get some CORS errors related to our API gateway configuration.

## Looking ahead

* If you were to start this unit over again, what would you do differently? How
  do you see yourself applying that to your work in later units, the capstone,
  or your internship?

I would have been more careful about reading through all of the project documents from the beginning. I think we were kind of in a rush to get started working on the project and should have spent more time reading through everything. We didn't realize we were supposed to be implementing a GSI till later in the unit, and it would have affected some of our design decisions early on if we had planned with that in mind. This can definitely be applied in the later units and on the job, especially on larger projects. It will save time refactoring later on if I spend more time carefully understanding requirements at the beginning.