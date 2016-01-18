<data>
{
  <first>
    {for $course in 
      for $activity in doc("courses1.xml")//activity
      let $group := $activity/code
      group by $group
      where $group != "NONE"
      return <course>
        <name>{($activity/courseActivity)[1]/text()}</name>
        <code>{$group}</code>
        <plannedminutes>{sum(
          for $courseactivity in $activity
          where string-length($courseactivity/endtime) = 8 and string-length($courseactivity/starttime) = 8
            and $activity/dateGiven > xs:date("2013-08-20") and $activity/dateGiven < xs:date("2013-11-09")
          return fn:minutes-from-duration(xs:time($courseactivity/endtime)-xs:time($courseactivity/starttime))
            +60*fn:hours-from-duration(xs:time($courseactivity/endtime)-xs:time($courseactivity/starttime))
        )}</plannedminutes>
      </course>
    where $course/plannedminutes > 0
    return $course}
  </first>
}
{
  <second>
    {for $course in 
      for $activity in doc("courses1.xml")//activity
      let $group := $activity/code
      group by $group
      where $group != "NONE"
      return <course>
        <name>{($activity/courseActivity)[1]/text()}</name>
        <code>{$group}</code>
        <plannedminutes>{sum(
          for $courseactivity in $activity
          where string-length($courseactivity/endtime) = 8 and string-length($courseactivity/starttime) = 8
            and $activity/dateGiven >= xs:date("2013-11-09") and $activity/dateGiven < xs:date("2014-01-30")
          return fn:minutes-from-duration(xs:time($courseactivity/endtime)-xs:time($courseactivity/starttime))
            +60*fn:hours-from-duration(xs:time($courseactivity/endtime)-xs:time($courseactivity/starttime))
        )}</plannedminutes>
      </course>
    where $course/plannedminutes > 0
    return $course}
  </second>
}
{
  <third>
    {for $course in 
      for $activity in doc("courses1.xml")//activity
      let $group := $activity/code
      group by $group
      where $group != "NONE"
      return <course>
        <name>{($activity/courseActivity)[1]/text()}</name>
        <code>{$group}</code>
        <plannedminutes>{sum(
          for $courseactivity in $activity
          where string-length($courseactivity/endtime) = 8 and string-length($courseactivity/starttime) = 8
            and $activity/dateGiven > xs:date("2014-01-30") and $activity/dateGiven < xs:date("2014-04-16")
          return fn:minutes-from-duration(xs:time($courseactivity/endtime)-xs:time($courseactivity/starttime))
            +60*fn:hours-from-duration(xs:time($courseactivity/endtime)-xs:time($courseactivity/starttime))
        )}</plannedminutes>
      </course>
    where $course/plannedminutes > 0
    return $course}
  </third>
}
{
  <fourth>
    {for $course in 
      for $activity in doc("courses1.xml")//activity
      let $group := $activity/code
      group by $group
      where $group != "NONE"
      return <course>
        <name>{($activity/courseActivity)[1]/text()}</name>
        <code>{$group}</code>
        <plannedminutes>{sum(
          for $courseactivity in $activity
          where string-length($courseactivity/endtime) = 8 and string-length($courseactivity/starttime) = 8
            and $activity/dateGiven > xs:date("2014-04-16") and $activity/dateGiven < xs:date("2014-08-01")
          return fn:minutes-from-duration(xs:time($courseactivity/endtime)-xs:time($courseactivity/starttime))
            +60*fn:hours-from-duration(xs:time($courseactivity/endtime)-xs:time($courseactivity/starttime))
        )}</plannedminutes>
      </course>
    where $course/plannedminutes > 0
    return $course}
  </fourth>
}
</data>