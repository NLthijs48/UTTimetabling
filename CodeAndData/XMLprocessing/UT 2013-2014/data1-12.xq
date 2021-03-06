<data>
{for $day in doc("data1.xml")//day
return <day>{$day//dategiven}{$day//daygiven}{
  for $activity in $day//activity
  return 
    for $studentset in tokenize(
        $activity//studentsets, ';'
      )
    return <set><name>{$studentset}</name>
      <duration>
        {fn:minutes-from-duration(xs:time($activity/endtime)-xs:time($activity/starttime))+60*fn:hours-from-duration(xs:time($activity/endtime)-xs:time($activity/starttime))+15}
      </duration>
      <endtime>{fn:minutes-from-time(xs:time($activity/endtime))+60*fn:hours-from-time(xs:time($activity/endtime))+15}</endtime>
      <starttime>{fn:minutes-from-time(xs:time($activity/starttime))+60*fn:hours-from-time(xs:time($activity/starttime))}</starttime>
    </set>
}</day>}
</data>