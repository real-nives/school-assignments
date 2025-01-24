#include "roster.h"
#include <iostream>
#include <string>
#include <sstream>

// Initializes all pointers to in classRosterArray to nullptr.
Roster::Roster() {
    for (int i = 0; i < 5; i++) {
        classRosterArray[i] = nullptr;
    }
}

// Frees the memory allocated for each Student object.
Roster::~Roster() {
    for (int i = 0; i < 5; i++) {
        delete classRosterArray[i];
        classRosterArray[i] = nullptr;
    }
}

// Adds new student to the first available slot in classRosterArray.
void Roster::add(std::string studentID, std::string firstName, std::string lastName, std::string emailAddress, int age, int daysInCourse1, int daysInCourse2, int daysInCourse3, DegreeProgram degreeProgram) {
    int i;
    for (i = 0; i < 5; i++) {
        if (classRosterArray[i] == nullptr) {
            classRosterArray[i] = new Student(studentID, firstName, lastName, emailAddress, age, daysInCourse1, daysInCourse2, daysInCourse3, degreeProgram);
            break;
        }
    }
}

// Removes a student from the roster by their StudentID.
void Roster::remove(std::string studentID) {
    bool found = false;
    for (int i = 0; i < 5; i++) {
        if (classRosterArray[i] != nullptr && classRosterArray[i]->getStudentID() == studentID) {
            delete classRosterArray[i];
            classRosterArray[i] = nullptr;
            found = true;
            std::cout << "Student with ID " << studentID << " removed." << std::endl << std::endl;
            break;
        }
    }
    if (!found) {
        std::cout << "Student with ID " << studentID << " not found." << std::endl;
    }
}

// Prints all students in the roster.
void Roster::printAll() const {
    for (int i = 0; i < 5; i++) {
        if (classRosterArray[i] != nullptr) {
            classRosterArray[i]->print();
        }
    }
}

// Prints the average number of days in courses for a given StudentID.
void Roster::printAverageDaysInCourse(std::string studentID) const {
    for (int i = 0; i < 5; i++) {
        if (classRosterArray[i] != nullptr && classRosterArray[i]->getStudentID() == studentID) {
            std::array<int, 3> days = classRosterArray[i]->getDaysToComplete();
            double average = (days[0] + days[1] + days[2]) / 3.0;
            std::cout << "Student ID: " << studentID << ", average days in course: " << average << std::endl;
            return;
        }
    }
    std::cout << "Student with ID " << studentID << " not found." << std::endl;
}

// Prints all invalid email addresses in the roster.
void Roster::printInvalidEmails() const {
    for (int i = 0; i < 5; i++) {
        if (classRosterArray[i] != nullptr) {
            std::string email = classRosterArray[i]->getEmailAddress();
            if (email.find('@') == std::string::npos || email.find('.') == std::string::npos || email.find(' ') != std::string::npos) {
                std::cout << "Invalid Email: " << email << std::endl;
            }
        }
    }
}

// Prints students enrolled in a certain degree program.
void Roster::printByDegreeProgram(DegreeProgram degreeProgram) const {
    for (int i = 0; i < 5; i++) {
        if (classRosterArray[i] != nullptr && classRosterArray[i]->getDegreeProgram() == degreeProgram) {
            classRosterArray[i]->print();
        }
    }
}

// Checks if student exists at a given index.
bool Roster::studentExistsAt(int index) const {
    if (index >= 0 && index < 5) {
        return classRosterArray[index] != nullptr;
    }
    return false;
}

// Retrieves the StudentID at a given index.
std::string Roster::getStudentIDAt(int index) const {
    if (index >= 0 && index < 5 && classRosterArray[index] != nullptr) {
        return classRosterArray[index]->getStudentID();
    }
    return "error";
}