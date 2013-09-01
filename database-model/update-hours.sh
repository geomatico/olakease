pw=($(<pw.dat))
database=($(<database.dat))

mysql -u $database -p$pw -h fergonco.es $database -e "update work_segments set $2 where id='$1'"