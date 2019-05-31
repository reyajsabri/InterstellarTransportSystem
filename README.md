# InterstellarTransportSystem
Demo Presentation 

I have checked in Initial Design of the given assesment which includes:
   - Working functionalities (Search Shortest Path between given source and destination)
   - JUnit Tests and Integration Tests Implemented
   - Upload record and save to in-Memory database (HSQL DB) [excel, XML file format ] implemented
   - JAXB Bindings, XML Validation against Schema file, XML marshaling/unmarshaling implemented
   - User Interfaces (Search shortest route, Upload XML/Excel traffic data, Download sample traffic data) implemented
   - Sample XML, XMLSchema, Excel files avaible for download

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
   - URL for the Landing page [Index page] on local host will be: http://localhost:8080/TransportSystem/
   - URL for the Seatch page on localhost will be: http://localhost:8080/TransportSystem/Search/
   - URL for the Upload Planet/Node information page on localhost will be: http://localhost:8080/TransportSystem/RouteUpload/


Please let me know if you have any questions.

Thanks,
Reyaj
Reyazsabri2006@gmail.com
