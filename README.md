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
   **Note:** You can't use docker ce with **WINDOWS HOME EDITION**
* [Docker-Compose](https://docs.docker.com/compose/compose-file/) - A tool that wraps up all the docker commands to set 
need to set up the environment into one easy command
  * Follow the installation guide [here](https://docs.docker.com/compose/install/)
* [PostgreSQL](https://www.postgresql.org) - This is an open source relational database(like MYSQL) that we will use to 
store our information in.
   * No need to install anything here, if docker is installed, docker will manage downloading and running this 
     application
 
## How to run the application using sbt
1. Either execute `sbt run` in a terminal or in the sbt shell in IntelliJ execute `run`
1. Open up a browser and go to the link http://localhost:9000 and the example page will show there

## How to add/update the Swagger UI
```bash
git clone git@github.com:swagger-api/swagger-ui
mkdir -p hearthapi/public/swagger
cp -R swagger-ui/dist/* hearthapi/public/swagger
```
    
## Where to get the hearthstone data?
All the data can be found at [HearthSim/hsdata](https://github.com/HearthSim/hsdata)
Process that I took in order to process/observe the data
```bash
# https://github.com/HearthSim/python-hearthstone
docker run -it -v $(pwd)/app:/app/ python:3.6.8 /bin/bash
cd app
git clone https://github.com/HearthSim/HearthstoneJSON.git
pip install hearthstone
apt-get update
apt-get install tree
```
How do I inserted the data into the DB (one off - will supply a code later)
```bash
spark-shell --packages 'postgresql:postgresql:9.1-901-1.jdbc4'
```
```scala
val df = spark.read.option("inferSchema", "true").json("cards.json")

import java.util.Properties
def buildProps(properties: (String, String)*): java.util.Properties = { val jProps = new java.util.Properties(); properties.foreach{case (key, value) => jProps.put(key, value)}; jProps }
val props = buildProps("user" -> "???", "password" -> "???", "driver" -> "org.postgresql.Driver")
def writeDF(table: String, data: org.apache.spark.sql.DataFrame) = data.write.jdbc("jdbc:postgresql://???:5432/hearth-api", table, props)
writeDF("cards", df.select('id, 'name, 'type, 'multiClassGroup, 'cardClass, 'cost, 'overload, 'attack, 'health, 'armor, 'durability, 'spellDamage, 'questReward, 'rarity, 'set, 'text, 'flavor, 'hideStats))
writeDF("card_meta", df.select('id, 'artist, 'collectible, 'collectionText, 'elite, 'faction, 'howToEarn, 'howToEarnGolden, 'puzzleType, 'race, 'targetingArrowText))
writeDF("referencedTags", df.select('id, explode('referencedTags) as "referencedTag"))
writeDF("mechanics", df.select('id, explode('mechanics) as "mechanic"))
writeDF("extra_classes", df.select('id, explode('classes) as "class").filter('class.isNotNull))
writeDF("entourages", df.select('id, 'entourage).filter('entourage.isNotNull).select('id, explode('entourage) as "entourage"))
```