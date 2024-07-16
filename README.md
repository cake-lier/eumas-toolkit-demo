# Yggdrasil demo for EUMAS toolkit papers track

This repository demonstrates the features shipped with the latest version of Yggdrasil,
part of the "HyperAgents" project,
as described in its presenting paper submitted to the EUMAS toolkit papers track.

## Prerequisites

* Java 21+
* Gradle 8.4+
* Docker 26.1.4+

## Setup

The current repository structure uses [jacamo-hypermedia](https://github.com/HyperAgents/jacamo-hypermedia) but as a git submodule.
So, you'll need to clone the repository recursively, along with its submodules.

### Cloning the repository

1. Clone this repository using the following command:

```bash
git clone --recursive git@github.com:cake-lier/eumas-toolkit-demo.git
```

2. Position yourself in the project folder, for example, using a command like the following:

```bash
cd eumas-toolkit-demo
```

3. Update all the submodules with: 

```bash
git submodule update
```

At this point, you're ready for running the demo!

### Running the demo

⚠️ Since the host needs access to the containers launched for the demo,
please ensure ports 8080, 8081, and 8082 are available.
If not,
update the ports
to open in all configuration files in the ```environment/src/main/resources/conf``` folder and also in the "hypermedia_agents.jcm"
configuration file in the ```hypermedia-agents``` folder before building the JAR. ⚠️

1. Build the Yggdrasil framework JAR file using the command: 

```bash
./gradlew environment:shadowJar
```

2. Launch the containers containing the three hypermedia workspaces using the command:

```bash
docker compose up
```

3. Wait until the following messages appear onscreen:

```
env_production   | [vert.x-eventloop-thread-0] INFO io.vertx.core.impl.launcher.commands.VertxIsolatedDeployer - Succeeded in deploying verticle
env_shared       | [vert.x-eventloop-thread-0] INFO io.vertx.core.impl.launcher.commands.VertxIsolatedDeployer - Succeeded in deploying verticle
env_consumption  | [vert.x-eventloop-thread-0] INFO io.vertx.core.impl.launcher.commands.VertxIsolatedDeployer - Succeeded in deploying verticle
```

⚠️ Pay attention to the fact that these messages could get buried between many others,
so review the logs the containers produce with care! ⚠️

4. Add hyperlinks across the three hypermedia workspaces by running the following script in a different shell from the one you used for the Docker command:

```bash
./scripts/add_remote_workspaces.sh
```

You can now browse the distributed hypermedia workspace from this [entrypoint](http://localhost:8082/workspaces/manufacturing#workspace).

5. Use the following command to start the JaCaMo platform containing the hypermedia agents in a different shell from the one you used for the Docker command:

```bash
./gradlew hypermedia-agents:runAgents
``` 

When you see a Java Swing window appearing, the setup is complete!
You can now examine what gets printed in it to [see the system at work](#what-is-happening).

## Clean-up

To close the window showing the output of the JaCaMo platform, press its "stop" button.
It will orderly shut down the whole agent platform without trouble.
To terminate the containers with the workspaces in them,
press CTRL+C in the shell executing the `docker compose up` command, and then,
after the containers stop, run the command `docker compose down` to delete the containers.
If you want a complete cleanup, delete the Yggdrasil image by running the command `docker image rm environment`.

## What is happening?

TODO

## Final remarks

TODO