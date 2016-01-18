<data>
{for $day in doc("data4.xml")//day
return <day>{
  $day/dategiven}{$day/daygiven}{
  for $teacher in distinct-values($day//teacher)
  let $max := max(
    for $activity in $day/activity
    where $teacher = $activity/teacher
    return $activity/endtime
  )
  let $min := min(
    for $activity in $day/activity
    where $teacher = $activity/teacher
    return $activity/starttime
  )
  return <teacher><name>{$teacher}</name><contactminutes>{sum(
    for $activity in $day/activity
    where $teacher = $activity/teacher
    return $activity/duration
)}</contactminutes>
  <minstart>{$min}</minstart>
  <maxend>{$max}</maxend>
  <collegeminutes>{$max - $min}</collegeminutes>
</teacher>
}
</day>}
</data>