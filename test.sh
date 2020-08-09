#!/bin/bash

if [[ $# -eq 0 ]] ; then
    echo 'Please re-run the test using the command: ./test ${user_id}'
    exit 0
fi

for word in 'Lorem' 'ipsum' 'dolor' 'sit' 'amet,' 'consectetur' 'adipiscing' 'elit'
do
    curl http://localhost:8080/eis/users/$1/words/$word

    sleep 2
    echo '.'
done