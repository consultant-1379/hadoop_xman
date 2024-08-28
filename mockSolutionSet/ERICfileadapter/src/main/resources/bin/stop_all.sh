#!/bin/bash

#stops all eps services registered on current service

for service in /etc/init.d/mock*
do
	$service stop
done
