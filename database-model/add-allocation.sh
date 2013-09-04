source read-config.sh

mysql -u $database -p$pw -h fergonco.es $database -e "insert into allocations (project_name, developer_name, start, finish) values('$1', '$developer_name', '$2', '$3');"