root_dir=/mnt/c/ProgramData/Jenkins/.jenkins/workspace/jmail
echo root_dir=$root_dir
cd $root_dir || exit 2
mkdir log
mvn package > log/maven.log
if [[ $? != 0 ]]; then
  echo "ERROR"
else
  echo "SUCCESS"
fi