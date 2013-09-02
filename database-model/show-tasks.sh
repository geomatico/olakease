pw=($(<pw.dat))
database=($(<database.dat))

where=''
[ $1 ] && where="where project_name='$1'"

mysql -u $database -p$pw -h fergonco.es $database -e "select * from tasks $where"
