pw=($(<pw.dat))
database=($(<database.dat))

mysql -u $database -p$pw -h fergonco.es $database -e "insert into tasks (project_name, description, hours) values('$1', '$2', $3);"