INTRODUCTION
I have designed a security alarm system using the skills and knowledge I gained during the term in this
course, it is the result of the experiences I have obtained in the duration of this course. It is also a
project that gave me valuable experience and understanding of the material I have studied in other
courses that I took.

CONTEXT:

What I have designed is a security alarm system, that will be used to alert the user of a trespasser at the
point it was installed in. The reason I have created this project is to not only give the users ease of mind
and security, to help protect them and what they how hold important, but to also protect our
community and nature, that was my goal with this project.

TECHNICAL REQUIREMENTS / SPECIFICATIONS:
The system should be able to do the following: Alert the user when an object passes through the IR
sensor by activating the buzzer, receive the potentiometer values to identify what the user wants to
modify or process, receive the button and LED light signal to indicate a press, compare the user’s input
to the stored password, and execute the three command, which are pausing the system for 10 secs,
turning off the alarm if activated, and shutting down the entire system.

The way I planned for the user to use the potentiometer to select the options, is by dividing the range of
the potentiometer into the number of options the user has. Each division of the potentiometer indicates
an option and rotating it to that area, points to the option and by pressing the button, the program will
modify the selection or process it depending on the option selected. To change between passcode input
screen and the select menu, I used a variable to indicate which screen should the user look at and had it
changed depending on the user’s action.

In this project I used a string ArrayList to store the pointer and change its position depending on the
potentiometer position. I have also used a String array to store inputted passcode and answer to later
compare when necessary.

COMPONENTS LIST:
• What was are the physical items in your system? Write a bulleted list. Provide descriptions
to clarify details.
1. The Arduino Board: Main control unit of the system which will communicate with the java program in
the compute
2. The IR sensor: It is used to sense the object in front of it and sending a signal to board
3. The Buzzer: It will be used to alert the user.
4. The Potentiometer: It will be used to receive the user’s input as a cursor, kind of.
5. The Button: It will be used to receive to receive the user’s input.
6. The LED: It will be used to the indicate button press to the rest of the class in the program.
7. The OLED Display: It will be used as visual of the user’s options and some actions.

PROCEDURE:

• Describe the process that you used in creating your project.
I started this project by making a plan of what the project will be, then created a flowchart to organize
the steps I will be taking in the making of this project. After I have established a flow, I wanted to follow,
I would divide it into smaller parts I need to do. After concluding what are the parts I need to work on, I
would into and understand the components and libraries I need to achieve my goal. By understanding
each part, I can combine the parts in a way that allows them to work together and be able to modify
them when needed.

A lot of stuff changed during the process of making this project. While I went through multiple iterations
to reach this final product, I found the most interesting change is the method I used to receive button
press input. In my initial solution I used an if-else statement in code loop to test if the button was
pressed, but this method allowed delay in receiving input as the program will have to go through all of
the previous which sometimes take longer than a press, and I was not able to integrate event listeners in
the task I was working on. Therefore, I changed the way a press is indicated, I used an eventlistener to
turn on the LED light when the button is pressed, then the main loop will test the LED state and if it is on
it will perform the code block in the statement. The use of the LED to indicate a press allowed the
program to feel fast and responsive.

TEST:
When testing this system, I would divide into parts and start with central components, in this case they
were the menu screen and passcode screen. Since I used a lot of if-else statement I would you usually
print something the statement I want to test to confirm that they are functioning. Another thing I would
to is print out value that are supposed to change to confirm if the program succeeded in modifying
them. It would look like this
On the left you can see potentiometer and to the right is the value used to choose the index of the
passcode that will be modified. This confirms that the point variable changes, I would then look into the
OLED screen and modify the value to verify that the user can view the changes. Using this method I was
able to debug a lot of problem I faced during the process of making this project.

LEARNING OUTCOMES:

CLO 1[Demonstrate the ability to test and debug a given program and reason about its
correctness.]: With a lot of trial and tweaks I was able to test all aspects of my program to confirm that it
is fully functional.

CLO 2[Given a problem specification and a suitable API, build an application that meets the given
requirement]: I mainly used the firmata4j library, which is essential to establishing a connection with
Arduino board and communicate with it. It allows me to read the pin signals sent by the components I
used and convey my instructions to them. I have also use the timer library to create task schedules for a
more accurate loop on reading times.

CLO 3[Use ready-made collections to solve problems involving aggregations of typed data]: I used
ArrayList to store the menu screen pointer indicator and change its position depending on the
potentiometer rotation.

CLO 4[Build an event-driven application that controls sensors and actuators in order to connect events
to physical actions.]: I have design an event driven program using the if-else statements
,addevenlistener method from firmata4j, and the sensors listed on the components list. Both the if-else
statement and the event listener worked together, the event listener turned of the LED when the button
is, the LED signal is carried on to the if-else statements to be used together with the potentiometer to
process commands.

CLO 5[Program common applications from a variety of engineering disciplines using an object-oriented
language and solve them on the compute]:I used visual indicators to debug problems, such as failure to update variable. I have also found a
solution to button presses when I was not able to find a way to integrate event listener into my timed
task. Using the LED light I was able to convey button presses to the rest of the program and improving
the flow of the program.

CONCLUSION
During this project, I learned a lot about java and deepened my understanding of it. I also learned a lot
about the design process of a project, how to approach a problem , how formulate a solution and
improve, and how to look for alternative ways to solve the problem.
