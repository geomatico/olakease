pw=($(<pw.dat))
database=($(<database.dat))

date=`date +%Y-%m-%d`
[ $4 ] && date=$4

mysql -u $database -p$pw -h fergonco.es $database -e "insert into work_segments (developer_name, task_id, num_hours, working_date) values('$1', '$2', $3, '$date');"