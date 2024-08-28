#!/bin/bash

#get the script home
_dir_=`dirname $0`
SCRIPTBIN=`cd $_dir_ 2>/dev/null && pwd || echo $_dir_`
SCRIPTHOME=${SCRIPTBIN}/..
SCRIPTLIB=${SCRIPTHOME}/lib
SCRIPTCONF=${SCRIPTHOME}/etc

#find all the library
find ${SCRIPTLIB} -type f -name "*.jar" > ${SCRIPTHOME}/.tmp
 while read file
do
        CP="${CP}:$file"
done <${SCRIPTHOME}/.tmp

java -cp ${CP} com.ericsson.eniq.events.modelservice.server.Main $*
