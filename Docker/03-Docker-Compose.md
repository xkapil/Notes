## Docker Compose

* ***Docker Compose*** is a way of defining and running multi-container distributed applications with Docker.
* With *Compose*, you use a Compose file to configure your application’s services. Then, using a single command, you create and start all the services from your configuration.
* *Compose* has commands to manage the entire lifecycle of your application.
* A sample `docker-compose.yml` :

  ```
  web:
      image: wordpress
      links:
       - mysql
      environment:
       - WORDPRESS_DB_PASSWORD=sample
      ports:
       - "127.0.0.3:8080:80"
  mysql:
      image: mysql:latest
      volumes:
          - .:/code
      environment:
          - MYSQL_ROOT_PASSWORD=sample
          - MYSQL_DATABASE=wordpress
  ```

* The above file states that there are two services required for a given application viz. *web* and *mysql*. These two will be running in separate containers.
* `links:` tells docker-compose that the service *web* is linked with *mysql*.
* `volumes` tells docker-compose to load them from your local file system.
* `ports "127.0.0.3:8080:80"` tells docker to expose port 80 of the container on the host machine to 127.0.0.3:8080.
* `ports 8080:80` would have bound the port 80 of container to port 8080 of host machine.

* You can execute `docker-compose -f <path-to-compose.yml> up` to start all the services. If the images do not exist locally, they will be pulled from the registry.
* `-f <file-path>` is optional. By default, docker-compose will look for docker-compose.yml in your current directory.
* `docker-compose up -d` will start the containers in the background.
* `docker-compose down` will stop and remove the containers.
* `docker-compose start/stop` will start/stop any existing containers.
* `docker-compose logs <service-name>` will show you the logs. <service-name> is optional.

#### Project name
* By default, when you start the containers using the command `docker-compose up`, it uses the current directory as project name, which is prefixed to the name of the containers.
* You can override this behaviour by providing your own custom project name using `-p`. Eg. `docker-compose -p myproject up`.
* You will be required to use the same project name while using other commands such as down/start/stop.

#### Scaling

* A sample docker-compose.yml:
  ```
  web:
      image: nginx
  lb:
      image: dockercloud/haproxy
      ports:
        - 8080:80
      links:
        - web
  ```

* Running `docker-compose scale web=3 up` will scale the *web* i.e. nginx service to 3 containers, and links them to the load balancer.
* `8080:80` tells docker to expose the port 80 of container to host machine's port 8080.
* Performing `curl http://localhost:8080` will forward the request to load balancer, which in turn will forward the request to one of the web/nginx container in round robin fashion.
* Running `docker-compose logs` will show you the GET requests.
