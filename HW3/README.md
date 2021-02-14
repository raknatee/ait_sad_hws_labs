# How to install

## Builder -

## Singleton -

## Factory -

## Adapter -

## Decorator -

## State



## install eclipse 
just install and wait

## create pom.xml (dependencies)

follow this > [https://spring.io/quickstart](https://spring.io/quickstart)

## Update how to development on Docker (optional) (22/1/2021)
I found how to development inside Docker container we need 2 things
### 1. first, I need configurate our project

#### 1.1 add devtools dependency 
```xml
<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-devtools</artifactId>
		<optional>true</optional>
</dependency>
````

#### 1.2 configurate build dependency to do not exclude Devtools on build stage 
```xml
<build>
	<plugins>
		<plugin>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-maven-plugin</artifactId>
			<configuration>
				<excludeDevtools>false</excludeDevtools>
			</configuration>
		</plugin>
	</plugins>
</build>
````

#### 1.3 add secret in /src/resources/application.properties
```xml
spring.devtools.remote.secret=secret
````

### 2. use Eclipse or any IDE that support Remote Development on Java App
#### 1. open your project_dir
#### 2. select run with configurations
#### 3. using this Main Class
```xml
org.springframework.boot.devtools.RemoteSpringApplication
```` 
#### 4. use this or whatever your host for Program Argument
```xml
http://localhost:8080
````