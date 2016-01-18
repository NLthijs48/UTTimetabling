
select
	xmlelement(name "day",
		xmlforest(days.dateGiven, days.daygiven),
		(select
			xmlagg(xmlelement(name "activity",
				xmlforest(t.starttime, t.endtime, t.studentsets, t.room, t.coursename, t.teacher)
			))
		from
			ut1314 as t
		where
			days.dateGiven = t.dateGiven
		)
	)
from
	(select distinct
		t.dateGiven, t.daygiven
	from
		ut1314 as t
	order by
		t.dateGiven
	) as days;