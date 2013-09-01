pw=($(<pw.dat))
database=($(<database.dat))

mysql -u $database -p$pw -h fergonco.es $database -e "insert into allocations (project_name, developer_name, start, finish) values('$1', '$2', '$3', '$4');"