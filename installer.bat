@echo off

mvn clean package && mvn antrun:run@copy_src && mvn exec:exec@installer && pause