call mvn clean install

docker rm -f ms-auth

docker image rm darren/ms-auth

call docker build . -t darren/ms-auth

call docker run -d --name ms-auth -p 8091:8080 darren/ms-auth
