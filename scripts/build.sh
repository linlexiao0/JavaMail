mkdir log
mvn package > log/maven.log
if [[ $? == 0 ]]; then
  echo "ERROR"
else
  echo "SUCCESS"
fi