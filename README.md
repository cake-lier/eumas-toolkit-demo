# Yggdrasil's demo

This repository demonstrates the features shipped with the latest version of Yggdrasil, part of the "HyperAgents" project.

## Prerequisites

* Java 21+
* Gradle 8.4+
* Docker 26.1.4+

## How do I run it?

1. Position yourself in the project folder, for example, using the ```cd``` command.
2. Build the Yggdrasil platform JAR file using the ```./gradlew environment:shadowJar``` command.
3. Launch the JAR file using the ```docker compose up``` command. 
⚠️ **Since the platform container uses the "host" network,
please make sure ports 8080, 8081 and 8082 are available for Yggdrasil. 
If not, update the ports to open in all Yggdrasil's configuration files in the ```environment/src/main/resources/conf``` folder and also in the "agents.jcm" 
configuration file in the ```agents``` folder before building the JAR.** ⚠️
4. Wait until the following messages appear onscreen.

```
env_production   | [vert.x-eventloop-thread-0] INFO io.vertx.core.impl.launcher.commands.VertxIsolatedDeployer - Succeeded in deploying verticle
env_shared       | [vert.x-eventloop-thread-0] INFO io.vertx.core.impl.launcher.commands.VertxIsolatedDeployer - Succeeded in deploying verticle
env_consumption  | [vert.x-eventloop-thread-0] INFO io.vertx.core.impl.launcher.commands.VertxIsolatedDeployer - Succeeded in deploying verticle
```

5. Add hyperlinks across the 3 hypermedia workspaces by running the following script:

```
./scripts/add_remote_workspaces.sh
```

You can now browse the distributed hypermedia workspace from the following entry point: http://localhost:8082/workspaces/manufacturing#workspace

6. Since the environment platform is now running, the JaCaMo platform containing the agents needs to be started. 
Use the command ```./gradlew agents:runHypermediaAgents``` in a different shell from the one you used for the Docker command.
7. The system has now finished running, so we can examine its logs.

## Clean-up

To close the two windows showing the logs of the JaCaMo platforms, press their "stop" button.
To terminate the Yggdrasil platform, press CTRL+C in the shell which is executing it, and then,
after the container stops, run the command ```docker compose down``` to delete the container. 
If you want a complete cleanup, delete the Yggdrasil image by running the command ```docker image rm environment```.

## What is happening?

TODO

## Final remarks

TODO