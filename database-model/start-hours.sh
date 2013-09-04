source read-config.sh

now=`date +%Y-%m-%d\ %H:%M`

mysql -u $database -p$pw -h fergonco.es $database -e "update work_segments set ending_time='$now' where developer_name='$developer_name' and ending_time is null;"
mysql -u $database -p$pw -h fergonco.es $database -e "insert into work_segments (developer_name, task_id, starting_time, ending_time) values('$developer_name', '$1', '$now', null);"
