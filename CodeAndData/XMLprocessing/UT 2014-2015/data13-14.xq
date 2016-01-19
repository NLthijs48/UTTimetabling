<data>
{for $set in doc("data13.xml")//set
let $group := $set/name
group by $group
return <set><name>{$group}</name><wastedminutes>{sum($set/collegeminutes) - sum($set/contactminutes)}</wastedminutes></set>}
</data>