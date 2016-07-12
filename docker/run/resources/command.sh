#!/usr/bin/env bash

# Copy to plugins
cp /data/build/distributions/*.xldp /opt/xld/server/plugins

# Link `ext` folder
ln -s -f /data/src/main/resources /opt/xld/server/ext

# Start XLD and
# Run XLD CLI xld_initialize
count=0
/opt/xld/server/bin/run.sh &
echo "Waiting for server to start..."
sleep 10
while true
do
  if [ $count -le 25 ]; then

    wget --spider -q http://localhost:4516
    if [ $? -ne 0 ] ;then
      echo "waiting $count"
      tail -1 /opt/xld/server/log/deployit.log
      sleep 4
      count=$(( count+1 ))
    else
      sleep 4
      echo "Website is up"
      /opt/xld/cli/bin/cli.sh -username admin -password admin -f /data/build/resources/test/docker/initialize/xld_initialize.py
      res=$?
      if [ $res != 0 ] ; then
        exit $res
      fi
      sleep 4
      exit
    fi
  else
    echo "Timeout exceeded...giving up waiting for website"
    exit 1
  fi
done