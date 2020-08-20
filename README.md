### 🌿 Spring + Atmosphere boilerplate

My fullstack development template, including Spring security with databsae session management, Atmosphere connection between the frontend and backend and typesafe sql.

#### Libraries/Frameworks used:

- Backend
    - [Spring](https://spring.io)
    - [Docker](https://www.docker.com)
    - [HikariCP](https://github.com/brettwooldridge/HikariCP)
    - [Jetbrains Exposed](https://github.com/JetBrains/Exposed)
    - [Atmosphere](https://github.com/Atmosphere/)
    - [Gson](https://github.com/google/gson)

- Frontend
    - [Angular](https://angular.io)
    - [Nuxt](https://nuxtjs.org)

---

### 🛠 Installation (backend)

Quick set up for development environment.

Requirements |
------------ |
Java SDK (i used 11) |
Gradle |
Intellij IDEA |

You just need to set up the environment variables for the database if you want to use postgresql.

For mysql take a look at [this wiki](https://github.com/JetBrains/Exposed/wiki/DataBase-and-DataSource).

- Open your spring "Run/Debug Configuration" in Intellij IDEA and edit the enrivonment variables

    ![set up environment variables](https://i.imgur.com/yJxnYpZ.png)
    
    Make sure you check "Include system environment variables".

---

### 🚅 Deployment (backend)

#### *Building*
    gradlew build

#### *Publishing (own linux server)*
If you don't have docker just run ``curl -L https://get.docker.com | bash`` as root.

1. Upload all files from `build/libs` to your server and create a docker image from it.
    ```shell script
   docker build -t backend .
    ```

2. Put your database connection data in a [docker secret](https://docs.docker.com/engine/swarm/secrets/).
    ```shell script
   printf "5432"             | docker secret create database_port - &&
   printf "postgres"         | docker secret create database_name - &&
   printf "postgres"         | docker secret create database_user - &&
   printf "127.0.0.1"        | docker secret create database_host - &&
   printf "mysecurepassword" | docker secret create database_pass -
    ```

3. Create a service with your backend image
    ```shell script
   docker service create \
        --name backend \
        --secret database_host \
        --secret database_port \
        --secret database_name \
        --secret database_user \
        --secret database_pass \
        --publish published=80,target=8080 \
        --publish published=8443,target=8443 \
        backend:latest
    ```

Now you successfully deployed the backend server. If you are not familiar with Docker, here are some useful commands. [https://docs.docker.com/engine/reference/commandline/service/](https://docs.docker.com/engine/reference/commandline/service/)

---

### 🚅 Deployment (Frontend)
1. Go to the folder of your favourite Framework.

2. Build the static files
   ```
   npm build
   ```
   
3. Publish it.

    There are many great free and paid services to host the files. Here are some sites:
    
    Name | Bandwidth/month
    ------------ | ------------
    [Vercel](https://vercel.com/) | Unlimited
    [Netlify](https://www.netlify.com/) | 100 GB
    [Render](https://render.com/) | 100 GB
    [Surge](https://surge.sh/) | Unlimited
