<!DOCTYPE html>
<html lang="cs">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Rozvrh hodin</title>
  <link rel="stylesheet" href="style.css">
  <link type="image/png" sizes="16x16" rel="icon" href="img/favicon/icons8-calendar-windows-11-color-16.png">
  <link type="image/png" sizes="32x32" rel="icon" href="img/favicon/icons8-calendar-windows-11-color-32.png">
  <link type="image/png" sizes="96x96" rel="icon" href="img/favicon/icons8-calendar-windows-11-color-96.png">
</head>

<body>
  <section class="table">
    <div class="header">
      <div class="cell corner"></div>
      <?php
      foreach ($timetableLessons as $key => $value) {
      ?>
        <div class="cell lesson-number">
          <div class="lesson">
            <p class="lesson-order"><?= $key ?></p>
            <p class="lesson-time"><?= $value ?></p>
          </div>
        </div>
      <?php
      }
      ?>
    </div>
    <div class="lessons-table">
      <?php
      foreach ($timetableClean as $dayName => $day) {
      ?>
        <div class="row">
          <div class="cell lesson-day">
            <div class="lesson">
              <p><?= $dayName ?></p>
            </div>
          </div>
          <?php
          foreach ($day as $lessonGroup) {
            if (!isset($lessonGroup)) {
          ?>
              <div class="cell empty"></div>
            <?php
            } else {
            ?>
              <div class="cell lesson-box">
                <?php
                foreach ($lessonGroup as $lesson) {
                ?>
                  <div class="lesson">
                    <div class="top">
                      <div class="left">
                        <p class="group"><?= $lesson->get_group() ?></p>
                      </div>
                      <div class="right">
                        <p class="classroom"><?= $lesson->get_classroom() ?></p>
                      </div>
                    </div>
                    <div class="center">
                      <p class="lesson-topic"><?= $lesson->get_subject() ?></p>
                    </div>
                    <div class="bottom">
                      <p class="teacher"><?= $lesson->get_teacher() ?></p>
                    </div>
                  </div>
                <?php
                }
                ?>
              </div>
          <?php
            }
          }
          ?>
        </div>
      <?php
      }
      ?>
    </div>
  </section>
</body>

</html>