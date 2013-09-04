source read-config.sh

mysql -u $database -p$pw -h fergonco.es $database -e "select * from allocations where developer_name='$developer_name' order by start ASC"