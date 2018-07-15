#! /bin/bash

java -Dspring.profiles.active=dev -Dspring.config.location=../config/application.yml -jar ../bin/parser-1.0.0.jar --accesslog=../input/access.log --startDate=2017-01-01.00:00:00 --duration=daily --threshold=500