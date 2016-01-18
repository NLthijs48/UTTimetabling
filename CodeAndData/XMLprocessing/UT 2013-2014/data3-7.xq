<count>
  {sum(for $day in doc("data3.xml")//day
  return sum(
    for $set in $day//set
    where $set/maxend >= 1110
    return sum(
      for $nextDay in doc("data3.xml")//day
      where (xs:date($day/dategiven) + xs:dayTimeDuration("P1D")) = xs:date($nextDay/dategiven)
      return sum(
        for $otherset in $nextDay//set
        where $set/name = $otherset/name and $otherset/minstart <= 630
        return 1
      )
    )
  ))}
</count>