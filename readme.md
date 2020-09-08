### How to use this spring-boot project

- Install packages with `mvn package`
- Run `mvn spring-boot:run` for starting the application (or use your IDE)

Application (with the embedded H2 database) is ready to be used ! You can access the url below for testing it :

- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 UI : http://localhost:8080/h2-console

> Don't forget to set the `JDBC URL` value as `jdbc:h2:mem:testdb` for H2 UI.



### Instructions

- download the zip file of this project
- create a repository in your own github named 'java-challenge'
- clone your repository in a folder on your machine
- extract the zip file in this folder
- commit and push

- Enhance the code in any ways you can see, you are free! Some possibilities:
  - Add tests
  - Change syntax
  - Protect controller end points
  - Add caching logic for database calls
  - Improve doc and comments
  - Fix any bug you might find
- Edit readme.md and add any comments. It can be about what you did, what you would have done if you had more time, etc.
- Send us the link of your repository.

#### Restrictions
- use java 8


#### What we will look for
- Readability of your code
- Documentation
- Comments in your code 
- Appropriate usage of spring boot
- Appropriate usage of packages
- Is the application running as expected
- No performance issues


### Notes for reviewer

#### Before starting

I don't have any previous experience in Java, so please try to keep that in mind. I focused more on the node application.

First I had to setup the entire environment, which took a while due to my quite slow internet connection and since it was
my first time setting up a Java development environment. I used SDK to install java, added various extensions on VSCode
and got it to work.

#### What I did

First I started by trying to add authentication. 

Updated to JUnit5. 

Created HelloController for learning how to test, then creating some tests for EmployeeController.

Removed scope from lombok, it caused getters to be undefined.

Cached getEmployee. 

#### If I had a lot more time

I could have properly studied Java and could have made the EmployeeRepositoryTest work as well.
As I said, I have zero experience in Java, but the basics don't seem too hard, and it kind of 
reminds me of Typescript (I switched to Typescript on the node santa app). It's more about
knowing the peculiarities of various libraries and how to use them I think, that a bit too much
for this short time.