* mower: Use moveForward instead move because this way is more explicit that the mower will move forward and if in the future we have a moveBackward, will be more easy to add.

* Check the readCommand, maybe could be in other part, there is some dependency between the service and the infra.

![classes](https://i.imgur.com/Qm7Ihcs.png)
![usecase](https://i.imgur.com/AyeEGSc.png)