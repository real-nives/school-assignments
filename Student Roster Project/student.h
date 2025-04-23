#pragma once
#include <array>
#include <string>
#include "degree.h"

// The Student class represents a student in the university.
// It contains all of the necessary attributes and operations that are related to a student.

class Student {

private:

	std::string studentID;
	std::string firstName;
    std::string lastName;
    std::string emailAddress;
    int age;
    std::array<int, 3> daysToComplete;
    DegreeProgram degreeProgram;

public:

    // Constructor that initiates a Student object with all of the necessary details.
    Student(std::string studentID, std::string firstName, std::string lastName, std::string emailAddress, int age,
        int daysInCourse1, int daysInCourse2, int daysInCourse3, DegreeProgram degreeProgram);

    // Accessor methods for retrieving the values of a student.
    std::string getStudentID() const;
    std::string getFirstName() const;
    std::string getLastName() const;
    std::string getEmailAddress() const;
    int getAge() const;
    std::array<int, 3> getDaysToComplete() const;
    DegreeProgram getDegreeProgram() const;

    // Mutator methods for setting the values of a student.
    void setStudentID(std::string studentID);
    void setFirstName(std::string firstName);
    void setLastName(std::string lastName);
    void setEmailAddress(std::string emailAddress);
    void setAge(int age);
    void setDaysToComplete(int daysInCourse1, int daysInCourse2, int daysInCourse3);
    void setDegreeProgram(DegreeProgram degreeProgram);

    // Method that prints student details.
    void print() const;
};