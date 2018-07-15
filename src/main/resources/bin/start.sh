#! /bin/bash

java -Dspring.profiles.active=dev -Dspring.config.location=file:${user.home}/NetBeansProjects/wallethub/parser/config/parser.yml -jar parser-1.0.0.jar --accesslog=${user.home}/NetBeansProjects/wallethub/parser/input/access.log,--startDate=2017-01-01.00:00:00,--duration=daily