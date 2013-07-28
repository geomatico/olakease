Introduction
============

This documentation describes the different modules in the application that can be reused in other application. A module is a javascript object that:

# initializes through an init method
# listens to zero or more events through the jquery event bus pattern (trigger and bind)
# reacts to the events doing some task like modifying the DOM, communicating with the server, etc.
# produces some event back on the application

''init'' methods are documented inline and this documentation only refers to the events the different modules listen and trigger.


- 