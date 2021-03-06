                      Parser v1.0.0

  System Requirements
  -------------------

  JDK:
    1.8

  MySQL:
    5.7 or above.
  

  Installing Parser
  -----------------

  1) Unpack the archive to your preferred directory, e.g.:

      cd /Users/rommelmedina/NetBeansProjects/wallethub

      unzip parser-1.0.0-dist.zip

  2) A directory called "parser-1.0.0" will be created.


  Running Parser
  --------------

  1) Make sure JAVA_HOME is set to the location of your JDK

  2) Login to MySql Server.

  3) Execute scripts for creating the log data schema inside log-data-schema.sql under /parser-1.0.0/sql

  4) Set MySQL username and password of the application.

    a) Open application.yml under /parser-1.0.0/config

    b) Change the value of username and password with the correct values.

    c) Save and close.

  5) Open Terminal or CommandPrompt and set working directory to /parser-1.0.0/bin. e.g.:

      cd /Users/rommelmedina/NetBeansProjects/wallethub/parser-1.0.0/bin

  6) Run the Parser Application by executing:

      "sh start.sh"

      or

      "java -Dspring.profiles.active=dev -Dspring.config.location=../config/application.yml -jar ../bin/parser-1.0.0.jar --accesslog=../input/access.log --startDate=2017-01-01.00:00:00 --duration=daily --threshold=500"

  7) Verify the result on the console or by executing the following queries on MySQL:

    a) Login to MySQL Server.

    b) Execute the following scripts:

        - USE report;

        - SELECT count(0) FROM access_log;

        - SELECT * FROM blocked_ip;


  Setting up Local Development Environment (Using NetBeans IDE 8.2)
  -----------------------------------------------------------------

  1) Unpack the archive to your preferred directory, e.g.:

      cd /Users/rommelmedina/NetBeansProjects/wallethub

      unzip parser-1.0.0-src.zip

  2) A directory called "parser" will be created.

  3) Open Netbeans IDE.

  4) Open project by clicking Open Project under File menu.

  5) Make sure to set the follwing JVM Args:
    
    a) -Dspring.profiles.active=dev 

    b) -Dspring.config.location=src/main/resources/application.yml

  6) Run Project (F6).

                        *** END ***

