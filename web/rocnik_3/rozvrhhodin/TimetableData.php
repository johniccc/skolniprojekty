<?php
class TimetableData
{
  private $timetableData;
  private $lessons;

  public function __construct()
  {
    $this->timetableData = [
      "Pondělí" => [
        [
          new Lesson("1", "WP", "KAS", "B104", "S1"),
          new Lesson("1", "PG", "FIS", "D363", "S2")
        ],
        [
          new Lesson("2", "WP", "KAS", "B104", "S1"),
          new Lesson("2", "PG", "FIS", "D363", "S2")
        ],
        [
          new Lesson("3", "AJ", "POS", "B401", "S1"),
          new Lesson("3", "PG", "FIS", "D363", "S2")
        ],
        [
          new Lesson("4", "OS", "PAN", "C217", "S1"),
          new Lesson("4", "IN", "DUS", "D055", "S2")
        ],
        [
          new Lesson("5", "OS", "PAN", "C217", "S1"),
          new Lesson("5", "IN", "DUS", "D055", "S2")
        ],
        [
          new Lesson("6", "OS", "PAN", "C217", "S1"),
          new Lesson("6", "AJ", "TRB", "B304", "S2")
        ]
      ],
      "Úterý" => [
        [new Lesson("2", "CJ", "BED", "B203", "")],
        [new Lesson("3", "CJ", "BED", "B203", "")],
        [new Lesson("4", "TV", "SVC", "TV", "")],
        [new Lesson("5", "MA", "BUA", "B125", "")],
        [
          new Lesson("7", "WA", "BAJ", "D263", "S1"),
          new Lesson("7", "WA", "KAS", "D363", "S2")
        ],
        [
          new Lesson("8", "WA", "BAJ", "D263", "S1"),
          new Lesson("8", "WA", "KAS", "D363", "S2")
        ],
        [new Lesson("9", "SV", "BED", "B321", "")],
      ],
      "Středa" => [
        [new Lesson("1", "EN", "BOU", "D252", "")],
        [new Lesson("2", "SV", "BED", "D252", "")],
        [new Lesson("3", "CJ", "BED", "B101", "")],
        [new Lesson("4", "MA", "BUA", "B103", "")],
        [new Lesson("5", "MA", "BUA", "B103", "")],
        [
          new Lesson("6", "AJ", "POS", "B130", "S1"),
          new Lesson("6", "AJ", "TRB", "B303", "S2")
        ]
      ],
      "Čtvrtek" => [
        [
          new Lesson("1", "PX", "PET", "B003", "PX1"),
          new Lesson("1", "PX", "JND", "B009", "PX2"),
          new Lesson("1", "PX", "JEL", "D251", "PX3")
        ],
        [
          new Lesson("2", "PX", "PET", "B003", "PX1"),
          new Lesson("2", "PX", "JND", "B009", "PX2"),
          new Lesson("2", "PX", "JEL", "D251", "PX3")
        ],
        [
          new Lesson("3", "PX", "PET", "B003", "PX1"),
          new Lesson("3", "PX", "JND", "B009", "PX2"),
          new Lesson("3", "PX", "JEL", "D251", "PX3")
        ],
        [new Lesson("4", "MA", "BUA", "B203", "")],
        [new Lesson("5", "TV", "SVC", "TV", "")],
        [
          new Lesson("7", "PS", "SED", "A005", "S1"),
          new Lesson("7", "WP", "KAS", "D263", "S2")
        ],
        [
          new Lesson("8", "PS", "SED", "A005", "S1"),
          new Lesson("8", "WP", "KAS", "D263", "S2")
        ]
      ],
      "Pátek" => [
        [
          new Lesson("1", "PG", "BAJ", "D263", "S1"),
          new Lesson("1", "PS", "NSK", "A005", "S2")
        ],
        [
          new Lesson("2", "PG", "BAJ", "D263", "S1"),
          new Lesson("2", "PS", "NSK", "A005", "S2")
        ],
        [
          new Lesson("3", "PG", "BAJ", "D263", "S1"),
          new Lesson("3", "AJ", "TRB", "B305", "S2")
        ],
        [
          new Lesson("4", "IN", "DUS", "D055", "S1"),
          new Lesson("4", "OS", "SED", "D353", "S2")
        ],
        [
          new Lesson("5", "IN", "DUS", "D055", "S1"),
          new Lesson("5", "OS", "SED", "D353", "S2")
        ],
        [
          new Lesson("6", "AJ", "POS", "D351", "S1"),
          new Lesson("6", "OS", "SED", "D353", "S2")
        ]
      ]
    ];

    $this->lessons = [
      0 => "7:05 - 7:50",
      1 => "8:00 - 8:45",
      2 => "8:50 - 9:35",
      3 => "9:45 - 10:30",
      4 => "10:50 - 11:35",
      5 => "11:45 - 12:30",
      6 => "12:40 - 13:25",
      7 => "13:35 - 14:20",
      8 => "14:25 - 15:10",
      9 => "15:15 - 16:00",
      10 => "16:05 - 16:50",
      11 => "16:55 - 17:40"
    ];
  }

  public function get_timetable()
  {
    return $this->timetableData;
  }

  public function get_lessons()
  {
    return $this->lessons;
  }
}
