source read-config.sh

mysql -u $database -p$pw -h fergonco.es $database -e "insert into projects (name, description, budget_amount) values('$1', '$2', $3);"