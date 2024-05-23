```mermaid
classDiagram
  class Teacher {
    id: Long
    Name: String
    Surname: String
    subjects: List<String>
    age: int
    premium: boolean
    receivedReviews: list<Review>
    location: Location
    availableTimeSlot: Time
    teacherSchedule: LessonSchedule
    resume: File
    email: String
    cellNum: String
  }
  
  class Student {
    id: Long
    Name: String
    Surname: String
    bonusPoints: int
    completedReviews: List<Review>
    studentAgenda: LessonsAgenda
    email: String
    cellNum: String
  }
  
  class Location {
    city: String
    street: String
    houseNumber: int
    teacher: Teacher
  }
  
  class Lesson {
    price: int
    subject: String
    teacher: Teacher
    date: Date
    student: Student
    studentSchedule: LessonSchedule
    teacherSchedule: LessonSchedule
  }
  
  class Review {
    id: Long
    stars: int
    title: String
    body: String
    studentId: Long
    teacherId: Long
    creationTime: Instant
  }
  
  class LessonsAgenda {
    id: Long
    lessons: ArrayList<Lesson>
  }
  
  class Payment {
    amount: double
  }
  
  class LessonPayment {
    lesson: Lesson
    teacher: Teacher
    student: Student
  }
  
  class PremiumPayment {
    teacher: Teacher
  }

  Teacher "0..*" -- "0..*" Student
  Teacher "1..1" -- "1..1" Location
  Student "1..1" -- "0..*" Lesson
  Student "1..1" -- "0..*" Review
  Teacher "1..1" -- "0..*" Review
  LessonsAgenda "1..1" -- "0..*" Lesson
  Teacher "1..1" -- "1..1" LessonsAgenda
  Student "1..1" -- "1..1" LessonsAgenda
  Student "1..1" -- "0..*" LessonPayment
  Lesson "1..1" -- "1..1" LessonPayment
  Teacher "1..1" -- "1..1" LessonPayment
  Payment <|-- LessonPayment
  Payment <|-- PremiumPayment
  PremiumPayment "1..N" -- "1..1" Teacher
