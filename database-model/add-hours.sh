pw=($(<pw.dat))
database=($(<database.dat))

mysql -u $database -p$pw -h fergonco.es $database -e "insert into work_segments (developer_name, task_id, starting_time, ending_time) values('$1', '$2', '$3', '$4');"
