pw=($(<pw.dat))
database=($(<database.dat))

now=`date +%Y-%m-%d\ 0:00`

# Check open segment
open_segment=`mysql -u $database -p$pw -h fergonco.es $database -e "select count(*) as '' from work_segments where developer_name='$1' and ending_time is null"`
if [ $open_segment != 0 ]
	then echo Open Segment!!!!
fi

# Show worked hours
worked_hours=`mysql -u $database -p$pw -h fergonco.es $database -e "select sum(TIME_TO_SEC(TIMEDIFF(ending_time, starting_time))/3600) as '' from work_segments where developer_name='$1' and starting_time > '$now' group by developer_name"`
echo Worked hours: $worked_hours

# Ongoing projects
echo Ongoing work:
mysql -u $database -p$pw -h fergonco.es $database -e "select p.name, t.description, t.hours, (select sum(time_to_sec(timediff(s.ending_time, s.starting_time)))/3600 from work_segments s where t.id=s.task_id) as developed_hours from projects p, tasks t where t.project_name=p.name and 0 < (select count(*) from tasks t, work_segments s where t.project_name=p.name and t.id=s.task_id and s.developer_name='$1') order by p.name desc"

#The same as previous one but with totals
mysql -u $database -p$pw -h fergonco.es $database -e "select p.name, sum(t.hours), sum((select sum(time_to_sec(timediff(s.ending_time, s.starting_time)))/3600 from work_segments s where t.id=s.task_id)) as developed_hours from projects p, tasks t where t.project_name=p.name and 0 < (select count(*) from tasks t, work_segments s where t.project_name=p.name and t.id=s.task_id and s.developer_name='$1') group by p.name order by p.name desc"


