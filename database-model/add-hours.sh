source read-config.sh

mysql -u $database -p$pw -h fergonco.es $database -e "insert into work_segments (developer_name, task_id, starting_time, ending_time) values('$developer_name', '$1', '$2', '$3');"
