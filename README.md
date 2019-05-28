# InterstellarTransportSystem
Demo Presentation 

I have checked in Initial Design of the given assesment which includes:
   - Working functionalities (Search Shortest Path between given source and destination)
   - Working JUnit Test and Integration Test
   - Working Upload record and save to in-Memory database (HSQL DB) [excel file only Need to work on XML file format ]

Planets / Graph information Upload:
   - Excelsheet upload is functional
   - XML Upload is in-progress: JAXB bindings, XSD and XML formate implemented. 

The Design contains two part: 
   - Project: "RouteFinderCore" which is core module for the search logic
   - Project: "InterstellarTransportSystem" which is web application and having Client interfaces (UI pages) to interact with system.

To Run the Application (Most common way):
   - checkout both the project and import both project as Maven Project in your ide / environment.
   - run maven update, Build, install "RouteFinderCore" project in your ide/environment 
   - run maven update, Build, install "InterstellarTransportSystem" project [this will produce deployable war file includes all dependencies]
   - deploy "InterstellarTransportSystem-0.0.1-SNAPSHOT.war" file.

Context root of the Application will be "TransportSystem/"
e.g. http://localhost:8080/TransportSystem/
URL for the Landing page [Index page] on local host will be: http://localhost:8080/TransportSystem/
URL for the Seatch page on localhost will be: http://localhost:8080/TransportSystem/Search/
URL for the Upload Planet/Node information page on localhost will be: http://localhost:8080/TransportSystem/RouteUpload/


Please let me know if you have any questions.

Thanks,
Reyaj
Reyazsabri2006@gmail.com
