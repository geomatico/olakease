source read-config.sh

echo PROJECTS TO BILL
echo =================
mysql -u $database -p$pw -h fergonco.es $database -e "select * from projects_to_bill"
echo
echo PROJECTS BEING DEVELOPED
echo =================
for project in `mysql -u $database -p$pw -h fergonco.es $database -e "select name as '' from projects_at_work"`
do
 echo  $project
 echo  ---------
 mysql -u $database -p$pw -h fergonco.es $database -e "select sum(t.hours) as estimated, sum((select sum(time_to_sec(timediff(s.ending_time, s.starting_time)))/3600 from work_segments s where s.task_id=t.id)) as worked from tasks t where t.project_name='$project'" 
 mysql -u $database -p$pw -h fergonco.es $database -e "select t.description, t.hours as estimated, (select sum(time_to_sec(timediff(s.ending_time, s.starting_time)))/3600 from work_segments s where s.task_id=t.id) as worked from tasks t where t.project_name='$project'" 
done
echo
echo PROJECTS WAITING
echo =================
mysql -u $database -p$pw -h fergonco.es $database -e "select * from waiting_projects"
echo
echo TOTAL = `mysql -u $database -p$pw -h fergonco.es $database -e "select count(*) as '' from projects"` PROJECTS
