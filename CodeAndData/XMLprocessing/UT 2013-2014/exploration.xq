<exploration>
  <wastedminutes>
    <teacher>
      <count>
      {count(doc("data11.xml")//teacher)}
      </count>
      <wasteminutes>
        {sum(doc("data11.xml")//wastedminutes)}
      </wasteminutes>
      <averagewasteminutes>
       {(sum(doc("data11.xml")//wastedminutes) div count(doc("data11.xml")//teacher))}
      </averagewasteminutes>
      <contactminutes>
        {sum(doc("data5.xml")//contactminutes)}
      </contactminutes>
      <maxwaste>
        {for $teacher in doc("data11.xml")//teacher          
        where $teacher/wastedminutes = max(doc("data11.xml")//wastedminutes)
        return $teacher}
      </maxwaste>
    </teacher>
    <student>
      <count>
      {count(doc("data14.xml")//set)}
      </count>
      <wasteminutes>
        {sum(doc("data14.xml")//wastedminutes)}
      </wasteminutes>
      <averagewasteminutes>
       {(sum(doc("data14.xml")//wastedminutes) div count(doc("data14.xml")//set))}
      </averagewasteminutes>
      <contactminutes>
        {sum(doc("data3.xml")//contactminutes)}
      </contactminutes>
      <maxwaste>
        {for $set in doc("data14.xml")//set
        where $set/wastedminutes = max(doc("data14.xml")//wastedminutes)
        return $set}
      </maxwaste>
     </student>
  </wastedminutes>
  
</exploration>