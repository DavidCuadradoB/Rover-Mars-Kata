# Description

The objective of this project is deploy and control a Mower over a Plateau.

## Assumptions
* Only one plateau is created. If the creation of the plateau fails, the program will exit.
* Several Mowers can be deployed in the same plateau. If the deployment of a Mower fails, the system does not fail and allows you to deploy a new Mower.
* When a Mower finish the movement, an obstacle is created in the plateau and a new Mower cannot be deployed o moved on this position.
* A Mower cannot be deployed outside the plateau
* A Mower cannot be deployed over an obstacle in the plateau
* A Mower cannot move outside the plateau. If the Mower try to move outside the plateau, the system does not fail but the Mower does not move.
* A Mower cannot move over an obstacle. If the Mower try to move over an obstacle, the system does not fail but the Mower does not move.


## How to use.
* Run the main class
* The terminal will ask you for the Plateau limits in the format of "X Y"
* The terminal will ask you for the Mower's initial position in the format "X Y Orientation", being orientation: N, S, E, W.
* The terminal will ask you for the Mower's movement commands in the format "MLR", being M: Move forward, L: Rotate Left, R: Rotate Right.
* The terminal will ask you if you want to deploy a new Mower introducing "enter" or write "exit" for exit the program.

## How it works

### Classes diagram:
![classes](https://i.imgur.com/kDqxkOK.png)

In this diagram, almost all the classes are represented. This is divided in 3 main packages: Infrastructure, Application and  Model.

* Infrastructure: The implementation are located here. For example, the Writer and the Reader implementations. In this case all of them are Terminal implementations but a new implementation could be used and should be here too. For example, read from a file instead from a terminal
  * `NewMissionController`: Get the commands from the user to create the plateau and the mower.
* Application: The use-case: NewMissionUseCase and the CreatePlateauService and DeployMowerService:
  * `CreatePlateauService`: Create the Plateau from the User input.
  * `DeployMowerService`: Create and move the Mower over the plateau from the User input. 
* Model: It's where all the business logic are. It has some Interfaces as `NewMissionCommandReader`, `MowerCommandReader` and `PlateauCommandReader` but also, it has the models as `Mower` and `Plateau`.
  * `Mower`: It is the model that represents a Mower. It has the logic to be created and the rotation. Also ask the plateau about the coordinate in case of movement.
  * `Plateau`: It is the model that represents a Plateau. It has the logic to create the Plateau and control the movement over itself. Also does some checks if the coordinates are available.

### New Mission use case
![usecase](https://i.imgur.com/XkpMOqi.png)

This diagram is the representation of the happy path of the application.

1. `main`: inject the dependencies to the rest of the services
2. `NewMissionController`: Get the commands from the user and calls the different services to start the usecase.
3. `CreatePlateauService`: Create a new Plateau based on the commands received by the user.
4. `DeployMowerService`: Deploy and move a new Mower based on the commands received by the user.
5. `Plateau`: Controls all related with the Plateau. The implementation `BorderPlateau` is an implementation with borders, but could have different implementations as a SphericalPlateau where one limit is connected with the start of the opposite side.
6. `Mower`: Controls all related with the Mower. It controls the Mower creations, checking if the initial position is correct. Also move and rotate the Mower.


## Things to take in consideration

1. The main point of discussion that I have with myself has been, how send the information to create the Plateau and the Mower to `CreatePlateauService` and `DeployMowerService`. I found 2 alternatives:
   1. Both services (`CreatePlateauService` and `DeployMowerService`) get the data calling to `PlateauCommandReader` and `MowerCommandReader` and they get the information from the user.
      1. This was my first option. Having `NewMissionUseCase` in the application layer and is `NewMissionUseCase` who control the main loop (create the plateau and deploying as much Mower as the user wants) calling `CreatePlateauService` and `DeployMowerService`.
      2. The problem with this option is that the driving-side of the application: getting information from the user, is requested in the application layer, even if the request per se was in the infrastructure layer, this smells to an incorrect responsibilities.
   2. `NewMissionController` calls both services (`CreatePlateauService` and `DeployMowerService`) passing the user commands in an DTO and create the plateau and deploy the Mower.
      1. `NewMissionController` is now in the infrastructure layer acting like a controller.
      2. This option is similar to what you have in an API. You get the information in the controller and pass it to the service.
      3. The problem is that the loop of the application: (create the plateau and deploying as much Mower as the user wants) is in the infrastructure layer. This could be incorrect depending on if you consider this a business logic or not.
      4. With an API, this loop should be in the system who calls the api, so I decided go with this option.
2. The logic has been separated in such a way that each class has only an objective, complying with the `Single responsibility principle`:
   1. `Main`: Dependency Injection. I'm not a big fan of this. I could prefer the inversion of control. I tried to use Spring for it, but it's complicated to use it without Controllers and I thought that this could be an overkill for this kata.
   2. `NewMissionController`: Acts as a controller, getting the commands from the user and calling the different services to create the plateau and deploy the Mower 
   3. `CreatePlateauService`: Create the Plateau based on the commands get from the `PlateauCommandReader`
   4. `DeployMowerService`: Controls the Mower based on the commands get from the `MowerCommandReader`
   5. `PlateauCommandReader`: Returns the commands for the plateau creation.
   6. `MowerCommandReader`: Returns the commands for the mower control.
   7. `Mower`: Representation of the Mower. It is not agnostic since it has the logic to move itself, rotate itself and create itself.
      1. **IMPORTANT!** this looks like an an `Entity`, but looks like a `Value Object`. This should have an Id and the compilation between two Mower should be with this id, but since this is a kata, I prefer not overcomplicate it. 
   8. `Plateau`: Representation of the Plateau. It is not agnostic since it has the logic to calculate the next available coordinate and control the obstacles.
      1. **IMPORTANT!** this looks like an `Entity`, but looks like a `Value Object`. This should have an Id and the compilation between two Mower should be with this id, but since this is a kata, I prefer not overcomplicate it.
   9. `Coordinates`: This is a `Value Object` that represent a coordinate.
   10. `Orientation`: This is a `Value Object` that represents an Orientation: North, South, East and West.
3. In all classes, they depend on abstractions, not implementations, complying with the `Dependency inversion principle`.
4. The reason because there are 3 different interfaces of the `CommandReaders`: `MowerCommandReader`, `PlateauCommandReader` and `NewMissionCommandReader` is to comply the `Interface segregation principle`.
   1. During the implementation, only one `CommandReader` was created, but then it was too generic and a coupling was created between services and the `CommandReader` implementations. This is why the interfaces was separated in several.
5. The `plateau` is an interface to comply the `Liskov principle` If you need a new type of plateau, just need to create a new implementation avoiding modifying the current implementation.
   1. The `Mower` could have the same logic, having a common interface. This is something that could be discussed.

## Improvements

1. Add a dependency injection system like Spring. It's weird to have this dependency injection from the `Main`
2. This could be easiest using an api system. Allowing to create and control the Plateau and the Mower
3. The `NewMissionController` could have some business logic. Depending on if you consider the flow of create the plateau and deploy the Mower as business logic, this could have business logic. But as I said before, this is a solution to get the user information in the infrastructure layer.
   1. This is similar what you have in an API, but it's more complex because in an api, the request are independents. In this case, the user needs to keep introducing information to create new Mowers.
4. I would like to analyse better if all the models should be `immutables`:
   1. In general: `Entities` should be Mutable and `Value Object` should be Immutable. But in this system, the Mower is created and moved in one usecase, and the Plateau only saved the obstacles. So this is why decided do it immutable. But I would like to analyse this decision with more time and with other point of view.
   2. Since those are immutable and the creation of the `Mower` is "complex", maybe I could add a `Factory` here to create the `Mowers` where check the creation of the `Mower`
   3. This `immutability` makes that the `execute()` method is weird. Probably if `Mower` was mutable it could be easier.
5. I considered the "Deploy of a Mower" as: The "creation" and the "movement" of the Mower. This could be divided in 2 parts, first deploy and second the movement. In an Api it could be clear, but since for this you need to save the Mower in some ddbb system, I prefer not overcomplicate the Kata and consider both actions as one.