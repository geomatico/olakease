pw=($(<pw.dat))
database=($(<database.dat))

mysql -u $database -p$pw -h fergonco.es $database -e "select * from tasks where project_name='$1'"
