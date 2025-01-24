#include "roster.h"
#include <iostream>
#include <sstream>
#include <string>

int main() {

    // Array containing the student data - mine added to the bottom.
    const std::string studentData[] =
    {
        "A1,John,Smith,John1989@gm ail.com,20,30,35,40,SECURITY",
        "A2,Suzan,Erickson,Erickson_1990@gmailcom,19,50,30,40,NETWORK",
        "A3,Jack,Napoli,The_lawyer99yahoo.com,19,20,40,33,SOFTWARE",
        "A4,Erin,Black,Erin.black@comcast.net,22,50,58,40,SECURITY",
        "A5,Nicholas,Ives,nives23@wgu.com,24,12,20,25,SOFTWARE"
    };

    // Print my information.
    std::cout << "Course: C867 - Scripting and Programming - Applications\n";
    std::cout << "Language: C++\n";
    std::cout << "WGU Student ID: 011300570\n";
    std::cout << "Name: Nicholas Ives\n\n";

    // Create an instance of the Roster class.
    Roster classRoster;

    // Add each student to classRoster.
    for (int i = 0; i < 5; i++) {
        std::string data = studentData[i];
        std::istringstream iss(data);
        std::string studentID, firstName, lastName, email, ageStr, daysInCourse1Str, daysInCourse2Str, daysInCourse3Str, degreeProgramStr;

        // Parse each field from the string.
        std::getline(iss, studentID, ',');
        std::getline(iss, firstName, ',');
        std::getline(iss, lastName, ',');
        std::getline(iss, email, ',');
        std::getline(iss, ageStr, ',');
        std::getline(iss, daysInCourse1Str, ',');
        std::getline(iss, daysInCourse2Str, ',');
        std::getline(iss, daysInCourse3Str, ',');
        std::getline(iss, degreeProgramStr);

        // Turn strings into integers.
        int age = std::stoi(ageStr);
        int daysInCourse1 = std::stoi(daysInCourse1Str);
        int daysInCourse2 = std::stoi(daysInCourse2Str);
        int daysInCourse3 = std::stoi(daysInCourse3Str);

        // Set the degree program.
        DegreeProgram degreeProgram;
        if (degreeProgramStr == "SECURITY") degreeProgram = DegreeProgram::SECURITY;
        else if (degreeProgramStr == "NETWORK") degreeProgram = DegreeProgram::NETWORK;
        else degreeProgram = DegreeProgram::SOFTWARE;

        // Add the student to the roster.
        classRoster.add(studentID, firstName, lastName, email, age, daysInCourse1, daysInCourse2, daysInCourse3, degreeProgram);
    }

    // Print all students.
    classRoster.printAll();
    std::cout << std::endl;

    // Print invalid emails.
    classRoster.printInvalidEmails();
    std::cout << std::endl;

    // Print average days in course for each student.
    for (int i = 0; i < 5; i++) {
        if (classRoster.studentExistsAt(i)) {
            classRoster.printAverageDaysInCourse(classRoster.getStudentIDAt(i));
        }
    }
    std::cout << std::endl;

    // Print students in the SOFTWARE program.
    classRoster.printByDegreeProgram(DegreeProgram::SOFTWARE);
    std::cout << std::endl;

    // Remove student with ID A3.
    classRoster.remove("A3");
    classRoster.printAll();
    std::cout << std::endl;

    // Try to remove A3 again to demonstrate error message.
    classRoster.remove("A3");
    std::cout << std::endl;

    return 0;
}