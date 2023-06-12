# File system navigation app

Client-server application that allows you to navigate the server's file system

* Server implementation: Java (Spring framework), bash, Docker, PostgreSQL
* Client implementation: React

## Usage

### Server

1. Clone project to your local
      ```sh
        git clone git@github.com:hetot/navigation-app.git <your-folder-name>
      ```
2. Go to project root and create jar file of project
      ```sh
        mvn clean install
      ```
3. Build docker image of the application
      ```sh
        docker build -t navigation-app .
      ```
4. Run application with docker-compose
      ```sh
        docker compose up -d
      ```

### Client

1. Checkout to the branch `front` and start app
      ```sh
        git checkout front
        npm start
      ```