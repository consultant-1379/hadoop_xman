#!/bin/bash


#Deletes eps services not applicable for given server.
#All eps instanses suitable for deletion should be listed in exclude_services file


host_name=$(hostname)

echo "In EPS server: $host_name"

for service in $(cat ${full_install_path}/exclude_services_$host_name)
do
	
	echo "excluding service: $service"
	chkconfig --del $service
	rm -rf /etc/init.d/$service

done;
