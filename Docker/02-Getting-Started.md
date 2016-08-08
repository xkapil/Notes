## Getting started with Docker

#### Docker Toolbox

* ***Docker Engine*** is a client-server application. It has a server which is a daemon process. It includes a REST Api to interact with server, and a CLI command line, which internally uses the said REST api.
* In our project, we are using the python library which internally calls the REST api for the interaction with the server.

* ***Docker Machine*** is a tool that lets you install Docker Engine on virtual hosts, and manage the hosts with `docker-machine` commands. You can use Machine to create Docker hosts on your local Mac or Windows box, on your company network, in your data center, or on cloud providers like AWS or Digital Ocean.
* Using `docker-machine` commands, you can *start*, *inspect*, *stop*, and *restart* a managed host, upgrade the Docker client and daemon, and configure a Docker client to talk to your host.
* Point the Machine CLI at a running, managed host, and you can run `docker` commands directly on that host. For example, run `docker-machine env default` to point to a host called *default*, follow on-screen instructions to complete env setup, and run `docker ps`, `docker run hello-world`, and so forth.
* Machine was the only way to run Docker on Mac or Windows previous to *Docker v1.12*. Starting with the beta program and Docker v1.12, Docker for Mac and Docker for Windows are available as native apps

* When people say ***Docker***, they typically mean *Docker Engine*.

#### Docker Architecture

  ![VM](images/architecture.svg)

* Both the Docker client and the daemon can run on the same system, or you can connect a Docker client to a remote Docker daemon. The Docker client and daemon communicate via sockets or through a RESTful API.

* To understand Docker’s internals, you need to know about three resources:
  * Docker images
  * Docker registries
  * Docker containers

* **Docker Image** is a read-only template. For example, an image could contain an Ubuntu operating system with Apache and your web application installed. Images are used to create Docker containers. Docker provides a simple way to build new images or update existing images, or you can download Docker images that other people have already created. Docker images are the *build* component of Docker.

* **Docker Registries** hold images. These are public or private stores from which you upload or download images. The public Docker registry is provided with the [Docker Hub](https://hub.docker.com/). It serves a huge collection of existing images for your use. These can be images you create yourself or you can use images that others have previously created. Docker registries are the *distribution* component of Docker.

* A **Docker Container** holds everything that is needed for an application to run. Each container is created from a Docker image. Docker containers can be run, started, stopped, moved, and deleted. Each container is an isolated and secure application platform. Docker containers are the *run* component of Docker.

* `docker run <image_name> <command>` will:
  * pull the image from the repository (eg. Docker Hub) if it doesn't exist on the host machine.
  * Create a container from the said image
  * The container is created in the file system and a read-write layer is added to the image.
  * Creates a network interface that allows the Docker container to talk to the local host.
  * Finds and attaches an available IP address from a pool.
  * Execute the <command> provided on the container.
  * Connects and logs standard input, outputs and errors for you to see how your application is running.
* Eg. `docker run -i -t ubuntu /bin/bash` will get the latest ubuntu image from Docker Hub, and executes the `bash` command. Switch -i is for interactive and -t to get to the terminal (--tty)


#### Useful Commands

* `docker images` to see the images on your host.
* `docker rmi <image-name>` to remove an image.
* `docker run <image-name>` to pull and load the image.
* `docker ps` to see all running containers
* `docker ps -a` to see all containers.
* `docker rm <container-id>` to remove a container.
* `docker start/stop <container-id>` to start/stop a container.
* `docker pull/push <namespace>/<image-name>` to pull/push image from the registry. *namespace* is nothing but your user-id on Docker Hub (or any registry)

#### Build your own image

* One can leverage an existing image to create a new image.
* User need to create a *Dockerfile* carrying the configuration for the new machine.
* A sample *Dockerfile* :

  ```
  FROM ubuntu:14.04
  MAINTAINER Kate Smith <ksmith@example.com>
  RUN apt-get update && apt-get install -y ruby ruby-dev
  RUN gem install sinatra
  ```

* Run `docker build -t <namespace>/<new-image-name> -f <path-to-Dockerfile>`
* Now you may want to push the image to Docker Hub by `docker push namespace>/<new-image-name>`.
