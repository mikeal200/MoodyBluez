# Moody Bluez

## Introduction

Moody Bluez allows users to record their overall mood daily. At the end of the day the user can choose thier overall mood for the day and then write a description as to what they did during the day and what they feel may have caused their overall mood. The users dashboard will contain metrics such as what weekday they tend to be the most happy. Moody Bluez is for someone who is looking to monitor their habits and develop an understanding of what causes them to feel certain ways. Users can interact with Moody Bluez using a set of RESTful service endpoints.

## Storyboard

![storyboard](ReadmeAssets/storyboard.png)

## Functional Requirements

### Requirement 100: Create Mood and mood description recording

#### Scenario

As a user I want the application be able to record my mood and my mood description.

#### Dependencies

User mood data and description data are available and accessible.

#### Assumptions

User is access to the mood recording and description during the day.

### Example
1.1

**Given** User mood recording data is available  
**Given** User mood description recording data is available


**When** I fill out the date, mood, and description, and cick submit;

**Then**: I should be able to create a mood entry on the calendar.

1.2

**Given** User mood recording data is available  
**Given** User mood description recording data is available

**When** I fill out the date with "aduhuibnudah:jiuoda", mood, and description, and cick submit

**Then**: I should receive an error of unknown date data.


### Requirement 101: Change Mood and mood description recording

#### Scenario

As a user I want the application be able to change the recording of my mood and my mood description during the day.

#### Dependencies

User mood data and description data are available and accessible.

#### Assumptions

User accesses to the mood recording and description during the day.

### Example
1.1

**Given** User mood recording data is available  
**Given** User mood description recording data is available

**When** I fill out the date, mood, and description, and cick submit;

**Then**: I should be able to change the mood and mood description of the concrete date on the calendar.

1.2

**Given** User mood recording data is available  
**Given** User mood description recording data is available

**When** I fill out the date with "aduhuibnudah:jiuoda", mood, and description, and cick submit

**Then**: I should receive an error with unknown date data.

### Requirement 102: View mood tendency

#### Scenario

As a user I want to see my mood tendency after a period of time of recording. So I can see the status of my mental health.

#### Dependencies

User mood data is available and accessible.

#### Assumptions

User accesses to the mood recording and mood tendency.

### Example
1.1

**Given** User mood recording data is available 

**When** I get into metrics page

**Then**: I should be able to see the mood tendency.

1.2

**Given** User mood recording data is available  
**Given** There is no mood recording data for mood "Anger"

**When** I get into metrics page, and click on "Anger" mood.

**Then**: I should receive no data for the "Anger" mood tendency.

## Class Diagram

![classdiagram](ReadmeAssets/classdiagram.JPG)


### Class Diagram Description

The day class will handle the date, string, and description. The day class will have methods to set and get the date and mood. 

The mood class will deal with the mood and description and will connect to the Day class. 

The entry class will deal with all the details of each entry such as the entry, description, and day. 

## JSON Schema

This is what we plan to export to another app.

> {
>  "type" : "object",
>  "properties" : {
>    "description" : {
>      "type" : "string"
>    },
>    "date" : {
>      "type" : "string"
>    },
>    "mood" : {
>      "type" : "string"
>    }
>  }
> }

## Team Members and Roles 

UI Specialist: Vismaya Manchaiah and Erich Wagner

Business Logic/Persistance: Puran Kansakar and Tianzuo Huang

DevOps/Product Owner/Scrum Master/Github Admin: Michael Williams

## Milestones

[Milestones](https://github.com/mikeal200/MoodyBluez/milestones)

## Projects

[Projects](https://github.com/mikeal200/MoodyBluez/projects)

## Standup

[We meet 8:00 PM Eastern on Discord](https://discord.gg/N6qNra5f36)
