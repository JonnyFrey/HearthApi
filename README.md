# HearthApi
REST API for building a smarter kind of hearthstone deck

# Development Quickstart

## Required Resources
Here are the resource that are needed in order to develop this project. Note that some of the resources you will not need
to install locally.
* [IntelliJ](https://www.jetbrains.com/idea/) - This is the ide("IDEA" if you wanna fight) that we'll be using for this 
project
  * Follow the installation guide [here](https://www.jetbrains.com/idea/download/)
* [sbt](https://www.scala-sbt.org/) - This is the build tool that will be responsible for compiling and running our play 
app
   * Follow the installation guide [here](https://www.scala-sbt.org/release/docs/Setup.html)
* [Play](https://www.playframework.com/documentation/2.7.x/Home) - This is the web framework written in scala that will 
responsible for running our web server
   * No need to install anything here, if sbt is installed, sbt will manage pulling this in as a dependency
* [Postman](https://www.getpostman.com) - This is a convenient app that can do rest requests that we can use against our 
api
   * Follow the installation guide [here](https://www.getpostman.com/downloads/)
* [Docker](https://www.docker.com) - A tool that allows us to build, deploy and run applications using _containers_ so 
we don't need to install everything in order for our application to run. This also gives us a common platform (like the 
JVM for instance) for us to be using the same applications even though are environments are different.
   * Follow the installation guide [here](https://www.docker.com/get-started) _"Yes that does mean creating an account"_
* [PostgreSQL](https://www.postgresql.org) - This is an open source relational database(like MYSQL) that we will use to 
store our information in.
   * No need to install anything here, if docker is installed, docker will manage downloading and running this 
     application
 
## How to run the application using sbt
1. Either execute `sbt run` in a terminal or in the sbt shell in IntelliJ execute `run`
1. Open up a browser and go to the link http://localhost:9000 and the example page will show there