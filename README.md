## We’d like to build an app that allows users to search for images which have been Tweeted.

### Your app should perform the following:
1. Use the Twitter Search API to find all Tweets with images with a hard coded search term
of #lovewhereyouwork
2. Display scrollable grid of thumbnails, ordered by recency.
  1. You only need to display a maximum of 50 results.
3. Allow the user to click on a thumbnail, which should load and show the image fullscreen.
  1. User should be able to close the fullscreen image and return to the results
screen.

### This is a time limited exercise, so you can make some assumptions:
* Hardcode the Consumer Key and Consumer Secret and get a bearer token only on
app start­up, rather than with each search
* No need to persist any data or app state. You should support standard activity states,
but do not need to handle offline state or persisting data/state on a hard stop of the app
or phone.
* You do not need to render the Tweet or any Tweet text
* You need to display only the first image from each Tweet
* Don’t worry about gracefully handling rate limiting
* Please use whatever 3rd party libraries or external dependencies you feel necessary.
Please use only public Android APIs. The lowest API version you should target is 16, but
please aim to use the highest stable API available.

### Things to be aware of:
* The search API you’ll be using will need only application­auth (you do not need a user
context)
* You will need to perform a small oauth dance, which is as easy as:
  * Make an encoded key: using the Consumer Key and Consumer Secret ,
combine and base64 encode it base64(key + “:” + secret)
  * Make a POST oauth2/token request to Twitter to get a bearer token using the
encoded key from step 1
  * Save this bearer token and when making requests to the Search API, supply the
bearer token in the headers (“Authorization: Bearer xxxxxxxxxxxxxx ”)
  * You can find more details about this in the Resources section

### Suggested improvements
These are some non prioritised set of improvements that we think would enhance the
functionality of the app. If you have the time, please feel free to take one more items and build
them into the app.

* Allow the user to input a search term which is used to perform the search query
* Remove the maximum of 50 images and allow paging of the search results, so the user
can keep infinitely scrolling (both up and down) through as many images as the Search
API provides
* Make the grid view flexible so that on devices with larger screens it adapts to the amount
of screen size available.
* On the fullscreen view, show the profile image and @handle of the account which
Tweeted the image
* If the Tweet has multiple images, allow the user to see those rather than only the 1st
image.
How we judge what you create
* Your app must compile.
* We’re looking at the quality of code, your code style, consistency of your code and
application of best practise.
* We appreciate that software crashes under unusual circumstances. We prefer that your
app does not crash, but we also don’t want the app being left in an unusable state (e.g.
user actions now ignored). Please don’t spend lots of time adding overly robust error
handling because we know you won’t have time!
