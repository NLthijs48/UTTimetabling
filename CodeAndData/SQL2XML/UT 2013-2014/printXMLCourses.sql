
select
	xmlelement(name "day",
		(select
			xmlagg(xmlelement(name "activity",
				xmlforest(days.date as "dateGiven", days.day as "dayGiven", t.starttime, t.endtime, t.coursename, t.code, t.activity as "courseActivity", t.type, t.size)
			))
		from
			ut1314_courses as t
		where
			days.date = t.date
		)
	)
from
	(select distinct
		t.date, t.day
	from
		ut1314_courses as t
	order by
		t.date
	) as days;