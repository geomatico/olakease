pw=($(<pw.dat))
database=($(<database.dat))

where=''
[ $1 ] && where="where developer_name='$1'"

mysql -u $database -p$pw -h fergonco.es $database -e "select * from allocations $where order by start ASC"