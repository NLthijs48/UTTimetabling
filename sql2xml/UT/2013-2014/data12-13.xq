<data>
{for $day in doc("data12.xml")//day
return <day>{
  $day/dategiven}{$day/daygiven}{
  for $name in distinct-values($day//name)
  let $max := max(
    for $set in $day/set
    where $name = $set/name
    return $set/endtime
  )
  let $min := min(
    for $set in $day/set
    where $name = $set/name
    return $set/starttime
  )
  return <set><name>{$name}</name><contactminutes>{min(($max - $min, sum(
    for $set in $day/set
    where $name = $set/name
    return $set/duration
)))}</contactminutes>
  <minstart>{$min}</minstart>
  <maxend>{$max}</maxend>
  <collegeminutes>{$max - $min}</collegeminutes>
</set>
}
</day>}
</data>