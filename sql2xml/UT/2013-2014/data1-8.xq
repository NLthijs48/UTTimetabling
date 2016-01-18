<data>
  {for $room in distinct-values(
    for $bulkroom in doc("data1.xml")//room
    return tokenize(
      $bulkroom, ';'
    )
   )
   return <room><name>{$room}</name><minutesused>
     {sum(for $activity in doc("data1.xml")//activity
     return sum(
       for $roomOther in distinct-values(tokenize(
         $activity/room, ';'
       ))
       where $room = $roomOther
       return fn:minutes-from-duration(xs:time($activity/endtime)-xs:time($activity/starttime))+60*fn:hours-from-duration(xs:time($activity/endtime)-xs:time($activity/starttime))
     ))}
     </minutesused></room>
  }
</data>