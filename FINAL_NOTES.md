* I had couple of problems dealing with the search API :( For some reason the data was not consistent and sometimes I got
 Twits with images and others not, for that reason I filtered out the non-image twits, which leads to a couple of them only, but hopefully enough for the challenge.

* Although not there, the pagination would be an easy task by just adding a special scroll listener to the RecyclerView 
and passing events to the presenter to modify the current page.

* I didn't dedicated much time to the visual details in order to have a proper/decent architecture.

* For timing reasons I didn't included JUnit tests, which honestly makes me feel bad, but adding them would be a matter 
of 30min or so. Personally I would have added test to this classes: the presenter, interactor, mapper, dataSource and even to the Renderer (using robolectric for this one). 