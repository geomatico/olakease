source read-config.sh

mysql -u $database -p$pw -h fergonco.es $database -e "update tasks set $2 where id=$1"