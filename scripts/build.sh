script_dir=$(dirname $(readlink -f $0))
root_dir=$script_dir/..
cd $root_dir || exit 2
mkdir log
mvn package > log/maven.log
if [[ $? == 0 ]]; then
  echo "ERROR"
else
  echo "SUCCESS"
fi