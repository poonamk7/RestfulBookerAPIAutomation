Select branch -> master to check the framework components

This framwork is developed to automate open source Restful Booker webservice. The test cases are developed to send different requests like GET, POST, PUT, DELETE and PATCH to deal with Booking details.
Following are the key features:
1. The test cases have been run individually, using TestNG suite and usig Maven Surefire plugin.
2. Different design patterns are like Builder, Facade and Singleton are implemented to add different layers to framework and separate the code.
3. Following components are implemented to build Request Payload - POJOs, Read Request Payload from an external file, using DataProvider. 
4. Test Listeners have been added to record the test events and collated the test results in the form of Dashboard using Extent Reports and display Request and Response Json in the generated Report.
5. Created different utility methods to build Json request, read data from properties file and to validate Response Json schema.
6. Use of Lombok to reduce boilerplate code.
