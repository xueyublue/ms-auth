call mvn clean install

call docker rm -f ms-auth

call docker image rm darren/ms-auth

call docker build . -t darren/ms-auth

call docker run -d --name ms-auth -p 8091:8080 darren/ms-auth
