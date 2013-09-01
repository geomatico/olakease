pw=($(<pw.dat))
database=($(<database.dat))

mysql -u $database -p$pw -h fergonco.es $database -e "update projects set $ where name='$1'"