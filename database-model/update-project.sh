source read-config.sh

mysql -u $database -p$pw -h fergonco.es $database -e "update projects set $2 where name='$1'"
