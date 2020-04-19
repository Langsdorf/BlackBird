# BlackBird :bird:
[![](https://jitpack.io/v/Langsdorf/BlackBird.svg)](https://jitpack.io/#Langsdorf/BlackBird) [![](https://api.travis-ci.org/Langsdorf/BlackBird.svg?branch=master)](https://travis-ci.org/Langsdorf/BlackBird)
### A simple Twitter API for Java using ScribeJava :dog:.
#### Learning project

## Why BlackBird? :confused:
It's simple, fast and easy! Why not?

## Examples :point_right:
Check out the [Examples](https://github.com/Langsdorf/BlackBird/tree/master/src/examples/java/com/github/langsdorf/blackbird) page.
## Maven :computer:
You can use BlackBird with Maven by using JitPack:
`

	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

	<dependency>
	    <groupId>com.github.Langsdorf</groupId>
	    <artifactId>BlackBird</artifactId>
	    <version>1.0</version>
	</dependency>


## Downloads :floppy_disk:
Check out the [releases](https://github.com/Langsdorf/BlackBird/releases) page.

## Questions :question:
Feel free to create an [issue](https://github.com/Langsdorf/BlackBird/issues).

## Getting Started :bowtie:

### Using the API with your user:
#### 1. Go to the [Twitter Apps Page](https://apps.twitter.com/)
##### 1.1 Create an application
##### 1.2 Once you have created the application, go to the "Keys and Access Tokens" page
##### 1.3 Go to the bottom of the page and click on "Create my access token"
##### 1.4 Now you have Consumer Key, Consumer Secret, Access Token and Access Token Secret
##### 1.5 Create the BlackBird object:

```java
BlackBird blackBird = new BlackBird(consumerKey, consumerSecret, accessToken, accessTokenSecret);
```
##### 1.6 That's it, see examples to learn more! :smile:

### Using the API with another User:
#### 1. Go to the [Twitter Apps Page] (https://apps.twitter.com/)
##### 1.1 Create an application
##### 1.2 Once you have created the application, go to the "Keys and Access Tokens" page
##### 1.3 You now have Consumer Key and Consumer Secret.
##### 1.4 Authenticate:
```java
URLBasedOAuth oauthURL = new URLBasedOAuth(consumerKey, consumerSecret);

String URL = oauthURL.getAuthorizationUrl(); // URL to the page that the user must go to grant the access.
String pin = ""; // Enter the PIN received after the user grants access.

BlackBird blackBird = oauthURL.authenticate(pin);
```
##### 1.5 That's it, see examples to learn more! :smile:

## Pull requests:
### If you are interested in helping, create a fork and feel free to create a pull request!

## Thanks:
### - [Twitter4j](https://github.com/yusuke/twitter4j)
### - [ScribeJava](https://github.com/scribejava/scribejava)
### - [Twitter =)](https://developer.twitter.com/)
