# Moody Bluez

## Introduction

Moody Bluez allows users to record their overall mood daily. At the end of the day the user can choose thier overall mood for the day and then write a description as to what they did during the day and what they feel may have caused their overall mood. The users dashboard will contain metrics such as what weekday they tend to be the most happy. Moody Bluez is for someone who is looking to monitor their habits and develop an understanding of what causes them to feel certain ways. Users can interact with Moody Bluez using a set of RESTful service endpoints.

## Storyboard

![storyboard](ReadmeAssets/storyboard.png)

## Requirements

1. As a user I want the application to be able to record my mood and my mood description.

### Example

**Given**: User mood recording data is available.

**When**: I fill out the date, mood, and description, and cick submit.

**Then**: An entry will be saved on the calendar.

### Example

**Given**: User mood recording data is available.

**When**: I fill out the date with "aduhuibnudah:jiuoda" and cick submit.

**Then**: I should receive an error of unknown date data.

2. As a user I want to be able to modify my entry of the day prior.

### Example

**Given**: User mood and description data is available.  

**When**: I modify the mood and description.

**Then**: The modified entry will persist on the calendar.

### Example

**Given** User mood and description data is available 

**When** I fill out the mood or description with "aduhuibnudah:jiuoda"

**Then** I should receive an error with unknown input.

3. As a user I want to see my mood tendency after a period of time of recording. So I can see the status of my mental health.

### Example

**Given** User is on metrics page. 

**When** I select "Sad" mood.

**Then** MoodyBluez will return the sad mood object, that will display the amount of times that mood has an entry on each day of the week.

### Example

**Given** There is no mood recording data for mood "Anger"

**When** I get into metrics page, and click on "Anger" mood.

**Then** I should receive no data for the "Anger" mood tendency.

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
