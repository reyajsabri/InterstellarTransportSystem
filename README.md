# InterstellarTransportSystem
Demo Presentation 

I have checked in Initial Design of the given assesment which includes:
   - Working functionalities (Search Shortest Path between given source and destination)
   - Working JUnit Test and Integration Test
   - Working Upload record and save to in-Memory database (HSQL DB) [excel file only Need to work on XML file format ]

The Design contains two part: 
      Project: "RouteFinderCore" which is core module for the search logic
      Project: "InterstellarTransportSystem" which is web application and having Client interfaces (UI pages) to interact with system.

Note: due to time constrain, XML format of the file-upload is not implemented. Probably tomorrow I will complete it. However, excel file-upload is implemented.

To Run the Application:
   - run maven Build, install "RouteFinderCore" project in your ide/environment 
   - run maven Build, install "InterstellarTransportSystem" project [this will produce deployable war file includes all dependencies]
   - deploy "InterstellarTransportSystem-0.0.1-SNAPSHOT.war" file.

Context root of the Application will be "TransportSystem/" 
URL for the Landing page [Index page] on local host will be: http://localhost:8080/TransportSystem/
URL for the Seatch page on localhost will be: http://localhost:8080/TransportSystem/Search/
URL for the Upload Planet/Node information page on localhost will be: http://localhost:8080/TransportSystem/RouteUpload/


Please let me know if you have any questions.
