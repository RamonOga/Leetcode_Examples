/*There is a table courses with columns: student and class
Please list out all classes which have more than or equal to 5 students.
Note:
The students should not be counted duplicate in each course.
  */

select class from  courses  group by class having count(distinct student) >= 5;