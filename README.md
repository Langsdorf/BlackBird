# BlackBird :bird:
[![](https://jitpack.io/v/Langsdorf/BlackBird.svg)](https://jitpack.io/#Langsdorf/BlackBird) [![](https://api.travis-ci.org/Langsdorf/BlackBird.svg?branch=master)](https://travis-ci.org/Langsdorf/BlackBird)
### Uma simples API do Twitter feito em Java usando ScribeJava :dog:.
### [English README](https://github.com/Langsdorf/BlackBird/blob/master/README_EN.md)
## Por que usar? :confused:
É simples, rápido e fácil! Por que não?

## Exemplos :point_right:
Veja a página [Exemplos](https://github.com/Langsdorf/BlackBird/tree/master/src/examples/java/com/github/langsdorf/blackbird)
## Maven :computer:
Você pode usar o BlackBird com Maven utilizando o JitPack:
```

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
	
```

## Downloads :floppy_disk:
Veja a página de [releases](https://github.com/Langsdorf/BlackBird/releases).

## Perguntas :question:
Sinta-se livre para criar uma [pergunta](https://github.com/Langsdorf/BlackBird/issues).

## Primeiros passos :bowtie:

### Usando a API com seu usuário:
#### 1. Vá até a [página de aplicativos do Twitter](https://apps.twitter.com/)
  ##### 1.1 Crie um aplicativo
  ##### 1.2 Depois de ter criado o aplicativo, vá até a aba "Keys and Access Tokens"
  ##### 1.3 Se quiser usar a API com seu usuário, vá no final da página e clique em "Create my access token"
  ##### 1.3.1 Caso você queria utilizar com outro usuário, terá que usar o modo de autenticação por PIN.
  ##### 1.4 Agora você possui Consumer Key, Consumer Secret, Access Token e Access Token Secret
  ##### 1.5 Crie o objeto BlackBird:
    ```java
    BlackBird blackBird = new BlackBird(consumerKey, consumerSecret, accessToken, accessTokenSecret);
    ```
  ##### 1.6 Tudo pronto, veja os exemplos para aprender mais! :smile:
  

### Usando a API com outro usuário:
#### 1. Vá até a [página de aplicativos do Twitter](https://apps.twitter.com/)
  ##### 1.1 Crie um aplicativo
  ##### 1.2 Depois de ter criado o aplicativo, vá até a aba "Keys and Access Tokens
  ##### 1.3 Agora você possui Consumer Key e Consumer Secret.
  ##### 1.4 Faça a autenticação:
    ```java
    URLBasedOAuth oauthURL = new URLBasedOAuth(consumerKey, consumerSecret);
    
    String URL = oauthURL.getAuthorizationUrl(); //URL para a página que o usuário deve ir para conceder o acesso.
    String pin = ""; //Coloque aqui o PIN recebido após o usuário conceder acesso.
    
    BlackBird blackBird = oauthURL.authenticate(pin);
    ```
  ##### 1.5 Tudo pronto, veja os exemplos para aprender mais! :smile:
  
 ## Pull requests:
 ## Caso você esteja interessado em ajudar, crie uma fork e sinta-se livre para criar um pull request!
 
 ## Agradecimentos:
 ### - [Twitter4j](https://github.com/yusuke/twitter4j)
 ### - [ScribeJava](https://github.com/scribejava/scribejava)
 ### - [Twitter =)](https://developer.twitter.com/)
 
 ###### Deixe uma estrela (star) :wink:
