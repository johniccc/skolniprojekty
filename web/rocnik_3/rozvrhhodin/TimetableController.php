<?php
class TimetableController
{
  public function show_timetable()
  {
    $timetable = new TimetableData();
    $timetableLessons = $timetable->get_lessons();
    $timetablePlain = $timetable->get_timetable();
    $timetableClean = $this->convert_timetable_array($timetablePlain, $timetableLessons);
    include "TimetableStructure.php";
  }

  public function convert_timetable_array($timetableMap, $lessons) // přidává do pole null, tam kde pro třídu není žádná hodina
  {
    $maxHours = count($lessons);
    foreach ($timetableMap as $key => $value) {
      $nullarray = array_fill(0, $maxHours, null);
      foreach ($value as $lesson) {
        $order = $lesson[0]->get_order();
        $nullarray[$order] = $lesson;
      }
      $timetableMap[$key] = $nullarray;
    }
    return $timetableMap;
  }
}
