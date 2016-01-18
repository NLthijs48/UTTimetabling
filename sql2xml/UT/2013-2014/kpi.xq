<data>
  <students>
    <kpi1>
      <aboveFour>
        {count(for $set in doc("data3.xml")//set
        where $set/contactminutes >= 180
        return 1)}
      </aboveFour>
      <belowFour>
        {count(for $set in doc("data3.xml")//set
        where $set/contactminutes < 180
        return 1)}
      </belowFour>
    </kpi1>
    <kpi2>
      <aboveSix>
        {count(for $set in doc("data3.xml")//set
        where $set/contactminutes > 270
        return 1)}
      </aboveSix>
      <belowSix>
        {count(for $set in doc("data3.xml")//set
        where $set/contactminutes <= 270
        return 1)}
      </belowSix>
    </kpi2>
    <kpi3>
      <aboveTwo>
        {count(for $set in doc("data3.xml")//set
        where ($set/collegeminutes - $set/contactminutes) > 90
        return 1)}
      </aboveTwo>
      <belowTwo>
        {count(for $set in doc("data3.xml")//set
        where ($set/collegeminutes - $set/contactminutes) <= 90
        return 1)}
      </belowTwo>
    </kpi3>
    <kpi4>
      <aboveEleven>
        {count(for $set in doc("data3.xml")//set
        where $set/collegeminutes > 495
        return 1)}
      </aboveEleven>
      <belowEleven>
        {count(for $set in doc("data3.xml")//set
        where $set/collegeminutes <= 495
        return 1)}
      </belowEleven>
    </kpi4>
    <kpi5>
      {doc("data7.xml")}
    </kpi5>
    <kpi6>
      <eveningSets>
        {count(for $day in doc("data3.xml")//day
        return count(
          for $set in $day//set
          where $day/daygiven = 'Friday' and $set/maxend > 1050
          return 1
        ))}
      </eveningSets>
    </kpi6>
  </students>
  <teachers>
    <kpi1>
      <aboveEight>
        {count(for $teacher in doc("data5.xml")//teacher
        where $teacher/contactminutes > 360
        return 1)}
      </aboveEight>
      <belowEight>
        {count(for $teacher in doc("data5.xml")//teacher
        where $teacher/contactminutes <= 360
        return 1)}
      </belowEight>
    </kpi1>
    <kpi2>
      {doc("data6.xml")}
    </kpi2>
  </teachers>
  <rooms>
    <kpi1>
      <aboveSeventy>
        {count(for $room in doc("data8.xml")//room
        where $room/minutesused >= 81000
        return 1)}
      </aboveSeventy>
      <belowSeventy>
        {count(for $room in doc("data8.xml")//room
        where $room/minutesused < 81000
        return 1)}
      </belowSeventy>
    </kpi1>
  </rooms>
</data>