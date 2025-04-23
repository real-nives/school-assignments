#pragma once
#include "student.h"
#include <string>

// Roster class manages a collection of Student objects.
// It has operations to add, remove, and display info about a student.

class Roster {
private:
	Student* classRosterArray[5];

public:

    // Constructor for Roster class.
	Roster();

    // Destructor for Roster class.
	~Roster();

    // Add new student to the roster.
    void add(std::string studentID, std::string firstName, std::string lastName, std::string emailAddress, int age,
        int daysInCourse1, int daysInCourse2, int daysInCourse3, DegreeProgram degreeProgram);

    // Removes a student from the roster by their StudentID.
    void remove(std::string studentID);

    // Prints all students in the roster.
    void printAll() const;

    // Print average number of days in course for a given student.
    void printAverageDaysInCourse(std::string studentID) const;

    // Prints invalid emails.
    void printInvalidEmails() const;

    // Prints students by degree program.
    void printByDegreeProgram(DegreeProgram degreeProgram) const;

    // Helper method to check if student exists at a given index.
    bool studentExistsAt(int index) const;

    // Helper method to get the StudentID at a given index.
    std::string getStudentIDAt(int index) const;
};