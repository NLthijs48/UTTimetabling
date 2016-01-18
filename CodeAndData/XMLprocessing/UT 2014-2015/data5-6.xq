<count>
  {sum(for $day in doc("data5.xml")//day
  return sum(
    for $teacher in $day//teacher
    where $teacher/maxend >= 1110
    return sum(
      for $nextDay in doc("data5.xml")//day
      where (xs:date($day/dategiven) + xs:dayTimeDuration("P1D")) = xs:date($nextDay/dategiven)
      return sum(
        for $otherteacher in $nextDay//teacher
        where $teacher/name = $otherteacher/name and $otherteacher/minstart <= 630
        return 1
      )
    )
  ))}
</count>