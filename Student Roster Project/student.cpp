#include "student.h"
#include <iostream>
#include <iomanip>

// Constructor implementation for the Student class.
Student::Student(std::string studentID, std::string firstName, std::string lastName, std::string emailAddress, int age,
    int daysInCourse1, int daysInCourse2, int daysInCourse3, DegreeProgram degreeProgram)
    : studentID(studentID), firstName(firstName), lastName(lastName), emailAddress(emailAddress), age(age), degreeProgram(degreeProgram) {
    daysToComplete = { daysInCourse1, daysInCourse2, daysInCourse3 };
}

// Accessor implementations.
std::string Student::getStudentID() const { return studentID; }
std::string Student::getFirstName() const { return firstName; }
std::string Student::getLastName() const { return lastName; }
std::string Student::getEmailAddress() const { return emailAddress; }
int Student::getAge() const { return age; }
std::array<int, 3> Student::getDaysToComplete() const { return daysToComplete; }
DegreeProgram Student::getDegreeProgram() const { return degreeProgram; }

// Mutator implementations.
void Student::setStudentID(std::string studentID) { this->studentID = studentID; }
void Student::setFirstName(std::string firstName) { this->firstName = firstName; }
void Student::setLastName(std::string lastName) { this->lastName = lastName; }
void Student::setEmailAddress(std::string emailAddress) { this->emailAddress = emailAddress; }
void Student::setAge(int age) { this->age = age; }
void Student::setDaysToComplete(int daysInCourse1, int daysInCourse2, int daysInCourse3) { daysToComplete[0] = daysInCourse1; daysToComplete[1] = daysInCourse2; daysToComplete[2] = daysInCourse3; }
void Student::setDegreeProgram(DegreeProgram degreeProgram) { this->degreeProgram = degreeProgram; }

// Print method implementation that prints student info. (Spacing issue when my info prints - not a huge concern)
void Student::print() const {
    std::cout << studentID << "\t";
    std::cout << "First Name: " << firstName << "\t";
    std::cout << "Last Name: " << lastName << "\t";
    std::cout << "Age: " << age << "\t";
    std::cout << "daysInCourse: {" << daysToComplete[0] << ", " << daysToComplete[1] << ", " << daysToComplete[2] << "} ";
    std::cout << "Degree Program: ";
        switch (degreeProgram) {
            case DegreeProgram::SECURITY: std::cout << "SECURITY"; break;
            case DegreeProgram::NETWORK: std::cout << "NETWORK"; break;
            case DegreeProgram::SOFTWARE: std::cout << "SOFTWARE"; break;
        }
        std::cout << std::endl;
}