# ESP-Game
1. After cloning the app... SignUp Fragment(Main Activity) will be the first screen that is visible to the user.
2. Enter a username and start the game - if the username already exists in the database then his previously unattempted task will be visible else a new user will be created.
3. The second screen is the Home Fragment which contains the list of the pending tasks and a floating action button which pairs with any random player within the database and starts a new tasks with random questions and options.
4. The list of tasks is listed using a recycler view and the click on any item will take you to the Task Fragment where the primary image along with two secondary images is shown.
5. After answering all 5 questions the score is updated on the firebase server which can be checked by using the signup fragment and enterring the username and clicking score button.
