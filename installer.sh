#!/usr/bin/env bash

mvn clean package && mvn antrun:run@copy_src && mvn exec:exec@installer