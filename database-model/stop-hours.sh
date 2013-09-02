pw=($(<pw.dat))
database=($(<database.dat))

now=`date +%Y-%m-%d\ %H:%M`

mysql -u $database -p$pw -h fergonco.es $database -e "update work_segments set ending_time='$now' where developer_name='$1' and ending_time is null;"
