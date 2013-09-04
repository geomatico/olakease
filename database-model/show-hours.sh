source read-config.sh

mysql -u $database -p$pw -h fergonco.es $database -e "select * from work_segments where developer_name='$developer_name'"
