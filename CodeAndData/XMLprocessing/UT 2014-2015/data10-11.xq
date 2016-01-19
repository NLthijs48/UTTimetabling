<data>
{for $teacher in doc("data10.xml")//teacher
let $group := $teacher/name
group by $group
return <teacher><name>{$group}</name><wastedminutes>{sum($teacher/collegeminutes) - sum($teacher/contactminutes)}</wastedminutes></teacher>}
</data>