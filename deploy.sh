#!/usr/bin/env bash

mvn clean package && mvn exec:exec@deploy-app