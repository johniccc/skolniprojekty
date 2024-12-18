<?php
class Lesson
{
  private $order;
  private $subject;
  private $teacher;
  private $classroom;
  private $group;

  public function __construct($order, $subject, $teacher, $classroom, $group)
  {
    $this->order = $order;
    $this->subject = $subject;
    $this->teacher = $teacher;
    $this->classroom = $classroom;
    $this->group = $group;
  }

  public function get_order()
  {
    return $this->order;
  }
  public function get_subject()
  {
    return $this->subject;
  }
  public function get_teacher()
  {
    return $this->teacher;
  }
  public function get_classroom()
  {
    return $this->classroom;
  }
  public function get_group()
  {
    return $this->group;
  }
}
