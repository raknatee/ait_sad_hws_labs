# Spring and K8S

## Compose Configurateion files

### Dockerfile.prod
```yaml
# http://whitfin.io/speeding-up-maven-docker-builds/

FROM maven:3.6.3-jdk-8

WORKDIR /home/app
# copy the project files
COPY ./pom.xml ./pom.xml

# build all dependencies for offline use
RUN mvn dependency:go-offline -B

# copy your other files
COPY . .

# build for release

RUN mvn package

# set the startup command to run your binary
CMD ["java", "-jar", "./target/NoteTaking-0.0.1-SNAPSHOT.jar"]
```

### docker-compose.yaml
```yaml
version: "3.3"
services:
  web:
    build: 
      context: .
      dockerfile: Dockerfile.prod
    image: docker.io/raknatee/note-java:1.0.0
    ports:
      - "8080:8080"
```

In this lab, I decided to use Docker for Build jar file and Run an Application together in one Docker container.

I used docker-compose.yaml for building and tagging Image earier.

## Build Docker Image

1. Run
```bash
docker-compose build
```

2. See Result
```bash
raknatee/note-java                          1.0.0                           26317c16474e        About a minute ago   664MB
```

3. login (only for a first time)
```bash
docker login
```

3. push to Docker Hub
```bash
docker push raknatee/note-java
```

4. See the result at >> https://hub.docker.com/r/raknatee/note-java

## Argument first round: Docker for Non-Linux User
Firstly, I need to talk about Docker for Non-Linux OS because it relates to Minikube.

### 1. For Windows (old way and only Windows Pro) and MacOS via Hypervisor 

Basically, Windows and MacOS need to create a small VM which is Linux and then use Docker inside it via Hyper-V (For Windows Pro) and HyperKit (For MacOS).

### 2. For Windows Home and Pro via WSL2 on Docker Destop version

WSL2 is new virtualization technic from Microsoft which can allow Linux kernel is installed in Windows with more light-weight.

With those benefit, Windows user can use Docker in Windows better (I means faster).

### 3. For Windows Home and Pro with Ubuntu inside WSL2 and using Docker CE version (Docker CE != Docker Destop). 

Only this way For Windows, we can use Docker with GPU for ML and DL training like Linux (Ubuntu) user! That's cool right?

How to use this way:

1. Install WSL2 in Windows
2. Install Ubuntu in WSL2
3. Install Docker CE in Ubuntu
4. If you want to use GPU please install nvidia-docker for WSL2.


## My Argument with my Computer
Problem:
    
1. I used Windows OS
2. I used Docker inside Ubuntu which inside WSL2
3. It looks like this >> Docker is in Ubuntu, Ubuntu is in WSL2 and WSL2 is in Windows.
4. I think I might install Minikube in Ubuntu which already in WSL2 but I think it's not a good idea.

My Minikube setup

1. Install Minikube on Windows with Hyper-V 
2. Start Minikube
```bash
minikube start --driver=hyperv  --disk-size=20GB 
```
3. Install kubectl in Ubuntu which is in WSL2
4. set kubectl config to Minikube

```bash
kubectl config set-cluster minikube --server=https://172.23.47.255:8443  --insecure-skip-tls-verify=true
```
```bash
kubectl config  set-credentials root --username=root --password=""
```

```bash
kubectl config set-context minikube-context --cluster minikube --user=root
```

```bash
kubectl config use-context minikube-context
```
## Deploy K8S

### kube/note.yaml
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: note
spec:
  replicas: 1
  selector:
    matchLabels:
      app: note
  template:
    metadata:
      labels:
        app: note
    spec:
      containers:
        - name: note
          image: raknatee/note-java:1.0.0
          ports:
            - containerPort: 8080
---
apiVersion: v1
kind: Service
metadata:
  name: note
spec:
  selector:
    app: note
  ports:
    - port: 8080
      targetPort: 8080
      nodePort: 30001
  type: LoadBalancer

```

```bash
kubectl apply -f kube
```