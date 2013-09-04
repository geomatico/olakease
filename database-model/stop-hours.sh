source read-config.sh

now=`date +%Y-%m-%d\ %H:%M`

mysql -u $database -p$pw -h fergonco.es $database -e "update work_segments set ending_time='$now' where developer_name='$developer_name' and ending_time is null;"
