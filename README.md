# HustleTest
Extract PhoneNumber and Link from Web Page. Add extracted URL to unvisited list. Repeat the process until the list of urls to visit, the “horizon”, is empty

Take a list of input urls
Make an http GET request to retrieve the content of the page
Parse the content on the page
Stores any phone number data present on the page.
Add any parsed out links found on the page to the list of input urls to crawl.
Repeat the process until the list of urls to visit, the “horizon”, is empty.
