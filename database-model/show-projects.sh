pw=($(<pw.dat))
database=($(<database.dat))

echo PROJECTS TO BILL
mysql -u $database -p$pw -h fergonco.es $database -e "select * from projects_to_bill"
echo
echo PROJECTS BEING DEVELOPED
mysql -u $database -p$pw -h fergonco.es $database -e "select * from projects_at_work"
echo
echo PROJECTS WAITING
mysql -u $database -p$pw -h fergonco.es $database -e "select * from waiting_projects"
echo
echo TOTAL = `mysql -u $database -p$pw -h fergonco.es $database -e "select count(*) as '' from projects"` PROJECTS
