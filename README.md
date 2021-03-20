# Beacon
###### tags: Codepath
:::info
Allows users to store precise points on the map, can associate a location with a title and description. 
:::

## Table of Contents
1. [App Overview](#App_Overview)
2. [Product Spec](#Product-Spec)
3. [Wireframes](#Wireframes)
## App Overview
### App Description
> This app should be able to take precise location data, and display the point on Google Maps using the Maps API. These 'pins' should be associated with a title and a description. The app will also include a widget for quickly creating a pin from the home screen, and have the ability to share the pin using the Android share tab. The app could also have social functionality if private and public pins are added, allowing users to create points anywhere on the map for things like business recommendations. Custom radii for pins could be implemented for displaying the location of social events on the map. An example of a use for a private pin goes as follows: After parking your car, you tap the widget on the home screen to create a pin so you can find your car in the large parking lot, and when returning to your car, instead of trying to hunt around for the car, you open the app and a precise point for the car's location is displayed on the map, with your current location also being visible.

### App Evalutation
- **Category:** Social Networking/event creation(optional)
- **Mobile:** This app will be created primarily for mobile, but could also be as viable on a computer, but Functionalities of the app could potentially have more features on the mobile version of the app.
- **Story:** Anaylzes users pin points which connects them to other users with the same pin point so that they can chat and make events.
- **Market:** 13+ is recommended age for the app because this app is more for social networking/meeting new people, and having fun at a pin point location, so it would be better for the user to be atleast 13+ to create social events. 
- **Habit:** There is no restriction to how often or unoften the app should be used, so it is totally dependant on the individual user and how many different groups of people they want to meet. 
- **Scope:** First we would start pairing people by local location pin points they like, then this would perhaps evolve into more further/world wide location pin points where users could potentially make friends from different countries and socialize/plan a vacation with a big group to cure their boredum. 

## Product Spec
### Required Stories

- [ ] Allow users to create and edit a pin with title, description, and location.
- [ ] Have log-in and log-out activity screens.
- [ ] Have activity with a list of pins by the user, and a tab with recently used pins. Tapping a pin displays the information about the pin and allows users to see its location.
- [ ] Implement a widget that allows for creation for a pin from the home screen.


### Optional Stories

- [ ] Have public and private pin attributes, private pins should only be displayed to their creator (could add sharable pins aswell).
- [ ] Allow users to create a group chat around the pin to create events.
- [ ] Allow users to set multiple status options (online, away, invisible)
- [ ] Allow users to chat in a set amount of radius from a pin's location.

### Screen Archetypes

* Login Screen
    * User can login
    * User can register a new account 
* Pin Screen
    * User can view all pins they created in a list, tapping on a pin displays information about the pin.
* Map Screen
    * A map that has pins for users to see and create (optional) chats in.
* Messages Screen (optional)
    * User can view recent messages from proximity chat, or recently used group chats from pins.
    * User can send and recieve messages and their notifications to users in a proximity or in a group chat.

### Navigation
* Tab Navigation (Tab to Screen)
    * Pin Screen
    * Map Screen
    * Messages Screen (optional)
* Flow Navigation (Screen to Screen)
    * Forced Log-in -> Account creation if no log in is available.
    * Add Pin Button -> Allows creation of new pin from map screen.
    * Pin Message (optional) -> Moves from map or pin list screen to a pin's message screen if the user wants to chat.

### Wireframes
<img src="https://github.com/BeaconApplication/Beacon/blob/main/wireframes.png" width=1000><br>
